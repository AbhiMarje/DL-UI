package com.example.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class HelperAdapter extends RecyclerView.Adapter<HelperAdapter.ViewHolder> {

    Activity activity;
    ArrayList<String> mname;
    ArrayList<String> mdate;
    ArrayList<String> mexpiry;
    ArrayList<String> mtitle;
    Context context;


    public HelperAdapter(Activity activity,ArrayList<String> mtitle, ArrayList<String> mname, ArrayList<String> mdate, ArrayList<String> mexpiry, Context context) {
        this.activity = activity;
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.BottomSheetStyle);
                View view = LayoutInflater.from(context).inflate(R.layout.bottom_sheet,
                        (LinearLayout) holder.itemView.findViewById(R.id.lsheet));

                TextView btitle, bname, bdate, bexpiry;
                btitle = view.findViewById(R.id.btitle);
                bname = view.findViewById(R.id.bname);
                bdate = view.findViewById(R.id.bdate);
                bexpiry = view.findViewById(R.id.bexpiry);
                btitle.setText(mtitle.get(position));
                bname.setText(mname.get(position));
                bexpiry.setText(mexpiry.get(position));
                bdate.setText(mdate.get(position));

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        bottomSheetDialog.getBehavior().setPeekHeight(activity.findViewById(R.id.cl).getHeight());
                        bottomSheetDialog.setContentView(view);
                        bottomSheetDialog.show();
                    }
                }, 700);
            }
        });

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
