package com.example.calculador.appdsm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "ContactPrefs";
    private static final String CONTACTS_KEY = "contacts";
    private static final String TAG = "MainActivity";
    private LinearLayout containerContatos;
    private ActivityResultLauncher<Intent> cadastroActivityResultLauncher;
    private static final int STATIC_CONTACT_COUNT = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        containerContatos = findViewById(R.id.container_contatos);

        setupActivityResultLauncher();
        setupStaticClickListeners();
        loadAndDisplayContacts();
    }

    private void setupActivityResultLauncher() {
        cadastroActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Intent data = result.getData();
                        String name = data.getStringExtra("EXTRA_CONTACT_NAME");
                        String email = data.getStringExtra("EXTRA_CONTACT_EMAIL");
                        String password = data.getStringExtra("EXTRA_CONTACT_PASSWORD");
                        String instagram = data.getStringExtra("EXTRA_CONTACT_INSTAGRAM");
                        String phone = data.getStringExtra("EXTRA_CONTACT_PHONE");


                        Log.d(TAG, "Número de telefone recebido da CadastroActivity: " + (phone != null ? phone : "nulo"));

                        saveContact(name, email, password, instagram, phone);
                        loadAndDisplayContacts();
                        Toast.makeText(this, "Novo contato '" + name + "' adicionado!", Toast.LENGTH_SHORT).show();
                    } else if (result.getResultCode() == RESULT_CANCELED) {
                        Toast.makeText(this, "Cadastro cancelado.", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    private void setupStaticClickListeners() {
        findViewById(R.id.Novoctt).setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Abrindo tela de cadastro...", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
            cadastroActivityResultLauncher.launch(intent);
        });
        findViewById(R.id.arthur).setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Abrindo Dados (Arthur)...", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("NAME", "Arthur Mansur Barros");
            intent.putExtra("EMAIL", "barrosarthur312@gmail.com");
            intent.putExtra("INSTAGRAM", "@_bigbarros");
            intent.putExtra("PHONE", "11948334209");
            startActivity(intent);
        });
        findViewById(R.id.kormann).setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Abrindo Dados (Kormann)...", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("NAME", "Matheus Menezes Kormann");
            intent.putExtra("EMAIL", "kormannmatheus@gmail.com");
            intent.putExtra("INSTAGRAM", "@_thekrma");
            intent.putExtra("PHONE", "11985349066");
            startActivity(intent);
        });
        findViewById(R.id.miguel).setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Abrindo Dados (Miguel)...", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("NAME", "Miguel Wilians Ferreira");
            intent.putExtra("EMAIL", "migmib55@gmail.com");
            intent.putExtra("INSTAGRAM", "@_mwillianns");
            intent.putExtra("PHONE", "11986054376");
            startActivity(intent);
        });
    }
    private void saveContact(String name, String email, String password, String instagram, String phone) {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String contactsJson = prefs.getString(CONTACTS_KEY, "[]");
        try {
            JSONArray jsonArray = new JSONArray(contactsJson);
            JSONObject newContact = new JSONObject();
            newContact.put("name", name);
            newContact.put("email", email);
            newContact.put("password", password);
            newContact.put("instagram", instagram);
            newContact.put("phone", phone != null ? phone : "");
            jsonArray.put(newContact);
            prefs.edit().putString(CONTACTS_KEY, jsonArray.toString()).apply();
        } catch (JSONException e) {
            Toast.makeText(this, "Erro ao salvar contato.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
    private List<JSONObject> loadContacts() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String contactsJson = prefs.getString(CONTACTS_KEY, "[]");
        List<JSONObject> contacts = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(contactsJson);
            for (int i = 0; i < jsonArray.length(); i++) {
                contacts.add(jsonArray.getJSONObject(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return contacts;
    }
    private void loadAndDisplayContacts() {
        while (containerContatos.getChildCount() > STATIC_CONTACT_COUNT) {
            containerContatos.removeViewAt(STATIC_CONTACT_COUNT);
        }
        List<JSONObject> contacts = loadContacts();
        for (JSONObject contact : contacts) {
            try {
                addDynamicContactTextView(
                        contact.getString("name"),
                        contact.getString("email"),
                        contact.getString("password"),
                        contact.getString("instagram"),
                        contact.optString("phone", "Não Informado")
                );
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    private void addDynamicContactTextView(String name, String email, String password, String instagram, String phone) {
        TextView contactTextView = new TextView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 0, 0, dpToPx(19));

        contactTextView.setLayoutParams(params);
        contactTextView.setText(name);
        contactTextView.setTextSize(25);
        contactTextView.setTextColor(getResources().getColor(R.color.white));
        contactTextView.setBackgroundColor(getResources().getColor(R.color.arthur));
        contactTextView.setGravity(Gravity.CENTER);
        contactTextView.setTypeface(null, Typeface.BOLD);
        contactTextView.setClickable(true);
        contactTextView.setFocusable(true);

        contactTextView.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Abrindo Dados (" + name + ")...", Toast.LENGTH_SHORT).show();
            Intent detailIntent = new Intent(MainActivity.this, DetailActivity.class);
            detailIntent.putExtra("NAME", name);
            detailIntent.putExtra("EMAIL", email);
            detailIntent.putExtra("INSTAGRAM", instagram);
            detailIntent.putExtra("PHONE", phone);
            startActivity(detailIntent);
        });
        containerContatos.addView(contactTextView);
    }
    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }
}
