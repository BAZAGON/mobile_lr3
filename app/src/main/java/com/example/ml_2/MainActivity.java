package com.example.ml_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.DividerItemDecoration;
import android.os.Bundle;
import java.util.ArrayList;
import android.content.Intent;
import android.util.Log;


public class MainActivity extends AppCompatActivity implements recycleRevievInterface{

    ArrayList<Character> Character = new ArrayList<Character>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setInitialData();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        // создаем адаптер
        CharacterAdapter adapter = new CharacterAdapter(MainActivity.this, Character, this);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);
    }

    private void setInitialData() {

        Character.add(new Character("Рик Санчес", R.drawable.rick, getResources().getString(R.string.Rick_desc)));
        Character.add(new Character("Морти Смит", R.drawable.morty, getResources().getString(R.string.Rick_desc)));
        Character.add(new Character("Бет Смит", R.drawable.beth,getResources().getString(R.string.Rick_desc)));
        Character.add(new Character("Джерри Смит", R.drawable.jerry,getResources().getString(R.string.Rick_desc)));
        Character.add(new Character("Саммер Смит", R.drawable.summer,getResources().getString(R.string.Rick_desc)));
    }
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, Detail.class);

        intent.putExtra("NAME", Character.get(position).getName());
        intent.putExtra("IMAGE", Character.get(position).getPictureResourse());
        intent.putExtra("DESCRIPTION", Character.get(position).getDescription());


        startActivity(intent);
    }

}
