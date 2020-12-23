package com.maindevpattern.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.maindevpattern.MainClass;
import com.maindevpattern.R;
import com.maindevpattern.activities.DetailsOfferActivity;
import com.maindevpattern.models.get.Liste;

import java.util.List;

public class AdapterCloak extends RecyclerView.Adapter<AdapterCloak.ViewHolder> {

    Context context;

    public void setDataList(List<Liste> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    List<Liste> dataList;


    public AdapterCloak(Context context, List<Liste> dataList) {
        this.context = context;
        this.dataList = dataList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);

        if (MainClass.font > 1){
                view = inflater.inflate(R.layout.fragment_large, parent, false);
        } else if (MainClass.font >= 1.3){
                view = inflater.inflate(R.layout.fragment_exlarge, parent, false);
        } else {
                view = inflater.inflate(R.layout.fragment, parent, false);
        }
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Liste liste = dataList.get(position);

        String firstCreditSum = liste.getAmount().getFrom().toString();
        String percentRate = liste.getPercent().getFrom().toString();

        //setting holders to textViews
        holder.firstCreditSum.setText(firstCreditSum + "₴");
        holder.percentRate.setText(percentRate + "%");
        //setting image holder with glide
        Glide.with(context)
                .load(dataList.get(position)
                        .getImg())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.progressBarGlide.setIndeterminate(false);
                        holder.progressBarGlide.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBarGlide.setIndeterminate(false);
                        holder.progressBarGlide.setVisibility(View.GONE);
                        return false;
                    }
                })
                .centerInside()
                .into(holder.imgCompany);

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(context, DetailsOfferActivity.class);
                myIntent.putExtra("position", position);
                context.startActivity(myIntent);

            }
        });

        holder.click_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(context, DetailsOfferActivity.class);
                myIntent.putExtra("position", position);
                context.startActivity(myIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        //declaring items
        ConstraintLayout click_layout;
        TextView firstCreditSum, percentRate;
        ImageView imgCompany;
        Button button;
        ProgressBar progressBarGlide;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //initializing
            progressBarGlide = itemView.findViewById(R.id.progressGlide);
            click_layout = itemView.findViewById(R.id.click_layout);
            button = itemView.findViewById(R.id.button);
            imgCompany = itemView.findViewById(R.id.imgCompany);
            firstCreditSum = itemView.findViewById(R.id.firstCreditSum);
            percentRate = itemView.findViewById(R.id.percentRate);

        }
    }

    @Override
    public int getItemViewType(int position) {

        return 1;
    }

}
