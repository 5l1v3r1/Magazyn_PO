package pl.liebertyesterday.shorti1996.magazyn.View.Internal.Magazynier_UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
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

    private ZamowieniaListActivity.ZamowieniaAdapter mZamowieniaAdapter;
    private NetworkCaller mCaller;
    private MagazynApi mService;

    ZamowienieDoKompletowania mZamowienie;
    List<PozycjaZamowienia> mPozycjeZamowienia = new LinkedList<>();

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
//                .doOnComplete(this::setupRecyclerView)
                .subscribe(zamowienieDoKompletowania -> {
                    Log.d(TAG, String.format("Zamowienie id %s", zamowienieDoKompletowania.getNrZamowienia()));
                    mZamowienie = zamowienieDoKompletowania;
                    mPozycjeZamowienia = zamowienieDoKompletowania.getPozycjeZamowienia();
                }, throwable -> {
                    Log.d(TAG, "onCreate: network error", throwable);
                });
    }
}
