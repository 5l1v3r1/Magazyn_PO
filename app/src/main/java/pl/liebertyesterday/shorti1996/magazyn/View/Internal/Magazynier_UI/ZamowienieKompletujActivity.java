package pl.liebertyesterday.shorti1996.magazyn.View.Internal.Magazynier_UI;

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

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pl.liebertyesterday.shorti1996.magazyn.Api.MagazynApi;
import pl.liebertyesterday.shorti1996.magazyn.Api.NetworkCaller;
import pl.liebertyesterday.shorti1996.magazyn.Model.PozycjaZamowienia;
import pl.liebertyesterday.shorti1996.magazyn.Model.ZamowienieDoKompletowania;
import pl.liebertyesterday.shorti1996.magazyn.R;

public class ZamowienieKompletujActivity extends AppCompatActivity {

    public static final String TAG = ZamowienieKompletujActivity.class.getSimpleName();

    @BindView(R.id.zamowienie_kompletuj_rv)
    RecyclerView mKompletujRv;

    @BindView(R.id.zamowienie_kompletuj_wait_info)
    TextView mWaitInfo;

    @BindView(R.id.wpisz_btn)
    Button mWpiszBtn;

    @BindView(R.id.skanuj_btn)
    Button mSkanujBtn;

    @BindView(R.id.anuluj_kompletowanie_btn)
    Button mAnulujBtn;

    private PozycjaZamowieniaAdapter mZamowieniaAdapter;
    private NetworkCaller mCaller;
    private MagazynApi mService;

    ZamowienieDoKompletowania mZamowienie;
    List<PozycjaZamowienia> mPozycjeZamowienia = new LinkedList<PozycjaZamowienia>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zamowienie_kompletuj);

        ButterKnife.bind(this);

        getDataFromApi();
    }

    private void getDataFromApi() {
        mCaller = new NetworkCaller();
        mService = mCaller.getService();
        final int nrZamowienia = getIntent().getIntExtra(
                ZamowieniaListActivity.EXTRA_NR_ZAMOWIENIA_DO_KOMPLETOWANIA, -1);
        mService.getZamowienieDoKompletowania(nrZamowienia)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(this::setupRecyclerView)
                .subscribe(zamowienieDoKompletowania -> {
                    Log.d(TAG, String.format("Zamowienie id %s", zamowienieDoKompletowania.getNrZamowienia()));
                    mZamowienie = zamowienieDoKompletowania;
                    mPozycjeZamowienia = zamowienieDoKompletowania.getPozycjeZamowienia();
                }, throwable -> {
                    Log.d(TAG, "onCreate: network error", throwable);
                });
    }

    private void setupRecyclerView() {
        hideWaitInfo();
        mKompletujRv.setLayoutManager(new LinearLayoutManager(this));
        mZamowieniaAdapter = new PozycjaZamowieniaAdapter(mPozycjeZamowienia);
        mKompletujRv.setAdapter(mZamowieniaAdapter);
    }

    private void hideWaitInfo() {
        mWaitInfo.setVisibility(View.GONE);
    }

    class PozycjaZamowieniaAdapter extends RecyclerView.Adapter<PozycjaZamowieniaAdapter.PozycjaZamowieniaViewHolder> {
        List<PozycjaZamowienia> mZamowienia;

        PozycjaZamowieniaAdapter(List<PozycjaZamowienia> zamowienia) {
            mZamowienia = zamowienia;
        }

        @Override
        public PozycjaZamowieniaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.pozycja_zamowienia_vh, parent, false);
            return new PozycjaZamowieniaViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(PozycjaZamowieniaViewHolder holder, int position) {
            final PozycjaZamowienia pozycjaZamowienia = mZamowienia.get(position);
            holder.mNazwa.setText(pozycjaZamowienia.getTowar().getNazwa());
            holder.mNrRegalu.setText(String.valueOf(pozycjaZamowienia.getLokalizacja().getNrRegal()));
            holder.mNrPolki.setText(String.valueOf(pozycjaZamowienia.getLokalizacja().getNrPolki()));
            holder.mLSztuk.setText(String.valueOf(pozycjaZamowienia.getIloscZam()));
            holder.mCzySkan.setChecked(pozycjaZamowienia.getCzySkan());
        }

        @Override
        public int getItemCount() {
            return mZamowienia.size();
        }

        class PozycjaZamowieniaViewHolder extends RecyclerView.ViewHolder
                implements View.OnClickListener {

            @BindView(R.id.towar_nazwa) TextView mNazwa;
            @BindView(R.id.nr_regalu) TextView mNrRegalu;
            @BindView(R.id.nr_polki) TextView mNrPolki;
            @BindView(R.id.l_sztuk) TextView mLSztuk;
            @BindView(R.id.skan_cb) CheckBox mCzySkan;

            PozycjaZamowieniaViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
                view.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                // TODO
            }
        }
    }
}
