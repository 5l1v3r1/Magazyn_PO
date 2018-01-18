package pl.koziel.liebert.magahurtomonitor.View.Internal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.koziel.liebert.magahurtomonitor.R;

public abstract class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.ll_buttons)
    LinearLayout mButtonsLl;

    @BindView(R.id.profil_nazwa)
    protected
    TextView mNazwa;

    @BindView(R.id.profil_avatar)
    protected
    ImageView mAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);

        mAvatar.setImageResource(R.drawable.ic_person_black_24dp);

        createButtons(mButtonsLl);
    }

    protected abstract void createButtons(ViewGroup viewGroup);
}
