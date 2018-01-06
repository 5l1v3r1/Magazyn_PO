package pl.liebertyesterday.shorti1996.magazyn.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by wojciech.liebert on 06.01.2018.
 */

public class PotrzebnyTowar extends Towar {

    @SerializedName("DoZamowienia")
    @Expose
    private Integer doZamowienia;

    public Integer getDoZamowienia() {
        return doZamowienia;
    }

    public void setDoZamowienia(Integer doZamowienia) {
        this.doZamowienia = doZamowienia;
    }
}
