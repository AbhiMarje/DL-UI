package com.example.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HelperAdapter extends RecyclerView.Adapter<HelperAdapter.ViewHolder> {

    ArrayList<String> mname;
    ArrayList<String> mdate;
    ArrayList<String> mexpiry;
    ArrayList<String> mtitle;
    Context context;

    public HelperAdapter(ArrayList<String> mtitle, ArrayList<String> mname, ArrayList<String> mdate, ArrayList<String> mexpiry,Context context) {
        this.mtitle = mtitle;
        this.mname = mname;
        this.mdate = mdate;
        this.mexpiry = mexpiry;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HelperAdapter.ViewHolder holder, int position) {
        holder.name.setText(mname.get(position));
        holder.date.setText(mdate.get(position));
        holder.expiry.setText(mexpiry.get(position));
        holder.title.setText(mtitle.get(position));
    }

    @Override
    public int getItemCount() {
        return mname.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name, date, expiry, title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date);
            expiry = itemView.findViewById(R.id.expiry);
            title = itemView.findViewById(R.id.title);
        }
    }
}
