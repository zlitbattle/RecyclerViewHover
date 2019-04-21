package com.zl.androidstudy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author zhangling
 * date 2019/4/19 23:14
 * description: 主页
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.hover)
    TextView hover;
    private int hoverHeight;
    /**
     * 存放列表显示的第一项的位置
     */
    private int currentIndex;
    private LinearLayoutManager linearLayoutManager;
    private Integer[] images = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6, R.drawable.img7,
            R.drawable.img8, R.drawable.img9, R.drawable.img10, R.drawable.img11, R.drawable.img12, R.drawable.img13, R.drawable.img14, R.drawable.img15,
            R.drawable.img16, R.drawable.img17, R.drawable.img18, R.drawable.img19, R.drawable.img20, R.drawable.img21, R.drawable.img22, R.drawable.img23,
            R.drawable.img24, R.drawable.img25, R.drawable.img26, R.drawable.img27, R.drawable.img28, R.drawable.img29, R.drawable.img30};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        SuspensionAdapter adapter = new SuspensionAdapter(Arrays.asList(images), this);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                hoverHeight = hover.getHeight();
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //获取列表中显示的第二项
                //findViewByPosition:根据位置查找列表中的item,返回该位置的View
                View currentView = linearLayoutManager.findViewByPosition(currentIndex + 1);
                if (currentView == null) {
                    return;
                }

                //第二项的Y坐标
                int oldTop = currentView.getTop();
                //第二项的Y坐标小于hover的高度（发生重叠）
                if (oldTop <= hoverHeight) {
                    //计算hover需要移动的Y坐标
                    int top = -(hoverHeight - oldTop);
                    //将hover上移
                    hover.setY(top);
                } else {
                    //未发生重叠，hover固定
                    hover.setY(0);
                }

                //当列表显示在屏幕顶部的元素发生改变时，同时修改currentIndex的值，并将hover的内容修改
                //findFirstVisibleItemPosition:查找列表中第一个显示item的下标
                int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                if (currentIndex != firstVisibleItemPosition) {
                    currentIndex = firstVisibleItemPosition;
                    hover.setY(0);
                    hover.setText(String.valueOf(currentIndex));
                }
            }
        });
    }
}