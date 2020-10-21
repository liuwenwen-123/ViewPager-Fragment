package com.example.viewpagwefragment;

import android.util.Log;
import android.view.View;

public class Fragment2 extends  LazyFragment
{
    private static final String TAG =Fragment2.class.getName() ;

    @Override
    protected int getLayoutResourse() {
        return R.layout.fragment2;
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
