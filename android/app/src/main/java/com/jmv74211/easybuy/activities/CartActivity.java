package com.jmv74211.easybuy.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jmv74211.easybuy.R;
import com.jmv74211.easybuy.models.Cart;
import com.jmv74211.easybuy.adapters.CartAdapter;

public class CartActivity extends AppCompatActivity {

  private Toolbar appbar;
  private FloatingActionButton fab;
  private RecyclerView recyclerView;
  private TextView cartPriceText;
  private Cart cart;
  private CartAdapter adapter;

  private FirebaseFirestore db = FirebaseFirestore.getInstance();
  private CollectionReference cartReference = db.collection("carts");

  // -----------------------------------------------------------------------------------------------

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cart);

    appbar = findViewById(R.id.app_bar);
    fab = findViewById(R.id.fab);
    cartPriceText = findViewById(R.id.shoppingListNumberPrice);

    setSupportActionBar(appbar);
    getSupportActionBar().setTitle(getResources().getString(R.string.productList));

    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(getApplicationContext(), String.valueOf(cart.getCartPrice()),
                Toast.LENGTH_SHORT).show();
      }
    });

    setUpRecyclerView();

  }

  // -----------------------------------------------------------------------------------------------

  @Override
  protected void onStart() {
    super.onStart();
    startListening();

  }

  // -----------------------------------------------------------------------------------------------

  @Override
  protected void onStop() {
    super.onStop();
  }

  // -----------------------------------------------------------------------------------------------

  private void setUpRecyclerView() {

    this.adapter = new CartAdapter(this, cart);
    recyclerView = findViewById(R.id.recyclerView);
    recyclerView.setHasFixedSize(true); // performance reasons
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(adapter);
  }

  // -----------------------------------------------------------------------------------------------

  private void loadData() {
    System.out.println("Load data");

    cartReference.get()
            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
              @Override
              public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                  cart = documentSnapshot.toObject(Cart.class);
                  adapter.setCart(cart);
                  adapter.notifyDataSetChanged();
                }
              }
            });
  }

  // -----------------------------------------------------------------------------------------------

  private void startListening() {
    cartReference.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
      @Override
      public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {
        if (e != null) {
          return;
        }
        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
          cart = documentSnapshot.toObject(Cart.class);
          adapter.setCart(cart);
          adapter.notifyDataSetChanged();
          cartPriceText.setText(String.valueOf(cart.getCartPrice()) +"€");
        }
      }
    });
  }

  // -----------------------------------------------------------------------------------------------

}



