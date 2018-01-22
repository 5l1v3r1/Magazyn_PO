package pl.koziel.liebert.magahurtomonitor.Controller.Api;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import pl.koziel.liebert.magahurtomonitor.Model.Dostawca;
import pl.koziel.liebert.magahurtomonitor.Model.Zamowienie;
import pl.koziel.liebert.magahurtomonitor.Model.ZamowienieDoKompletowania;
import pl.koziel.liebert.magahurtomonitor.Model.Zlecenie;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by wojciech.liebert on 02.01.2018.
 */

/**
 * Interfejs służący do komunikacji z API. Musi być używany z Retrofit
 */
public interface MagazynApi {
    String SERVICE_ENDPOINT = "http://shorti1996.pythonanywhere.com/";

    /**
     *
     * @return Lista dostawcow
     */
    @GET("dostawcy")
    Observable<List<Dostawca>> getDostawcy();

    /**
     *
     * @return Lista zamowien
     */
    @GET("zamowienia")
    Observable<List<Zamowienie>> getZamowienia();

    /**
     *
     * @param nrZamowienia nr wybranego zamowienia
     * @return Szczegoly zamowienia
     */
    @GET("zamowienia/{NrZamowienia}")
    Observable<ZamowienieDoKompletowania> getZamowienieDoKompletowania(@Path("NrZamowienia") int nrZamowienia);

    /**
     * Tworzy dostawe na podane zlecenia
     * @param zlecenia Lista zlecen, ktore skladaja sie na dostawe
     * @return
     */
    @PUT("utworz_dostawe")
    Observable<ResponseBody> putUtworzDostawe(@Body List<Zlecenie> zlecenia);
}
