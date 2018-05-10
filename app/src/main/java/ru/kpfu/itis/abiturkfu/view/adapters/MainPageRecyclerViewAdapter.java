package ru.kpfu.itis.abiturkfu.view.adapters;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import ru.kpfu.itis.abiturkfu.R;
import ru.kpfu.itis.abiturkfu.model.entities.Facility;

/**
 * Created by Bulat on 02.04.2018 at 00:11.
 */
public class MainPageRecyclerViewAdapter extends RecyclerView.Adapter<MainPageRecyclerViewAdapter.MainPageViewHolder> {

    private List<Facility> facilities = new ArrayList<>();
    private OnItemClickListener listener;

    public MainPageRecyclerViewAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MainPageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_main_fragment, parent, false);
        return new MainPageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainPageViewHolder holder, int position) {
        final Facility facility = facilities.get(position);

        holder.text.setText(facility.getName());

        holder.exam_points.setText(String.format(holder.itemView.getContext().getString(R.string.average_points), facility.getMiddle_score()));
        holder.itemView.setOnClickListener(v -> listener.onClick(v, facility.getId()));

        Glide.with(holder.itemView.getContext())
                .load(facility.getImage_link())
                .into(holder.contentPhoto);
    }


    @Override
    public int getItemCount() {
        return facilities.size();
    }

    /**
     * Filling data using diff utils
     */
    public void setData(List<Facility> data) {
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return facilities.size();
            }

            @Override
            public int getNewListSize() {
                return data.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return facilities.get(oldItemPosition).getId() == data.get(newItemPosition).getId();
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                return facilities.get(oldItemPosition).equals(data.get(newItemPosition));
            }
        });
        facilities = data;
        result.dispatchUpdatesTo(this);
    }


    public class MainPageViewHolder extends RecyclerView.ViewHolder {

        TextView text;
        TextView exam_points;
        ImageView contentPhoto;

        public MainPageViewHolder(View itemView) {
            super(itemView);

            text = itemView.findViewById(R.id.item_title);
            exam_points = itemView.findViewById(R.id.exam_points);
            contentPhoto = itemView.findViewById(R.id.content_photo);
        }
    }

    public interface OnItemClickListener {
        void onClick(View view, int facilityId);
    }
}
