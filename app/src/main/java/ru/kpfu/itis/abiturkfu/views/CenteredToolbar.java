package ru.kpfu.itis.abiturkfu.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.widget.TextView;

import ru.kpfu.itis.abiturkfu.R;

public class CenteredToolbar extends Toolbar {
    private TextView titleView;

    public CenteredToolbar(Context context) {
        this(context, null);
    }

    public CenteredToolbar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, android.support.v7.appcompat.R.attr.toolbarStyle);
    }

    public CenteredToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.toolbar_text_layout, this);
        titleView = this.findViewById(R.id.toolbar_title);
    }

    @Override
    public void setTitle(CharSequence title) {
        titleView.setText(title);
        super.setTitle("");
    }
}
