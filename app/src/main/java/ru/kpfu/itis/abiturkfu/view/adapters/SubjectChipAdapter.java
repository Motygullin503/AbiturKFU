package ru.kpfu.itis.abiturkfu.view.adapters;

import android.support.annotation.NonNull;
import android.support.design.chip.Chip;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.kpfu.itis.abiturkfu.R;
import ru.kpfu.itis.abiturkfu.model.entities.Subject;

public class SubjectChipAdapter extends RecyclerView.Adapter<SubjectChipAdapter.SubjectChipViewHolder> {
    private List<Subject> subjects;

    @NonNull
    @Override
    public SubjectChipViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subject_chip, parent, false);
        return new SubjectChipViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectChipViewHolder holder, int position) {
        holder.chip.setChipText(subjects.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return subjects == null ? 0 : subjects.size();
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
        notifyDataSetChanged();
    }

    static class SubjectChipViewHolder extends RecyclerView.ViewHolder {
        final Chip chip;

        public SubjectChipViewHolder(@NonNull View itemView) {
            super(itemView);
            chip = itemView.findViewById(R.id.chip);
        }
    }
}
