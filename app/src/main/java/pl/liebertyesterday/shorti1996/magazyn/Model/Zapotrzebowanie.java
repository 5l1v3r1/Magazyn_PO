package pl.liebertyesterday.shorti1996.magazyn.Model;

import java.util.Date;

/**
 * Created by wojciech.liebert on 01.01.2018.
 */

public class Zapotrzebowanie {
    private int iD;
    public int iloscBrak;
    public Date dataZap;
    private Date dataPrzetworzenia;
    public Logistyk logistyk;
    public Vector<Zlecenie> zalecenia = new Vector<Zlecenie>();
    public Towar brakującyTowar;
}