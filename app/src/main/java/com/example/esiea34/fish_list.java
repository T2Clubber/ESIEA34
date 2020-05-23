package com.example.esiea34;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class fish_list extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapteur mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish_list);


       makeApiCall();
    }

    private void showList(List<Pokemon> pokemonList) {

        recyclerView = (RecyclerView) findViewById(R.id.fishes);

        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

       /* List<String> input = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            input.add("Test" + i);
        }
        */

        // define an adapter
        mAdapter = new ListAdapteur(pokemonList);
        recyclerView.setAdapter(mAdapter);

    }

    private static final String BASE_URL = "https://pokeapi.co/";

    private void makeApiCall() {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            PokeAPI pokeApi = retrofit.create(PokeAPI.class);

            Call<RestPokemonResponse> call = pokeApi.getPokemonResponse();

            call.enqueue(new Callback<RestPokemonResponse>() {

                @Override
                public void onResponse(Call<RestPokemonResponse> call, Response<RestPokemonResponse> response) {
                    if(response.isSuccessful() && response.body() != null){
                        List<Pokemon> pokemonList = response.body().getResults();
                       // Toast.makeText(getApplicationContext(),"API Success", Toast.LENGTH_SHORT).show();
                        showList(pokemonList);
                    } else {
                        showError();
                    }
                }

                @Override
                public void onFailure(Call<RestPokemonResponse> call, Throwable t) {
                    showError();
                }
            });

        }

    private void showError() {
        Toast.makeText(getApplicationContext(),"API Error", Toast.LENGTH_SHORT).show();
    }

}



