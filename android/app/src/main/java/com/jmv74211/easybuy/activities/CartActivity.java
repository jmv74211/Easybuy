package com.jmv74211.easybuy.activities;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jmv74211.easybuy.R;
import com.jmv74211.easybuy.adapters.CartAdapter;
import com.jmv74211.easybuy.models.Cart;
import com.jmv74211.easybuy.models.Product;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity implements CartAdapter.OnCardListener {

  private Toolbar appbar;
  private FloatingActionButton fab;
  private RecyclerView recyclerView;
  private TextView cartPriceText;
  private Cart cart;
  private CartAdapter adapter;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cart);

    appbar = (Toolbar) findViewById(R.id.app_bar);
    fab = findViewById(R.id.fab);
    recyclerView = findViewById(R.id.recyclerView);
    cartPriceText = findViewById(R.id.shoppingListNumberPrice);

    setSupportActionBar(appbar);
    getSupportActionBar().setTitle(getResources().getString(R.string.productList));

    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(getApplicationContext(), String.valueOf(cart.getCartPrice()), Toast.LENGTH_SHORT).show();
        System.out.println(cart);
      }
    });

    Product p1 = new Product(1, "Mermelada de melocotón", 1.05f, "440g", 1);
    Product p2 = new Product(2, "Leche semidesnatada", 0.58f, "1L", 1);

    List<Pair<Product, Integer>> data = new ArrayList<>();
    data.add(new Pair<Product, Integer>(p1, 1));
    data.add(new Pair<Product, Integer>(p2, 1));

    this.cart = new Cart(data);
    cartPriceText.setText(String.valueOf(cart.getCartPrice()) + "€");

    recyclerView.setHasFixedSize(true);

    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    adapter = new CartAdapter(this, cart, this);

    recyclerView.setAdapter(adapter);

  }

  @Override
  public void onCardClick(int position) {

  }

}



