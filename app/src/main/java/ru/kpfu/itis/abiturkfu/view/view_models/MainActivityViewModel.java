package ru.kpfu.itis.abiturkfu.view.view_models;

import android.arch.lifecycle.ViewModel;
import android.support.v4.app.Fragment;

import ru.kpfu.itis.abiturkfu.view.fragments.MainFragment;

public class MainActivityViewModel extends ViewModel {
    private Class<? extends Fragment> lastFragmentClazz = MainFragment.class;

    public Class<? extends Fragment> getLastFragmentClazz() {
        if (lastFragmentClazz == null) {
            return MainFragment.class;
        }
        return lastFragmentClazz;
    }

    public void setLastFragmentClazz(Class<? extends Fragment> lastFragmentClazz) {
        this.lastFragmentClazz = lastFragmentClazz;
    }
}
