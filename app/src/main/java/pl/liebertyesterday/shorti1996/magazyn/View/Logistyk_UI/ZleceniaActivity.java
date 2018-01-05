package pl.liebertyesterday.shorti1996.magazyn.View.Logistyk_UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pl.liebertyesterday.shorti1996.magazyn.Api.MagazynApi;
import pl.liebertyesterday.shorti1996.magazyn.Api.NetworkCaller;
import pl.liebertyesterday.shorti1996.magazyn.Model.Zapotrzebowanie;
import pl.liebertyesterday.shorti1996.magazyn.R;

public class ZleceniaActivity extends AppCompatActivity {

    public static final String TAG = ZleceniaActivity.class.getSimpleName();
    private MagazynApi mService;
    private NetworkCaller mCaller;
    private List<Zapotrzebowanie> mZapotrzebowanie = new LinkedList<>();

    @BindView(R.id.zam_rv) RecyclerView mZapotrzebowanieRv;
    @BindView(R.id.zapotrzebowanie_wait_info) TextView mWaitInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zlecenia);

        ButterKnife.bind(this);

        getDataFromApi();
    }

    private void getDataFromApi() {
        mCaller = new NetworkCaller();
        mService = mCaller.getService();
        mService.listZapotrzebowanie()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(this::setupRecyclerView)
                .subscribe(zapotrzebowania -> {
                    for (Zapotrzebowanie z : zapotrzebowania) {
                        Log.d(TAG, String.format("onCreate: mZapotrzebowanie id %s, na %s", z.getIDZapotrzebowania(), z.getTowarId()));
                        mZapotrzebowanie.add(z);
                    }
                }, throwable -> {
                    Log.d(TAG, "onCreate: network error");
                });
    }

    private void setupRecyclerView() {
        hideWaitInfo();
        mZapotrzebowanieRv.setLayoutManager(new LinearLayoutManager(this));
        mZapotrzebowanieRv.setAdapter(new ZamowieniaAdapter(mZapotrzebowanie));
    }

    private void hideWaitInfo() {
        mWaitInfo.setVisibility(View.GONE);
    }

    class ZamowieniaAdapter extends RecyclerView.Adapter<ZamowieniaAdapter.ZamowieniaViewHolder> {
        private List<Zapotrzebowanie> mZapotrzebowanie;

        public ZamowieniaAdapter(List<Zapotrzebowanie> zapotrzebowanie) {
            mZapotrzebowanie = zapotrzebowanie;
        }

        @Override
        public ZamowieniaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.zlecenie_vh, parent, false);
            return new ZamowieniaViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ZamowieniaViewHolder holder, int position) {
            final Zapotrzebowanie zapotrzebowanie = mZapotrzebowanie.get(position);
            holder.mId.setText(String.valueOf(zapotrzebowanie.getIDZapotrzebowania()));
            holder.mTowar.setText(String.valueOf(zapotrzebowanie.getTowar().getNazwa()));
            holder.mIlosc.setText(String.valueOf(zapotrzebowanie.getIloscBrak()));
        }

        @Override
        public int getItemCount() {
            return mZapotrzebowanie.size();
        }

        class ZamowieniaViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.zapotrzebowanie_id) TextView mId;
            @BindView(R.id.zapotrzebowanie_towar) TextView mTowar;
            @BindView(R.id.zapotrzebowanie_ilosc) TextView mIlosc;

            ZamowieniaViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }
}
