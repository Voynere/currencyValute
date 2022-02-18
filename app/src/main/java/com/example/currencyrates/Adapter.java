package com.example.currencyrates;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {
    HashMap<Integer, ObjectValute> list;
    Context context;

    public Adapter(HashMap<Integer, ObjectValute> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rv_item = LayoutInflater.from(context).inflate(R.layout.rv_item,parent,false);
        Holder h = new Holder(rv_item);


        return h;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
Log.e("pos","pos"+position);
        holder.title.setText(list.get(position).title);
        holder.name.setText(list.get(position).name);
        holder.value.setText(""+list.get(position).value);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView title,name,value;

        public Holder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            name = itemView.findViewById(R.id.name);
            value = itemView.findViewById(R.id.value);
        }
    }
}
