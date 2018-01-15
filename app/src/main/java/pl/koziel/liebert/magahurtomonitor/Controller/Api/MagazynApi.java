package pl.koziel.liebert.magahurtomonitor.Controller.Api;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import pl.koziel.liebert.magahurtomonitor.Model.Dostawca;
import pl.koziel.liebert.magahurtomonitor.Model.Zamowienie;
import pl.koziel.liebert.magahurtomonitor.Model.ZamowienieDoKompletowania;
import pl.koziel.liebert.magahurtomonitor.Model.Zapotrzebowanie;
import pl.koziel.liebert.magahurtomonitor.Model.Zlecenie;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by wojciech.liebert on 02.01.2018.
 */

public interface MagazynApi {
    String SERVICE_ENDPOINT = "http://shorti1996.pythonanywhere.com/";

    @GET("zapotrzebowanie")
    Observable<List<Zapotrzebowanie>> getZapotrzebowanie();

    @GET("dostawcy")
    Observable<List<Dostawca>> getDostawcy();

    @GET("zamowienia")
    Observable<List<Zamowienie>> getZamowienia();


    @GET("zamowienia/{NrZamowienia}")
    Observable<ZamowienieDoKompletowania> getZamowienieDoKompletowania(@Path("NrZamowienia") int nrZamowienia);

    @PUT("utworz_dostawe")
    Observable<ResponseBody> putUtworzDostawe(@Body List<Zlecenie> zlecenia);
}
