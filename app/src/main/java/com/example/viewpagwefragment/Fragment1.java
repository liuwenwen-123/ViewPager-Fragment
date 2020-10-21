package com.example.viewpagwefragment;

import android.util.Log;
import android.view.View;

public class Fragment1  extends  LazyFragment
{
    private static final String TAG =Fragment1.class.getName() ;

    @Override
    protected int getLayoutResourse() {
        return R.layout.fragment1;
    }

    @Override
    protected void initView(View rootView) {

    }

    @Override
    public void onFragmentLoad() {
        super.onFragmentLoad();
        Log.e(TAG,"onFragmentLoad");
    }

    @Override
    public void onFragmentStop() {
        super.onFragmentStop();
        Log.e(TAG,"onFragmentStop");
    }
}
