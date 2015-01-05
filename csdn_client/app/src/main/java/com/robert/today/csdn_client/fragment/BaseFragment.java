package com.robert.today.csdn_client.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robert.today.csdn_client.activity.MainActivity;

import java.lang.reflect.Field;

/**
 * Created by chenjun06 on 2014/12/26.
 */
public abstract class BaseFragment extends Fragment implements MainActivity.KeyDownListener {

    public abstract void onBackPress();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (null != getActivity() && getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).registerKeyDownListener(this);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != getActivity() && getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).unregisterKeyDownListener(this);
        }
    }

    @Override
    public void onDetach() {
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            new RuntimeException(e);
        } catch (IllegalAccessException e) {
            new RuntimeException(e);
        }
        super.onDetach();
    }

    @Override
    public void onBackPressed() {
        onBackPress();
    }
}
