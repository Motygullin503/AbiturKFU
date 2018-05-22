package ru.kpfu.itis.abiturkfu.view.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.kpfu.itis.abiturkfu.R;
import ru.kpfu.itis.abiturkfu.model.entities.Contest;
import ru.kpfu.itis.abiturkfu.model.entities.Course;

public class ContestsAdapter extends RecyclerView.Adapter<ContestsAdapter.ContestsViewHolder> {

    List<Contest> contests;
    private CoursesAdapter.OnItemClickListener listener;

    public ContestsAdapter(CoursesAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ContestsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_item, parent, false);

        return new ContestsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContestsViewHolder holder, int position) {
        final Contest contest = contests.get(position);

        holder.tvName.setText(contest.getName());
        holder.tvTerms.setText(contest.getTerm());
        holder.itemView.setOnClickListener(view -> listener.onClick(view, contest.getId()));
    }

    @Override
    public int getItemCount() {
        return contests == null ? 0 : contests.size();
    }

    public void setData(List<Contest> contests) {
        this.contests = contests;
        notifyDataSetChanged();
    }

    public class ContestsViewHolder extends RecyclerView.ViewHolder{


        TextView tvName;
        TextView tvTerms;

        public ContestsViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name);
            tvTerms = itemView.findViewById(R.id.tv_terms);
        }
    }

    public interface OnItemClickListener {
        void onClick(View view, int courseId);
    }


}
