package pl.liebertyesterday.shorti1996.magazyn.Model;

import java.util.Date;

/**
 * Created by wojciech.liebert on 01.01.2018.
 */

public class Zlecenie {
    private int iD;
    public int iloscZlec;
    public Date dataZlec;
//    public StatusZlecenia statusZlecenia;
    private String uwagi;
    public Zapotrzebowanie zapotrzebowanie;
    public Logistyk zlecający;
    public Dostawa dostawaZlecona;
}
