package ru.kpfu.itis.abiturkfu.view.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.kpfu.itis.abiturkfu.R;
import ru.kpfu.itis.abiturkfu.databinding.ItemPlanBinding;
import ru.kpfu.itis.abiturkfu.model.entities.Speciality;

public class PlanPageRecyclerViewAdapter extends RecyclerView.Adapter<PlanPageRecyclerViewAdapter.PlanPageViewHolder> {
    private OnItemClickListener clickListener;
    private List<Speciality> specialities;

    public PlanPageRecyclerViewAdapter(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
        specialities = new ArrayList<>();
    }

    @NonNull
    @Override
    public PlanPageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPlanBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_plan, parent, false);
        return new PlanPageViewHolder(binding);
    }

    public void setSpecialities(List<Speciality> data) {
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return specialities.size();
            }

            @Override
            public int getNewListSize() {
                return data.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return specialities.get(oldItemPosition).getId() == data.get(newItemPosition).getId();
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                return specialities.get(oldItemPosition).equals(data.get(newItemPosition));
            }
        });
        this.specialities = data;
        result.dispatchUpdatesTo(this);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanPageViewHolder holder, int position) {
        final Speciality speciality = specialities.get(position);
        holder.r.tvName.setText(speciality.getName());

        StringBuilder eduTypeBuilder = new StringBuilder("<font color=\"#0068b4\">Форма обучения: </font>");
        if (speciality.getEducationForms() != null) {
            for (int i = 0; i < speciality.getEducationForms().size(); i++) {
                if (i != 0) {
                    eduTypeBuilder.append(", ");
                }
                eduTypeBuilder.append(speciality.getEducationForms().get(i).getName());
            }
        }
        holder.r.tvStudyType.setText(Html.fromHtml(eduTypeBuilder.toString()));

        boolean hasBudget = false;
        StringBuilder countBuilder = new StringBuilder("<font color=\"#0068b4\">Форма обучения: </font>");
        if (speciality.getBudgetPlace() != null && speciality.getBudgetPlace() > 0) {
            hasBudget = true;
            countBuilder.append(speciality.getBudgetPlace());
            countBuilder.append(" бюджетных");
        }
        if (speciality.getContractPlace() != null && speciality.getContractPlace() > 0) {
            if (hasBudget) {
                countBuilder.append(", ");
            }
            countBuilder.append(speciality.getContractPlace());
            countBuilder.append(" контрактных");
        }
        holder.r.tvCounts.setText(Html.fromHtml(countBuilder.toString()));

        holder.itemView.setOnClickListener(v -> clickListener.onClick(v, speciality.getId()));
    }

    @Override
    public int getItemCount() {
        return specialities.size();
    }

    public interface OnItemClickListener {
        void onClick(View view, int specialityId);
    }

    class PlanPageViewHolder extends RecyclerView.ViewHolder {
        public ItemPlanBinding r;

        PlanPageViewHolder(ItemPlanBinding binding) {
            super(binding.getRoot());
            r = binding;
        }
    }
}
