package pl.koziel.liebert.magahurtomonitor.Model;

/**
 * Created by wojciech.liebert on 07.01.2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Zamowienie {

    @SerializedName("AdresID")
    @Expose
    private Integer adresID;
    @SerializedName("DataRealizacji")
    @Expose
    private Object dataRealizacji;
    @SerializedName("DataZlozenia")
    @Expose
    private String dataZlozenia;
    @SerializedName("KlientNrKlienta")
    @Expose
    private Object klientNrKlienta;
    @SerializedName("NrZamowienia")
    @Expose
    private Integer nrZamowienia;
    @SerializedName("PodatnikNrPodatnika")
    @Expose
    private Integer podatnikNrPodatnika;
    @SerializedName("StatusZamowienia")
    @Expose
    private String statusZamWienia;
    @SerializedName("UwagiDoZamowienia")
    @Expose
    private Object uwagiDoZamowienia;

    public Integer getAdresID() {
        return adresID;
    }

    public void setAdresID(Integer adresID) {
        this.adresID = adresID;
    }

    public Object getDataRealizacji() {
        return dataRealizacji;
    }

    public void setDataRealizacji(Object dataRealizacji) {
        this.dataRealizacji = dataRealizacji;
    }

    public String getDataZlozenia() {
        return dataZlozenia;
    }

    public void setDataZlozenia(String dataZlozenia) {
        this.dataZlozenia = dataZlozenia;
    }

    public Object getKlientNrKlienta() {
        return klientNrKlienta;
    }

    public void setKlientNrKlienta(Object klientNrKlienta) {
        this.klientNrKlienta = klientNrKlienta;
    }

    public Integer getNrZamowienia() {
        return nrZamowienia;
    }

    public void setNrZamowienia(Integer nrZamowienia) {
        this.nrZamowienia = nrZamowienia;
    }

    public Integer getPodatnikNrPodatnika() {
        return podatnikNrPodatnika;
    }

    public void setPodatnikNrPodatnika(Integer podatnikNrPodatnika) {
        this.podatnikNrPodatnika = podatnikNrPodatnika;
    }

    public String getStatusZamWienia() {
        return statusZamWienia;
    }

    public void setStatusZamWienia(String statusZamWienia) {
        this.statusZamWienia = statusZamWienia;
    }

    public Object getUwagiDoZamowienia() {
        return uwagiDoZamowienia;
    }

    public void setUwagiDoZamowienia(Object uwagiDoZamowienia) {
        this.uwagiDoZamowienia = uwagiDoZamowienia;
    }

}
