package ru.kpfu.itis.abiturkfu.view.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.kpfu.itis.abiturkfu.R;
import ru.kpfu.itis.abiturkfu.model.entities.Course;
import ru.kpfu.itis.abiturkfu.view.activities.CourseActivity;
import ru.kpfu.itis.abiturkfu.view.adapters.CoursesAdapter;


public class CoursesFragment extends Fragment {


    RecyclerView rvCourses;
    CoursesAdapter adapter;

    public CoursesFragment() {

    }



    public static CoursesFragment newInstance(String param1, String param2) {
        CoursesFragment fragment = new CoursesFragment();


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_courses, container, false);
        view.findViewById(R.id.rv_courses);

        initializeRecyclerView();

        return view;
    }

    public void initializeRecyclerView() {
        adapter = new CoursesAdapter(((view, courseId) -> CourseActivity.start(getContext(), courseId)));

        rvCourses.setLayoutManager(new LinearLayoutManager(getContext()));
        rvCourses.setAdapter(adapter);

    }

    void fill(List<Course> courses) {

        //Todo: fill
        adapter.setData(courses);
    }
}
