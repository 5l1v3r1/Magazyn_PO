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
import android.widget.TextView;

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
import pl.liebertyesterday.shorti1996.magazyn.R;

public class ZleceniaDostawcyActivity extends AppCompatActivity {

    public static final String TAG = ZleceniaActivity.class.getSimpleName();

    public static final String EXTRA_WYBRANY_DOSTAWCA = "extra-wyb-dost";

    private MagazynApi mService;
    private NetworkCaller mCaller;
    private List<Dostawca> mDostawcy = new LinkedList<>();

    @BindView(R.id.dostawcy_rv)
    RecyclerView mDostawcyRv;
    @BindView(R.id.dostawcy_wait_info)
    TextView mWaitInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zlecenia_dostawcy);

        ButterKnife.bind(this);

        getDataFromApi();
    }

    private void getDataFromApi() {
        mCaller = new NetworkCaller();
        mService = mCaller.getService();
        mService.getDostawcy()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(this::setupRecyclerView)
                .subscribe(dostawcy -> {
                    for (Dostawca d : dostawcy) {
                        Log.d(TAG, String.format("onCreate: dostawca id %s", d.getNrDostawcy()));
                        mDostawcy.add(d);
                    }
                }, throwable -> {
                    Log.d(TAG, "onCreate: network error");
                });
    }

    private void setupRecyclerView() {
        hideWaitInfo();
        mDostawcyRv.setLayoutManager(new LinearLayoutManager(this));
        mDostawcyRv.setAdapter(new DostawcyAdapter(mDostawcy));
    }

    private void hideWaitInfo() {
        mWaitInfo.setVisibility(View.GONE);
    }

    class DostawcyAdapter extends RecyclerView.Adapter<ZleceniaDostawcyActivity.DostawcyAdapter.DostawcaViewHolder> {
        private List<Dostawca> mDostawcy;

        public DostawcyAdapter(List<Dostawca> dostawca) {
            mDostawcy = dostawca;
        }

        @Override
        public DostawcyAdapter.DostawcaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.dostawca_vh, parent, false);
            return new ZleceniaDostawcyActivity.DostawcyAdapter.DostawcaViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(DostawcyAdapter.DostawcaViewHolder holder, int position) {
            final Dostawca dostawca = mDostawcy.get(position);
            holder.mNazwa.setText(String.valueOf(dostawca.getNazwa()));
            holder.mIle.setText(String.valueOf(dostawca.getIleZapotrzebowan()));
        }

        @Override
        public int getItemCount() {
            return mDostawcy.size();
        }

        class DostawcaViewHolder extends RecyclerView.ViewHolder
                implements View.OnClickListener {

            @BindView(R.id.dostawca_nazwa) TextView mNazwa;
            @BindView(R.id.dostawca_ile) TextView mIle;

            DostawcaViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
                view.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
//                List<PotrzebnyTowar> potrzebneTowary
//                        = mDostawcy.get(getAdapterPosition()).getPotrzebneTowary();
                Dostawca dostawca = mDostawcy.get(getAdapterPosition());
                Gson gson = new Gson();
                String jsonString = gson.toJson(dostawca);
                Intent intent = new Intent(getApplicationContext(), ZleceniaPredefiniowaneActivity.class);
                intent.putExtra(EXTRA_WYBRANY_DOSTAWCA, jsonString);
                startActivity(intent);
            }
        }
    }

}
