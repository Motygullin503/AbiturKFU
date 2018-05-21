package ru.kpfu.itis.abiturkfu.view.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ru.kpfu.itis.abiturkfu.R;
import ru.kpfu.itis.abiturkfu.model.entities.Committee;

public class AboutUsCommitteeAdapter extends RecyclerView.Adapter<AboutUsCommitteeAdapter.AboutUsCommitteeViewHolder> {

    List<Committee> committees;

    @NonNull
    @Override
    public AboutUsCommitteeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.committee_item, parent, false);

        return new AboutUsCommitteeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AboutUsCommitteeViewHolder holder, int position) {

        final Committee committee = committees.get(position);
        holder.title.setText(committee.getName());
        holder.director.setText(committee.getSecretary());
        holder.mail.setText(committee.getEmail());
        holder.phone.setText(committee.getPhone());
        holder.address.setText(committee.getLocation());
        holder.worktime.setText(committee.getWorkTime());


    }

    @Override
    public int getItemCount() {
        return committees.size();
    }

    public void setData(List<Committee> committees) {
        this.committees = committees;
    }

    public class AboutUsCommitteeViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView director;
        TextView address;
        TextView mail;
        TextView phone;
        TextView worktime;


        public AboutUsCommitteeViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tv_title);
            director = itemView.findViewById(R.id.director);
            address = itemView.findViewById(R.id.address);
            mail = itemView.findViewById(R.id.mail);
            phone = itemView.findViewById(R.id.phone);
            worktime = itemView.findViewById(R.id.worktime);
        }
    }

}
