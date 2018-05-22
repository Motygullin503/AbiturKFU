package ru.kpfu.itis.abiturkfu.view.fragments;

import android.os.Bundle;
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
import ru.kpfu.itis.abiturkfu.model.entities.Course;
import ru.kpfu.itis.abiturkfu.model.repository.AbiturientRepository;
import ru.kpfu.itis.abiturkfu.model.repository.ResponseLiveData;
import ru.kpfu.itis.abiturkfu.view.activities.CourseActivity;
import ru.kpfu.itis.abiturkfu.view.activities.MainActivity;
import ru.kpfu.itis.abiturkfu.view.adapters.CoursesAdapter;


public class CoursesFragment extends Fragment {
    @Inject
    AbiturientRepository repository;
    private RecyclerView rvCourses;
    private CoursesAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        App.getComponent().inject(this);
        View view = inflater.inflate(R.layout.fragment_courses, container, false);
        rvCourses = view.findViewById(R.id.rv_courses);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Курсы");

        initializeRecyclerView();

        repository.getCourses().observe(
                this,
                this::fill,
                status -> ((MainActivity) getActivity()).showLoading(status == ResponseLiveData.Status.LOADING),
                throwable -> Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show()
        );

        return view;
    }

    public void initializeRecyclerView() {
        adapter = new CoursesAdapter(((view, courseId) -> CourseActivity.start(getContext(), courseId)));

        rvCourses.setLayoutManager(new LinearLayoutManager(getContext()));
        rvCourses.setAdapter(adapter);

    }

    void fill(List<Course> courses) {
        adapter.setData(courses);
    }
}
