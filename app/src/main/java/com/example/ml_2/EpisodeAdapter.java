package com.example.ml_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.ViewHolder> {
    Context context;
    private final String[] episode;
    private final LayoutInflater inflater;

    public EpisodeAdapter(Context context, String[] episode){
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.episode = episode;
    }
    @NonNull
    @Override
    public EpisodeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.detail_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeAdapter.ViewHolder holder, int position) {
        holder.textView.setText(episode[position]);
    }

    @Override
    public int getItemCount() {
        return episode.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        final TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.episText);
        }
    }
}
