package ru.kpfu.itis.abiturkfu.view.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.kpfu.itis.abiturkfu.R;
import ru.kpfu.itis.abiturkfu.model.entities.Course;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.CoursesViewHolder> {

    List<Course> courses;
    private CoursesAdapter.OnItemClickListener listener;

    public CoursesAdapter(CoursesAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public CoursesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_item, parent, false);


        return new CoursesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoursesViewHolder holder, int position) {
        final Course course = courses.get(position);

        holder.tvName.setText(course.getName());
        holder.tvTerms.setText(course.getTerm());
        holder.itemView.setOnClickListener(view -> listener.onClick(view, course.getId()));
    }

    @Override
    public int getItemCount() {
        return courses == null ? 0 : courses.size();
    }

    public void setData(List<Course> courses) {
        this.courses = courses;
        notifyDataSetChanged();
    }

    public class CoursesViewHolder extends RecyclerView.ViewHolder{

        TextView tvName;
        TextView tvTerms;

        public CoursesViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name);
            tvTerms = itemView.findViewById(R.id.tv_terms);
        }
    }

    public interface OnItemClickListener {
        void onClick(View view, int courseId);
    }

}
