package pl.koziel.liebert.magahurtomonitor.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by wojciech.liebert on 01.01.2018.
 */

public class Zlecenie {

    @SerializedName("IloscZlec")
    @Expose
    private int iloscZlec;
    @SerializedName("DostawaID")
    @Expose
    private int dostawaId;
    @SerializedName("ZapotrzebowanieID")
    @Expose
    private int zapotrzebowanieId;
    @SerializedName("LogistykNrLogistyka")
    @Expose
    private int logistykNrLogistyka;
    @SerializedName("NrDostawcy")
    @Expose
    private int nrDostawcy;

    public Zlecenie(){}

    public Zlecenie(int ilosc, int idZapotrz) {
        this();
        setIloscZlec(ilosc);
        setZapotrzebowanieId(idZapotrz);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Zlecenie) {
            Zlecenie z = ((Zlecenie) obj);
            return this.getDostawaId() == z.getDostawaId() && this.getIloscZlec() == z.getIloscZlec();
        } else {
            return super.equals(obj);
        }
    }

    public int getIloscZlec() {
        return iloscZlec;
    }

    public void setIloscZlec(int iloscZlec) {
        this.iloscZlec = iloscZlec;
    }

    public int getDostawaId() {
        return dostawaId;
    }

    public void setDostawaId(int dostawaId) {
        this.dostawaId = dostawaId;
    }

    public int getZapotrzebowanieId() {
        return zapotrzebowanieId;
    }

    public void setZapotrzebowanieId(int zapotrzebowanieId) {
        this.zapotrzebowanieId = zapotrzebowanieId;
    }

    public int getLogistykNrLogistyka() {
        return logistykNrLogistyka;
    }

    public void setLogistykNrLogistyka(int logistykNrLogistyka) {
        this.logistykNrLogistyka = logistykNrLogistyka;
    }

    public int getNrDostawcy() {
        return nrDostawcy;
    }

    public void setNrDostawcy(int nrDostawcy) {
        this.nrDostawcy = nrDostawcy;
    }
}
