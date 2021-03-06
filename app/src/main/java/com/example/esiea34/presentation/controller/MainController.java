package com.example.esiea34.presentation.controller;

import android.content.SharedPreferences;

import com.example.esiea34.Singletons;
import com.example.esiea34.Constants;
import com.example.esiea34.presentation.model.Pokemon;
import com.example.esiea34.presentation.model.RestPokemonResponse;
import com.example.esiea34.presentation.view.ListActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainController {

    private SharedPreferences sharedPreferences;
    private Gson gson;
    private ListActivity view;

    public MainController(ListActivity list_activity, Gson gson, SharedPreferences sharedPreferences) {
        this.view = list_activity;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
    }

    public void onStart() {

        List<Pokemon> pokemonList = getDataFromCache();

        if(pokemonList != null) {
            view.showList(pokemonList);
        } else {
            makeApiCall();
        }

    }
    private void makeApiCall() {

        Call<RestPokemonResponse> call = Singletons.getPokeApi().getPokemonResponse();

        call.enqueue(new Callback<RestPokemonResponse>() {

            @Override
            public void onResponse(Call<RestPokemonResponse> call, Response<RestPokemonResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<Pokemon> pokemonList = response.body().getResults();
                    // Toast.makeText(getApplicationContext(),"API Success", Toast.LENGTH_SHORT).show();
                    saveList(pokemonList);
                    view.showList(pokemonList);

                } else {
                    view.showError();
                }
            }

            @Override
            public void onFailure(Call<RestPokemonResponse> call, Throwable t) {
                view.showError();
            }
        });

    }

    private void saveList(List<Pokemon> pokemonList) {

        String jsonString = gson.toJson(pokemonList);

        sharedPreferences
                .edit()
                .putString(Constants.KEY_POKEMON_LIST, jsonString)
                .apply();

    }

    private List<Pokemon> getDataFromCache() {

        String jsonPokemon = sharedPreferences.getString(Constants.KEY_POKEMON_LIST, null);
        Type listType = new TypeToken<List<Pokemon>>(){}.getType();

        if (jsonPokemon == null){
            return null;
        } else {

            return gson.fromJson(jsonPokemon, listType);
        }
    }

    public void onItemClick(Pokemon pokemon) {
        view.navigateToDetails(pokemon);

    }

    public void onButtonAClick() {

    }

    public void onButtonBClick() {

    }

}
