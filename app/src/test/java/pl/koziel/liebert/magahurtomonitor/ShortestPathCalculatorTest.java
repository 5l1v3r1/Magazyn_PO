package pl.koziel.liebert.magahurtomonitor;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import pl.koziel.liebert.magahurtomonitor.Controller.ShortestPathCalculator;
import pl.koziel.liebert.magahurtomonitor.Model.Lokalizacja;

import static org.junit.Assert.assertEquals;

/**
 * Created by wojciech.liebert on 14.01.2018.
 */

/**
 * This test doesn't work for now
 */
@Ignore
@RunWith(Parameterized.class)
public class ShortestPathCalculatorTest implements ShortestPathCalculator.OnShortestPathFound {

    private final List<Lokalizacja> mLokalizacje;
    private final List<Lokalizacja> mExpected;
    private final Lokalizacja mLoc;

    public ShortestPathCalculatorTest(List<Lokalizacja> lokalizacje,
                                      Lokalizacja currLoc,
                                      List<Lokalizacja> expectedLoc) {
        mLokalizacje = lokalizacje;
        mExpected = expectedLoc;
        mLoc = currLoc;
    }


    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new ArrayList<Lokalizacja>() {{
                    add(new Lokalizacja(1, 1));
                }}},
                {new Lokalizacja(1, 1)},
                {new ArrayList<Lokalizacja>() {{
                    add(new Lokalizacja(1, 1));
                    add(new Lokalizacja(1, 1));
                    add(new Lokalizacja(8, 1));
                }}}
        });
    }

    @Test
    public void test() {
        ArrayList<Lokalizacja> l = new ArrayList<>(mLokalizacje);
        Collections.copy(l, mLokalizacje);
        new ShortestPathCalculator(this).addStartAndEndLoc(mLoc, mLokalizacje);
        assertEquals(l, mExpected);
    }

    @Override
    public void onShortestPathFound(List<Lokalizacja> bestPath) {
        // STUB
    }
}