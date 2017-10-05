package com.example.deepakrattan.sqlitedatabasedemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by deepak.rattan on 10/5/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<SingleRow> singleRowArrayList;

    public MyAdapter(Context context, ArrayList<SingleRow> singleRowArrayList) {
        this.context = context;
        this.singleRowArrayList = singleRowArrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SingleRow singleRow = singleRowArrayList.get(position);
        holder.txtName.setText(singleRow.getName());
        holder.txtPhone.setText(singleRow.getPhone());
    }

    @Override
    public int getItemCount() {
        return singleRowArrayList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName, txtPhone;


        public MyViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtPhone = (TextView) itemView.findViewById(R.id.txtPhone);
        }
    }


}
