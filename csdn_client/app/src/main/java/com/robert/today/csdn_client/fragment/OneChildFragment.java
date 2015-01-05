package com.robert.today.csdn_client.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.robert.today.csdn_client.R;

/**
 * Created by chenjun06 on 2014/12/26.
 */
public class OneChildFragment extends BaseFragment {

    private static String COLOR_BACKGROUND = "color_background";
    private int mPos = 0;

    private View mContentView;
    private TextView mTestText;

    public static OneChildFragment newInstance(int pos) {
        OneChildFragment f = new OneChildFragment();
        Bundle args = new Bundle();
        args.putInt("pos_tag", pos);
        f.setArguments(args);
        return f;
    }

    public OneChildFragment() {
    }

    @Override
    public void onBackPress() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(null != savedInstanceState) {
            mPos = savedInstanceState.getInt(COLOR_BACKGROUND, 0);
        }
        return inflater.inflate(R.layout.fragment_one_child, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(null != savedInstanceState) {
            mPos = savedInstanceState.getInt(COLOR_BACKGROUND, 0);
        }

        if(null != getArguments()) {
            mPos = getArguments().getInt("pos_tag", 0);
        }

        mContentView = view.findViewById(R.id.fragment_one_child_main);
        mTestText = (TextView) view.findViewById(R.id.test_text);
        mTestText.setText(String.valueOf(mPos));
        mTestText.setTextColor(getBackgroundColor(mPos));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(null != outState) {
            outState.putInt(COLOR_BACKGROUND, mPos);
        }

    }

    private int getBackgroundColor(int pos) {
        switch(pos) {
            case 0:
                return R.color.yellow;
            case 1:
                return R.color.blue;
            case 2:
                return R.color.green;
            case 3:
                return R.color.red;
            case 4:
                return R.color.yellow;
            case 5:
                return R.color.purple;
        }
        return R.color.yellow;
    }
}
