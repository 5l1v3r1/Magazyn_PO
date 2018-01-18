package pl.koziel.liebert.magahurtomonitor.View.Internal.Logistyk_UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import pl.koziel.liebert.magahurtomonitor.R;
import retrofit2.Converter;
import retrofit2.HttpException;
import retrofit2.Retrofit;

/**
 * Aktywność wybierania typu trybu tworzenia zleceń.
 * Do wyboru jest opcja ręcznego i automatycznego generowania zleceń.
 */
public class OrderTypeChoiceActivity extends AppCompatActivity {

    private static final String TAG = OrderTypeChoiceActivity.class.getSimpleName();

    @BindView(R.id.zlec_dostawcy_btn)
    Button dostawcyBtn;

    @BindView(R.id.gen_plan_dostaw_btn)
    Button planDostawyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_type_choice);

        ButterKnife.bind(this);

        planDostawyBtn.setOnClickListener(view ->
                Toast.makeText(OrderTypeChoiceActivity.this,
                    R.string.dostepne_w_krotce,
                    Toast.LENGTH_SHORT).show());

        dostawcyBtn.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ZleceniaDostawcyActivity.class);
            startActivity(intent);
        });
    }

    public void convertNetworkError(Throwable throwable, Retrofit retrofit) throws IOException {
        if(throwable instanceof HttpException) {
            //we have a HTTP exception (HTTP status code is not 200-300)
            Converter<ResponseBody, Error> errorConverter =
                    retrofit.responseBodyConverter(Error.class, new Annotation[0]);
            //maybe check if ((HttpException) throwable).code() == 400 ??
            Error error = errorConverter.convert(((HttpException) throwable).response().errorBody());
        }
    }

    public class Error {
        public List<String> messages;
    }
}
