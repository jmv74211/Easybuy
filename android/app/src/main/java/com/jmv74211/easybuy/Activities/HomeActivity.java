package com.jmv74211.easybuy.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.jmv74211.easybuy.Adapters.ShoppingListAdapter;
import com.jmv74211.easybuy.DBInfo.ShoppingListDBInfo;
import com.jmv74211.easybuy.Data.CurrentUserInfo;
import com.jmv74211.easybuy.Data.Data;
import com.jmv74211.easybuy.POJO.ShoppingList;
import com.jmv74211.easybuy.R;

import java.util.ArrayList;
import java.util.Collections;

import javax.annotation.Nullable;

public class HomeActivity extends AppCompatActivity implements ShoppingListAdapter.OnCardListener {

    private DrawerLayout drawer;
    private Toolbar appbar;
    private FloatingActionButton fab;
    private RecyclerView recyclerView;

    private ArrayList<ShoppingList> shoppingList = new ArrayList<>();

    private ShoppingListAdapter adapter;

    private static CurrentUserInfo currentUserInfo;
    private static ShoppingListDBInfo shoppingListDBInfo = ShoppingListDBInfo.getInstance();

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection(shoppingListDBInfo.getCollectionName());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        appbar = (Toolbar) findViewById(R.id.app_bar);
        fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recyclerView);

        setSupportActionBar(appbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.myList));

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, appbar, R.string.navigationDrawerOpen, R.string.navigationDrawerClose);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                return drawerNavitagionItemSelected(menuItem);
            }
        });

        currentUserInfo = CurrentUserInfo.getInstance(this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),CreateShoppingListActivity.class);
                startActivity(intent);
            }
        });

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ShoppingListAdapter(this, shoppingList, this);

        recyclerView.setAdapter(adapter);

        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if (e != null) {
                    return;
                }

                for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {
                    if (doc.getType() == DocumentChange.Type.ADDED) {
                        ShoppingList sh = doc.getDocument().toObject(ShoppingList.class);
                        sh.setId(doc.getDocument().getId());
                        shoppingList.add(sh);
                        Collections.sort(shoppingList, Collections.reverseOrder());
                        adapter.notifyDataSetChanged();
                    }

                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    //DRAWER
    public boolean drawerNavitagionItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.drawer_myProfile) {

        } else if (id == R.id.drawer_logout) {

            currentUserInfo.clear();
            mAuth.signOut();
            FirebaseAuth.getInstance().signOut();
            finish();
            goToLoginActivity();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // Recyclerview click
    @Override
    public void onCardClick(int position) {
        String shoppingCartId = shoppingList.get(position).getId();
        goToProductShoppingListActivity(shoppingCartId);
    }

    public void goToLoginActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void goToProductShoppingListActivity(String shoppingCartId){
        Intent intent = new Intent(this, ProductShoppingListActivity.class);

        // Global var to share with other activities
        for(ShoppingList s: shoppingList){
            if(s.getId() == shoppingCartId)
                Data.setShoppingList(s);
        }

        startActivity(intent);
    }

}
