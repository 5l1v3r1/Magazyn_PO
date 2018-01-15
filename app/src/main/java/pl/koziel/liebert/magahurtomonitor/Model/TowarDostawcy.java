package pl.koziel.liebert.magahurtomonitor.Model;

/**
 * Created by wojciech.liebert on 01.01.2018.
 */

public class TowarDostawcy {
    private int iD;
    private int ilosc;
    private String uwagi;
    public Dostawca oferującyTowar;
    public Towar oferowanyTowar;

    public void setOferującyTowar(Dostawca oferującyTowar) {
        this.oferującyTowar = oferującyTowar;
    }

    public Dostawca getOferującyTowar() {
        return this.oferującyTowar;
    }

    public void setOferowanyTowar(Towar oferowanyTowar) {
        this.oferowanyTowar = oferowanyTowar;
    }

    public Towar getOferowanyTowar() {
        return this.oferowanyTowar;
    }
}
