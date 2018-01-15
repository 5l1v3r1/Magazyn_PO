package pl.koziel.liebert.magahurtomonitor.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.koziel.liebert.magahurtomonitor.R;
import pl.koziel.liebert.magahurtomonitor.View.Internal.Logistyk_UI.OrderTypeChoiceActivity;
import pl.koziel.liebert.magahurtomonitor.View.Internal.Magazynier_UI.ZamowieniaListActivity;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.logistyk_button)
    Button mLogistykBtn;

    @BindView(R.id.magazynier_button)
    Button mMagazynierBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


        mLogistykBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, OrderTypeChoiceActivity.class);
            startActivity(intent);
        });

        mMagazynierBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, ZamowieniaListActivity.class);
            startActivity(intent);
        });
    }
}
