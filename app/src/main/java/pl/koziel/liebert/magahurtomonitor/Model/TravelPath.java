package pl.koziel.liebert.magahurtomonitor.Model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by wojciech.liebert on 13.01.2018.
 */

/**
 * Uporządkowana ścieżka łącząca lokalizacje ({@link Lokalizacja}).
 */
public class TravelPath {

    public List<Lokalizacja> mLokalizacje = new LinkedList<>();
    private List<Lokalizacja> mPrev = new LinkedList<>();

    public TravelPath(List<Lokalizacja> lokalizacje) {
        mLokalizacje = lokalizacje;
    }

    /**
     * @return Odległość pomiędzy dwiema lokalizacjami
     */
    public int getDistance() {
        int distance = 0;
        for (int i = 0; i < mLokalizacje.size() - 1; i++) {
            distance += mLokalizacje.get(i).distanceFrom(mLokalizacje.get(i + 1));
        }
        return distance;
    }

    /**
     * Zamienia kolejnością dwie wybrane losowo lokalizacje na ścieżce
     */
    public void swap() {
        mPrev = mLokalizacje;
        int a = generateRandomIndex();
        int b = generateRandomIndex();
        Lokalizacja x = mLokalizacje.get(a);
        Lokalizacja y = mLokalizacje.get(b);
        mLokalizacje.set(a, y);
        mLokalizacje.set(b, x);
    }

    /**
     * Cofa ostatnią zamianę dokonaną za pomocą {@link #swap()}
     */
    public void revertSwap() {
        if (mPrev != null) {
            mLokalizacje = mPrev;
        }
    }

    /**
     * Don't return first or last index.
     * We don't want to change start or end position in the generated path.
     * @return
     */
    public int generateRandomIndex() {
        int randomIndex = (int) Math.round(Math.random() * (mLokalizacje.size() - 1));
        if (randomIndex == 0 || randomIndex == mLokalizacje.size() - 1) {
            return generateRandomIndex();
        } else {
            return randomIndex;
        }
    }
}
