package com.jmv74211.easybuy.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jmv74211.easybuy.Adapters.SectionListAdapter;
import com.jmv74211.easybuy.Data.SectionData;
import com.jmv74211.easybuy.POJO.Section;
import com.jmv74211.easybuy.R;

import java.util.ArrayList;
import java.util.HashMap;

public class SectionListActivity extends AppCompatActivity implements SectionListAdapter.OnCardListener {

    private RecyclerView recyclerView;

    private SectionListAdapter adapter;

    private ArrayList<Section> sectionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_section);

        SectionData sectionData = SectionData.getInstance(this);
        sectionList = sectionData.getSectionData();

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);

        GridLayoutManager mGridLayoutManager = new GridLayoutManager(SectionListActivity.this, 2);
        recyclerView.setLayoutManager(mGridLayoutManager);

        adapter = new SectionListAdapter(this, sectionList, this);

        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onCardClick(int position) {
        Intent intent = new Intent(this, ProductSearchActivity.class);

        HashMap<String, Object> data = new HashMap<>();
        data.put("sectionId", position);
        intent.putExtra("hashMap", data);
        startActivity(intent);

    }
}
