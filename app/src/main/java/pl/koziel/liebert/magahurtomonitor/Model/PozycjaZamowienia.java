package pl.koziel.liebert.magahurtomonitor.Model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by wojciech.liebert on 12.01.2018.
 */

public class PozycjaZamowienia implements Comparable<PozycjaZamowienia> {

    @SerializedName("IDPozycjiZamowienia")
    @Expose
    private Integer iDPozycjiZamowienia;
    @SerializedName("IloscZam")
    @Expose
    private Integer iloscZam;
    @SerializedName("Lokalizacja")
    @Expose
    private Lokalizacja lokalizacja;
    @SerializedName("Towar")
    @Expose
    private Towar towar;
    @SerializedName("TowarID")
    @Expose
    private Integer towarID;
    @SerializedName("ZamowienieNrZamowienia")
    @Expose
    private Integer zamowienieNrZamowienia;

    private boolean czySkan;

    public Integer getIDPozycjiZamowienia() {
        return iDPozycjiZamowienia;
    }

    public void setIDPozycjiZamowienia(Integer iDPozycjiZamowienia) {
        this.iDPozycjiZamowienia = iDPozycjiZamowienia;
    }

    public Integer getIloscZam() {
        return iloscZam;
    }

    public void setIloscZam(Integer iloscZam) {
        this.iloscZam = iloscZam;
    }

    public Lokalizacja getLokalizacja() {
        return lokalizacja;
    }

    public void setLokalizacja(Lokalizacja lokalizacja) {
        this.lokalizacja = lokalizacja;
    }

    public Towar getTowar() {
        return towar;
    }

    public void setTowar(Towar towar) {
        this.towar = towar;
    }

    public Integer getTowarID() {
        return towarID;
    }

    public void setTowarID(Integer towarID) {
        this.towarID = towarID;
    }

    public Integer getZamowienieNrZamowienia() {
        return zamowienieNrZamowienia;
    }

    public void setZamowienieNrZamowienia(Integer zamowienieNrZamowienia) {
        this.zamowienieNrZamowienia = zamowienieNrZamowienia;
    }

    public Boolean getCzySkan() {
        return czySkan;
    }

    public void setCzySkan(Boolean czySkan) {
        this.czySkan = czySkan;
    }

    @Override
    public int compareTo(@NonNull PozycjaZamowienia otherZamowienia) {
        return this.getIDPozycjiZamowienia() - otherZamowienia.getIDPozycjiZamowienia();
    }
}