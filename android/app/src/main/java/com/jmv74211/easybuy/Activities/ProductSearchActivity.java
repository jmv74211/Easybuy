package com.jmv74211.easybuy.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.jmv74211.easybuy.Adapters.ProductShoppingListAdapter;
import com.jmv74211.easybuy.DBInfo.ProductDBInfo;
import com.jmv74211.easybuy.POJO.CartProduct;
import com.jmv74211.easybuy.POJO.Product;
import com.jmv74211.easybuy.R;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class ProductSearchActivity extends AppCompatActivity implements ProductShoppingListAdapter.OnCardListener {

    private Toolbar appbar;
    private FloatingActionButton fab;
    private RecyclerView recyclerView;

    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<CartProduct> cartProducts = new ArrayList<>();

    private ProductShoppingListAdapter adapter;

    private ProductDBInfo productDBInfo = ProductDBInfo.getInstance();

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference productsCollectionReference = db.collection(productDBInfo.getCollectionName());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_shopping_list);

        appbar = (Toolbar) findViewById(R.id.app_bar);
        fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recyclerView);

        setSupportActionBar(appbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.productList));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ProductShoppingListAdapter(this, cartProducts, this);

        recyclerView.setAdapter(adapter);

        // PRODUCTS
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
                        adapter.notifyDataSetChanged();
                    }

                }
            }
        });

        // CART PRODUCTS




    }

    @Override
    public void onCardClick(int position) {
        Toast.makeText(this, "POSITION " + position, Toast.LENGTH_SHORT).show();
    }
}
