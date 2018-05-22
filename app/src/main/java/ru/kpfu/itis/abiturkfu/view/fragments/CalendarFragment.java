package ru.kpfu.itis.abiturkfu.view.fragments;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import ru.kpfu.itis.abiturkfu.App;
import ru.kpfu.itis.abiturkfu.R;
import ru.kpfu.itis.abiturkfu.databinding.FragmentCalendarBinding;
import ru.kpfu.itis.abiturkfu.model.entities.Event;
import ru.kpfu.itis.abiturkfu.model.repository.AbiturientRepository;
import ru.kpfu.itis.abiturkfu.model.repository.ResponseLiveData;
import ru.kpfu.itis.abiturkfu.view.activities.MainActivity;

public class CalendarFragment extends Fragment {
    @Inject
    AbiturientRepository repository;
    private FragmentCalendarBinding r;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        App.getComponent().inject(this);
        r = DataBindingUtil.inflate(inflater, R.layout.fragment_calendar, container, false);

        if (getActivity() != null && ((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Календарь");
        }

        SimpleDateFormat bottomDate = new SimpleDateFormat("EEEE, dd MMMM", new Locale("ru"));
        Date now = new Date(System.currentTimeMillis());
        setDate(now, bottomDate);
        setMonthName(now);
        r.base.compactcalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                setDate(dateClicked, bottomDate);
                List<com.github.sundeepk.compactcalendarview.domain.Event> events = r.base.compactcalendarView.getEvents(dateClicked);
                r.bottom.container.removeAllViewsInLayout();
                for (com.github.sundeepk.compactcalendarview.domain.Event event : events) {
                    Event e = (Event) event.getData();
                    View view = View.inflate(getContext(), R.layout.item_in_bottim_sheet, null);
                    TextView startDate = view.findViewById(R.id.tv_start_time);
                    TextView endDate = view.findViewById(R.id.tv_end_time);
                    TextView title = view.findViewById(R.id.tv_title);
                    TextView address = view.findViewById(R.id.tv_address);
                    String startTime = "";
                    if (e.getStartTime() != null) {
                        startTime = e.getStartTime().toString("kk:mm", new Locale("ru"));
                    }
                    String endTime = "";
                    if (e.getEndTime() != null) {
                        endTime = e.getEndTime().toString("kk:mm", new Locale("ru"));
                    }
                    startDate.setText(startTime);
                    endDate.setText(endTime);
                    title.setText(e.getName());
                    address.setText(e.getAddress());
                    r.bottom.container.addView(view);
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                setMonthName(firstDayOfNewMonth);
            }
        });

        BottomSheetBehavior sheetBehavior = BottomSheetBehavior.from(r.bottom.bottomSheet);
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED)
                    r.bottom.imageView.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_bottom_rectangle));
                else
                    r.bottom.imageView.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_combined_shape));
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        repository.getEvents().observe(
                this,
                this::fill,
                status -> ((MainActivity) getActivity()).showLoading(status == ResponseLiveData.Status.LOADING),
                throwable -> Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show()
        );

        return r.getRoot();
    }

    private void setDate(Date dateClicked, SimpleDateFormat bottomDate) {
        String date = bottomDate.format(dateClicked);
        date = date.substring(0, 1).toUpperCase() + date.substring(1);
        r.bottom.tvDate.setText(date);
    }

    private void setMonthName(Date firstDayOfNewMonth) {
        DateTime dateTime = new DateTime(firstDayOfNewMonth);
        SimpleDateFormat format = new SimpleDateFormat("LLLL", new Locale("ru"));
        String month = format.format(firstDayOfNewMonth);
        month = month.substring(0, 1).toUpperCase() + month.substring(1).toLowerCase();
        r.base.tvMonth.setText(month);
        r.base.tvYear.setText(dateTime.toString("YYYY"));
    }

    private void fill(List<Event> events) {
        for (Event event : events) {
            r.base.compactcalendarView.addEvent(
                    new com.github.sundeepk.compactcalendarview.domain.Event(
                            Color.rgb(255, 255, 255),
                            event.getStartTime().getMillis(),
                            event
                    )
            );
        }
    }

}
