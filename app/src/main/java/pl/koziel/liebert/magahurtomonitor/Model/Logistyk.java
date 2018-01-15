package pl.koziel.liebert.magahurtomonitor.Model;

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
