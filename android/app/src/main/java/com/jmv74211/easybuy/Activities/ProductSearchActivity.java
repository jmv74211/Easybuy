package com.jmv74211.easybuy.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.jmv74211.easybuy.Adapters.ProductListAdapter;
import com.jmv74211.easybuy.DBInfo.ProductDBInfo;
import com.jmv74211.easybuy.Data.SectionData;
import com.jmv74211.easybuy.POJO.Product;
import com.jmv74211.easybuy.R;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Nullable;

public class ProductSearchActivity extends AppCompatActivity implements ProductListAdapter.OnCardListener {

    private Toolbar appbar;
    private FloatingActionButton fab;
    private RecyclerView recyclerView;

    private ArrayList<Product> products = new ArrayList<>();
    private int sectionId;

    private ProductListAdapter adapter;

    private ProductDBInfo productDBInfo = ProductDBInfo.getInstance();

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference productsCollectionReference = db.collection(productDBInfo.getCollectionName());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_search_list);

        appbar = (Toolbar) findViewById(R.id.app_bar);
        fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recyclerView);

        Intent intent = getIntent();
        HashMap<String, Object> data = (HashMap<String, Object>) intent.getSerializableExtra("hashMap");

        sectionId = (int) data.get("sectionId");
        SectionData sectionData = SectionData.getInstance(this);
        String sectionName = sectionData.getSection(sectionId).trim();

        setSupportActionBar(appbar);

        getSupportActionBar().setTitle(sectionName);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(), "ID = " + sectionId, Toast.LENGTH_SHORT).show();
            }
        });

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
                    // Set full
                    adapter.updateFullList(new ArrayList<Product>(products));
                }
            });
        }
    }

    @Override
    public void onCardClick(int position) {
        Toast.makeText(this, "POSITION " + position, Toast.LENGTH_SHORT).show();
    }

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
}
