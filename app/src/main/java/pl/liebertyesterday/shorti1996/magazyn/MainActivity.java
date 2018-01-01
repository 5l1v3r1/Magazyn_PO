package pl.liebertyesterday.shorti1996.magazyn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

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
