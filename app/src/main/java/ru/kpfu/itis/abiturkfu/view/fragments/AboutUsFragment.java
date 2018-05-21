package ru.kpfu.itis.abiturkfu.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.kpfu.itis.abiturkfu.R;
import ru.kpfu.itis.abiturkfu.model.entities.Committee;
import ru.kpfu.itis.abiturkfu.model.entities.Partner;
import ru.kpfu.itis.abiturkfu.model.entities.PartnerAndCommittee;
import ru.kpfu.itis.abiturkfu.view.adapters.AboutUsCommitteeAdapter;
import ru.kpfu.itis.abiturkfu.view.adapters.AboutUsPartnersAdapter;
import ru.kpfu.itis.abiturkfu.view.views.CirclePagerIndicatorDecoration;


public class AboutUsFragment extends Fragment {


    RecyclerView rvPartners;
    RecyclerView rvCommittees;
    AboutUsPartnersAdapter aboutUsPartnersAdapter;
    AboutUsCommitteeAdapter aboutUsCommitteeAdapter;


    public AboutUsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);

        rvPartners = view.findViewById(R.id.rv_partners);
        rvCommittees = view.findViewById(R.id.rv_priem);

        initializeRecyclerViews();


        return view;
    }


    public void initializeRecyclerViews() {
        aboutUsCommitteeAdapter = new AboutUsCommitteeAdapter();
        aboutUsPartnersAdapter = new AboutUsPartnersAdapter();

        rvPartners.setLayoutManager(new LinearLayoutManager(getContext()));
        rvPartners.setAdapter(aboutUsPartnersAdapter);

        rvCommittees.setLayoutManager(new LinearLayoutManager(getContext()));
        rvCommittees.setAdapter(aboutUsCommitteeAdapter);
        rvCommittees.addItemDecoration(new CirclePagerIndicatorDecoration());
    }


    public void fill(PartnerAndCommittee partnerAndCommittee) {

        fillPartnersAdapter(partnerAndCommittee.getPartners());

        fillCommitteeAdapter(partnerAndCommittee.getCommittee());

    }

    private void fillCommitteeAdapter(List<Committee> committee) {
        aboutUsCommitteeAdapter.setData(committee);
    }

    private void fillPartnersAdapter(List<Partner> partners) {
        aboutUsPartnersAdapter.setData(partners);
    }


}
