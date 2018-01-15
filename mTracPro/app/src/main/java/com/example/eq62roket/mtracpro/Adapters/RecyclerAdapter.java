package com.example.eq62roket.mtracpro.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eq62roket.mtracpro.Activities.HistoryActivity;
import com.example.eq62roket.mtracpro.Helpers.History;
import com.example.eq62roket.mtracpro.R;

import java.util.ArrayList;

/**
 * Created by eq62roket on 1/11/18.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private ArrayList<History> arrayList;

    public RecyclerAdapter(ArrayList<History> arrayList, HistoryActivity historyActivity) {
        this.arrayList = arrayList;
    }

    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item, parent, false);

        return new RecyclerAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.MyViewHolder holder, int position) {
        History history = arrayList.get(position);
        holder.Id.setText(history.getId());
        holder.RawMsg.setText(history.getRawMsg());
        holder.Detail.setText(history.getDetail());
        holder.Date.setText(history.getDate());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Id, RawMsg, Detail, Date;

        public MyViewHolder(View view) {
            super(view);
            Id = (TextView) view.findViewById(R.id.id);
            RawMsg = (TextView) view.findViewById(R.id.rawMsg);
            Detail = (TextView) view.findViewById(R.id.detail);
            Date = (TextView) view.findViewById(R.id.date);
        }
    }
}
