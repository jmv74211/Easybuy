package com.jmv74211.easybuy.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jmv74211.easybuy.POJO.ShoppingList;
import com.jmv74211.easybuy.R;

import java.util.ArrayList;


public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ShoppingListViewHolder> {

    private Context context;
    private ArrayList<ShoppingList> shoppingList;
    private OnCardListener onCardListener;

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public ShoppingListAdapter(Context context, ArrayList<ShoppingList> shoppingList, OnCardListener onCardListener) {
        this.context = context;
        this.shoppingList= shoppingList;
        this.onCardListener = onCardListener;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @NonNull
    @Override
    public ShoppingListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_shopping_list, null);
        ShoppingListViewHolder holder = new ShoppingListViewHolder(view, this.onCardListener);

        return holder;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onBindViewHolder(@NonNull ShoppingListViewHolder holder, int position) {
        ShoppingList sl1 = shoppingList.get(position);

        holder.textViewDate.setText(sl1.getDate());
        holder.textViewTime.setText(sl1.getTime());
        holder.imageView.setImageResource(context.getResources().getIdentifier("mercadona_logo", "drawable", context.getPackageName()));
        holder.TextViewNameList.setText(sl1.getName());
        holder.textViewParticipants.setText(sl1.getNumParticipants()+"/10");
        holder.textViewProducts.setText(sl1.getNumParticipants()+"/10");
        holder.textViewPrice.setText(sl1.getPrice()+" €");


    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public int getItemCount() {
        return this.shoppingList.size();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    class ShoppingListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView textViewDate, textViewTime, TextViewNameList, textViewParticipants, textViewProducts, textViewPrice;
        OnCardListener onCardListener;

        public ShoppingListViewHolder(@NonNull final View itemView, OnCardListener onCardListener) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewTime = itemView.findViewById(R.id.textViewTime);
            TextViewNameList = itemView.findViewById(R.id.textListName);
            textViewParticipants = itemView.findViewById(R.id.textViewParticipants);
            textViewProducts = itemView.findViewById(R.id.textViewProducts);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);

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
