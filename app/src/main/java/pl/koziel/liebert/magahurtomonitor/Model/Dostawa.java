package pl.koziel.liebert.magahurtomonitor.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wojciech.liebert on 01.01.2018.
 */

/**
 * Dostarczenie zleceń zakupu towarów przez jednego dostawcę.
 */
public class Dostawa {

    @SerializedName("DostawcaNrDostawcy")
    @Expose
    private int dostawcaNrDostawcy;

    @SerializedName("PotrzebneTowary")
    @Expose
    private List<PotrzebnyTowar> potrzebneTowary;

    public int getDostawcaNrDostawcy() {
        return dostawcaNrDostawcy;
    }

    public void setDostawcaNrDostawcy(int dostawcaNrDostawcy) {
        this.dostawcaNrDostawcy = dostawcaNrDostawcy;
    }

    public List<PotrzebnyTowar> getPotrzebneTowary() {
        return potrzebneTowary;
    }

    public void setPotrzebneTowary(List<PotrzebnyTowar> potrzebneTowary) {
        this.potrzebneTowary = potrzebneTowary;
    }
}