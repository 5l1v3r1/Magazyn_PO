package pl.liebertyesterday.shorti1996.magazyn.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.liebertyesterday.shorti1996.magazyn.R;
import pl.liebertyesterday.shorti1996.magazyn.View.Logistyk_UI.OrderTypeChoiceActivity;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.zrob_dobrze)
    Button zrobDobrzeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        zrobDobrzeBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, OrderTypeChoiceActivity.class);
            startActivity(intent);
        });
    }
}
