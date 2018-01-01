package pl.liebertyesterday.shorti1996.magazyn.Model;

import java.util.Date;

/**
 * Created by wojciech.liebert on 01.01.2018.
 */

public class Dostawa {
    private int iD;
    public int nrDostawy;
    private Date dataDost;
    private boolean czyZrealizowane;
    private Date dataUtworzenia;
    public Dostawca dostawca;
    public Vector<Zlecenie> zleceniaRealizowane = new Vector<Zlecenie>();
    public Vector<PartiaTowaru> partieTowaru = new Vector<PartiaTowaru>();
}