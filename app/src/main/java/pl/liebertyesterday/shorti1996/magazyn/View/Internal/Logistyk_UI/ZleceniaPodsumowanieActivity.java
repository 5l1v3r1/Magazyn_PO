package pl.liebertyesterday.shorti1996.magazyn.View.Internal.Logistyk_UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pl.liebertyesterday.shorti1996.magazyn.Controller.Api.MagazynApi;
import pl.liebertyesterday.shorti1996.magazyn.Controller.Api.NetworkCaller;
import pl.liebertyesterday.shorti1996.magazyn.Controller.PredefZlecAdapter;
import pl.liebertyesterday.shorti1996.magazyn.Model.PotrzebnyTowar;
import pl.liebertyesterday.shorti1996.magazyn.Model.Zlecenie;
import pl.liebertyesterday.shorti1996.magazyn.R;
import pl.liebertyesterday.shorti1996.magazyn.View.MainActivity;

public class ZleceniaPodsumowanieActivity extends AppCompatActivity {

    public static final String TAG = ZleceniaPodsumowanieActivity.class.getSimpleName();

    @BindView(R.id.podsum_zlec_rv)
    RecyclerView mZleceniaRv;
    @BindView(R.id.podsumowanie_zlec_gotowe)
    Button mGotoweBtn;
    private PredefZlecAdapter mZlecAdapter;
    private List<Zlecenie> mZlecenia;
    private ArrayList<PotrzebnyTowar> mPotrzebneTowary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zlecenia_podsumowanie);

        ButterKnife.bind(this);

        parseExtras();

        mZlecAdapter = new PredefZlecAdapter(mPotrzebneTowary){
            @Override
            public void onBindViewHolder(PotrzebnyTowarViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                holder.mCzyZam.setVisibility(View.INVISIBLE);
                holder.mCzyZam.setOnCheckedChangeListener(null);
            }
        };
        mZleceniaRv.setLayoutManager(new LinearLayoutManager(this));
        mZleceniaRv.setAdapter(mZlecAdapter);

        mGotoweBtn.setOnClickListener(view -> {
        NetworkCaller caller = new NetworkCaller();
        MagazynApi serviceApi = caller.getService();
        serviceApi.putUtworzDostawe(mZlecenia)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseBody -> {
                    Log.d(TAG, "utworzZlecenia: " + responseBody.string());
                    Toast.makeText(getApplicationContext(), R.string.dostawa_wyslana_toast, Toast.LENGTH_LONG).show();
                    final ZleceniaPodsumowanieActivity activity = ZleceniaPodsumowanieActivity.this;
                    final Intent intent = new Intent(activity, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    Toast.makeText(activity, "Dostawa utworzona", Toast.LENGTH_SHORT).show();
                    activity.startActivity(intent);
                }, throwable -> {
                    Log.e(TAG, "utworzZlecenia: Did not post to server", throwable);
                    Toast.makeText(getApplicationContext(), R.string.nie_wyslano_dostawy_toast, Toast.LENGTH_LONG).show();
                });
        });
    }

    @NonNull
    private void parseExtras() {
        String extraZlecenia = getIntent().getStringExtra(ZleceniaPredefiniowaneActivity.EXTRA_ZLECENIA);
        Zlecenie[] zleceniaArr = new Gson().fromJson(extraZlecenia, Zlecenie[].class);
        mZlecenia = new ArrayList<>(Arrays.asList(zleceniaArr));

        String extraPotrzebneTowary = getIntent().getStringExtra(ZleceniaPredefiniowaneActivity.EXTRA_POTRZEBNY_TOWAR);
        PotrzebnyTowar[] potTow = new Gson().fromJson(extraPotrzebneTowary, PotrzebnyTowar[].class);
        mPotrzebneTowary = new ArrayList<>(Arrays.asList(potTow));
    }

}
