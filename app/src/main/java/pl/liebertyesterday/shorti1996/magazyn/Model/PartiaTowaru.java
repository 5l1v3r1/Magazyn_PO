package pl.liebertyesterday.shorti1996.magazyn.Model;

import java.util.Date;

/**
 * Created by wojciech.liebert on 01.01.2018.
 */

public class PartiaTowaru {
    private int iD;
    public int iloscWPartii;
    /**
     * Data ważności partii danego towaru.
     */
    public Date dataWazn;
    public int nrPartii;
    public Dostawa dostawa;
    public Towar towarDostarczony;
}