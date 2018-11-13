package com.example.lobster.superior.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lobster.superior.R;
import com.example.lobster.superior.model.Goods;

import java.util.List;


public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder> {
    private static final String TAG = "ShoppingCartAdapter";
    private Context mContext;
    private List<Goods> mGoodsList;
    private OnRecyclerViewItemDeleteClickListener itemDeleteClickListener;
    private OnRecyclerViewItemAddClickListener itemAddClickListener;
    private OnRecyclerViewItemReduceClickListener itemReduceClickListener;

    public ShoppingCartAdapter(Context mContext, List<Goods> mGoodsList, OnRecyclerViewItemDeleteClickListener itemDeleteClickListener, OnRecyclerViewItemAddClickListener itemAddClickListener, OnRecyclerViewItemReduceClickListener itemReduceClickListener) {
        this.mContext = mContext;
        this.mGoodsList = mGoodsList;
        this.itemDeleteClickListener = itemDeleteClickListener;
        this.itemAddClickListener = itemAddClickListener;
        this.itemReduceClickListener = itemReduceClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.shoppingcart_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.d(TAG, "位置: " + position);
        final Goods goods = mGoodsList.get(position);
        //save date in tag of itemView，for later use
        holder.itemView.setTag(goods);
        holder.goods_order.setText(String.valueOf(position + 1) + ".");
        holder.goods_name.setText(goods.getName());
        holder.goods_number.setText(goods.getNumber());
        holder.goods_price.setText(" $ " + goods.getPrice());
        holder.goods_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemDeleteClickListener.onItemDeleteClick(v, goods);
            }
        });
        holder.goods_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemAddClickListener.onItemAddClick(v, position);
            }
        });
        holder.goods_reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemReduceClickListener.onItemReduceClick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mGoodsList != null && mGoodsList.size() > 0) {
            return mGoodsList.size();
        }
        return 0;
    }

    public interface OnRecyclerViewItemDeleteClickListener {
        void onItemDeleteClick(View view, Goods goods);
    }

    public interface OnRecyclerViewItemAddClickListener {
        void onItemAddClick(View view, int position);
    }

    public interface OnRecyclerViewItemReduceClickListener {
        void onItemReduceClick(View view, int position);
    }


    //define viewHolder  to hold every item's interface elements
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView goods_order;
        public TextView goods_name;
        public ImageView market_image;
        public TextView goods_number;
        public TextView goods_price;
        public ImageButton goods_delete;
        public ImageButton goods_add;
        public ImageButton goods_reduce;

        public ViewHolder(View view) {
            super(view);
            goods_order = (TextView) view.findViewById(R.id.goods_order);
            goods_name = (TextView) view.findViewById(R.id.goods_name);
            market_image = (ImageView) view.findViewById(R.id.market_image);
            goods_number = (TextView) view.findViewById(R.id.goods_number);
            goods_price = (TextView) view.findViewById(R.id.goods_price);
            goods_delete = (ImageButton) view.findViewById(R.id.goods_delete);
            goods_add = (ImageButton) view.findViewById(R.id.goods_add);
            goods_reduce = (ImageButton) view.findViewById(R.id.goods_reduce);
            ;
        }
    }
}
