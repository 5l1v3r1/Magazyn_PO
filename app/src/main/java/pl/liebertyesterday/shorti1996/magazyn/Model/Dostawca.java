package pl.liebertyesterday.shorti1996.magazyn.Model;

/**
 * Created by wojciech.liebert on 01.01.2018.
 */

public class Dostawca {
    public int nrDostawcy;
    private String opinia;
    public Vector<Dostawa> dostawyDostawcy = new Vector<Dostawa>();
    public Vector<TowarDostawcy> oferuje = new Vector<TowarDostawcy>();
}