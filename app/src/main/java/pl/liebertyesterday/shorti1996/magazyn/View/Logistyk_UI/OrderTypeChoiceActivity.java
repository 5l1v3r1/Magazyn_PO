package pl.liebertyesterday.shorti1996.magazyn.View.Logistyk_UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import pl.liebertyesterday.shorti1996.magazyn.R;
import retrofit2.Converter;
import retrofit2.HttpException;
import retrofit2.Retrofit;

public class OrderTypeChoiceActivity extends AppCompatActivity {

    private static final String TAG = OrderTypeChoiceActivity.class.getSimpleName();

    @BindView(R.id.zlec_dostawcy_btn)
    Button dostawcyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_type_choice);

        ButterKnife.bind(this);

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
