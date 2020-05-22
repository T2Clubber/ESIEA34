package com.example.esiea34;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface PokeAPI {

    @GET("api/v2/pokemon")
    Call<RestPokemonResponse> getPokemonResponse();

}
