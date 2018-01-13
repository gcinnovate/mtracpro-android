package com.example.eq62roket.mtracpro.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eq62roket.mtracpro.Activities.History;
import com.example.eq62roket.mtracpro.R;

import java.util.ArrayList;

/**
 * Created by eq62roket on 1/11/18.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.HistoryViewHolder>{

    ArrayList<History> arrayList = new ArrayList<>();

    public RecyclerAdapter(ArrayList<History> arrayList){
        this.arrayList = arrayList;
    }
    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);
        HistoryViewHolder historyViewHolder = new HistoryViewHolder(view);
        return historyViewHolder;
    }

    @Override
    public void onBindViewHolder(HistoryViewHolder holder, int position) {
        holder.Id.setText(arrayList.get(position).getId());
        holder.RawMsg.setText(arrayList.get(position).getRawMsg());
        holder.Detail.setText(arrayList.get(position).getDetail());
        holder.Date.setText(arrayList.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder{
        TextView Id, RawMsg, Detail, Date;
        public HistoryViewHolder(View itemView) {
            super(itemView);
//            Id = (TextView)itemView.findViewById(R.id.id);
//            RawMsg = (TextView)itemView.findViewById(R.id.rawMsg);
//            Detail = (TextView)itemView.findViewById(R.id.details);
//            Date = (TextView)itemView.findViewById(R.id.date);
        }
    }
}
