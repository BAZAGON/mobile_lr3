package com.example.ml_2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        EpisodeAdapter episodeAdapter = new EpisodeAdapter(Detail.this, new String[0]);
        recyclerView.setAdapter(episodeAdapter);

        String name = getIntent().getStringExtra("NAME");
        String Image = getIntent().getStringExtra("IMAGE");
        String[] description = getIntent().getStringArrayExtra("DESCRIPTION");

        TextView Dname = findViewById(R.id.DetName);
        ImageView Dimage = findViewById(R.id.DetImage);

        Glide
                .with(this)
                .load(Image)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(Dimage);

        Dname.setText(name);

        new Detail.MyAsyncTask(description,recyclerView).execute();
    }
        private class MyAsyncTask extends AsyncTask<String, Void, String[]> {

            String[] des;
            RecyclerView recyclerView;

            public MyAsyncTask(String[] des, RecyclerView recyclerView) {
                this.des = des;
                this.recyclerView = recyclerView;
            }

            @Override
            protected String[] doInBackground(String... urls) {
                String[] res = new String[des.length];
                OkHttpClient client = new OkHttpClient();

                JSONArray array2 = null;
                try {
                    array2 = new JSONArray(des);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                for (int j = 0; j < array2.length(); j++) {

                    Request request1 = null;
                    try {
                        request1 = new Request.Builder()
                                .url(array2.getString(j))
                                .build();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    try (Response response2 = client.newCall(request1).execute()) {
                        JSONObject jsonResponse2 = new JSONObject(response2.body().string());
                        res[j] = jsonResponse2.getString("name");
                        res[j] += " ";
                        res[j] += jsonResponse2.getString("air_date");
                        res[j] += " ";
                        res[j] += jsonResponse2.getString("episode");
                    } catch (IOException | JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                return res;
            }
            protected void onPostExecute(String[] res) {
                EpisodeAdapter adapter = new EpisodeAdapter(Detail.this, res);
                recyclerView.setAdapter(adapter);
            }

        }

}


