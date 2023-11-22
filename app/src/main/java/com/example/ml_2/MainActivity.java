package com.example.ml_2;

import static com.google.android.material.internal.ContextUtils.getActivity;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;


import android.content.Intent;
import android.util.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements recycleRevievInterface{

    public static final String URL_base = "https://rickandmortyapi.com/";
    public CharactersList Chars = new CharactersList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBRepository rep = new DBRepository(getApplication());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_base)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        API_interface apiInterface = retrofit.create(API_interface.class);

        Call<CharactersList> call = apiInterface.getInfo();

        if(IsOnline()) {
            call.enqueue(new Callback<CharactersList>() {
                @Override
                public void onResponse(Call<CharactersList> call, Response<CharactersList> response) {
                    if (!response.isSuccessful()) {
                        Log.d("onResponse().", "Code: " + response.code());
                        return;
                    }
                    Chars = response.body();
                    configureRecyclerView();
                    //загрузка данных в бд
                    rep.ClearAll();
                    for (int i = 0; i < Chars.CharacterList.size(); i++) {
                        rep.Insert(Chars.CharacterList.get(i));
                    }

                }

                @Override
                public void onFailure(Call<CharactersList> call, Throwable t) {
                    Log.d("onFailure(): ", t.getMessage(), t);
                }
            });
        }
        else {
            Chars.CharacterList = rep.getAllChars();
            configureRecyclerView();
        }
    }

    protected boolean IsOnline(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }


    private void configureRecyclerView() {
        CharacterAdapter adapter = new CharacterAdapter(this, Chars.CharacterList, this);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, Detail.class);

        intent.putExtra("ID",Chars.CharacterList.get(position).getId());
        intent.putExtra("NAME", Chars.CharacterList.get(position).getName());
        intent.putExtra("IMAGE", Chars.CharacterList.get(position).getPictureResourse());
        intent.putExtra("DESCRIPTION", Chars.CharacterList.get(position).getDescription());


        startActivity(intent);
    }


}
