package com.jmv74211.easybuy.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jmv74211.easybuy.R;
import com.jmv74211.easybuy.data.CartData;
import com.jmv74211.easybuy.data.Data;
import com.jmv74211.easybuy.data.SettingsData;
import com.jmv74211.easybuy.data.ThemeData;
import com.jmv74211.easybuy.models.Cart;
import com.jmv74211.easybuy.adapters.CartAdapter;
import com.jmv74211.easybuy.tools.Theme;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.HashMap;

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
    Data.getInstance(this).reloadData(); // load AppData
    setContentView(R.layout.activity_cart);
    setUpViews();
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


  private void setUpSwipeTouch() {
    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
      @Override
      public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder
              viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return false;
      }

      @Override
      public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        deleteItem(position);
      }
    };

    new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
  }

  // -----------------------------------------------------------------------------------------------

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.settings_appbar, menu);
    return true;
  }

  // -----------------------------------------------------------------------------------------------

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.settings:
        goToSettingsActivity();
        break;

    }

    return super.onOptionsItemSelected(item);
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
          cart.setId(documentSnapshot.getId());
          CartData.getInstance(getApplicationContext()).setCartData(cart);
          adapter.setCart(CartData.getInstance(getApplicationContext()).getCartData());
          adapter.notifyDataSetChanged();
          cartPriceText.setText(String.valueOf(cart.getCartPrice()) +
                  SettingsData.getInstance(getApplicationContext()).getCurrency());
        }
      }
    });
  }

  // -----------------------------------------------------------------------------------------------

  private void setUpViews() {
    setUpAppbar();

    // SettingsData.getInstance(this).loadData();

    setUpButtons();

    setUpRecyclerView();

    setUpSwipeTouch();
  }

  // -----------------------------------------------------------------------------------------------


  private void setUpAppbar() {
    Theme theme = ThemeData.getInstance(getApplicationContext()).
            getTheme(SettingsData.getInstance(getApplicationContext()).getTheme());

    appbar = findViewById(R.id.app_bar);

    setSupportActionBar(appbar);
    getSupportActionBar().setTitle(getResources().getString(R.string.productList));
    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(
            theme.getColorPrimary())));

    if (Build.VERSION.SDK_INT >= 21) {
      getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
      getWindow().setStatusBarColor(Color.parseColor(theme.getColorPrimaryDark()));
    }
  }


  // -----------------------------------------------------------------------------------------------

  private void setUpButtons() {
    Theme theme = ThemeData.getInstance(getApplicationContext()).
            getTheme(SettingsData.getInstance(getApplicationContext()).getTheme());
    int colorAccent = Color.parseColor(theme.getColorAccent());

    fab = findViewById(R.id.fab);
    fab.setBackgroundTintList(ColorStateList.valueOf(colorAccent));
    cartPriceText = findViewById(R.id.shoppingListNumberPrice);
    cartPriceText.setTextColor(colorAccent);

    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        goToSectionActivity();
      }
    });
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

  private void deleteItem(int position) {
    cart.getProducts().remove(position);
    CartData.getInstance(getApplicationContext()).setCartData(cart);

    HashMap<String, Object> mapInformation = cart.toMap();

    cartReference.document(cart.getId()).update(mapInformation).addOnSuccessListener(new OnSuccessListener<Void>() {
      @Override
      public void onSuccess(Void aVoid) {
        successDeleteCartProduct();
        adapter.notifyDataSetChanged();
      }
    }).addOnFailureListener(new OnFailureListener() {
      @Override
      public void onFailure(@NonNull Exception e) {
        errorDeleteCartProduct();
      }
    });
  }

  // -----------------------------------------------------------------------------------------------

  private void successDeleteCartProduct() {
    StyleableToast.makeText(this, this.getResources().getText(R.string.productDeletedSuccess).toString(),
            Toast.LENGTH_LONG, R.style.toastSuccessAddProduct).show();
  }

  // -----------------------------------------------------------------------------------------------

  private void errorDeleteCartProduct() {
    StyleableToast.makeText(this, this.getResources().getText(R.string.productDeletedError).toString(),
            Toast.LENGTH_LONG, R.style.toastErrorAddProduct).show();
  }

  // -----------------------------------------------------------------------------------------------


  private void goToSectionActivity() {
    Intent intent = new Intent(this, SectionActivity.class);
    startActivity(intent);
  }

  // -----------------------------------------------------------------------------------------------

  private void goToSettingsActivity() {
    Intent intent = new Intent(this, SettingsActivity.class);
    startActivity(intent);
  }

  // -----------------------------------------------------------------------------------------------

}



