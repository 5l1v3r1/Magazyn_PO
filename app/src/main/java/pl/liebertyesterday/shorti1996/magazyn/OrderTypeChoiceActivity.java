package pl.liebertyesterday.shorti1996.magazyn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pl.liebertyesterday.shorti1996.magazyn.Api.MagazynApi;
import pl.liebertyesterday.shorti1996.magazyn.Api.NetworkCaller;
import pl.liebertyesterday.shorti1996.magazyn.Model.Zapotrzebowanie;

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
            NetworkCaller caller = new NetworkCaller();
            final MagazynApi service = caller.getService();
//            Observable<List<Zapotrzebowanie>> zapotrzebowanie =
            service.listZapotrzebowanie()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(zapotrzebowania -> {
                        for (Zapotrzebowanie z : zapotrzebowania) {
                            Log.d(TAG, String.format("onCreate: zapotrzebowanie na %s", z.getTowarId()));
                        }
                    });
        });
    }
}
