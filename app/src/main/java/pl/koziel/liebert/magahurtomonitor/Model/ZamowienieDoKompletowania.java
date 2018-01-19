package pl.koziel.liebert.magahurtomonitor.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wojciech.liebert on 12.01.2018.
 */

/**
 * {@link Zamowienie} uzupełnione o pozycje zaówienia.
 */
public class ZamowienieDoKompletowania extends Zamowienie {
    @SerializedName("pozycjeZamowienia")
    @Expose
    private List<PozycjaZamowienia> pozycjeZamowienia = null;

    public List<PozycjaZamowienia> getPozycjeZamowienia() {
        return pozycjeZamowienia;
    }

    public void setPozycjeZamowienia(List<PozycjaZamowienia> pozycjeZamowienia) {
        this.pozycjeZamowienia = pozycjeZamowienia;
    }
}
