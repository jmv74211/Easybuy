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
import android.widget.Toast;

import com.jmv74211.easybuy.Adapters.ShoppingListAdapter;
import com.jmv74211.easybuy.Data.Data;
import com.jmv74211.easybuy.POJO.ShoppingList;
import com.jmv74211.easybuy.R;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements ShoppingListAdapter.OnCardListener {

    private DrawerLayout drawer;
    private Toolbar appbar;
    private FloatingActionButton fab;
    private RecyclerView recyclerView;

    private ArrayList<ShoppingList> shoppingList;

    private ShoppingListAdapter adapter;


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


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),CreateShoppingListActivity.class);
                startActivity(intent);
            }
        });

        this.shoppingList = Data.getInstance().getShoppingList();

        System.out.println("SHOPPING LIST = " + this.shoppingList);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ShoppingListAdapter(this, shoppingList, this);

        recyclerView.setAdapter(adapter);

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

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // Recyclerview click
    @Override
    public void onCardClick(int position) {
        Toast.makeText(this, "POSITION " + position, Toast.LENGTH_SHORT).show();
    }

}
