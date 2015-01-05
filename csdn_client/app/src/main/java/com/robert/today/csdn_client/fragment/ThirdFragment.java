package com.robert.today.csdn_client.fragment;

import android.content.Intent;
import android.os.Bundle;
//import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidquery.AQuery;
import com.robert.today.csdn_client.R;
import com.robert.today.csdn_client.activity.TabActivity;

/**
 * Created by chenjun06 on 2014/12/25.
 */
public class ThirdFragment extends BaseFragment {

    private static ThirdFragment mThirdFragment;

    public static ThirdFragment newInstance() {
        if(null == mThirdFragment) {
            mThirdFragment = new ThirdFragment();
        }
        return mThirdFragment;
    }

    @Override
    public void onBackPress() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_third, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AQuery aq = new AQuery(view);
        aq.id(R.id.click_button_id).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(getActivity(), TabActivity.class);
                startActivity(i);
            }
        }).visible();
    }
}
