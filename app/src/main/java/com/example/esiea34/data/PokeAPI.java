package com.example.esiea34.data;

import com.example.esiea34.presentation.model.RestPokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;


public interface PokeAPI {

     @GET("api/v2/pokemon")
    Call<RestPokemonResponse> getPokemonResponse();

}
