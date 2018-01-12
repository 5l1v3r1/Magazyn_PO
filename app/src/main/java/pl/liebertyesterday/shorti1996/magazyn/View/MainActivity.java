package pl.liebertyesterday.shorti1996.magazyn.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import pl.liebertyesterday.shorti1996.magazyn.R;
import pl.liebertyesterday.shorti1996.magazyn.View.Internal.Logistyk_UI.OrderTypeChoiceActivity;
import pl.liebertyesterday.shorti1996.magazyn.View.Internal.Magazynier_UI.ZamowieniaListActivity;

public class MainActivity extends AppCompatActivity {

//    @BindView(R.id.logistyk_button)
//    Button mLogistykBtn;
//
//    @BindView(R.id.magazynier_button)
//    Button mMagazynierBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


        findViewById(R.id.logistyk_button).setOnClickListener(view -> {
            Intent intent = new Intent(this, OrderTypeChoiceActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.magazynier_button).setOnClickListener(view -> {
            Intent intent = new Intent(this, ZamowieniaListActivity.class);
            startActivity(intent);
        });
    }
}
