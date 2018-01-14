package pl.liebertyesterday.shorti1996.magazyn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.liebertyesterday.shorti1996.magazyn.Model.Lokalizacja;
import pl.liebertyesterday.shorti1996.magazyn.Model.TravelPath;

/**
 * Created by wojciech.liebert on 13.01.2018.
 */

public class AnnealingSimulator {

    TravelPath travelPath;

    public AnnealingSimulator(TravelPath travelPath) {
        this.travelPath = travelPath;
    }

    public List<Lokalizacja> simulateAnnealing(double startingTemperature, double coolingRate) {
        double t = startingTemperature;

        ArrayList<Lokalizacja> lokalizacje = new ArrayList<>(travelPath.mLokalizacje);
        TravelPath travel = new TravelPath(lokalizacje);
        double bestDistance = travel.getDistance();
        List<Lokalizacja> bestPath = new ArrayList<>(lokalizacje);
        double lastImprovementTime = 0;
        double lastImprovementDelta = Integer.MAX_VALUE;

        boolean shouldContinue = true;
        while (t > 0.01) {
            travel.swap();
            double currentDistance = travel.getDistance();
            if (currentDistance < bestDistance) {
                lastImprovementTime = t;
                lastImprovementDelta = Math.abs(bestDistance - currentDistance);
                bestDistance = currentDistance;
                bestPath = new ArrayList<>(lokalizacje);
                Collections.copy(bestPath, lokalizacje);
            } else if (Math.exp((bestDistance - currentDistance) / t) < Math.random()) {
                travel.revertSwap();
            }
            t *= coolingRate;
        }

        return bestPath;
    }

    public double simulateAnnealingForDistance(double startingTemperature, double coolingRate) {
        List<Lokalizacja> lokalizacje = simulateAnnealing(startingTemperature, coolingRate);
        return new TravelPath(lokalizacje).getDistance();
    }
}
