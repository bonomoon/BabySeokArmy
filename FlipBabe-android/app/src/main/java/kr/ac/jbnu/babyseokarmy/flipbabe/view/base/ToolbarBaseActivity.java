package kr.ac.jbnu.babyseokarmy.flipbabe.view.base;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import kr.ac.jbnu.babyseokarmy.flipbabe.R;

/**
 * Copyright 2018 BongO Moon All rights reserved.
 *
 * @author BongO Moon(http://github.com/bongOmoon)
 * @since 2018.04.10
 * AndroidX에 있는 Toolbar를 이용하는 경우 이 클래스를 상속해야 한다.
 */

public class ToolbarBaseActivity extends AppCompatActivity {


    protected static String TAG = ToolbarBaseActivity.class.getSimpleName();

    protected Context context;
    protected Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TAG = getClass().getSimpleName();
        context = this;
    }

    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);

        try {
            toolbar = findViewById(R.id.toolbar);
            toolbar.setTitle(getTitle());
            setSupportActionBar(toolbar);
        } catch (Exception e) {
            Log.e(TAG, "Cannot find Toolbar at R.id.toolbar");
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);

        if (toolbar != null)
            toolbar.setTitle(title);
    }

    public void setToolbarVisibility(boolean visible) {
        toolbar.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public void setDisplayHomeAsUpEnable(boolean enable){
        if(getSupportActionBar() == null)
            return;

        getSupportActionBar().setDisplayHomeAsUpEnabled(enable);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            onBackPressed();

        return super.onOptionsItemSelected(item);
    }
}
