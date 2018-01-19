package pl.koziel.liebert.magahurtomonitor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import pl.koziel.liebert.magahurtomonitor.Controller.AnnealingSimulator;
import pl.koziel.liebert.magahurtomonitor.Model.Lokalizacja;
import pl.koziel.liebert.magahurtomonitor.Model.TravelPath;

import static org.junit.Assert.assertEquals;

/**
 * Created by wojciech.liebert on 13.01.2018.
 */
@RunWith(Parameterized.class)
public class TravelPathTest {

    static final double DELTA = 1e-5;

    private final List<Lokalizacja> mLokalizacje;
    private final List<Lokalizacja> mExpectedLokalizacje;

    public TravelPathTest(List<Lokalizacja> lokalizacje, List<Lokalizacja> expected) {
        mLokalizacje = lokalizacje;
        mExpectedLokalizacje = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new LinkedList<Lokalizacja>() {{
                    add(new Lokalizacja(1, 1));
                    add(new Lokalizacja(7, 3));
                    add(new Lokalizacja(5, 3));
                    add(new Lokalizacja(2, 55));
                    add(new Lokalizacja(8, 1));
                }}, new LinkedList<Lokalizacja>() {{
                    add(new Lokalizacja(1, 1));
                    add(new Lokalizacja(2, 55));
                    add(new Lokalizacja(5, 3));
                    add(new Lokalizacja(7, 3));
                    add(new Lokalizacja(8, 1));
                }}},
                {new LinkedList<Lokalizacja>() {{
                    add(new Lokalizacja(1, 1));
                    add(new Lokalizacja(8, 1));
                    add(new Lokalizacja(2, 5));
                    add(new Lokalizacja(3, 5));
                    add(new Lokalizacja(4, 5));
                    add(new Lokalizacja(6, 5));
                    add(new Lokalizacja(3, 25));
                    add(new Lokalizacja(3, 28));
                    add(new Lokalizacja(7, 20));
                    add(new Lokalizacja(8, 3));
                }}, new LinkedList<Lokalizacja>() {{
                    add(new Lokalizacja(1, 1));
                    add(new Lokalizacja(2, 5));
                    add(new Lokalizacja(3, 28));
                    add(new Lokalizacja(3, 25));
                    add(new Lokalizacja(3, 5));
                    add(new Lokalizacja(4, 5));
                    add(new Lokalizacja(6, 5));
                    add(new Lokalizacja(7, 20));
                    add(new Lokalizacja(8, 1));
                    add(new Lokalizacja(8, 3));
                }}}
        });
    }

    @Test
    public void travelPathLengthTest() {
        double distance = new AnnealingSimulator(new TravelPath(mLokalizacje))
                .simulateAnnealingForDistance(100, 0.99999);
        assertEquals(new TravelPath(mExpectedLokalizacje).getDistance(), distance, DELTA);
    }

}
