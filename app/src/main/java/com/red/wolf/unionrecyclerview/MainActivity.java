package com.red.wolf.unionrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.red.wolf.unionrecyclerview.adapter.ListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView mMainLessonRecyclerView;
    RecyclerView mMainLessonFloatRecyclerView;
    LinearLayoutManager mMainFloatManager;
    LinearLayoutManager mMainManager;

    List<String> mList = new ArrayList<>();

    //  记录RecyclerView上次滑动的偏移量
    //  RecyclerView的偏移量默认为 0 第一次进入的判断是-1 但其他情况(反着滑)偏移量为负
    private int mFloatOffset = -1;
    private int mOffset = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainLessonRecyclerView = (RecyclerView) findViewById(R.id.mMainLessonRecyclerView);
        mMainLessonFloatRecyclerView = (RecyclerView) findViewById(R.id.mMainLessonFloatRecyclerView);
        //  模拟数据
        for (int i = 0; i < 20; i++) {
            mList.add("_" + i);
        }

        initRecyclerView();
    }

    private void initRecyclerView() {
        mMainManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        mMainFloatManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);

        mMainLessonRecyclerView.setLayoutManager(mMainManager);
        mMainLessonFloatRecyclerView.setLayoutManager(mMainFloatManager);

        //  监听RecyclerView 滑动
        mMainLessonRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //  newState RecyclerView的滑动状态
                //  0 滑动停止
                //  1 屏幕没停且手在屏幕上
                //  2 屏幕没停 惯性滑动
                //  if (newState == RecyclerView.SCROLL_STATE_IDLE) {}
                //  if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {}
                //  if (newState == RecyclerView.SCROLL_STATE_SETTLING) {}
                //  表示没有被赋值 那么就赋值
                if (mOffset == -1) {
                    //  将此时RecyclerView的水平偏移量拿到
                    mOffset = mMainLessonRecyclerView.computeHorizontalScrollOffset();
                    //  然后 对另一个RecyclerView 进行偏移操作
                    //  scrollBy 从当前偏移量 移动mOffset个偏移量
                    mMainLessonFloatRecyclerView.scrollBy(mOffset, 0);
                } else {
                    //  证明不是第一次从0开始滑动,那么X的算法就变了 需要用当前的偏移量减去上次的偏移量
                    mMainLessonFloatRecyclerView.scrollBy(mMainLessonRecyclerView.computeHorizontalScrollOffset() - mOffset, 0);
                    //  更新偏移量
                    mOffset = mMainLessonRecyclerView.computeHorizontalScrollOffset();
                }

            }
        });
        //  同理
        mMainLessonFloatRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (mFloatOffset == -1) {
                    mFloatOffset = mMainLessonFloatRecyclerView.computeHorizontalScrollOffset();
                    mMainLessonRecyclerView.scrollBy(mFloatOffset, 0);
                } else {
                    mMainLessonRecyclerView.scrollBy(mMainLessonFloatRecyclerView.computeHorizontalScrollOffset() - mFloatOffset, 0);
                    mFloatOffset = mMainLessonFloatRecyclerView.computeHorizontalScrollOffset();
                }
            }
        });
        ListAdapter ada = new ListAdapter(this, mList);
        mMainLessonRecyclerView.setAdapter(ada);
        mMainLessonFloatRecyclerView.setAdapter(ada);
//        mMainLessonRecyclerView.setAdapter(new ListAdapter(this, mList));
//        mMainLessonFloatRecyclerView.setAdapter(new ListFloatAdapter(this, mList));


    }
}
