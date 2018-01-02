package pl.liebertyesterday.shorti1996.magazyn.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by wojciech.liebert on 01.01.2018.
 */

public class Zapotrzebowanie {

    @SerializedName("DataPrzetworzenia")
    @Expose
    private Object dataPrzetworzenia;
    @SerializedName("DataZap")
    @Expose
    private String dataZap;
    @SerializedName("ID")
    @Expose
    private Integer id;
    @SerializedName("IloscBrak")
    @Expose
    private Integer iloscBrak;
    @SerializedName("PracownikID")
    @Expose
    private Integer pracownikID;
    @SerializedName("TowarID")
    @Expose
    private Integer towarID;

    public Object getDataPrzetworzenia() {
        return dataPrzetworzenia;
    }

    public void setDataPrzetworzenia(Object dataPrzetworzenia) {
        this.dataPrzetworzenia = dataPrzetworzenia;
    }

    public String getDataZap() {
        return dataZap;
    }

    public void setDataZap(String dataZap) {
        this.dataZap = dataZap;
    }

    public Integer getID() {
        return id;
    }

    public void setID(Integer iD) {
        this.id = iD;
    }

    public Integer getIloscBrak() {
        return iloscBrak;
    }

    public void setIloscBrak(Integer iloscBrak) {
        this.iloscBrak = iloscBrak;
    }

    public Integer getPracownikID() {
        return pracownikID;
    }

    public void setPracownikID(Integer pracownikID) {
        this.pracownikID = pracownikID;
    }

    public Integer getTowarID() {
        return towarID;
    }

    public void setTowarID(Integer towarID) {
        this.towarID = towarID;
    }

}