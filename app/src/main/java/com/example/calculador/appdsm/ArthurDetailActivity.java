package com.example.calculador.appdsm;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ArthurDetailActivity extends AppCompatActivity {

    private static final String NAME = "Arthur Mansur Barros";
    private static final String PHONE = "11948334209";
    private static final String EMAIL = "barrosarthur312@gmail.com";
    private static final String SOCIAL_HANDLE = "_bigbarros";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arthur_detail);

        TextView detailTitle = findViewById(R.id.detail_title);
        TextView detailInfo = findViewById(R.id.detail_info);

        detailTitle.setText("Informações de Arthur");
        String fullDetails = "Nome: " + NAME + "\n" + "Telefone: " + formatPhoneNumber(PHONE) + "\n" + "Email: " + EMAIL + "\n" + "Instagram: " + SOCIAL_HANDLE + "\n";
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