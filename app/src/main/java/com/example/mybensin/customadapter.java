package com.example.mybensin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class customadapter extends RecyclerView.Adapter<customadapter.MyViewHolder> {

    private Context context;
    private ArrayList id,jarak,efis,jeben,fcost,fneed;

    customadapter(Context context,ArrayList id,ArrayList jarak,ArrayList efis,ArrayList jeben,ArrayList fcost,ArrayList fneed)
    {
        this.context=context;
        this.id=id;
        this.jarak=jarak;
        this.efis=efis;
        this.jeben=jeben;
        this.fcost=fcost;
        this.fneed=fneed;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.iddatabase.setText(String.valueOf(id.get(position)));
        holder.jarak.setText(String.valueOf(jarak.get(position)));
        holder.efi.setText(String.valueOf(efis.get(position)));
        holder.jben.setText(String.valueOf(jeben.get(position)));
        holder.cobe.setText(String.valueOf(fcost.get(position)));
        holder.nb.setText(String.valueOf(fneed.get(position)));
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView iddatabase,jarak,efi,jben,cobe,nb;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iddatabase=itemView.findViewById(R.id.idku);
            jarak =itemView.findViewById(R.id.jarakt);
            efi = itemView.findViewById(R.id.efisien);
            jben = itemView.findViewById(R.id.jenisb);
            cobe = itemView.findViewById(R.id.costfu);
            nb = itemView.findViewById(R.id.needfu);
        }
    }
}
