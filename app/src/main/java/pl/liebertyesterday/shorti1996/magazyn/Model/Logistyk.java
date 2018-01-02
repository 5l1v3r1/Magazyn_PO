package pl.liebertyesterday.shorti1996.magazyn.Model;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by wojciech.liebert on 01.01.2018.
 */

public class Logistyk extends Pracownik {
    public String nrLogistyka;
    public Collection<Zapotrzebowanie> zapotrzebowaniaPrzetwarzane = new LinkedList<>();
    public Collection<Zlecenie> zleceniaWystawione = new LinkedList<>();
}
