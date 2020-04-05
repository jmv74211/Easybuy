package com.jmv74211.easybuy.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jmv74211.easybuy.R;
import com.jmv74211.easybuy.models.Section;

import java.util.ArrayList;


public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.SectionViewHolder> {

  private Context context;
  private ArrayList<Section> sectionList;
  private OnCardListener onCardListener;

  // ---------------------------------------------------------------------------------------------

  public SectionAdapter(Context context, ArrayList<Section> sectionList, OnCardListener onCardListener) {
    this.context = context;
    this.sectionList= sectionList;
    this.onCardListener = onCardListener;
  }

  // ---------------------------------------------------------------------------------------------

  @NonNull
  @Override
  public SectionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    LayoutInflater inflater = LayoutInflater.from(context);
    View view = inflater.inflate(R.layout.card_section, null);
    SectionViewHolder holder = new SectionViewHolder(view, this.onCardListener);

    return holder;
  }

  // ---------------------------------------------------------------------------------------------

  @Override
  public void onBindViewHolder(@NonNull SectionViewHolder holder, int position) {
    Section section = sectionList.get(position);

    holder.sectionNameText.setText(section.getName());
    holder.relativeLayout.setBackgroundColor(Color.parseColor(section.getColor()));
    String photoName = "section_" + position;

    holder.imageView.setImageResource(context.getResources().getIdentifier(photoName,
            "drawable", context.getPackageName()));
  }

  // ---------------------------------------------------------------------------------------------

  @Override
  public int getItemCount() {
    return this.sectionList.size();
  }

  // ---------------------------------------------------------------------------------------------

  class SectionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView imageView;
    RelativeLayout relativeLayout;
    TextView sectionNameText;
    OnCardListener onCardListener;

    public SectionViewHolder(@NonNull final View itemView, OnCardListener onCardListener) {
      super(itemView);

      imageView = itemView.findViewById(R.id.sectionImage);
      relativeLayout = itemView.findViewById(R.id.headerSection);
      sectionNameText = itemView.findViewById(R.id.sectionName);

      this.onCardListener = onCardListener;

      itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      onCardListener.onCardClick(getAdapterPosition());
    }
  }

  // ---------------------------------------------------------------------------------------------

  public interface OnCardListener {
    void onCardClick(int position);
  }

  // ---------------------------------------------------------------------------------------------

}
