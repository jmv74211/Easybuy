package com.jmv74211.easybuy.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jmv74211.easybuy.POJO.CartProduct;
import com.jmv74211.easybuy.POJO.Product;
import com.jmv74211.easybuy.R;

import java.util.ArrayList;


public class ProductShoppingListAdapter extends RecyclerView.Adapter<ProductShoppingListAdapter.ShoppingListProductViewHolder> {

    private Context context;
    private ArrayList<CartProduct> cartProducts;
    private OnCardListener onCardListener;

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public ProductShoppingListAdapter(Context context, ArrayList<CartProduct> shoppingListCartProducts, OnCardListener onCardListener) {
        this.context = context;
        this.cartProducts = shoppingListCartProducts;
        this.onCardListener = onCardListener;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @NonNull
    @Override
    public ShoppingListProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_product, null);
        ShoppingListProductViewHolder holder = new ShoppingListProductViewHolder(view, this.onCardListener);

        return holder;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onBindViewHolder(@NonNull ShoppingListProductViewHolder holder, int position) {

        CartProduct cp = cartProducts.get(position);
        Product p = cartProducts.get(position).getProduct();

        holder.textProductName.setText(p.getName());
        holder.textQuantity.setText("X" + cp.getQuantity());
        holder.imageView.setImageResource(context.getResources().getIdentifier("product_" + String.valueOf(p.getId()), "drawable", context.getPackageName()));
        holder.textPrice.setText(p.getPrice() * cp.getQuantity() + "€");


    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public int getItemCount() {
        return this.cartProducts.size();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    class ShoppingListProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView textProductName, textQuantity, textPrice, cartPrice;
        OnCardListener onCardListener;

        public ShoppingListProductViewHolder(@NonNull final View itemView, OnCardListener onCardListener) {
            super(itemView);

            imageView = itemView.findViewById(R.id.productImageView);
            textProductName = itemView.findViewById(R.id.textProductName);
            textQuantity = itemView.findViewById(R.id.textNumberQuantity);
            textPrice = itemView.findViewById(R.id.textViewPrice);
            cartPrice = itemView.findViewById(R.id.shoppingListNumberPrice);

            this.onCardListener = onCardListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onCardListener.onCardClick(getAdapterPosition());
        }
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////

    // Recyclerview click interface
    public interface OnCardListener {
        void onCardClick(int position);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

}