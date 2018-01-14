package pl.liebertyesterday.shorti1996.magazyn.View.Internal.Magazynier_UI;

import android.Manifest;
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

import com.tedpark.tedpermission.rx2.TedRx2Permission;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pl.liebertyesterday.shorti1996.magazyn.AnnealingSimulator;
import pl.liebertyesterday.shorti1996.magazyn.Api.MagazynApi;
import pl.liebertyesterday.shorti1996.magazyn.Api.NetworkCaller;
import pl.liebertyesterday.shorti1996.magazyn.Model.Lokalizacja;
import pl.liebertyesterday.shorti1996.magazyn.Model.PozycjaZamowienia;
import pl.liebertyesterday.shorti1996.magazyn.Model.TravelPath;
import pl.liebertyesterday.shorti1996.magazyn.Model.ZamowienieDoKompletowania;
import pl.liebertyesterday.shorti1996.magazyn.R;

public class ZamowienieKompletujActivity extends AppCompatActivity {

    public static final String TAG = ZamowienieKompletujActivity.class.getSimpleName();
    private static final int SCAN_REQUEST_CODE = 1001;
    public static final String EXTRA_SCAN_RESULT = "extra-scan-result";

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
    List<PozycjaZamowienia> mPozycjeZamowienia = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zamowienie_kompletuj);

        ButterKnife.bind(this);

        mWpiszBtn.setOnClickListener(view -> {
            calc();
        });

        mSkanujBtn.setOnClickListener(view -> {
            TedRx2Permission.with(this)
//                .setRationaleTitle("Title")
//                .setRationaleMessage("Message") // "we need permission for read contact and find your location"
                    .setPermissions(Manifest.permission.CAMERA)
                    .request()
                    .observeOn(Schedulers.io())
                    .subscribeOn(Schedulers.io())
                    .subscribe(tedPermissionResult -> {
                        if (tedPermissionResult.isGranted()) {
                            Intent scanIntent = new Intent(ZamowienieKompletujActivity.this, SimpleScannerActivity.class);
                            startActivityForResult(scanIntent, SCAN_REQUEST_CODE);
                        }
                        else {
                            Toast.makeText(this,
                                    "Camera permission denied. Can't use scanner.", Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }, throwable -> {
                    }, () -> {
                    });
        });

        getDataFromApi();
    }

    private void calc() {
        List<Lokalizacja> lokalizacje = new LinkedList<>();
        for (PozycjaZamowienia pz:
             mPozycjeZamowienia) {
            lokalizacje.add(pz.getLokalizacja());
        }
        lokalizacje.add(0, new Lokalizacja(1,1));
        lokalizacje.add(new Lokalizacja(2,20));
        lokalizacje.add(new Lokalizacja(8,1));
        TravelPath travelPath = new TravelPath(lokalizacje);

        Observable.defer(() -> Observable.just(new AnnealingSimulator(travelPath))
                    .map(annealingSimulator ->
                        annealingSimulator.simulateAnnealing(100, 0.9999))
                    .subscribeOn(Schedulers.computation())
            ).subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::a);
    }

    private void a(List<Lokalizacja> bestPath) {
        List<PozycjaZamowienia> zamowieniaPath = new LinkedList<>();
        for (int i = 0; i < bestPath.size(); i++) {
            if (i != 0 || i != zamowieniaPath.size() - 1) {
                for (PozycjaZamowienia pz : mZamowieniaAdapter.mZamowienia) {
                    if (pz.getLokalizacja().getIDLokalizacji().equals(bestPath.get(i).getIDLokalizacji())) {
                        zamowieniaPath.add(pz);
                        break;
                    }
                }
            }
        }
        mZamowieniaAdapter.mZamowienia = zamowieniaPath;
        mZamowieniaAdapter.notifyDataSetChanged();
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
                    List<PozycjaZamowienia> pozycje = zamowienieDoKompletowania.getPozycjeZamowienia();
                    Collections.sort(pozycje, (p1, p2) -> p2.getLokalizacja().getNrRegal() - p1.getLokalizacja().getNrPolki());
                    mPozycjeZamowienia = pozycje;
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SCAN_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                if (data.hasExtra(EXTRA_SCAN_RESULT)) {
                    String result = data.getStringExtra(EXTRA_SCAN_RESULT);
                    Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
                    int res = Integer.parseInt(result);
                    final PozycjaZamowienia pozycjaZamowienia = findPozycjaZamowienia(res);
                    if (pozycjaZamowienia != null) {
                        pozycjaZamowienia.setCzySkan(true);
                        mZamowieniaAdapter.notifyDataSetChanged();
                    } else {
                        Log.w(TAG, "onActivityResult: Cos poszlo nie tak");
                    }
                }
            }
        }
    }

    private PozycjaZamowienia findPozycjaZamowienia(int towarId) {
        for (PozycjaZamowienia pz :
                mPozycjeZamowienia) {
            if (pz.getTowarID() == towarId) {
                return pz;
            }
        }
        return null;
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
