package com.example.calculador.appdsm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CadastroActivity extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextPassword, editTextInstagram, editTextPhone;
    private Button buttonCadastrar, buttonVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        editTextName = findViewById(R.id.edit_text_name);
        editTextEmail = findViewById(R.id.edit_text_email);
        editTextPassword = findViewById(R.id.edit_text_password);
        editTextInstagram = findViewById(R.id.edit_text_instagram);
        editTextPhone = findViewById(R.id.edit_text_phone);
        buttonCadastrar = findViewById(R.id.button_cadastrar);
        buttonVoltar = findViewById(R.id.button_voltar_cadastro);

        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String instagram = editTextInstagram.getText().toString().trim();
                String phone = editTextPhone.getText().toString().trim();
                if (name.isEmpty() || email.isEmpty() || password.isEmpty() || instagram.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(CadastroActivity.this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
                } else {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("EXTRA_CONTACT_NAME", name);
                    resultIntent.putExtra("EXTRA_CONTACT_EMAIL", email);
                    resultIntent.putExtra("EXTRA_CONTACT_PASSWORD", password);
                    resultIntent.putExtra("EXTRA_CONTACT_INSTAGRAM", instagram);
                    resultIntent.putExtra("EXTRA_CONTACT_PHONE", phone);
                    setResult(RESULT_OK, resultIntent);
                    Toast.makeText(CadastroActivity.this, "Usuário " + name + " cadastrado!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
        buttonVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}
