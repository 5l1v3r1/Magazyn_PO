package pl.koziel.liebert.magahurtomonitor.Controller;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pl.koziel.liebert.magahurtomonitor.Model.Lokalizacja;
import pl.koziel.liebert.magahurtomonitor.Model.PozycjaZamowienia;
import pl.koziel.liebert.magahurtomonitor.Model.TravelPath;

/**
 * Created by wojciech.liebert on 14.01.2018.
 */

/**
 * Pozwala asynchronicznie wyznaczyć najktótszą ścieżkę dla kompletowania zamówienia
 */
public class ShortestPathCalculator {
    private OnShortestPathFound callback;

    /**
     * Interfejs, dzięki któremu można zasygnalizować zakończenie obliczeń
     */
    public interface OnShortestPathFound {
        /**
         * Zakończenie obliczeń
         * @param bestPath Wyznaczona optymalna trasa
         */
        void onShortestPathFound(List<Lokalizacja> bestPath);
    }

    public ShortestPathCalculator(OnShortestPathFound callback) {
        this.callback = callback;
    }

    /**
     * Oblicza optymalną ścieżkę
     * @param currentLoc Pozycja początkowa
     * @param pozycjeZamowienia Lista pozycji zamówienia
     */
    public void calculateShortestPath(Lokalizacja currentLoc, List<PozycjaZamowienia> pozycjeZamowienia) {
        List<Lokalizacja> lokalizacje = new LinkedList<>();
        for (PozycjaZamowienia pz:
                pozycjeZamowienia) {
            lokalizacje.add(pz.getLokalizacja());
        }
        addStartAndEndLoc(currentLoc, lokalizacje);
        TravelPath travelPath = new TravelPath(lokalizacje);

        Observable.defer(() -> Observable.just(new AnnealingSimulator(travelPath))
                .map(annealingSimulator ->
                        annealingSimulator.simulateAnnealing(100, 0.9999))
                .subscribeOn(Schedulers.computation())
        ).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((bestPath) -> {
                    removeStartAndEndLoc(bestPath);
                    callback.onShortestPathFound(bestPath);
                });
    }

    /**
     * Dodaje podaną lokalizację na początek ścieżki i wyjście z magazynu na jej koniec.
     * @param currentLoc Lokalizacja startowa
     * @param lokalizacje Istniejąca ścieżka wyrażona uporządkowanymi lokalizacjami
     */
    @VisibleForTesting
    public void addStartAndEndLoc(@NonNull Lokalizacja currentLoc, @NonNull List<Lokalizacja> lokalizacje) {
        // Add start position
        lokalizacje.add(0, currentLoc);
        // Add end position
        lokalizacje.add(new Lokalizacja(8,1));
    }

    /**
     * Usuwa pierwszą i ostatnią lokalizację z listy lokalizacji.
     * @param lokalizacje Istniejąca uporządkowana lista lokalizacji
     */
    private void removeStartAndEndLoc(@NonNull List<Lokalizacja> lokalizacje) {
        lokalizacje.remove(0);
        lokalizacje.remove(lokalizacje.size() - 1);
    }
}
