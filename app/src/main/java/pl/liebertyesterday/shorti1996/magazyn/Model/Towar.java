package pl.liebertyesterday.shorti1996.magazyn.Model;

/**
 * Created by wojciech.liebert on 01.01.2018.
 */

import java.util.Vector;

public class Towar {
    private int iD;
    public int idTowaru;
    private String nazwa;
    /**
     * Ilość towaru dostępnego do sprzedaży.
     */
    private int dostępne;
    /**
     * Iość towaru zarezerwowanego: zamówionego, przed fakturowaniem.
     */
    private int zarezerwowane;
    /**
     * Ilość towaru znajdującego się w depozycie klienckim.
     */
    private int wDepozycie;
    private int wDostawie;
    private int min;
    private int max;
    private JednostkaMiary miara;
    private double iloscWMierze;
//    private KategoriaTowaru typTowaru;
//    private String opisTowaru;
//    private String cechyToaru;
//    public PartiaTowaru partiaTowaru;
//    public Vector<Zapotrzebowanie> zapotrzebowaniaTowaru = new Vector<Zapotrzebowanie>();
//    public Vector<PozycjaZamówienia> pozycjaTowaru = new Vector<PozycjaZamówienia>();
//    public Vector<TowarDostawcy> oferuje = new Vector<TowarDostawcy>();
//    public Magazyn stan_magazynowy;
//    public Vector<LokalizacjaTowaru> unnamed_LokalizacjaTowaru_ = new Vector<LokalizacjaTowaru>();
}