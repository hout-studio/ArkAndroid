package com.hout.ark.activity;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by brlnt on 10/10/16.
 */

public abstract class ArkAppCompatActivity extends AppCompatActivity {

    @CallSuper
    protected void onCreate(Bundle savedInstanceState, @LayoutRes int id) {
        super.onCreate(savedInstanceState);
        setContentView(id);

        // Bind butterknife
        ButterKnife.bind(this);

        // TODO: 10/9/16 make presenter initialization available in here, for now let it init in each activity
    }
}