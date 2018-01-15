package pl.koziel.liebert.magahurtomonitor.Model;

/**
 * Created by wojciech.liebert on 13.01.2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static pl.koziel.liebert.magahurtomonitor.Model.WeAreAgile.KARA_ZA_ZMIANE_REGALU;
import static pl.koziel.liebert.magahurtomonitor.Model.WeAreAgile.KARA_ZA_ZMIANE_RZADKU_W_REGALE;
import static pl.koziel.liebert.magahurtomonitor.Model.WeAreAgile.WYSOKOSC_REGALU;

public class Lokalizacja {

    @SerializedName("IDLokalizacji")
    @Expose
    private Integer iDLokalizacji;
    @SerializedName("IDLokalizacjiTowaru")
    @Expose
    private Integer iDLokalizacjiTowaru;
    @SerializedName("IloscLokal")
    @Expose
    private Integer iloscLokal;
    @SerializedName("Info")
    @Expose
    private Object info;
    @SerializedName("KodLokalizacji")
    @Expose
    private String kodLokalizacji;
    @SerializedName("LokalizacjaID")
    @Expose
    private Integer lokalizacjaID;
    @SerializedName("NrPolki")
    @Expose
    private Integer nrPolki;
    @SerializedName("NrRegal")
    @Expose
    private Integer nrRegal;
    @SerializedName("NrStrefySkladowania")
    @Expose
    private Integer nrStrefySkAdowania;
    @SerializedName("TowarID")
    @Expose
    private Integer towarID;
    @SerializedName("WytrzymaloscPolki")
    @Expose
    private Object wytrzymaloscPolki;

    public Lokalizacja() {}

    public Lokalizacja(int nrRegalu, int nrPolki) {
        this();
        setNrRegal(nrRegalu);
        setNrPolki(nrPolki);
    }

    public Integer getIDLokalizacji() {
        return iDLokalizacji;
    }

    public void setIDLokalizacji(Integer iDLokalizacji) {
        this.iDLokalizacji = iDLokalizacji;
    }

    public Integer getIDLokalizacjiTowaru() {
        return iDLokalizacjiTowaru;
    }

    public void setIDLokalizacjiTowaru(Integer iDLokalizacjiTowaru) {
        this.iDLokalizacjiTowaru = iDLokalizacjiTowaru;
    }

    public Integer getIloscLokal() {
        return iloscLokal;
    }

    public void setIloscLokal(Integer iloscLokal) {
        this.iloscLokal = iloscLokal;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }

    public String getKodLokalizacji() {
        return kodLokalizacji;
    }

    public void setKodLokalizacji(String kodLokalizacji) {
        this.kodLokalizacji = kodLokalizacji;
    }

    public Integer getLokalizacjaID() {
        return lokalizacjaID;
    }

    public void setLokalizacjaID(Integer lokalizacjaID) {
        this.lokalizacjaID = lokalizacjaID;
    }

    public Integer getNrPolki() {
        return nrPolki;
    }

    public void setNrPolki(Integer nrPolki) {
        this.nrPolki = nrPolki;
    }

    public Integer getNrRegal() {
        return nrRegal;
    }

    public void setNrRegal(Integer nrRegal) {
        this.nrRegal = nrRegal;
    }

    public Integer getNrStrefySkAdowania() {
        return nrStrefySkAdowania;
    }

    public void setNrStrefySkAdowania(Integer nrStrefySkAdowania) {
        this.nrStrefySkAdowania = nrStrefySkAdowania;
    }

    public Integer getTowarID() {
        return towarID;
    }

    public void setTowarID(Integer towarID) {
        this.towarID = towarID;
    }

    public Object getWytrzymaloscPolki() {
        return wytrzymaloscPolki;
    }

    public void setWytrzymaloscPolki(Object wytrzymaloscPolki) {
        this.wytrzymaloscPolki = wytrzymaloscPolki;
    }

    public int distanceFrom(Lokalizacja other) {
        int len;
        if (getNrRegal().equals(other.getNrRegal())) {
            len = Math.abs((getNrPolki() / WYSOKOSC_REGALU) * KARA_ZA_ZMIANE_RZADKU_W_REGALE
                    - other.getNrPolki() / WYSOKOSC_REGALU * KARA_ZA_ZMIANE_RZADKU_W_REGALE);
        } else {
            len = Math.abs((getNrPolki() / WYSOKOSC_REGALU) * KARA_ZA_ZMIANE_RZADKU_W_REGALE)
                    + KARA_ZA_ZMIANE_REGALU * (Math.abs(getNrRegal() - other.getNrRegal()))
                    + Math.abs((other.getNrPolki() / WYSOKOSC_REGALU) * KARA_ZA_ZMIANE_RZADKU_W_REGALE);
        }
        return len;
    }

}