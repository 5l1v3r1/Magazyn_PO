package pl.liebertyesterday.shorti1996.magazyn.Model;

/**
 * Created by wojciech.liebert on 01.01.2018.
 */

public class Logistyk extends Pracownik {
    public String nrLogistyka;
    public Vector<Zapotrzebowanie> zapotrzebowaniaPrzetwarzane = new Vector<Zapotrzebowanie>();
    public Vector<Zlecenie> zleceniaWystawione = new Vector<Zlecenie>();
}
