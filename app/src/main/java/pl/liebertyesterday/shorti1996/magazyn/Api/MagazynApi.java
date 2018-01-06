package pl.liebertyesterday.shorti1996.magazyn.Api;

import org.json.JSONObject;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import pl.liebertyesterday.shorti1996.magazyn.Model.Dostawca;
import pl.liebertyesterday.shorti1996.magazyn.Model.Zapotrzebowanie;
import pl.liebertyesterday.shorti1996.magazyn.Model.Zlecenie;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created by wojciech.liebert on 02.01.2018.
 */

public interface MagazynApi {
    String SERVICE_ENDPOINT = "http://shorti1996.pythonanywhere.com/";

    @GET("zapotrzebowanie")
    Observable<List<Zapotrzebowanie>> getZapotrzebowanie();

    @GET("dostawcy")
    Observable<List<Dostawca>> getDostawcy();

    @PUT("utworz_dostawe")
    Observable<ResponseBody> putUtworzDostawe(@Body List<Zlecenie> zlecenia);
}
