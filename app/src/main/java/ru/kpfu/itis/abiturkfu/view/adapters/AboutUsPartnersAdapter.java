package ru.kpfu.itis.abiturkfu.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import ru.kpfu.itis.abiturkfu.R;
import ru.kpfu.itis.abiturkfu.model.entities.Partner;

public class AboutUsPartnersAdapter extends RecyclerView.Adapter<AboutUsPartnersAdapter.AboutUsPartnersViewHolder> {

    List<Partner> partners;


    @NonNull
    @Override
    public AboutUsPartnersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);

        return new AboutUsPartnersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AboutUsPartnersViewHolder holder, int position) {
        final Partner partner = partners.get(position);

        Glide.with(holder.itemView.getContext())
                .load(partner.getImageLink())
                .into(holder.partnerLogo);
    }

    @Override
    public int getItemCount() {
        return partners.size();
    }

    public void setData(List<Partner> partners) {
        this.partners = partners;
    }
    public class AboutUsPartnersViewHolder extends RecyclerView.ViewHolder{

        ImageView partnerLogo;


        public AboutUsPartnersViewHolder(View itemView) {
            super(itemView);

            partnerLogo = itemView.findViewById(R.id.partner_logo);
        }


    }

}
