package com.jmv74211.easybuy.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import com.jmv74211.easybuy.R;
import com.jmv74211.easybuy.adapters.ProductSearchAdapter;
import com.jmv74211.easybuy.data.CartData;
import com.jmv74211.easybuy.data.ProductData;
import com.jmv74211.easybuy.data.SectionData;
import com.jmv74211.easybuy.data.SettingsData;
import com.jmv74211.easybuy.data.ThemeData;
import com.jmv74211.easybuy.dialogs.DialogAddProduct;
import com.jmv74211.easybuy.models.Cart;
import com.jmv74211.easybuy.models.Product;
import com.jmv74211.easybuy.models.ProductSelection;
import com.jmv74211.easybuy.tools.Theme;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductSearchActivity extends AppCompatActivity implements ProductSearchAdapter.OnCardListener,
        DialogAddProduct.DialogAddProductListener{

  private Toolbar appbar;
  private RecyclerView recyclerView;
  private ProductSearchAdapter adapter;
  private Theme theme;
  private ArrayList<Product> products = new ArrayList<>();

  private FirebaseFirestore db = FirebaseFirestore.getInstance();
  private CollectionReference cartReference = db.collection("carts");

  // -----------------------------------------------------------------------------------------------

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_product_search);

    int sectionId = getIntentData(getIntent());
    loadData(sectionId);

    setUpViews(sectionId);
  }

  // -----------------------------------------------------------------------------------------------

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.search_appbar, menu);

    MenuItem searchItem = menu.findItem(R.id.action_search);
    SearchView searchView = (SearchView) searchItem.getActionView();

    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String s) {
        return false;
      }

      @Override
      public boolean onQueryTextChange(String s) {

        adapter.getFilter().filter(s);
        return false;
      }
    });

    return true;
  }

  // -----------------------------------------------------------------------------------------------

  @Override
  public void onCardClick(int position){
    openDialogAddProduct(products.get(position).getId());
  }

  // -----------------------------------------------------------------------------------------------

  @Override
  public void applyData(int productId, int quantity) {
    addProductToCart(productId, quantity);
  }

  // -----------------------------------------------------------------------------------------------

  private int getIntentData(Intent intent) {
    int sectionId = (int) ((HashMap<String, Object>) intent.getSerializableExtra("hashMap"))
            .get("sectionId");

    return sectionId;
  }

  // -----------------------------------------------------------------------------------------------

  private void setUpViews(int sectionId){
    setUpAppBar(sectionId);
    setUpRecyclerview(sectionId);
  }

  // -----------------------------------------------------------------------------------------------

  private void setUpAppBar(int sectionId) {
    appbar = findViewById(R.id.app_bar);

    SectionData sectionData = SectionData.getInstance(this);
    String sectionName = sectionData.getSection(sectionId).trim();

    setSupportActionBar(appbar);

    getSupportActionBar().setTitle(sectionName);

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(
            theme.getColorPrimary())));

    if (Build.VERSION.SDK_INT >= 21) {
      getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
      getWindow().setStatusBarColor(Color.parseColor(theme.getColorPrimaryDark()));
    }
  }

  // -----------------------------------------------------------------------------------------------

  private void setUpRecyclerview(int sectionId) {
    recyclerView = findViewById(R.id.recyclerView);

    recyclerView.setHasFixedSize(true);

    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    adapter = new ProductSearchAdapter(this, this.products, this);

    recyclerView.setAdapter(adapter);
  }

  // -----------------------------------------------------------------------------------------------

  private void addProductToCart(int productId, int quantity){
    Cart cart = CartData.getInstance(getApplicationContext()).getCartData();

    if(cart.containsProduct(productId)){
      cart.getProducts().get(cart.getProductPosition(productId)).sumQuantity(quantity);
      updateDatabase(cart);
    }
    else{
      cart.addProduct(new ProductSelection(ProductData.getInstance(this).getProduct(productId), quantity));
      updateDatabase(cart);
    }
  }

  // -----------------------------------------------------------------------------------------------

  private void loadData(int sectionId){
    ProductData productData = ProductData.getInstance(this);
    theme = ThemeData.getInstance(getApplicationContext()).
            getTheme(SettingsData.getInstance(getApplicationContext()).getTheme());

    if(sectionId == 0){
      this.products =  productData.getAllproducts();
    }
    else{
     this.products = productData.getProducts(sectionId);
    }
  }

  // -----------------------------------------------------------------------------------------------

  private void updateDatabase(Cart cart) {
    cartReference.document(cart.getId()).update(cart.toMap()).addOnSuccessListener(new OnSuccessListener<Void>() {

      @Override
      public void onSuccess(Void aVoid) {
        successAddProductToast();
      }
    }).addOnFailureListener(new OnFailureListener() {
      @Override
      public void onFailure(@NonNull Exception e) {
        errorAddProductToast();
      }
    });
  }


  // -----------------------------------------------------------------------------------------------

  private void openDialogAddProduct(int productId) {
    DialogAddProduct dialog = new DialogAddProduct(productId);
    dialog.show(getSupportFragmentManager(), "dialogAddProduct");
  }

  // -----------------------------------------------------------------------------------------------

  private void successAddProductToast(){
    StyleableToast.makeText(this,getResources().getText(R.string.productAddedSuccess).toString(),
            Toast.LENGTH_LONG,R.style.toastSuccessAddProduct).show();
  }

  // -----------------------------------------------------------------------------------------------

  private void errorAddProductToast(){

    StyleableToast.makeText(this,getResources().getText(R.string.productAddedError).toString(),
            Toast.LENGTH_LONG,R.style.toastErrorAddProduct).show();
  }

  // -----------------------------------------------------------------------------------------------

}
