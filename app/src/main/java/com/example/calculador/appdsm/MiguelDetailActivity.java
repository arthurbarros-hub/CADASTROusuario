package com.example.calculador.appdsm;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MiguelDetailActivity extends AppCompatActivity {

    private static final String NAME = "Miguel Wilians Ferreira";
    private static final String PHONE = "11986054376";
    private static final String EMAIL = "migmib55@gmail.com";
    private static final String SOCIAL_HANDLE = "@_mwillianns";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miguel_detail);
        TextView detailTitle = findViewById(R.id.detail_title);
        TextView detailInfo = findViewById(R.id.detail_info);

        detailTitle.setText("Informações de Miguel");
        String fullDetails = String.format("Nome: %s\nTelefone: %s\nEmail: %s\nInstagram: %s\n", NAME, formatPhoneNumber(PHONE), EMAIL, SOCIAL_HANDLE);
        detailInfo.setText(fullDetails);
    }
    private String formatPhoneNumber(String phoneNumber) {
        if (phoneNumber != null && phoneNumber.length() == 11) {
            return "(" + phoneNumber.substring(0, 2) + ") " + phoneNumber.substring(2, 7) + "-" + phoneNumber.substring(7, 11);
        }
        return phoneNumber;
    }
    public void goBack(View view) {
        finish();
    }
}