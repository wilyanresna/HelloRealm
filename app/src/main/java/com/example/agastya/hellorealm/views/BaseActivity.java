package com.example.agastya.hellorealm.views;

import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by AgastyA on 1/26/2018.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }
}