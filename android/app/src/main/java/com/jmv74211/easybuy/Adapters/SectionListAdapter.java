package com.jmv74211.easybuy.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jmv74211.easybuy.POJO.Section;
import com.jmv74211.easybuy.R;
import com.jmv74211.easybuy.Tools.CustomColor;

import java.util.ArrayList;


public class SectionListAdapter extends RecyclerView.Adapter<SectionListAdapter.SectionListViewHolder> {

    private Context context;
    private ArrayList<Section> sectionList;
    private OnCardListener onCardListener;

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public SectionListAdapter(Context context, ArrayList<Section> sectionList, OnCardListener onCardListener) {
        this.context = context;
        this.sectionList= sectionList;
        this.onCardListener = onCardListener;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @NonNull
    @Override
    public SectionListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_section, null);
        SectionListViewHolder holder = new SectionListViewHolder(view, this.onCardListener);

        return holder;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onBindViewHolder(@NonNull SectionListViewHolder holder, int position) {
        Section section = sectionList.get(position);
        CustomColor color = CustomColor.getInstance();

        holder.sectionNameText.setText(section.getName());
        holder.relativeLayout.setBackgroundColor(Color.parseColor(section.getColor()));
        String photoName = "section_" + position;

        holder.imageView.setImageResource(context.getResources().getIdentifier(photoName, "drawable", context.getPackageName()));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public int getItemCount() {
        return this.sectionList.size();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    class SectionListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        RelativeLayout relativeLayout;
        TextView sectionNameText;
        OnCardListener onCardListener;

        public SectionListViewHolder(@NonNull final View itemView, OnCardListener onCardListener) {
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

    ////////////////////////////////////////////////////////////////////////////////////////////////


    // Recyclerview click interface
    public interface OnCardListener {
        void onCardClick(int position);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

}
