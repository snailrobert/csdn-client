package com.robert.today.csdn_client.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.robert.today.csdn_client.R;

/**
 * Created by chenjun06 on 2015/1/4.
 */
public class TestFragment extends BaseFragment {

    public static TestFragment newInstance(int showText) {
        TestFragment testFragment = new TestFragment();
        Bundle args = new Bundle();
        args.putInt("show_text", showText);
        testFragment.setArguments(args);
        return testFragment;
    }

    @Override
    public void onBackPress() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View backView = view.findViewById(R.id.test_fragment_id);
        TextView textView = (TextView) view.findViewById(R.id.test_text_id);

        int showText = getArguments().getInt("show_text", 0);
        switch(showText) {
            case 11:
                backView.setBackgroundColor(R.color.purple);
                textView.setText("11");
                break;
            case 22:
                backView.setBackgroundColor(R.color.blue);
                textView.setText("22");
                break;
            default:
                break;
        }
    }
}
