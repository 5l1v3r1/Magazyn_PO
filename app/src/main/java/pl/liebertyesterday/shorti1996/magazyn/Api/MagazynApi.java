package pl.liebertyesterday.shorti1996.magazyn.Api;

import java.util.List;

import io.reactivex.Observable;
import pl.liebertyesterday.shorti1996.magazyn.Model.Dostawca;
import pl.liebertyesterday.shorti1996.magazyn.Model.Zapotrzebowanie;
import retrofit2.http.GET;

/**
 * Created by wojciech.liebert on 02.01.2018.
 */

public interface MagazynApi {
    String SERVICE_ENDPOINT = "http://shorti1996.pythonanywhere.com/";

    @GET("zapotrzebowanie")
    Observable<List<Zapotrzebowanie>> getZapotrzebowanie();

    @GET("dostawcy")
    Observable<List<Dostawca>> getDostawcy();
}
