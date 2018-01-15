package pl.koziel.liebert.magahurtomonitor.View.Internal.Logistyk_UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.koziel.liebert.magahurtomonitor.Controller.PredefZlecAdapter;
import pl.koziel.liebert.magahurtomonitor.Model.Dostawca;
import pl.koziel.liebert.magahurtomonitor.Model.PotrzebnyTowar;
import pl.koziel.liebert.magahurtomonitor.Model.WeAreAgile;
import pl.koziel.liebert.magahurtomonitor.Model.Zlecenie;
import pl.koziel.liebert.magahurtomonitor.R;

// TODO dodac (un)check all
public class ZleceniaPredefiniowaneActivity extends AppCompatActivity {

    public static final String TAG = ZleceniaPredefiniowaneActivity.class.getSimpleName();
    public static final String EXTRA_ZLECENIA = "extra-zlecenia";
    public static final String EXTRA_POTRZEBNY_TOWAR = "extra-potrzebny-towar";

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
        List<PotrzebnyTowar> doZamowienia = new LinkedList<>();
        for (PotrzebnyTowar pt : mPredefZlecAdapter.mPotrzebneTowary) {
            if (pt.isCzyZamowic()) {
                doZamowienia.add(pt);
                Zlecenie zlecenie = new Zlecenie();
                zlecenie.setIloscZlec(pt.getDoZamowienia());
                zlecenie.setZapotrzebowanieId(pt.getIdZapotrzebowania());
                zlecenie.setLogistykNrLogistyka(WeAreAgile.NUMER_LOGISTYKA);
                zlecenie.setNrDostawcy(mDostawca.getNrDostawcy());
                zlecenia.add(zlecenie);
            }
        }

        String zleceniaArr = new Gson().toJson(zlecenia);
        String potrzebnyTowarArr = new Gson().toJson(doZamowienia);

        Intent intent = new Intent(this, ZleceniaPodsumowanieActivity.class);
        intent.putExtra(EXTRA_ZLECENIA, zleceniaArr);
        intent.putExtra(EXTRA_POTRZEBNY_TOWAR, potrzebnyTowarArr);
        startActivity(intent);
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

    }
