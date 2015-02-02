package com.robert.today.csdn_client.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robert.today.csdn_client.R;

/**
 * Created by chenjun06 on 2014/12/25.
 */
public class OneFragment extends BaseFragment {

    private static final String NUM_FRAGMENT = "num_fragment";

    private static OneFragment mOneFragment;

    private int mCurrentFragmentPos = -1;

    public static OneFragment newInstance(int currentPos) {
        if(null == mOneFragment) {
            mOneFragment = new OneFragment();
            mOneFragment.setType("OneFragment");
            Bundle bundle = new Bundle();
            bundle.putInt("current_pos", currentPos);
            mOneFragment.setArguments(bundle);
        }
        return mOneFragment;
    }

    @Override
    public void onBackPress() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(null != savedInstanceState) {
            mCurrentFragmentPos = savedInstanceState.getInt("pos_tag", 0);
        }
        return inflater.inflate(R.layout.fragment_one, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(null != savedInstanceState) {
            mCurrentFragmentPos = savedInstanceState.getInt("pos_tag", 0);
        }
        initViews(view);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(null != outState) {
            outState.putInt("pos_tag", mCurrentFragmentPos);
        }
    }

    private void initViews(View view) {
        mCurrentFragmentPos = getArguments().getInt("current_pos", 0);
        initMainView();
    }

    private void initMainView() {
        addFragment();
    }

    private void addFragment() {
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction tx = fm.beginTransaction();
        if(null != fm.getFragments() && fm.getFragments().size() > 0) {
            return;
        }
        tx.add(R.id.fragment_back, OneChildFragment.newInstance(mCurrentFragmentPos), "0");
        tx.commitAllowingStateLoss();
    }

    public void replaceFragment(int pos) {
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction tx = fm.beginTransaction();
        tx.replace(R.id.fragment_back, OneChildFragment.newInstance(pos), String.valueOf(pos));
        tx.addToBackStack(null);
        tx.commitAllowingStateLoss();
    }
}
