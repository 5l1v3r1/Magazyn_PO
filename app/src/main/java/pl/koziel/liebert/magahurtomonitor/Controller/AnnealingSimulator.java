package pl.koziel.liebert.magahurtomonitor.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.koziel.liebert.magahurtomonitor.Model.Lokalizacja;
import pl.koziel.liebert.magahurtomonitor.Model.TravelPath;

/**
 * Created by wojciech.liebert on 13.01.2018.
 */

/**
 * Klasa implementująca algorytm zainspirowany procesem wyrzażania w metalurgii.
 * Rozwiązuje problem komiwojażera-
 * w tym przypadku zebrania wszystkich potrzebnych towarów pokonując jak najkrótszą drogę.
 * Źródło: http://www.baeldung.com/java-simulated-annealing-for-traveling-salesman
 */
public class AnnealingSimulator {

    TravelPath travelPath;

    /**
     *
     * @param travelPath Nieuporządkowana ścieżka zawierająca lokalizacje, które muszą zostać odwiedzone.
     */
    public AnnealingSimulator(TravelPath travelPath) {
        this.travelPath = travelPath;
    }

    /**
     * Przeprowadzenie symulacji mającej na celu wyznaczenie najkrótszej ścieżki
     * @param startingTemperature energia systemu w momencie początkowym
     * @param coolingRate procent spadku temperatury w każdym kroku
     * @return Lista lokalizacji uporządkowana w optymalny sposób
     */
    public List<Lokalizacja> simulateAnnealing(double startingTemperature, double coolingRate) {
        double t = startingTemperature;

        ArrayList<Lokalizacja> lokalizacje = new ArrayList<>(travelPath.mLokalizacje);
        TravelPath travel = new TravelPath(lokalizacje);
        double bestDistance = travel.getDistance();
        List<Lokalizacja> bestPath = new ArrayList<>(lokalizacje);
        double lastImprovementTime = 0;
        double lastImprovementDelta = Integer.MAX_VALUE;

        boolean shouldContinue = true;

        // In each step of simulation randomly swap two localisations in the traveling order.
        // Furthermore, calculate the currentDistance.
        // If the newly calculated currentDistance is lower than bestDistance, save it as the best.
        while (t > 0.01) {
            travel.swap();
            double currentDistance = travel.getDistance();
            if (currentDistance < bestDistance) {
                lastImprovementTime = t;
                lastImprovementDelta = Math.abs(bestDistance - currentDistance);
                bestDistance = currentDistance;
                bestPath = new ArrayList<>(lokalizacje);
                Collections.copy(bestPath, lokalizacje);
            }
            // Check if Boltzmann function of probability distribution is lower than randomly picked
            // value in a range from 0-1.
            // If it is, revert the swap of the cities.
            // If not, keep the new order of the cities, as it can help to avoid the local minima.
            else if (Math.exp((bestDistance - currentDistance) / t) < Math.random()) {
                travel.revertSwap();
            }
            t *= coolingRate;
        }

        return bestPath;
    }

    /**
     * Wywołuje metodę {@link #simulateAnnealing(double, double)}, ale zamiast najkrótszej ścieżki
     * zwraca jej długość
     * @param startingTemperature
     * @param coolingRate
     * @return długość najkrótszej ścieżki
     */
    public double simulateAnnealingForDistance(double startingTemperature, double coolingRate) {
        List<Lokalizacja> lokalizacje = simulateAnnealing(startingTemperature, coolingRate);
        return new TravelPath(lokalizacje).getDistance();
    }
}
