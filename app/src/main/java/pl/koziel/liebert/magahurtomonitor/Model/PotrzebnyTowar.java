package pl.koziel.liebert.magahurtomonitor.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by wojciech.liebert on 06.01.2018.
 */

/**
 * Zachowuje wszystkie właściwości {@link Towar},
 * ale przechowuje informacje zwiazane z zapotrzebowaniem i zamówieniem.
 */
public class PotrzebnyTowar extends Towar {

    @SerializedName("DoZamowienia")
    @Expose
    private Integer doZamowienia;
    @SerializedName("IDZapotrzebowania")
    @Expose
    private int idZapotrzebowania;
    @SerializedName("IloscBrak")
    private Integer iloscBrak;
    private boolean czyZamowic;

    public PotrzebnyTowar(){}

    public PotrzebnyTowar(int idTowaru, int doZam, boolean czyZam, String nazwa, int idZapotrz) {
        this();
        setIDTowaru(idTowaru);
        setNazwa(nazwa);
        setDoZamowienia(doZam);
        setCzyZamowic(czyZam);
        setIdZapotrzebowania(idZapotrz);
    }

    public Integer getDoZamowienia() {
        return doZamowienia;
    }

    public void setDoZamowienia(Integer doZamowienia) {
        this.doZamowienia = doZamowienia;
    }

    public int getIdZapotrzebowania() {
        return idZapotrzebowania;
    }

    public void setIdZapotrzebowania(int idZapotrzebowania) {
        this.idZapotrzebowania = idZapotrzebowania;
    }

    public boolean isCzyZamowic() {
        return czyZamowic;
    }

    public void setCzyZamowic(boolean czyZamowic) {
        this.czyZamowic = czyZamowic;
    }

    public Integer getIloscBrak() {
        return iloscBrak;
    }

    public void setIloscBrak(Integer iloscBrak) {
        this.iloscBrak = iloscBrak;
    }
}
