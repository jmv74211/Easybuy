package com.jmv74211.easybuy.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jmv74211.easybuy.R;
import com.jmv74211.easybuy.data.SettingsData;
import com.jmv74211.easybuy.data.ThemeData;
import com.jmv74211.easybuy.models.Product;
import com.jmv74211.easybuy.tools.Theme;

import java.util.ArrayList;
import java.util.List;

public class ProductSearchAdapter extends RecyclerView.Adapter<ProductSearchAdapter.ProductSearchViewHolder> implements Filterable {

  private Context context;
  private ArrayList<Product> products;
  private OnCardListener onCardListener;
  private ArrayList<Product> productsFullList;
  private Filter filter;

   // -----------------------------------------------------------------------------------------------

   public ProductSearchAdapter(Context context, ArrayList<Product> productList, OnCardListener onCardListener) {
    this.context = context;
    this.products = productList;
    this.onCardListener = onCardListener;
    this.productsFullList = new ArrayList<Product>(productList);
  }

   // -----------------------------------------------------------------------------------------------

   @NonNull
   @Override
   public ProductSearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
     LayoutInflater inflater = LayoutInflater.from(context);
     View view = inflater.inflate(R.layout.card_product, null);
     ProductSearchViewHolder holder = new ProductSearchViewHolder(view, onCardListener);

     return holder;
   }

   // -----------------------------------------------------------------------------------------------

   @Override
   public void onBindViewHolder(@NonNull ProductSearchViewHolder holder, int position) {
     Product product = this.products.get(position);
     String quantity = product.getQuantity();
     Theme theme = ThemeData.getInstance(context).getTheme(SettingsData.getInstance(context).getTheme());

     holder.textProductName.setText(product.getName());
     holder.textQuantity.setText(": " + quantity);
     holder.textQuantity.setTextColor(Color.parseColor(theme.getColorPrimaryDark()));

     int resourceId = context.getResources().getIdentifier("product_" +
             String.valueOf(product.getId()), "drawable", context.getPackageName());

     if (resourceId != 0)
       holder.productImage.setImageResource(resourceId);
     else
       holder.productImage.setImageResource(context.getResources().
               getIdentifier("product_unavailable", "drawable", context.getPackageName()));

     holder.textPrice.setText(product.getPrice() + SettingsData.getInstance(context).getCurrency());
     holder.textPrice.setTextColor(Color.parseColor(theme.getColorAccent()));
   }

   // -----------------------------------------------------------------------------------------------

   @Override
   public int getItemCount() {
     if (this.products == null)
       return 0;

     return this.products.size();
   }

   // -----------------------------------------------------------------------------------------------

   @Override
   public Filter getFilter() {
    setFilter();
    return this.filter;
   }

   // -----------------------------------------------------------------------------------------------

   private void setFilter(){
    this.filter = new Filter() {
      @Override
      protected FilterResults performFiltering(CharSequence constraint) {
        ArrayList<Product> filteredList = new ArrayList<Product>();

        if (constraint == null || constraint.length() == 0) {
          filteredList.addAll(productsFullList);
        } else {
          String filterPattern = constraint.toString().toLowerCase().trim();

          for (Product item : productsFullList) {

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
   }

   // -----------------------------------------------------------------------------------------------

   class ProductSearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

      ImageView productImage;
      TextView textProductName, textQuantity, textPrice;
      OnCardListener onCardListener;

       // -----------------------------------------------------------------------------------------------

      public ProductSearchViewHolder(@NonNull final View itemView, OnCardListener onCardListener) {
        super(itemView);

        productImage = itemView.findViewById(R.id.productImageView);
        textProductName = itemView.findViewById(R.id.textProductName);
        textQuantity = itemView.findViewById(R.id.textNumberQuantity);
        textPrice = itemView.findViewById(R.id.textViewPrice);

        this.onCardListener = onCardListener;

        itemView.setOnClickListener(this);
      }

      // -----------------------------------------------------------------------------------------------

      @Override
      public void onClick(View v) {
        onCardListener.onCardClick(getAdapterPosition());
      }
    }

    // -----------------------------------------------------------------------------------------------

    public interface OnCardListener {
      void onCardClick(int position);
    }

   // -----------------------------------------------------------------------------------------------

}
