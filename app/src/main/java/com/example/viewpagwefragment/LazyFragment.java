package com.example.viewpagwefragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class LazyFragment extends Fragment {

    private static final String TAG = LazyFragment.class.getName();

    //    初始化 页面
    View rootView = null;
    private   boolean   icCreateView=false;
    private   boolean  isCurrentVisibleState=false;  // 当前可见状态
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView");
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutResourse(), container, false);
        }

        initView(rootView);
        icCreateView=true;
//        弥补第一次  不走onCreateView（）
        if(getUserVisibleHint()==true){
            setUserVisibleHint(true);
        }
        return rootView;
    }

    protected abstract int getLayoutResourse();

    protected abstract void initView(View rootView);


    //    判断 fragment  是否可见   //   比onCreateView 执行早
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(icCreateView==true){  //创建了view  再去执行   因为 比onCreateView 执行早
            if (isVisibleToUser && !isCurrentVisibleState) {  // 可见 去加载数据
                dispatchUserVisibleHint(true);
            } else if(!isVisibleToUser && isCurrentVisibleState){
                dispatchUserVisibleHint(false); //  不加见 取消加载
            }
        }

    }

    //  事件分发
    public void dispatchUserVisibleHint(boolean isVisibleToUser) {
        Log.e(TAG, "dispatchUserVisibleHint");
        isCurrentVisibleState=isVisibleToUser;
        if (isVisibleToUser) {
            onFragmentLoad();
        } else {
            onFragmentStop();
        }
    }

    //    停止网络 加载请求
    public void onFragmentStop() {
        Log.e(TAG, "onFragmentStop");
    }

    //    网络 加载请求
    public void onFragmentLoad() {
        Log.e(TAG, "onFragmentStop");
    }

    @Override
    public void onResume() {
        Log.e(TAG, "onResume");
        super.onResume();
//        从可见不交互---》可见可交互
        if(getUserVisibleHint() && !isCurrentVisibleState){
            dispatchUserVisibleHint(true);
        }
    }

    @Override
    public void onPause() {
        Log.e(TAG, "onPause");
        super.onPause();
        //          可见可交互 ---》 从可见不交互
        if(!getUserVisibleHint() && isCurrentVisibleState){
            dispatchUserVisibleHint(false);
        }
    }

    @Override
    public void onDestroyView() {
        Log.e(TAG, "onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
    }
}
