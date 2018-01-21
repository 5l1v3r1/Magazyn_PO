package pl.koziel.liebert.magahurtomonitor.View.Internal.Logistyk_UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import pl.koziel.liebert.magahurtomonitor.R;
import pl.koziel.liebert.magahurtomonitor.View.Internal.ProfileActivity;

public class ProfileLogistykActivity extends ProfileActivity {

    private static final String[] buttonLabels = {
            "Stwórz zamówienie",
            "Przetwórz zapotrzebowania",
            "Stwórz zlecenie",
            "Dane pracownika",
            "Wyloguj",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mNazwa.setText("Logistyk00001");
    }

    @Override
    protected void createButtons(ViewGroup viewGroup) {
        for (int i = 0; i < buttonLabels.length; i++) {
            LinearLayout row = new LinearLayout(this);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, 165);
            layoutParams.setMargins(0, 60, 0, 0);

            Button newButton = new Button(this);
            newButton.setText(buttonLabels[i]);
            newButton.setBackgroundResource(R.drawable.button_white_size);
            newButton.setTextColor(getResources().getColor(R.color.colorAccent));
            newButton.setTextSize(12);
            row.addView(newButton, layoutParams);

            if (i == 1) {
                newButton.setOnClickListener(view ->
                        startActivity(new Intent(ProfileLogistykActivity.this, OrderTypeChoiceActivity.class)));
            } else {
                newButton.setEnabled(false);
//                newButton.setOnClickListener(view ->
//                        Toast.makeText(this, R.string.dostepne_w_krotce, Toast.LENGTH_SHORT).show());
            }

            viewGroup.addView(row);
        }
    }
}
