package com.example.ml_2;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;



public class CharacterAdapter  extends RecyclerView.Adapter<CharacterAdapter.ViewHolder>{
    private final recycleRevievInterface recycleRevievInterface;
    private final LayoutInflater inflater;
    Context context;
    private final List<Character> Characters;


    CharacterAdapter(Context context, List<Character> Characters, recycleRevievInterface recycleRevievInterface) {
        this.context = context;
        this.Characters = Characters;
        this.recycleRevievInterface = recycleRevievInterface;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public CharacterAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item_course, parent, false);
        return new ViewHolder(view, recycleRevievInterface);
    }
    @Override
    public void onBindViewHolder(CharacterAdapter.ViewHolder holder, int position) {
        Character state = Characters.get(position);
            Glide
                    .with(context)
                    .load(state.getPictureResourse())
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(holder.imageView);

        holder.nameView.setText(state.getName());
    }

    @Override
    public int getItemCount() {
        return Characters.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        final TextView nameView;
        ViewHolder(View view, recycleRevievInterface recycleRevievInterface){
            super(view);
            nameView = view.findViewById(R.id.name);
            imageView = view.findViewById(R.id.picture);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recycleRevievInterface != null){
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            recycleRevievInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
