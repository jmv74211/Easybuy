package com.jmv74211.easybuy.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.jmv74211.easybuy.Adapters.CartListAdapter;
import com.jmv74211.easybuy.DBInfo.ShoppingListDBInfo;
import com.jmv74211.easybuy.Data.Data;
import com.jmv74211.easybuy.POJO.CartProduct;
import com.jmv74211.easybuy.POJO.ShoppingList;
import com.jmv74211.easybuy.R;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class CartListActivity extends AppCompatActivity implements CartListAdapter.OnCardListener {

    private Toolbar appbar;
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private TextView cartPriceText;

    private ArrayList<CartProduct> cartProducts = new ArrayList<>();
    private ShoppingList shoppingList;

    private CartListAdapter adapter;

    private ShoppingListDBInfo shoppingListDBInfo = ShoppingListDBInfo.getInstance();

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection(shoppingListDBInfo.getCollectionName());

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_shopping_list);

        appbar = (Toolbar) findViewById(R.id.app_bar);
        fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recyclerView);
        cartPriceText = findViewById(R.id.shoppingListNumberPrice);

        setSupportActionBar(appbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.productList));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSectionListActivity();
            }
        });

        shoppingList = Data.getShoppingList();
        cartProducts = shoppingList.getCartProducts();

        cartPriceText.setText(String.valueOf(shoppingList.calculatePrice()) +"€");

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new CartListAdapter(this, cartProducts, this);

        recyclerView.setAdapter(adapter);

        // SWIPE MOVE TO DELETE A PRODUCT CART
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);

        collectionReference.whereEqualTo(FieldPath.documentId(),shoppingList.getId()).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if (e != null) {
                    return;
                }

                for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {
                    if (doc.getType() == DocumentChange.Type.MODIFIED) {
                        ShoppingList sh = doc.getDocument().toObject(ShoppingList.class);
                        cartProducts = sh.getCartProducts();
                        adapter.notifyDataSetChanged();
                    }

                }

                cartPriceText.setText(String.valueOf(shoppingList.calculatePrice()) +"€");

            }
        });


    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void goToSectionListActivity(){

        Intent intent = new Intent(getApplication(),SectionListActivity.class);
        startActivity(intent);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onCardClick(int position) {

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    // When the user go back, it is forced to create homeActivity to update shoppingListData
 /*   @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
    }*/

    ////////////////////////////////////////////////////////////////////////////////////////////////

    // SWIPE CALLBACK
    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            int position = viewHolder.getAdapterPosition();
            adapter.deleteItem(position);
        }
    };

    ////////////////////////////////////////////////////////////////////////////////////////////////

}



