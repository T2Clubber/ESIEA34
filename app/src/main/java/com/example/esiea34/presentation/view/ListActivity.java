package com.example.esiea34.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.SingleLineTransformationMethod;
import android.widget.Toast;

import com.example.esiea34.Constants;
import com.example.esiea34.R;
import com.example.esiea34.Singletons;
import com.example.esiea34.presentation.controller.MainController;
import com.example.esiea34.presentation.model.Pokemon;

import java.util.List;

public class ListActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private ListAdapteur mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_activity);

        controller = new MainController(
                this,
                Singletons.getGson(),
                Singletons.getSharedPreferencesInstance(getApplicationContext())
        );
        controller.onStart();

    }

    public void showList(List<Pokemon> pokemonList) {

        recyclerView = (RecyclerView) findViewById(R.id.list_activity);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

       /* List<String> input = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            input.add("Test" + i);
        }
        */

        // define an adapter
        mAdapter = new ListAdapteur(pokemonList, new ListAdapteur.OnItemClickListener() {
            @Override
            public void onItemClick(Pokemon item) {
                controller.onItemClick(item);
            }
        });
        recyclerView.setAdapter(mAdapter);

    }

    public void showError() {
        Toast.makeText(getApplicationContext(),"API Error", Toast.LENGTH_SHORT).show();
    }

    public void navigateToDetails(Pokemon pokemon) {
        Intent myIntent = new Intent(ListActivity.this, DetailsActivity.class);
    myIntent.putExtra(Constants.KEY_POKEMON, Singletons.getGson().toJson(pokemon));
        ListActivity.this.startActivity(myIntent);
    }
}



