package com.robert.today.csdn_client.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.robert.today.csdn_client.R;

public class ProgressBarView extends LinearLayout {
    public static final int RETRY_PROGRESSBAR_SHOW_TIME =  1 * 1000;//用户重试操作的时候 显示进度条的时间
    private static final int DEFAULT_PROGRESS_RES_ID = R.drawable.ic_launcher;
    private static final int DEFAULT_HOLDER_RES_ID = R.drawable.base_empty_view;
    private ProgressBar progressBar;
    private ImageView holderView;
    private View view;
    private TextView descText;

    public ProgressBarView(Context context) {
        super(context);
    }

    public ProgressBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = LayoutInflater.from(context).inflate(R.layout.layout_loading_bar, this, true);
        progressBar = (ProgressBar) findViewById(R.id.base_loading_progressbar);
        holderView = (ImageView) findViewById(R.id.base_loading_icon);
        descText = (TextView) findViewById(R.id.base_loading_desc);
        view.setOnClickListener(null);
    }

    public void hide() {
        view.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        holderView.setVisibility(View.GONE);
    }

    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        holderView.setVisibility(View.VISIBLE);
        holderView.setImageResource(DEFAULT_PROGRESS_RES_ID);
    }

    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        holderView.setVisibility(View.VISIBLE);
    }

    public void showHolderView(int resId) {
        showHolderView(resId, null);
    }

    public void showDescText(int stringsId){
        progressBar.setVisibility(View.GONE);
        holderView.setVisibility(View.GONE);
        descText.setVisibility(View.VISIBLE);
        descText.setText(stringsId);
    }

    public void showHolderView(int resId, OnClickListener onClickListener) {
        hideProgress();
        holderView.setImageResource(resId);
        holderView.setOnClickListener(onClickListener);
    }

    public void setHolderViewOnclickListener(OnClickListener onClickListener) {
        holderView.setOnClickListener(onClickListener);
    }

    public void showRetryView(OnClickListener onClickListener) {
        showHolderView(DEFAULT_HOLDER_RES_ID, onClickListener);
    }
}
