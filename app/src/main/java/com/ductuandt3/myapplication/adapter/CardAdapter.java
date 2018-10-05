package com.ductuandt3.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ductuandt3.myapplication.R;
import com.ductuandt3.myapplication.entity.CardEntity;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder>{
    Context mContext;
    ArrayList<CardEntity> cardEntityArrayList;

    public CardAdapter(Context mContext, ArrayList<CardEntity> cardEntityArrayList) {
        this.mContext = mContext;
        this.cardEntityArrayList = cardEntityArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_card_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
//        holder.mImageCard.setImageResource(cardEntityArrayList.get(position).get);
        holder.mTxtCardNumber.setText(cardEntityArrayList.get(position).getmCardNumber());
        holder.mTxtDate.setText("Date: " + cardEntityArrayList.get(position).getmDate());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.d("AAAAA", "onClick:position  " + position);
            }

        });

    }

    @Override
    public int getItemCount() {
        return cardEntityArrayList.size();
    }
    
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView mImageCard;
        private TextView mTxtCardNumber;
        private TextView mTxtDate;
        private ItemClickListener itemClickListener;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mImageCard = itemView.findViewById(R.id.list_item_image_card);
            mTxtCardNumber = itemView.findViewById(R.id.list_item_card_number);
            mTxtDate = itemView.findViewById(R.id.list_item_date);
        }

        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition());
        }
    }
}
