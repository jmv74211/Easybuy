package com.jmv74211.easybuy.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jmv74211.easybuy.DBInfo.ShoppingListDBInfo;
import com.jmv74211.easybuy.Data.Data;
import com.jmv74211.easybuy.POJO.CartProduct;
import com.jmv74211.easybuy.POJO.Product;
import com.jmv74211.easybuy.POJO.ShoppingList;
import com.jmv74211.easybuy.R;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.ArrayList;
import java.util.HashMap;


public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.CartListViewHolder> {

    private Context context;
    private ArrayList<CartProduct> cartProducts;
    private OnCardListener onCardListener;

    private ShoppingListDBInfo shoppingListDBInfo = ShoppingListDBInfo.getInstance();

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection(shoppingListDBInfo.getCollectionName());

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public CartListAdapter(Context context, ArrayList<CartProduct> shoppingListCartProducts, OnCardListener onCardListener) {
        this.context = context;
        this.cartProducts = shoppingListCartProducts;
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


    public void deleteItem(int position) {

        //roduct product = cartProducts.get(position).getProduct();

        //System.out.println("EN DELETE PRODUCT VALE = " + product.toString());
        System.out.println("EN DELETE ANTES = " + cartProducts.toString());

        //cartProducts.remove(product);

        cartProducts.remove(position);

        System.out.println("EN DELETE DESPUES = " + cartProducts.toString());

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

    public void successDeleteCartProduct(){
        StyleableToast.makeText(context,context.getResources().getText(R.string.productDeletedSuccess).toString(),
                Toast.LENGTH_LONG,R.style.toastSuccessAddProduct).show();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void errorDeleteCartProduct(){
        StyleableToast.makeText(context,context.getResources().getText(R.string.productDeletedError).toString(),
                Toast.LENGTH_LONG,R.style.toastErrorAddProduct).show();
    }


}
