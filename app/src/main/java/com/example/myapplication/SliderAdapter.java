package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.MyViewHolder> {

    List<CardInfo> cardList;

    public SliderAdapter(List<CardInfo> list) {
        this.cardList = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.front.setText(cardList.get(position).getWord());
        holder.back.setText(cardList.get(position).getDefinition());
        // e tu set back o day
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView front;
        TextView back;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            front = itemView.findViewById(R.id.card_front);
            back = itemView.findViewById(R.id.card_back);

        }
    }
}
