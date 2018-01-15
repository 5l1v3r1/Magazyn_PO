package pl.koziel.liebert.magahurtomonitor;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void t() {
        SimpleDateFormat fromString = new SimpleDateFormat("mmm yyyy HH:mm:ss");
        SimpleDateFormat toString = new SimpleDateFormat("yyyy-mm-dd");

        String input = "Jan 2018 00:00:00";
        try {
            fromString.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}