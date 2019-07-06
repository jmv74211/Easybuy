package com.jmv74211.easybuy.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.jmv74211.easybuy.POJO.Product;
import com.jmv74211.easybuy.R;

import java.util.ArrayList;
import java.util.List;


public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ShoppingListProductViewHolder> implements Filterable {

    private Context context;
    private ArrayList<Product> products;
    private OnCardListener onCardListener;

    private ArrayList<Product> productsFull;

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public ProductListAdapter(Context context, ArrayList<Product> productList, OnCardListener onCardListener) {
        this.context = context;
        this.products = productList;
        this.onCardListener = onCardListener;
        this.productsFull = new ArrayList<Product>(productList);

    }

    // Built this method because in the constructor products and productsFull is null, until is updated
    // asynchronously with adapter.notifyDataSetChanged(), wich only modifies producs, so it is
    // neccesary to call this method to set fullList
    public void updateFullList(ArrayList<Product> fullList){
            this.productsFull = fullList;
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

        Product p = products.get(position);

        holder.textProductName.setText(p.getName());
        holder.textQuantity.setText(p.getQuantity());
        holder.imageView.setImageResource(context.getResources().getIdentifier("product_" + String.valueOf(p.getId()), "drawable", context.getPackageName()));
        holder.textPrice.setText(p.getPrice() + "€");

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public int getItemCount() {
        return this.products.size();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public Filter getFilter() {
        return productsFilter;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private Filter productsFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Product> filteredList = new ArrayList<Product>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(productsFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Product item : productsFull) {

                    if (item.getName().toLowerCase().trim().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            products.clear();
            products.addAll( (List) results.values);
            notifyDataSetChanged();
        }
    };


    ////////////////////////////////////////////////////////////////////////////////////////////////

    class ShoppingListProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView textProductName, textQuantity, textPrice;
        OnCardListener onCardListener;

        public ShoppingListProductViewHolder(@NonNull final View itemView, OnCardListener onCardListener) {
            super(itemView);

            imageView = itemView.findViewById(R.id.productImageView);
            textProductName = itemView.findViewById(R.id.textProductName);
            textQuantity = itemView.findViewById(R.id.textNumberQuantity);
            textPrice = itemView.findViewById(R.id.textViewPrice);

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
