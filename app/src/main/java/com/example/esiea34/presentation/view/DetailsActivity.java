package com.example.esiea34.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.esiea34.Constants;
import com.example.esiea34.R;
import com.example.esiea34.Singletons;
import com.example.esiea34.presentation.model.Pokemon;

public class DetailsActivity extends AppCompatActivity {

    private TextView txtDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_activity);

        txtDetail = findViewById(R.id.detail_txt);
        Intent intent = getIntent();
        String pokemonJson = intent.getStringExtra(Constants.KEY_POKEMON);
        Pokemon pokemon = Singletons.getGson().fromJson(pokemonJson, Pokemon.class);

        showDetails(pokemon);
    }

    private void showDetails(Pokemon pokemon) {
        txtDetail.setText(pokemon.getName());
    }
}
