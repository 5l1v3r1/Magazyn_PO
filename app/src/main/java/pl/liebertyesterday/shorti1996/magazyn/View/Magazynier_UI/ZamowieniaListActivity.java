package pl.liebertyesterday.shorti1996.magazyn.View.Magazynier_UI;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pl.liebertyesterday.shorti1996.magazyn.Api.MagazynApi;
import pl.liebertyesterday.shorti1996.magazyn.Api.NetworkCaller;
import pl.liebertyesterday.shorti1996.magazyn.Model.Zamowienie;
import pl.liebertyesterday.shorti1996.magazyn.R;

public class ZamowieniaListActivity extends AppCompatActivity {

    public static final String TAG = ZamowieniaListActivity.class.getSimpleName();

    private List<Zamowienie> mZamowienia;

    @BindView(R.id.zamowienia_rv)
    RecyclerView mZamowieniaRv;
    @BindView(R.id.zamowienia_list_wait_info)
    TextView mWaitInfo;
    private ZamowieniaAdapter mZamowieniaAdapter;
    private NetworkCaller mCaller;
    private MagazynApi mService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zlecenia_predefiniowane);

        ButterKnife.bind(this);

//        mAnulujBtn.setOnClickListener(view -> ZleceniaPredefiniowaneActivity.this.finish());
//        mGotoweBtn.setOnClickListener(view -> utworzZlecenia());
//        String dostawcaJson = getIntent().getStringExtra(ZleceniaDostawcyActivity.EXTRA_WYBRANY_DOSTAWCA);
//        Gson gson = new Gson();
////        PotrzebnyTowar[] potrzebneTowary = gson.fromJson(potrzebneTowaryJson, PotrzebnyTowar[].class);
//        mDostawca = gson.fromJson(dostawcaJson, Dostawca.class);
//        mZamowienia = mDostawca.getPotrzebneTowary();
        getDataFromApi();
    }

    private void getDataFromApi() {
        mCaller = new NetworkCaller();
        mService = mCaller.getService();
        mService.getZamowienia()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(this::setupRecyclerView)
                .subscribe(zamowienia -> {
                    for (Zamowienie z : zamowienia) {
                        Log.d(TAG, String.format("Zamowienie id %s", z.getNrZamowienia()));
                        mZamowienia.add(z);
                    }
                }, throwable -> {
                    Log.d(TAG, "onCreate: network error");
                });
    }

    private void setupRecyclerView() {
        hideWaitInfo();
        mZamowieniaRv.setLayoutManager(new LinearLayoutManager(this));
        mZamowieniaAdapter = new ZamowieniaAdapter(mZamowienia);
        mZamowieniaRv.setAdapter(mZamowieniaAdapter);
    }

    private void hideWaitInfo() {
        mWaitInfo.setVisibility(View.GONE);
    }

    class ZamowieniaAdapter extends RecyclerView.Adapter<ZamowieniaAdapter.ZamowienieViewHolder> {
        List<Zamowienie> mZamowienia;

        ZamowieniaAdapter(List<Zamowienie> zamowienia) {
            mZamowienia = zamowienia;
        }

        @Override
        public ZamowienieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.zamowienie_vh, parent, false);
            return new ZamowienieViewHolder(itemView);
        }

        @SuppressLint("DefaultLocale")
        @Override
        public void onBindViewHolder(ZamowienieViewHolder holder, int position) {
            final Zamowienie zamowienie = mZamowienia.get(position);
            final String nrZam = String.format("Zamówienie %5d", zamowienie.getNrZamowienia());
            holder.mNazwa.setText(nrZam);
            final String formattedDate = reformatDate(zamowienie);
            holder.mData.setText(formattedDate);
        }

        @SuppressLint("SimpleDateFormat")
        private String reformatDate(Zamowienie zamowienie) {
            SimpleDateFormat fromFormat = new SimpleDateFormat("EEE, dd MMM yyy HH:mm:ss z"
                    , new Locale("en", "US"));
            Date date;
            try {
                date = fromFormat.parse(zamowienie.getDataZlozenia());
            } catch (ParseException e) {
                e.printStackTrace();
                date = new Date(0);
            }
            SimpleDateFormat toFormat = new SimpleDateFormat("d MMMM yyyy");
            return toFormat.format(date);
        }

        @Override
        public int getItemCount() {
            return mZamowienia.size();
        }

        class ZamowienieViewHolder extends RecyclerView.ViewHolder
                implements View.OnClickListener {

            @BindView(R.id.zamowienie_nazwa) TextView mNazwa;
            @BindView(R.id.zamowienie_data) TextView mData;

            ZamowienieViewHolder(View view) {
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

