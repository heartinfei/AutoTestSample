package com.example.heartinfei.testsample.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.example.heartinfei.testsample.R;


/**
 * 简介：ProgressLayout
 *
 * @author 王强（249346528@qq.com） 2017/9/15
 */
public class ProgressLayout extends ConstraintLayout {
    private final ConstraintLayout.LayoutParams layoutParams =
            new ConstraintLayout.LayoutParams(LayoutParams.MATCH_CONSTRAINT, LayoutParams.MATCH_CONSTRAINT);
    private View progressView;
    private int progressLayoutResId;

    public ProgressLayout(Context context) {
        super(context);
        init(null, 0);
    }

    public ProgressLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public ProgressLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    public static ProgressLayout of(Activity actvity) {
        return actvity.findViewById(R.id.progress_layout_id);
    }

    public static ProgressLayout of(Fragment fragment) {
        return fragment.getView().findViewById(R.id.progress_layout_id);
    }

    private void init(AttributeSet attrs, int defStyle) {
        if (getId() == -1) {
            setId(R.id.progress_layout_id);
        }
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.ProgressLayout, defStyle, 0);
        progressLayoutResId = a.getResourceId(R.styleable.ProgressLayout_progressLayout,
                R.layout.default_progress_layout);
        a.recycle();
        layoutParams.topToTop = LayoutParams.PARENT_ID;
        layoutParams.bottomToBottom = LayoutParams.PARENT_ID;
        layoutParams.leftToLeft = LayoutParams.PARENT_ID;
        layoutParams.rightToRight = LayoutParams.PARENT_ID;
    }

    /**
     * 当前的loading状态
     *
     * @return true 正显示loading
     */
    public boolean isProgressing() {
        return progressView != null && progressView.isShown();
    }

    /**
     * 显示／隐藏 加载提示界面
     *
     * @param show true显示
     */
    public void showProgress(boolean show) {
        if (show) {
            progressView = LayoutInflater.from(getContext())
                    .inflate(progressLayoutResId, null, true);
            layoutParams.width = getMeasuredWidth();
            layoutParams.height = getMeasuredHeight();
            addView(progressView, layoutParams);
        } else {
            if (progressView != null) {
                if (progressView.isShown()) {
                    removeView(progressView);
                }
                progressView = null;
            }
        }
    }//end showProgress
}
