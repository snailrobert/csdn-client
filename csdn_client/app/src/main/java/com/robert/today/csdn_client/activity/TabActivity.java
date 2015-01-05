package com.robert.today.csdn_client.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.Window;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.robert.today.csdn_client.R;

import com.robert.today.csdn_client.fragment.OneChildFragment;
import com.robert.today.csdn_client.fragment.TestFragment;

/**
 * Created by chenjun06 on 2015/1/4.
 */
public class TabActivity extends SherlockFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 使ActionBar透明
        requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.activity_tab);
//        initActionBar();
        initSupportActionBar();
    }

    /**
     * 使用android3.0以上原生的ActionBar
     */
    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("TabActivity");
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_HOME_AS_UP);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        TestFragment oneFragment = TestFragment.newInstance(11);
        actionBar.addTab(actionBar.newTab().setText("test1")
                .setTabListener(new TestTabListener(oneFragment)));
        TestFragment twoFragment = TestFragment.newInstance(22);
        actionBar.addTab(actionBar.newTab().setText("test2")
                .setTabListener(new TestTabListener(twoFragment)));



    }

    /**
     * 使用ActionBarSherlock，以支持Android2.3
     */
    private void initSupportActionBar() {
        com.actionbarsherlock.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("TabActivity");
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_HOME_AS_UP);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        OneChildFragment oneFragment = OneChildFragment.newInstance(11);
        actionBar.addTab(actionBar.newTab().setText("test1")
                .setTabListener(new SupportTestTabListener(oneFragment)));
        OneChildFragment twoFragment = OneChildFragment.newInstance(22);
        actionBar.addTab(actionBar.newTab().setText("test2")
                .setTabListener(new SupportTestTabListener(twoFragment)));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        ActionBar bar = getSupportActionBar();
        switch(event.getAction()) {
            case MotionEvent.ACTION_UP:
                if(bar.isShowing()) {
                    bar.hide();
                } else {
                    bar.show();
                }
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
       switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                break;
       }
       return super.onMenuItemSelected(featureId, item);
    }

    private class SupportTestTabListener implements com.actionbarsherlock.app.ActionBar.TabListener {

        private android.support.v4.app.Fragment mFragment;

        public SupportTestTabListener(android.support.v4.app.Fragment fragment) {
            this.mFragment = fragment;
        }

        @Override
        public void onTabSelected(com.actionbarsherlock.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {
            fragmentTransaction.add(R.id.fragment_container_id, mFragment, null);
        }

        @Override
        public void onTabUnselected(com.actionbarsherlock.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {
            fragmentTransaction.remove(mFragment);
        }

        @Override
        public void onTabReselected(com.actionbarsherlock.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

        }
    }

    private class TestTabListener implements ActionBar.TabListener {

        private Fragment mFragment;

        public TestTabListener(Fragment fragment) {
            this.mFragment = fragment;
        }

        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            ft.add(R.id.fragment_container_id, mFragment, null);
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
            ft.remove(mFragment);
        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

        }
    }
}
