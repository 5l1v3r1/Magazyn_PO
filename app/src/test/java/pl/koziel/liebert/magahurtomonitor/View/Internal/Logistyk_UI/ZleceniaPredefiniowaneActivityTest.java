package pl.koziel.liebert.magahurtomonitor.View.Internal.Logistyk_UI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import pl.koziel.liebert.magahurtomonitor.Model.PotrzebnyTowar;
import pl.koziel.liebert.magahurtomonitor.Model.Zlecenie;

import static org.junit.Assert.assertEquals;

/**
 * Created by wojciech.liebert on 15.01.2018.
 */
@RunWith(Parameterized.class)
public class ZleceniaPredefiniowaneActivityTest {
    private List<PotrzebnyTowar> mPotrzebneTowary;
    private int mDostawcaId;
    private List<Zlecenie> mZleceniaExpected;

    public ZleceniaPredefiniowaneActivityTest(List<PotrzebnyTowar> mPotrzebneTowary,
                                              int mDostawcaId,
                                              List<Zlecenie> zleceniaExpected) {
        this.mPotrzebneTowary = mPotrzebneTowary;
        this.mDostawcaId = mDostawcaId;
        this.mZleceniaExpected = zleceniaExpected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new LinkedList<PotrzebnyTowar>() {{
                    add(new PotrzebnyTowar(1, 10, true, "Towar1", 1));
                }}, 1,
                new LinkedList<Zlecenie>() {{
                    add(new Zlecenie(10, 1));
                }}
                }
        });
    }

    @Test
    public void tworzenieZlecenZPotrzebnychTowarow() {
        ZleceniaPredefiniowaneActivity.PotrzebneTowaryOrazZleceniaHelper towaryZleceniaHelper
                = new ZleceniaPredefiniowaneActivity
                        .PotrzebneTowaryOrazZleceniaHelper(mPotrzebneTowary, mDostawcaId).invoke();
        assertEquals(mZleceniaExpected, towaryZleceniaHelper.getZlecenia());
    }

}