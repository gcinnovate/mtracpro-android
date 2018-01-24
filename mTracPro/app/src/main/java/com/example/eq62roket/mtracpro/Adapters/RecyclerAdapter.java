package com.example.eq62roket.mtracpro.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.eq62roket.mtracpro.Helpers.History;
import com.example.eq62roket.mtracpro.R;

import java.util.ArrayList;

/**
 * Created by eq62roket on 1/11/18.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{
    private ArrayList<History> arrayList;

    public RecyclerAdapter(ArrayList<History> arrayList) {
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
        holder.period.setText(history.getPeriod());
        holder.rawMsg.setText(history.getRawMsg());
        holder.detail.setText(history.getDetails());
        holder.date.setText(history.getDate());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView rawMsg, detail, date, period;

        private MyViewHolder(View view) {
            super(view);
            period = (TextView) view.findViewById(R.id.period);
            rawMsg = (TextView) view.findViewById(R.id.rawMsg);
            detail = (TextView) view.findViewById(R.id.detail);
            date = (TextView) view.findViewById(R.id.date);
        }
    }

    public void setFilter(ArrayList<History> listHistory){
        arrayList = new ArrayList<>();
        arrayList.addAll(listHistory);
        notifyDataSetChanged();
    }
}
