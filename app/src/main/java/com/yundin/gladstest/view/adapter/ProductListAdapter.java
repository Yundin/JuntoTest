package com.yundin.gladstest.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yundin.gladstest.R;
import com.yundin.gladstest.model.dto.Product;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Yundin Vladislav
 */
public class ProductListAdapter extends RecyclerView.Adapter {

    private List<Product> data;
    private LayoutInflater inflater;
    private View.OnClickListener onClickListener;

    public ProductListAdapter(Context context, List<Product> data, View.OnClickListener listener) {
        this.data = data;
        this.inflater = LayoutInflater.from(context);
        this.onClickListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.product_card, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ProductViewHolder myHolder = (ProductViewHolder) holder;
        Product item = data.get(position);

        myHolder.titleTextView.setText(item.getTitle());
        myHolder.descriptionTextView.setText(item.getDescription());
        myHolder.votesTextView.setText(String.valueOf(item.getVotes()));

        myHolder.itemView.setTag(item.getTitle());
        myHolder.itemView.setOnClickListener(onClickListener);

        Glide
                .with(inflater.getContext())
                .load(item.getThumbnail().getImageUrl())
                .into(myHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void updateDataWithNotify(List<Product> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView imageView;
        @BindView(R.id.title)
        TextView titleTextView;
        @BindView(R.id.description)
        TextView descriptionTextView;
        @BindView(R.id.votes)
        TextView votesTextView;

        public ProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
