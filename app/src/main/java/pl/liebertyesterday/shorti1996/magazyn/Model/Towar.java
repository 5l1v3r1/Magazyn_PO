package pl.liebertyesterday.shorti1996.magazyn.Model;

/**
 * Created by wojciech.liebert on 01.01.2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Towar {

    @SerializedName("CechyTowaru")
    @Expose
    private String cechyTowaru;
    @SerializedName("Dostepne")
    @Expose
    private Integer dostepne;
    @SerializedName("IDTowaru")
    @Expose
    private Integer idTowaru;
    @SerializedName("IloscWMierze")
    @Expose
    private Double iloscWMierze;
    @SerializedName("Max")
    @Expose
    private Integer max;
    @SerializedName("Miara")
    @Expose
    private Integer miara;
    @SerializedName("Min")
    @Expose
    private Integer min;
    @SerializedName("Nazwa")
    @Expose
    private String nazwa;
    @SerializedName("OpisTowaru")
    @Expose
    private String opisTowaru;
    @SerializedName("TypTowaru")
    @Expose
    private String typTowaru;
    @SerializedName("WDepozycie")
    @Expose
    private Integer wDepozycie;
    @SerializedName("WDostawie")
    @Expose
    private Integer wDostawie;
    @SerializedName("Zarezerwowane")
    @Expose
    private Integer zarezerwowane;

    public String getCechyTowaru() {
        return cechyTowaru;
    }

    public void setCechyTowaru(String cechyTowaru) {
        this.cechyTowaru = cechyTowaru;
    }

    public Integer getDostepne() {
        return dostepne;
    }

    public void setDostepne(Integer dostepne) {
        this.dostepne = dostepne;
    }

    public Integer getIDTowaru() {
        return idTowaru;
    }

    public void setIDTowaru(Integer idTowaru) {
        this.idTowaru = idTowaru;
    }

    public Double getIloscWMierze() {
        return iloscWMierze;
    }

    public void setIloscWMierze(Double iloscWMierze) {
        this.iloscWMierze = iloscWMierze;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getMiara() {
        return miara;
    }

    public void setMiara(Integer miara) {
        this.miara = miara;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Object getOpisTowaru() {
        return opisTowaru;
    }

    public void setOpisTowaru(String opisTowaru) {
        this.opisTowaru = opisTowaru;
    }

    public String getTypTowaru() {
        return typTowaru;
    }

    public void setTypTowaru(String typTowaru) {
        this.typTowaru = typTowaru;
    }

    public Integer getWDepozycie() {
        return wDepozycie;
    }

    public void setWDepozycie(Integer wDepozycie) {
        this.wDepozycie = wDepozycie;
    }

    public Integer getWDostawie() {
        return wDostawie;
    }

    public void setWDostawie(Integer wDostawie) {
        this.wDostawie = wDostawie;
    }

    public Integer getZarezerwowane() {
        return zarezerwowane;
    }

    public void setZarezerwowane(Integer zarezerwowane) {
        this.zarezerwowane = zarezerwowane;
    }

}