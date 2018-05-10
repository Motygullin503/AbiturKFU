package ru.kpfu.itis.abiturkfu.view.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AdapterView;

import ru.kpfu.itis.abiturkfu.R;
import ru.kpfu.itis.abiturkfu.databinding.ItemPlanBinding;

public class PlanPageRecyclerViewAdapter extends RecyclerView.Adapter<PlanPageRecyclerViewAdapter.PlanPageViewHolder> {
    private AdapterView.OnItemClickListener clickListener;

    @NonNull
    @Override
    public PlanPageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPlanBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_plan, parent, false);
        return new PlanPageViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanPageViewHolder holder, int position) {
        // TODO: 05.05.18 add class and filling
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class PlanPageViewHolder extends RecyclerView.ViewHolder {
        public ItemPlanBinding r;

        public PlanPageViewHolder(ItemPlanBinding binding) {
            super(binding.getRoot());
            r = binding;
        }
    }
}
