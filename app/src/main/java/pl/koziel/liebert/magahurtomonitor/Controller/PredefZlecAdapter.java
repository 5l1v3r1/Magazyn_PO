package pl.koziel.liebert.magahurtomonitor.Controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.koziel.liebert.magahurtomonitor.Model.PotrzebnyTowar;
import pl.koziel.liebert.magahurtomonitor.R;

/**
 * Created by wojciech.liebert on 15.01.2018.
 */

public class PredefZlecAdapter extends RecyclerView.Adapter<PredefZlecAdapter.PotrzebnyTowarViewHolder> {
    public List<PotrzebnyTowar> mPotrzebneTowary;

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

    public class PotrzebnyTowarViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        @BindView(R.id.predef_zlec_nazwa_tow)
        TextView mNazwa;
        @BindView(R.id.predef_zlec_ilosc) TextView mIle;
        @BindView(R.id.predef_zlec_czy_zam)
        public CheckBox mCzyZam;

        public PotrzebnyTowarViewHolder(View view) {
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

