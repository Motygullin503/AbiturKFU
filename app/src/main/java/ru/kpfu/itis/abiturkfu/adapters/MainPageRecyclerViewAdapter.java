package ru.kpfu.itis.abiturkfu.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.kpfu.itis.abiturkfu.R;
import ru.kpfu.itis.abiturkfu.entities.Post;

/**
 * Created by Bulat on 02.04.2018 at 00:11.
 */
public class MainPageRecyclerViewAdapter extends RecyclerView.Adapter<MainPageRecyclerViewAdapter.MainPageViewHolder> {

    List<Post> posts = new ArrayList<>();

    @NonNull
    @Override
    public MainPageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_main_fragment, parent, false);
        return new MainPageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainPageViewHolder holder, int position) {
        final Post post = posts.get(position);

        holder.text.setText(post.getTitle());

        holder.exam_points.setText(post.getExamPoints());
    }


    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void setData(List<Post> data) {
        // TODO: 24.04.18 Add DiffUtils
        posts = data;
        notifyDataSetChanged();
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
}
