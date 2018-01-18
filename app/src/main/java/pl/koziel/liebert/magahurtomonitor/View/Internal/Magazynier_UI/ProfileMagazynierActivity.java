package pl.koziel.liebert.magahurtomonitor.View.Internal.Magazynier_UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import pl.koziel.liebert.magahurtomonitor.View.Internal.ProfileActivity;

public class ProfileMagazynierActivity extends ProfileActivity {

    private static final String[] buttonLabels = {
            "Skompletuj zamówienie",
            "Lista zamówień",
            "Dane pracownika",
            "Wyloguj",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mNazwa.setText("Magazynier00001");
    }

    @Override
    protected void createButtons(ViewGroup viewGroup) {
        for (int i = 0; i < buttonLabels.length; i++) {
            LinearLayout row = new LinearLayout(this);
            row.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            Button newButton = new Button(this);
            newButton.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            newButton.setText(buttonLabels[i]);
            row.addView(newButton);

            if (i == 0) {
                newButton.setOnClickListener(view ->
                        startActivity(new Intent(ProfileMagazynierActivity.this, ZamowieniaListActivity.class)));
            } else {
                newButton.setEnabled(false);
//                newButton.setOnClickListener(view ->
//                        Toast.makeText(this, R.string.dostepne_w_krotce, Toast.LENGTH_SHORT).show());
            }

            viewGroup.addView(row);
        }
    }
}
