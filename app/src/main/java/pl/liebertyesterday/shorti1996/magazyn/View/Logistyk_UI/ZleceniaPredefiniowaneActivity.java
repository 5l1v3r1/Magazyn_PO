package pl.liebertyesterday.shorti1996.magazyn.View.Logistyk_UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.liebertyesterday.shorti1996.magazyn.Model.PotrzebnyTowar;
import pl.liebertyesterday.shorti1996.magazyn.R;

// TODO dodac (un)check all
public class ZleceniaPredefiniowaneActivity extends AppCompatActivity {

    public static final String TAG = ZleceniaPredefiniowaneActivity.class.getSimpleName();

    private List<PotrzebnyTowar> mPotrzebneTowary;

    @BindView(R.id.predef_zlec_rv)
    RecyclerView mZleceniaRv;
    @BindView(R.id.predef_zlec_wait)
    TextView mWaitInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zlecenia_predefiniowane);

        ButterKnife.bind(this);

        String potrzebneTowaryJson = getIntent().getStringExtra(ZleceniaDostawcyActivity.extra_potrzebne_towary);
        Gson gson = new Gson();
        PotrzebnyTowar[] potrzebneTowary = gson.fromJson(potrzebneTowaryJson, PotrzebnyTowar[].class);
        mPotrzebneTowary = new LinkedList(Arrays.asList(potrzebneTowary));
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        hideWaitInfo();
        mZleceniaRv.setLayoutManager(new LinearLayoutManager(this));
        mZleceniaRv.setAdapter(new PredefZlecAdapter(mPotrzebneTowary));
    }

    private void hideWaitInfo() {
        mWaitInfo.setVisibility(View.GONE);
    }

    class PredefZlecAdapter extends RecyclerView.Adapter<PredefZlecAdapter.PotrzebnyTowarViewHolder> {
        private List<PotrzebnyTowar> mPotrzebneTowary;

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
            }

            @Override
            public void onClick(View view) {
                mCzyZam.toggle();
            }
        }
    }
}
