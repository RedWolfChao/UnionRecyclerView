#RecyclerView联动

**应用场景 :**

 1. **同一个界面多个RecyclerView**
 2. **同时只会显示一个**
 3. **例如用RecyclerView当做TabLayout,某个悬浮,某个正常的情况**
 4. **...不解释了(PS:能用到的就用到了)**

**思路 :**

 1. **监听RecyclerView的滑动**
 2. **当RecyclerView滑动的时候,使用computeHorizontalScrollOffset()得到指定RecyclerView的偏移量**
 3. **其它的RecyclerView调用scrollBy(x,y)来进行代码操作平移**

**核心代码 :**

```
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

```

