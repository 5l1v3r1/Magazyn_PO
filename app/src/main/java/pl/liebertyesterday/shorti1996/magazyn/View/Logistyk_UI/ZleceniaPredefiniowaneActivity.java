package pl.liebertyesterday.shorti1996.magazyn.View.Logistyk_UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pl.liebertyesterday.shorti1996.magazyn.Api.MagazynApi;
import pl.liebertyesterday.shorti1996.magazyn.Api.NetworkCaller;
import pl.liebertyesterday.shorti1996.magazyn.Model.Dostawca;
import pl.liebertyesterday.shorti1996.magazyn.Model.PotrzebnyTowar;
import pl.liebertyesterday.shorti1996.magazyn.Model.WeAreAgile;
import pl.liebertyesterday.shorti1996.magazyn.Model.Zlecenie;
import pl.liebertyesterday.shorti1996.magazyn.R;
import pl.liebertyesterday.shorti1996.magazyn.View.MainActivity;

// TODO dodac (un)check all
public class ZleceniaPredefiniowaneActivity extends AppCompatActivity {

    public static final String TAG = ZleceniaPredefiniowaneActivity.class.getSimpleName();

    private Dostawca mDostawca;
    private List<PotrzebnyTowar> mPotrzebneTowary;

    @BindView(R.id.predef_zlec_rv)
    RecyclerView mZleceniaRv;
    @BindView(R.id.predef_zlec_wait)
    TextView mWaitInfo;
    @BindView(R.id.predef_zlec_anuluj)
    Button mAnulujBtn;
    @BindView(R.id.predef_zlec_gotowe)
    Button mGotoweBtn;
    private PredefZlecAdapter mPredefZlecAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zlecenia_predefiniowane);

        ButterKnife.bind(this);

        mAnulujBtn.setOnClickListener(view -> ZleceniaPredefiniowaneActivity.this.finish());
        mGotoweBtn.setOnClickListener(view -> utworzZlecenia());
        String dostawcaJson = getIntent().getStringExtra(ZleceniaDostawcyActivity.EXTRA_WYBRANY_DOSTAWCA);
        Gson gson = new Gson();
//        PotrzebnyTowar[] potrzebneTowary = gson.fromJson(potrzebneTowaryJson, PotrzebnyTowar[].class);
        mDostawca = gson.fromJson(dostawcaJson, Dostawca.class);
        mPotrzebneTowary = mDostawca.getPotrzebneTowary();
        setupRecyclerView();
    }

    private void utworzZlecenia() {
        List<Zlecenie> zlecenia = new LinkedList<>();
        for (PotrzebnyTowar pt : mPredefZlecAdapter.mPotrzebneTowary) {
            if (pt.isCzyZamowic()) {
                Zlecenie zlecenie = new Zlecenie();
                zlecenie.setIloscZlec(pt.getDoZamowienia());
                zlecenie.setZapotrzebowanieId(pt.getIdZapotrzebowania());
                zlecenie.setLogistykNrLogistyka(WeAreAgile.NUMER_LOGISTYKA);
                zlecenie.setNrDostawcy(mDostawca.getNrDostawcy());
                zlecenia.add(zlecenie);
            }
        }
//        zlecenia.toArray();
//        Gson gson = new Gson();
//        String JsonToSend = gson.toJson(zlecenia.toArray(), Zlecenie[].class);
        NetworkCaller caller = new NetworkCaller();
        MagazynApi serviceApi = caller.getService();
        serviceApi.putUtworzDostawe(zlecenia)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseBody -> {
                    Log.d(TAG, "utworzZlecenia: " + responseBody.string());
                    Toast.makeText(getApplicationContext(), R.string.dostawa_wyslana_toast, Toast.LENGTH_LONG).show();
                    final ZleceniaPredefiniowaneActivity activity = ZleceniaPredefiniowaneActivity.this;
                    final Intent intent = new Intent(activity, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    activity.startActivity(intent);
                }, throwable -> {
                    Log.e(TAG, "utworzZlecenia: Did not post to server", throwable);
                    Toast.makeText(getApplicationContext(), R.string.nie_wyslano_dostawy_toast, Toast.LENGTH_LONG).show();
                });
    }

    private void setupRecyclerView() {
        hideWaitInfo();
        mZleceniaRv.setLayoutManager(new LinearLayoutManager(this));
        mPredefZlecAdapter = new PredefZlecAdapter(mPotrzebneTowary);
        mZleceniaRv.setAdapter(mPredefZlecAdapter);
    }

    private void hideWaitInfo() {
        mWaitInfo.setVisibility(View.GONE);
    }

    class PredefZlecAdapter extends RecyclerView.Adapter<PredefZlecAdapter.PotrzebnyTowarViewHolder> {
        List<PotrzebnyTowar> mPotrzebneTowary;

        public PredefZlecAdapter(List<PotrzebnyTowar> potrzebneTowary) {
            mPotrzebneTowary = potrzebneTowary;
        }

        @Override
        public PotrzebnyTowarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.predefiniowane_zlecenie_vh, parent, false);
            return new PotrzebnyTowarViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(PotrzebnyTowarViewHolder holder, int position) {
            final PotrzebnyTowar potrzebnyTowar = mPotrzebneTowary.get(position);
            holder.mNazwa.setText(String.valueOf(potrzebnyTowar.getNazwa()));
            holder.mIle.setText(String.valueOf(potrzebnyTowar.getDoZamowienia()));
        }

        @Override
        public int getItemCount() {
            return mPotrzebneTowary.size();
        }

        class PotrzebnyTowarViewHolder extends RecyclerView.ViewHolder
                implements View.OnClickListener {

            @BindView(R.id.predef_zlec_nazwa_tow) TextView mNazwa;
            @BindView(R.id.predef_zlec_ilosc) TextView mIle;
            @BindView(R.id.predef_zlec_czy_zam) CheckBox mCzyZam;

            PotrzebnyTowarViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
                view.setOnClickListener(this);
                mCzyZam.setOnCheckedChangeListener((compoundButton, b) -> {
                    mPotrzebneTowary.get(getAdapterPosition()).setCzyZamowic(b);
                });
            }

            @Override
            public void onClick(View view) {
                mCzyZam.toggle();
            }
        }
    }
}
