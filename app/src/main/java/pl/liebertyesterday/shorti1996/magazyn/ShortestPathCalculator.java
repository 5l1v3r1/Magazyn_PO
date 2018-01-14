package pl.liebertyesterday.shorti1996.magazyn;

import android.support.annotation.NonNull;

import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pl.liebertyesterday.shorti1996.magazyn.Model.Lokalizacja;
import pl.liebertyesterday.shorti1996.magazyn.Model.PozycjaZamowienia;
import pl.liebertyesterday.shorti1996.magazyn.Model.TravelPath;

/**
 * Created by wojciech.liebert on 14.01.2018.
 */

public class ShortestPathCalculator {
    private OnShortestPathFound callback;

    public interface OnShortestPathFound {
        void onShortestPathFound(List<Lokalizacja> bestPath);
    }

    public ShortestPathCalculator(OnShortestPathFound callback) {
        this.callback = callback;
    }

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

    private void addStartAndEndLoc(@NonNull Lokalizacja currentLoc, @NonNull List<Lokalizacja> lokalizacje) {
        // Add start position
        lokalizacje.add(0, currentLoc);
        // Add end position
        lokalizacje.add(new Lokalizacja(8,1));
    }

    private void removeStartAndEndLoc(@NonNull List<Lokalizacja> lokalizacje) {
        lokalizacje.remove(0);
        lokalizacje.remove(lokalizacje.size() - 1);
    }
}
