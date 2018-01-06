package pl.liebertyesterday.shorti1996.magazyn.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wojciech.liebert on 01.01.2018.
 */

public class Dostawca {

    @SerializedName("IleZapotrzebowan")
    @Expose
    private Integer ileZapotrzebowan;
    @SerializedName("Nazwa")
    @Expose
    private String nazwa;
    @SerializedName("NrDostawcy")
    @Expose
    private Integer nrDostawcy;
    @SerializedName("Opinia")
    @Expose
    private String opinia;
    @SerializedName("PodatnikNrPodatnika")
    @Expose
    private Integer podatnikNrPodatnika;
    @SerializedName("PotrzebneTowary")
    @Expose
    private List<PotrzebnyTowar> potrzebneTowary = null;

    public Integer getIleZapotrzebowan() {
        return ileZapotrzebowan;
    }

    public void setIleZapotrzebowan(Integer ileZapotrzebowan) {
        this.ileZapotrzebowan = ileZapotrzebowan;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Integer getNrDostawcy() {
        return nrDostawcy;
    }

    public void setNrDostawcy(Integer nrDostawcy) {
        this.nrDostawcy = nrDostawcy;
    }

    public String getOpinia() {
        return opinia;
    }

    public void setOpinia(String opinia) {
        this.opinia = opinia;
    }

    public Integer getPodatnikNrPodatnika() {
        return podatnikNrPodatnika;
    }

    public void setPodatnikNrPodatnika(Integer podatnikNrPodatnika) {
        this.podatnikNrPodatnika = podatnikNrPodatnika;
    }

    public List<PotrzebnyTowar> getPotrzebneTowary() {
        return potrzebneTowary;
    }

    public void setPotrzebneTowary(List<PotrzebnyTowar> potrzebneTowary) {
        this.potrzebneTowary = potrzebneTowary;
    }

}