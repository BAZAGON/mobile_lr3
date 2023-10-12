package com.example.ml_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
public class Detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

       String name = getIntent().getStringExtra("NAME");
       int Image = getIntent().getIntExtra("IMAGE",0);
       String description = getIntent().getStringExtra("DESCRIPTION");

       TextView Dname = findViewById(R.id.DetName);
       ImageView Dimage = findViewById(R.id.DetImage);
       TextView Descrip = findViewById(R.id.DetDescrip);

       Dname.setText(name);
       Dimage.setImageResource(Image);
       Descrip.setText(description);
    }
}


