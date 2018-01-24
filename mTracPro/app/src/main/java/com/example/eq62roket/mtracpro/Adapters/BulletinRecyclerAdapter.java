package com.example.eq62roket.mtracpro.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eq62roket.mtracpro.Helpers.Bulletin;
import com.example.eq62roket.mtracpro.R;

import java.util.ArrayList;


/**
 * Created by eq62roket on 1/23/18.
 */

public class BulletinRecyclerAdapter extends RecyclerView.Adapter<BulletinRecyclerAdapter.BulletinViewHolder>{
    private ArrayList<Bulletin> bulletinArrayList;

    public BulletinRecyclerAdapter(ArrayList<Bulletin> bulletinArrayList) {
        this.bulletinArrayList = bulletinArrayList;
    }
    @Override
    public BulletinRecyclerAdapter.BulletinViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View bulletinView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_bulletin, parent, false);

        return new BulletinRecyclerAdapter.BulletinViewHolder(bulletinView);
    }

    @Override
    public void onBindViewHolder(BulletinRecyclerAdapter.BulletinViewHolder holder, int position) {
        Bulletin bulletin = bulletinArrayList.get(position);
        holder.content.setText(bulletin.getContent());
        holder.date.setText(bulletin.getDate());

    }

    @Override
    public int getItemCount() {
        return bulletinArrayList.size();
    }

    public class BulletinViewHolder extends RecyclerView.ViewHolder{
        private TextView id, content, date;
        public BulletinViewHolder(View itemView) {
            super(itemView);
            content = (TextView) itemView.findViewById(R.id.bContent);
            date = (TextView) itemView.findViewById(R.id.bDate);
        }
    }

    public void setFilter(ArrayList<Bulletin> listBulletin){
        bulletinArrayList = new ArrayList<>();
        bulletinArrayList.addAll(listBulletin);
        notifyDataSetChanged();
    }
}
