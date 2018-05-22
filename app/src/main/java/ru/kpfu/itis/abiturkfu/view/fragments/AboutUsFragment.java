package ru.kpfu.itis.abiturkfu.view.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import ru.kpfu.itis.abiturkfu.App;
import ru.kpfu.itis.abiturkfu.R;
import ru.kpfu.itis.abiturkfu.model.entities.Committee;
import ru.kpfu.itis.abiturkfu.model.entities.Partner;
import ru.kpfu.itis.abiturkfu.model.entities.PartnerAndCommittee;
import ru.kpfu.itis.abiturkfu.model.repository.AbiturientRepository;
import ru.kpfu.itis.abiturkfu.model.repository.ResponseLiveData;
import ru.kpfu.itis.abiturkfu.view.activities.MainActivity;
import ru.kpfu.itis.abiturkfu.view.adapters.AboutUsCommitteeAdapter;
import ru.kpfu.itis.abiturkfu.view.adapters.AboutUsPartnersAdapter;
import ru.kpfu.itis.abiturkfu.view.views.CirclePagerIndicatorDecoration;


public class AboutUsFragment extends Fragment {


    RecyclerView rvPartners;
    RecyclerView rvCommittees;
    AboutUsPartnersAdapter aboutUsPartnersAdapter;
    AboutUsCommitteeAdapter aboutUsCommitteeAdapter;
    FloatingActionButton fabVk;
    FloatingActionButton fabInstagram;

    @Inject
    AbiturientRepository repository;

    public AboutUsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        App.getComponent().inject(this);
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);
        if (getActivity() != null && ((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("О нас");
        }

        fabVk = view.findViewById(R.id.fab_vk);
        fabVk.setOnClickListener(view1 -> {
            Uri uri = Uri.parse("https://vk.com/kazan_federal_university");

            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });

        fabInstagram = view.findViewById(R.id.fab_instagram);
        fabInstagram.setOnClickListener(view1 -> {
            Uri uri = Uri.parse("https://www.instagram.com/kazanfederaluniversity/");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });

        rvPartners = view.findViewById(R.id.rv_partners);
        rvCommittees = view.findViewById(R.id.rv_priem);

        initializeRecyclerViews();

        repository.getAboutUs().observe(
                this,
                this::fill,
                status -> ((MainActivity) getActivity()).showLoading(status == ResponseLiveData.Status.LOADING),
                throwable -> Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show()
        );

        return view;
    }


    public void initializeRecyclerViews() {
        aboutUsCommitteeAdapter = new AboutUsCommitteeAdapter();
        aboutUsPartnersAdapter = new AboutUsPartnersAdapter();

        rvPartners.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvPartners.setAdapter(aboutUsPartnersAdapter);

        rvCommittees.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
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
