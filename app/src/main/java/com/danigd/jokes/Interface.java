package com.danigd.jokes;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Interface {
        @GET("jokes/random")
        Call <Chiste> getChiste();
    }
