package pl.koziel.liebert.magahurtomonitor.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by wojciech.liebert on 01.01.2018.
 */

public class Zapotrzebowanie {

    @SerializedName("DataZap")
    @Expose
    private String dataZap;
    @SerializedName("IDZapotrzebowania")
    @Expose
    private Integer idZapotrzebowania;
    @SerializedName("IloscBrak")
    @Expose
    private Integer iloscBrak;
    @SerializedName("LogistykNrLogistyka")
    @Expose
    private String logistykNrLogistyka;
    @SerializedName("Towar")
    @Expose
    private Towar towar;
    @SerializedName("TowarID")
    @Expose
    private Integer towarId;

    public String getDataZap() {
        return dataZap;
    }

    public void setDataZap(String dataZap) {
        this.dataZap = dataZap;
    }

    public Integer getIDZapotrzebowania() {
        return idZapotrzebowania;
    }

    public void setIDZapotrzebowania(Integer idZapotrzebowania) {
        this.idZapotrzebowania = idZapotrzebowania;
    }

    public Integer getIloscBrak() {
        return iloscBrak;
    }

    public void setIloscBrak(Integer iloscBrak) {
        this.iloscBrak = iloscBrak;
    }

    public String getLogistykNrLogistyka() {
        return logistykNrLogistyka;
    }

    public void setLogistykNrLogistyka(String logistykNrLogistyka) {
        this.logistykNrLogistyka = logistykNrLogistyka;
    }

    public Towar getTowar() {
        return towar;
    }

    public void setTowar(Towar towar) {
        this.towar = towar;
    }

    public Integer getTowarId() {
        return towarId;
    }

    public void setTowarId(Integer towarId) {
        this.towarId = towarId;
    }

}