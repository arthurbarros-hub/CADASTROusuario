package com.example.calculador.appdsm;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private TextView detailTitle;
    private TextView detailName;
    private TextView detailEmail;
    private TextView detailInstagram;
    private TextView detailPhone;
    private Button backButton;
    private String contactEmail;
    private String contactInstagram;
    private String contactPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Inicializa TextViews e Button
        detailTitle = findViewById(R.id.detail_title);
        detailName = findViewById(R.id.detail_name);
        detailEmail = findViewById(R.id.detail_email);
        detailInstagram = findViewById(R.id.detail_instagram);
        detailPhone = findViewById(R.id.detail_phone);
        backButton = findViewById(R.id.back_button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String name = extras.getString("NAME", "Não Informado");
            contactEmail = extras.getString("EMAIL", "Não Informado");
            contactInstagram = extras.getString("INSTAGRAM", "Não Informado");
            contactPhone = extras.getString("PHONE", "Não Informado").trim();

            detailTitle.setText("Informações de " + name);
            detailName.setText("Nome: " + name);
            detailEmail.setText("Email: " + contactEmail);
            detailInstagram.setText("Instagram: " + contactInstagram);
            detailPhone.setText("Telefone: " + contactPhone);


            detailEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!"Não Informado".equals(contactEmail) && contactEmail != null && !contactEmail.isEmpty()) {
                        openEmailApp(contactEmail);
                    } else {
                        Toast.makeText(DetailActivity.this, "Email não disponível.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            detailInstagram.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!"Não Informado".equals(contactInstagram) && contactInstagram != null && !contactInstagram.isEmpty()) {
                        openInstagramProfile(contactInstagram);
                    } else {
                        Toast.makeText(DetailActivity.this, "Instagram não disponível.", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            detailPhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!"Não Informado".equals(contactPhone) && contactPhone != null && !contactPhone.isEmpty()) {
                        dialPhoneNumber(contactPhone);
                    } else {
                        Toast.makeText(DetailActivity.this, "Telefone não disponível.", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        } else {
            detailTitle.setText("Detalhes do Contato");
            detailName.setText("Nome: Não disponível");
            detailEmail.setText("Email: Não disponível");
            detailInstagram.setText("Instagram: Não disponível");
            detailPhone.setText("Telefone: Não disponível");
        }
    }
    private void openEmailApp(String emailAddress) {
        try {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:" + emailAddress));
            if (emailIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(emailIntent);
            } else {
                Toast.makeText(this, "Nenhum aplicativo de e-mail encontrado.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Erro ao abrir o aplicativo de e-mail: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
    private void openInstagramProfile(String instagramHandle) {
        String handle = instagramHandle.startsWith("@") ? instagramHandle.substring(1) : instagramHandle;
        Uri uri;
        Intent instagramIntent;

        try {
            uri = Uri.parse("http://instagram.com/_u/" + handle);
            instagramIntent = new Intent(Intent.ACTION_VIEW, uri);
            instagramIntent.setPackage("com.instagram.android");
            startActivity(instagramIntent);
        } catch (Exception e) {
            uri = Uri.parse("http://instagram.com/" + handle);
            instagramIntent = new Intent(Intent.ACTION_VIEW, uri);
            if (instagramIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(instagramIntent);
            } else {
                Toast.makeText(this, "Não foi possível abrir o Instagram. Verifique o aplicativo ou conexão.", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void dialPhoneNumber(String phoneNumber) {
        try {
            Intent dialIntent = new Intent(Intent.ACTION_DIAL);
            dialIntent.setData(Uri.parse("tel:" + phoneNumber));
            if (dialIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(dialIntent);
            } else {
                Toast.makeText(this, "Nenhum aplicativo de telefone encontrado.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Erro ao tentar discar: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
