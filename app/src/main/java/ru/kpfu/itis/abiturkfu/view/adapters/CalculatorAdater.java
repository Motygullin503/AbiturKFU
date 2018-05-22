package ru.kpfu.itis.abiturkfu.view.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;

import ru.kpfu.itis.abiturkfu.R;
import ru.kpfu.itis.abiturkfu.model.entities.Subject;

public class CalculatorAdater extends RecyclerView.Adapter<CalculatorAdater.CalcViewHolder> {
    final List<Subject> subjects;

    public CalculatorAdater(List<Subject> subjects) {
        this.subjects = subjects;
    }

    @NonNull
    @Override
    public CalcViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calc, parent, false);
        return new CalcViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalcViewHolder holder, int position) {
        Subject subject = subjects.get(position);
        holder.checkBox.setText(subject.getName());
        holder.checkBox.setChecked(subject.isChecked());

        holder.itemView.setOnClickListener(view -> {
            holder.checkBox.setChecked(!subject.isChecked());
            subject.setChecked(!subject.isChecked());

        });

        holder.checkBox.setOnClickListener(view -> {
            holder.checkBox.setChecked(!subject.isChecked());
            subject.setChecked(!subject.isChecked());
        });
    }

    public ArrayList<String> getSelectedSubjects() {
        ArrayList<String> strings = new ArrayList<>();
        for (Subject subject : subjects) {
            if (subject.isChecked()) {
                strings.add(subject.getName());
            }
        }
        return strings;
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    static class CalcViewHolder extends RecyclerView.ViewHolder {
        final CheckBox checkBox;

        public CalcViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.cb_subject);
        }
    }
}
