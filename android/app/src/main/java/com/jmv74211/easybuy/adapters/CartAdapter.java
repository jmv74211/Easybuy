package com.jmv74211.easybuy.adapters;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jmv74211.easybuy.R;
import com.jmv74211.easybuy.models.Cart;
import com.jmv74211.easybuy.models.Product;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartListViewHolder> {

  private Context context;
  private Cart cart;
  private OnCardListener onCardListener;

  ////////////////////////////////////////////////////////////////////////////////////////////////

  public CartAdapter(Context context, Cart cart, OnCardListener onCardListener) {
    this.context = context;
    this.cart = cart;
    this.onCardListener = onCardListener;
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////

  @NonNull
  @Override
  public CartListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    LayoutInflater inflater = LayoutInflater.from(context);
    View view = inflater.inflate(R.layout.card_product, null);
    CartListViewHolder holder = new CartListViewHolder(view, this.onCardListener);

    return holder;
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////

  @Override
  public void onBindViewHolder(@NonNull CartListViewHolder holder, int position) {

    Pair<Product, Integer> item = this.cart.getProducts().get(position);
    Product p = item.first;
    int quantity = item.second;

    holder.textProductName.setText(p.getName());
    holder.textQuantity.setText("X" + quantity);

    int resourceId = context.getResources().getIdentifier("product_" + String.valueOf(p.getId()), "drawable", context.getPackageName());

    if (resourceId != 0)
      holder.imageView.setImageResource(resourceId);
    else
      holder.imageView.setImageResource(context.getResources().getIdentifier("product_unavailable", "drawable", context.getPackageName()));

    holder.textPrice.setText(p.getPrice() * quantity + "€");
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////

  @Override
  public int getItemCount() {
    return this.cart.getProducts().size();
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////

/*  public void deleteItem(int position) {

    this.cart.getProducts().remove(position);

    ShoppingList shoppingList = Data.getShoppingList();
    shoppingList.setCartProducts(cartProducts);
    HashMap<String, Object> mapInformation = shoppingList.toMap();

    successDeleteCartProduct();

    collectionReference.document(Data.getShoppingList().getId()).update(mapInformation).addOnSuccessListener(new OnSuccessListener<Void>() {

      @Override
      public void onSuccess(Void aVoid) {

      }
    }).addOnFailureListener(new OnFailureListener() {
      @Override
      public void onFailure(@NonNull Exception e) {
        errorDeleteCartProduct();
      }
    });

    notifyDataSetChanged();

  }
*/

  ////////////////////////////////////////////////////////////////////////////////////////////////

  class CartListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView imageView;
    TextView textProductName, textQuantity, textPrice, cartPrice;
    OnCardListener onCardListener;

    public CartListViewHolder(@NonNull final View itemView, OnCardListener onCardListener) {
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

  public void successDeleteCartProduct() {
    StyleableToast.makeText(context, context.getResources().getText(R.string.productDeletedSuccess).toString(),
            Toast.LENGTH_LONG, R.style.toastSuccessAddProduct).show();
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////

  public void errorDeleteCartProduct() {
    StyleableToast.makeText(context, context.getResources().getText(R.string.productDeletedError).toString(),
            Toast.LENGTH_LONG, R.style.toastErrorAddProduct).show();
  }

}