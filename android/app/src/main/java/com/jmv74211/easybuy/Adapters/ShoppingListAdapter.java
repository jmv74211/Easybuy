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
import com.jmv74211.easybuy.POJO.ShoppingList;
import com.jmv74211.easybuy.R;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ShoppingListViewHolder> {

    private Context context;
    private ArrayList<ShoppingList> shoppingList;
    private OnCardListener onCardListener;

    private static ShoppingListDBInfo shoppingListDBInfo = ShoppingListDBInfo.getInstance();

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection(shoppingListDBInfo.getCollectionName());

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public ShoppingListAdapter(Context context, ArrayList<ShoppingList> shoppingList, OnCardListener onCardListener) {
        this.context = context;
        this.shoppingList = shoppingList;
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
        holder.textViewProducts.setText(String.valueOf(sl1.getNumProducts()) + " productos");
        holder.textViewPrice.setText(sl1.calculatePrice() + " €");

        int numParticipants = sl1.getNumParticipants();
        holder.textViewParticipants.setText(sl1.getNumParticipants() + "/10");

        for (int i = numParticipants; i < 10; i++) {
            holder.participantsImages[i].setVisibility(View.GONE);
        }

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
        CircleImageView[] participantsImages;
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

            participantsImages = new CircleImageView[10];

            participantsImages[0] = (CircleImageView) itemView.findViewById(R.id.participant1);
            participantsImages[1] = (CircleImageView) itemView.findViewById(R.id.participant2);
            participantsImages[2] = (CircleImageView) itemView.findViewById(R.id.participant3);
            participantsImages[3] = (CircleImageView) itemView.findViewById(R.id.participant4);
            participantsImages[4] = (CircleImageView) itemView.findViewById(R.id.participant5);
            participantsImages[5] = (CircleImageView) itemView.findViewById(R.id.participant6);
            participantsImages[6] = (CircleImageView) itemView.findViewById(R.id.participant7);
            participantsImages[7] = (CircleImageView) itemView.findViewById(R.id.participant8);
            participantsImages[8] = (CircleImageView) itemView.findViewById(R.id.participant9);
            participantsImages[9] = (CircleImageView) itemView.findViewById(R.id.participant10);

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

    public void deleteItem(int position) {

        String id = shoppingList.get(position).getId();

        shoppingList.remove(position);

        successDeleteShoppingList();

        collectionReference.document(id).delete().addOnSuccessListener(new OnSuccessListener<Void>() {

            @Override
            public void onSuccess(Void aVoid) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                errorDeleteShoppingList();
            }
        });

        notifyDataSetChanged();

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void successDeleteShoppingList(){
        StyleableToast.makeText(context,context.getResources().getText(R.string.shoppingListDeletedSuccess).toString(),
                Toast.LENGTH_LONG,R.style.toastSuccessAddProduct).show();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void errorDeleteShoppingList(){
        StyleableToast.makeText(context,context.getResources().getText(R.string.shoppingListDeletedError).toString(),
                Toast.LENGTH_LONG,R.style.toastErrorAddProduct).show();
    }

}
