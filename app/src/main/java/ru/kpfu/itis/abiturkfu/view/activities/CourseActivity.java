package ru.kpfu.itis.abiturkfu.view.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import ru.kpfu.itis.abiturkfu.R;
import ru.kpfu.itis.abiturkfu.model.entities.Course;
import ru.kpfu.itis.abiturkfu.model.entities.CourseCategory;
import ru.kpfu.itis.abiturkfu.model.entities.CourseFee;

public class CourseActivity extends AppCompatActivity {

    LinearLayout container;
    Button goToSite;
    Course course;
    TextView courseInfo;
    TextView courseEnlistment;

    private static final String KEY_COURSE_ID = "COURSE_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        courseInfo = findViewById(R.id.course_info);
        courseEnlistment = findViewById(R.id.course_enlistment);

        goToSite = findViewById(R.id.btn_web_site);
        goToSite.setOnClickListener(view -> {
            if (course.getWebsite()!=null) {
                Uri website = Uri.parse(course.getWebsite());
                Intent intent = new Intent(Intent.ACTION_VIEW, website);
                startActivity(intent);
            }

        });

        container = findViewById(R.id.view_container);
    }

    public static void start (Context context, int courseId) {
        Intent starter = new Intent(context, CourseActivity.class);
        starter.putExtra(KEY_COURSE_ID, courseId);
        context.startActivity(starter);
    }

    LinearLayout inflateCourseCategory(CourseCategory category) {
        View headerView = View.inflate(this, R.layout.course_header_item, null);
        TextView tv_type = headerView.findViewById(R.id.tv_type);
        tv_type.setText(category.getTitle());

        container.addView(headerView);
        return headerView.findViewById(R.id.container);
    }

    void inflateCourseFee(LinearLayout container, CourseFee fee) {
        View view = View.inflate(this, R.layout.course_fee_item, null);
        TextView tv_type = view.findViewById(R.id.tv_type);
        TextView tv_fee = view.findViewById(R.id.tv_fee);
        TextView tv_amount = view.findViewById(R.id.tv_rubles);

        tv_type.setText(fee.getTitle());
        tv_fee.setText(fee.getPrice());

        if (fee.getPayPerYear()) {
            tv_amount.setText("руб. в год");
        } else {
            tv_amount.setText("руб.");
        }

        container.addView(view);

    }

    void fill(Course course) {

        this.course = course;

        courseInfo.setText(course.getDescription());
        courseEnlistment.setText(course.getEnlistment());
        //ToDo: fill

        for (CourseCategory category : course.getCourseCategories()) {
            int id = category.getId();
            LinearLayout container = inflateCourseCategory(category);
            for (CourseFee fee : course.getCourseFees()) {
                if (id == fee.getCourseCategoryId()){
                    inflateCourseFee(container, fee);
                }
            }
        }
    }

}
