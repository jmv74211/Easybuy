package com.jmv74211.easybuy.adapters;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jmv74211.easybuy.R;
import com.jmv74211.easybuy.models.Cart;
import com.jmv74211.easybuy.models.Product;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

  private Context context;
  private Cart cart = new Cart();

  // -----------------------------------------------------------------------------------------------

  public CartAdapter(Context context, Cart cart) {
    this.context = context;
    this.cart = cart;
  }

  // -----------------------------------------------------------------------------------------------

  @NonNull
  @Override
  public CartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    LayoutInflater inflater = LayoutInflater.from(context);
    View view = inflater.inflate(R.layout.card_product, null);
    CartViewHolder holder = new CartViewHolder(view);

    return holder;
  }

  // -----------------------------------------------------------------------------------------------

  @Override
  public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {

    Product product = this.cart.getProducts().get(position).getProduct();
    int quantity = this.cart.getProducts().get(position).getQuantity();

    holder.textProductName.setText(product.getName());
    holder.textQuantity.setText("X" + quantity);

    int resourceId = context.getResources().getIdentifier("product_" +
            String.valueOf(product.getId()), "drawable", context.getPackageName());

    if (resourceId != 0)
      holder.productImage.setImageResource(resourceId);
    else
      holder.productImage.setImageResource(context.getResources().
              getIdentifier("product_unavailable", "drawable", context.getPackageName()));

    holder.textPrice.setText(product.getPrice() * quantity + "€");


  }

  // -----------------------------------------------------------------------------------------------

  @Override
  public int getItemCount() {
    if (this.cart == null)
      return 0;

    return this.cart.getProducts().size();
  }

  // -----------------------------------------------------------------------------------------------

  class CartViewHolder extends RecyclerView.ViewHolder {

    ImageView productImage;
    TextView textProductName, textQuantity, textPrice, cartPrice;

    public CartViewHolder(@NonNull final View itemView) {
      super(itemView);

      productImage = itemView.findViewById(R.id.productImageView);
      textProductName = itemView.findViewById(R.id.textProductName);
      textQuantity = itemView.findViewById(R.id.textNumberQuantity);
      textPrice = itemView.findViewById(R.id.textViewPrice);
      cartPrice = itemView.findViewById(R.id.shoppingListNumberPrice);
    }
  }

  // -----------------------------------------------------------------------------------------------

  public void setCart(Cart cart) {
    this.cart = cart;
  }

  // -----------------------------------------------------------------------------------------------

}
