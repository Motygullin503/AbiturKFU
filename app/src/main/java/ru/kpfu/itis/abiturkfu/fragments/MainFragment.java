package ru.kpfu.itis.abiturkfu.fragments;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.kpfu.itis.abiturkfu.R;
import ru.kpfu.itis.abiturkfu.activities.FilterActivity;
import ru.kpfu.itis.abiturkfu.adapters.MainPageRecyclerViewAdapter;
import ru.kpfu.itis.abiturkfu.databinding.FragmentMainBinding;
import ru.kpfu.itis.abiturkfu.entities.Post;


public class MainFragment extends Fragment {
    private MainPageRecyclerViewAdapter adapter;
    private FragmentMainBinding r;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        r = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        setHasOptionsMenu(true);

        initRecyclerView();

        return r.getRoot();
    }

    private void initRecyclerView() {
        adapter = new MainPageRecyclerViewAdapter();

        r.list.setAdapter(adapter);
        r.list.setLayoutManager(new LinearLayoutManager(getContext()));

        Post post = new Post("Высшая школа информационных систем и интеллектуальных технологий", "Средний балл по ЕГЭ:100", null);
        List<Post> posts = new ArrayList<>();
        posts.add(post);
        adapter.setData(posts);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_filter) {
            Intent intent = new Intent(getContext(), FilterActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
