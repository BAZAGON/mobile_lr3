package com.example.ml_2;

import static com.google.android.material.internal.ContextUtils.getActivity;

import com.google.gson.annotations.SerializedName;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.util.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements recycleRevievInterface{

    public static final String URL_base = "https://rickandmortyapi.com/";
    List<Character> Characters = new ArrayList<Character>();
    CharactersList Chars;
    DBRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_base)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        API_interface apiInterface = retrofit.create(API_interface.class);

        Call<CharactersList> call = apiInterface.getInfo();

        call.enqueue(new Callback<CharactersList>() {
            @Override
            public void onResponse(Call<CharactersList> call, Response<CharactersList> response) {
                if(!response.isSuccessful()){
                    Log.d("onResponse().", "Code: " + response.code());
                    return;
                }
                Chars = response.body();
                loadIntoDB(Chars,repository);
                SetData();
                RecyclerView recyclerView = configureRecyclerView();

            }
            @Override
            public void onFailure(Call<CharactersList> call, Throwable t) {
                Log.d("onFailure(): ", t.getMessage(), t);
            }
        });

    }

    public void loadIntoDB(CharactersList list, DBRepository rep){
        rep.ClearAll();

        for(int i = 0; i < list.CharacterList.size(); i++){
            rep.Insert(list.CharacterList.get(i));
        }
    }

    private void SetData(){
        String name, photo;
        String[] desk;
        for(int i = 0; i < Chars.CharacterList.size(); i++){
            name = Chars.CharacterList.get(i).getName();
            photo = Chars.CharacterList.get(i).getPictureResourse();
            desk = Chars.CharacterList.get(i).getDescription();

            Characters.add(new Character(name,photo,desk));
        }
    }

    private RecyclerView configureRecyclerView() {
        CharacterAdapter adapter = new CharacterAdapter(this, Characters, this);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);

        return recyclerView;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, Detail.class);

        intent.putExtra("NAME", Characters.get(position).getName());
        intent.putExtra("IMAGE", Characters.get(position).getPictureResourse());
        intent.putExtra("DESCRIPTION", Characters.get(position).getDescription());


        startActivity(intent);
    }

}
