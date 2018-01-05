package pl.liebertyesterday.shorti1996.magazyn.View.Logistyk_UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import pl.liebertyesterday.shorti1996.magazyn.Api.MagazynApi;
import pl.liebertyesterday.shorti1996.magazyn.Api.NetworkCaller;
import pl.liebertyesterday.shorti1996.magazyn.Model.Zapotrzebowanie;
import pl.liebertyesterday.shorti1996.magazyn.R;
import retrofit2.Converter;
import retrofit2.HttpException;
import retrofit2.Retrofit;

public class OrderTypeChoiceActivity extends AppCompatActivity {

    private static final String TAG = OrderTypeChoiceActivity.class.getSimpleName();

    @BindView(R.id.zapotrzebowan_btn)
    Button zapotrzebowanieBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_type_choice);

        ButterKnife.bind(this);

        zapotrzebowanieBtn.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ZleceniaActivity.class);
            startActivity(intent);
//            NetworkCaller caller = new NetworkCaller();
//            final MagazynApi service = caller.getService();
////            Observable<List<Zapotrzebowanie>> zapotrzebowanie =
//            service.listZapotrzebowanie()
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(zapotrzebowania -> {
//                        for (Zapotrzebowanie z : zapotrzebowania) {
//                            Log.d(TAG, String.format("onCreate: zapotrzebowanie na %s", z.getTowarId()));
//                        }
//                    }, throwable -> {
//                        Log.d(TAG, "onCreate: network error");
//                    });
        });
    }

    public void convertNetworkError(Throwable throwable, Retrofit retrofit) throws IOException {
        if(throwable instanceof HttpException) {
            //we have a HTTP exception (HTTP status code is not 200-300)
            Converter<ResponseBody, Error> errorConverter =
                    retrofit.responseBodyConverter(Error.class, new Annotation[0]);
            //maybe check if ((HttpException) throwable).code() == 400 ??
            Error error = errorConverter.convert(((HttpException) throwable).response().errorBody());
        }
    }

    public class Error {
        public List<String> messages;
    }
}
