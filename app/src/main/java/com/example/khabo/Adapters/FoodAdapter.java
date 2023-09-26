package com.example.khabo.Adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.khabo.Models.FoodModel;
import com.example.khabo.R;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.viewholder> {
    ArrayList<FoodModel> list;
    Context context;

    public FoodAdapter(ArrayList<FoodModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_foodlist,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        final FoodModel model= list.get(position);
        holder.fdimage.setImageResource(model.getImage());
        holder.name.setText(model.getName());
        holder.price.setText(model.getPrice());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        ImageView fdimage;
        TextView name,price;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            fdimage=itemView.findViewById(R.id.imageView);
            name=itemView.findViewById(R.id.name);
            price=itemView.findViewById(R.id.order_price);
        }
    }
}
