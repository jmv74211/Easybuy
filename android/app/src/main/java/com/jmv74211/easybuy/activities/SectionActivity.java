package com.jmv74211.easybuy.activities;

import androidx.appcompat.app.AppCompatActivity;
import com.jmv74211.easybuy.R;
import com.jmv74211.easybuy.adapters.SectionAdapter;
import com.jmv74211.easybuy.data.SectionData;
import com.jmv74211.easybuy.models.Section;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.HashMap;

public class SectionActivity extends AppCompatActivity implements SectionAdapter.OnCardListener {

  private RecyclerView recyclerView;

  private SectionAdapter adapter;

  private ArrayList<Section> sectionList;

  // -----------------------------------------------------------------------------------------------

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_section);
    setWindowsStatusBarColor();
    loadData();
    setUpRecyclerView();
  }

  // -----------------------------------------------------------------------------------------------

  private void loadData(){
    SectionData sectionData = SectionData.getInstance(this);
    sectionList = sectionData.getSectionData();
  }

  // -----------------------------------------------------------------------------------------------

  private void setUpRecyclerView(){
    recyclerView = findViewById(R.id.recyclerView);

    recyclerView.setHasFixedSize(true);

    GridLayoutManager mGridLayoutManager = new GridLayoutManager(SectionActivity.this, 2);
    recyclerView.setLayoutManager(mGridLayoutManager);

    adapter = new SectionAdapter(this, sectionList, this);

    recyclerView.setAdapter(adapter);
  }

  // -----------------------------------------------------------------------------------------------

  @Override
  public void onCardClick(int position) {
    goToProductSearchActivity(position);
  }

  // -----------------------------------------------------------------------------------------------

  private void goToProductSearchActivity(int sectionId){
    Intent intent = new Intent(this, ProductSearchActivity.class);
    HashMap<String, Object> data = new HashMap<>();
    data.put("sectionId", sectionId);
    intent.putExtra("hashMap", data);
    startActivity(intent);
  }

  // -----------------------------------------------------------------------------------------------

  private void setWindowsStatusBarColor(){
    if (Build.VERSION.SDK_INT >= 21) {
      getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
      getWindow().setStatusBarColor(getResources().getColor(R.color.cdarkBLue));
    }
  }

  // -----------------------------------------------------------------------------------------------

}
