package com.danigd.jokes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cargarJson();

        /*TabHost host = getTabHost();
        host.addTab(host.newTabSpec("aleatorio").setIndicator("TAB1").setContent(new Intent(this, Tab1Activity.class))););
        host.addTab(host.newTabSpec("category").setIndicator("TAB2"));*/
    }
    private void cargarJson(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.chucknorris.io")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Interface request = retrofit.create(Interface.class);
        Call<Chiste> call = request.getChiste();

        call.enqueue(new Callback<Chiste>() {
            @Override
            public void onResponse(Call<Chiste> call, Response<Chiste> response) {
                tv = findViewById(R.id.tv);
                Chiste chiste = response.body();
                tv.setText(chiste.getValue());
            }


            @Override
            public void onFailure(Call<Chiste> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
}
