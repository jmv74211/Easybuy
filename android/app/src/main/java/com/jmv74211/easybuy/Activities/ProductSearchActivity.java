package com.jmv74211.easybuy.Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.jmv74211.easybuy.Adapters.ProductListAdapter;
import com.jmv74211.easybuy.DBInfo.ProductDBInfo;
import com.jmv74211.easybuy.DBInfo.ShoppingListDBInfo;
import com.jmv74211.easybuy.Data.Data;
import com.jmv74211.easybuy.Data.SectionData;
import com.jmv74211.easybuy.Dialogs.DialogAddProduct;
import com.jmv74211.easybuy.POJO.CartProduct;
import com.jmv74211.easybuy.POJO.Product;
import com.jmv74211.easybuy.POJO.ShoppingList;
import com.jmv74211.easybuy.R;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Nullable;

public class ProductSearchActivity extends AppCompatActivity implements ProductListAdapter.OnCardListener,
        DialogAddProduct.DialogAddProductListener {

    private Toolbar appbar;
    private RecyclerView recyclerView;

    private ArrayList<Product> products = new ArrayList<>();
    private int sectionId;

    private ProductListAdapter adapter;

    private ProductDBInfo productDBInfo = ProductDBInfo.getInstance();
    private ShoppingListDBInfo shoppingListDBInfo = ShoppingListDBInfo.getInstance();

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference productsCollectionReference = db.collection(productDBInfo.getCollectionName());
    private CollectionReference shoppingListCollectionReference = db.collection(shoppingListDBInfo.getCollectionName());

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_search_list);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        appbar = (Toolbar) findViewById(R.id.app_bar);
        recyclerView = findViewById(R.id.recyclerView);

        Intent intent = getIntent();
        HashMap<String, Object> data = (HashMap<String, Object>) intent.getSerializableExtra("hashMap");

        sectionId = (int) data.get("sectionId");
        SectionData sectionData = SectionData.getInstance(this);
        String sectionName = sectionData.getSection(sectionId).trim();

        setSupportActionBar(appbar);

        getSupportActionBar().setTitle(sectionName);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ProductListAdapter(this, products, this);

        recyclerView.setAdapter(adapter);


        if (sectionId == 0) { // ALL PRODUCTS

            productsCollectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                    if (e != null) {
                        return;
                    }

                    for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {
                        if (doc.getType() == DocumentChange.Type.ADDED) {
                            Product p = doc.getDocument().toObject(Product.class);
                            products.add(p);
                        }

                    }
                    adapter.notifyDataSetChanged();
                    adapter.updateFullList(new ArrayList<Product>(products));

                }
            });


        } else { // SECTION PRODUCTS

            productsCollectionReference.whereEqualTo("section", sectionId).addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                    if (e != null) {
                        return;
                    }

                    for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {
                        if (doc.getType() == DocumentChange.Type.ADDED) {
                            Product p = doc.getDocument().toObject(Product.class);
                            products.add(p);
                        }

                    }

                    adapter.notifyDataSetChanged();
                    adapter.updateFullList(new ArrayList<Product>(products));
                }
            });
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onCardClick(int position) {
        openDialogAddProduct(products.get(position).getId());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void openDialogAddProduct(int productId) {

        DialogAddProduct dialog = new DialogAddProduct(productId);
        dialog.show(getSupportFragmentManager(), "dialogAddProduct");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

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

    ////////////////////////////////////////////////////////////////////////////////////////////////

    // Method to get products quantity data. Called when accept button of dialogAddProduct clicked.
    @Override
    public void applyData(int productId, int quantity) {

        String id = Data.getShoppingList().getId();
        addProductToCart(productId, quantity);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void addProductToCart(int productId, int quantity) {

        // ShoppingList already has that product, then sum quantity
        if (Data.checkIfCartContainsProduct(productId)) {
            ArrayList<CartProduct> cartProducts = Data.getShoppingList().getCartProducts();

            for (int i = 0; i < cartProducts.size(); i++) {
                if (cartProducts.get(i).getProduct().getId() == productId) {
                    cartProducts.get(i).setQuantity(cartProducts.get(i).getQuantity() + quantity);

                    ShoppingList shoppingList = Data.getShoppingList();
                    shoppingList.setCartProducts(cartProducts);
                    Data.setShoppingList(shoppingList);
                }
            }
        }
        // Add a new cartProduct to the list
        else {
            CartProduct cartProduct = new CartProduct(getProduct(productId), quantity);
            ShoppingList sh = Data.getShoppingList();
            sh.addCartProduct(cartProduct);
            Data.setShoppingList(sh);
        }


        // UPDATE OBJECT IN DATABASE

        HashMap<String, Object> mapInformation = new HashMap<>();

        mapInformation = Data.getShoppingList().toMap();

        sucessAddProductToast();

        shoppingListCollectionReference.document(Data.getShoppingList().getId()).update(mapInformation).addOnSuccessListener(new OnSuccessListener<Void>() {

            @Override
            public void onSuccess(Void aVoid) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                errorAddProductToast();
            }
        });

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public Product getProduct(int productId) {

        Product product = null;

        for (Product item : products) {
            if (productId == item.getId()) {
                product = item;
            }
        }

        return product;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void sucessAddProductToast(){
        StyleableToast.makeText(this,getResources().getText(R.string.productAddedSuccess).toString(),
                                Toast.LENGTH_LONG,R.style.toastSuccessAddProduct).show();

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void errorAddProductToast(){

        StyleableToast.makeText(this,getResources().getText(R.string.productAdddedError).toString(),
                Toast.LENGTH_LONG,R.style.toastErrorAddProduct).show();

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

}
