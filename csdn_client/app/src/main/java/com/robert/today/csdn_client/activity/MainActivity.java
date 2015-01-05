package com.robert.today.csdn_client.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.robert.library.view.PagerSlidingTabStrip;
import com.robert.today.csdn_client.R;
import com.robert.today.csdn_client.adapter.LeftMenuAdapter;
import com.robert.today.csdn_client.model.LeftMenuItem;
import com.robert.today.csdn_client.utils.ImageLoaderTools;

import net.simonvt.menudrawer.MenuDrawer;

import java.util.ArrayList;
import java.util.List;

import com.robert.today.csdn_client.fragment.OneFragment;
import com.robert.today.csdn_client.fragment.ThirdFragment;
import com.robert.today.csdn_client.fragment.TwoFragment;

/**
 * Created by chenjun06 on 2015/1/5.
 */
public class MainActivity extends SherlockFragmentActivity implements LeftMenuAdapter.MenuItemListener  {

    String[] mTitles = {"页面1", "页面2", "页面3"};

    // declare view pager and pager tab strip
    private ViewPager mViewPager;
    private PagerSlidingTabStrip mPagerTab;
    private CustomFragmentAdapter mCustomAdapter;

    // declare menu drawer and recycler view
    private MenuDrawer mMenuDrawer;
    private RecyclerView mLeftMenuListView;
    private LeftMenuAdapter mMenuAdapter;

    // screen density
    private DisplayMetrics dm;

    private List<KeyDownListener> mKeyDownListenerList;
    private List<LeftMenuItem> mItems;
    private int mCurrentFragmentPos = 0;
    private OneFragment mOneFragment;
    private int mPagerOffsetPixels;
    private int mPagerPosition;

    public interface KeyDownListener {
        public void onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dm = getResources().getDisplayMetrics();
        initActionBar();
        ImageLoaderTools.getInstance().initImageLoader(getApplicationContext());
        mKeyDownListenerList = new ArrayList<KeyDownListener>();
        initViews();
    }

    @Override
    public void onBackPressed() {
        for(KeyDownListener keyDownListener : mKeyDownListenerList) {
            if(null != keyDownListener) {
                keyDownListener.onBackPressed();
            }
        }

        super.onBackPressed();

        final int drawerState = mMenuDrawer.getDrawerState();
        if (drawerState == MenuDrawer.STATE_OPEN || drawerState == MenuDrawer.STATE_OPENING) {
            mMenuDrawer.closeMenu();
            return;
        }
    }

    @Override
    public boolean onOptionsItemSelected(com.actionbarsherlock.view.MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mMenuDrawer.toggleMenu();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMenuItemClicked(View view) {
        int pos = (Integer) view.getTag();
        if(-1 != pos) {
            if(null == mOneFragment) {
                return;
            }

            if(mOneFragment.isResumed()) {
                mOneFragment.replaceFragment(pos);
            }
        }

        mMenuDrawer.closeMenu();
    }

    private void initActionBar() {
        ActionBar bar = getSupportActionBar();
        bar.setCustomView(R.layout.activity_title);
        bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        bar.setDisplayShowCustomEnabled(true);
    }

    private void initLeftMenu() {
        initMenuAdapter();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mLeftMenuListView.setLayoutManager(layoutManager);
        mLeftMenuListView.setAdapter(mMenuAdapter);
    }

    private void initMenuAdapter() {
        mItems = new ArrayList<LeftMenuItem>();
        mItems.add(new LeftMenuItem("Cat 1"));
        mItems.add(new LeftMenuItem("Item 1", R.drawable.ic_action_refresh_dark, 0));
        mItems.add(new LeftMenuItem("Item 2", R.drawable.ic_action_select_all_dark, 1));
        mItems.add(new LeftMenuItem("Cat 2"));
        mItems.add(new LeftMenuItem("Item 3", R.drawable.ic_action_refresh_dark, 2));
        mItems.add(new LeftMenuItem("Item 4", R.drawable.ic_action_select_all_dark, 3));
        mItems.add(new LeftMenuItem("Cat 3"));
        mItems.add(new LeftMenuItem("Item 5", R.drawable.ic_action_refresh_dark, 4));
        mItems.add(new LeftMenuItem("Item 6", R.drawable.ic_action_select_all_dark, 5));

        mMenuAdapter = new LeftMenuAdapter();
        mMenuAdapter.setItemList(mItems);
        mMenuAdapter.setMenuItemListener(this);
    }

    public void registerKeyDownListener(KeyDownListener keyDownListener) {
        mKeyDownListenerList.add(keyDownListener);
    }

    public void unregisterKeyDownListener(KeyDownListener keyDownListener) {
        mKeyDownListenerList.remove(keyDownListener);
    }

    /**
     072.
     * 对PagerSlidingTabStrip的各项属性进行赋值。
     073.
     */
    private void setTabsValue() {
        // 设置Tab是自动填充满屏幕的
        mPagerTab.setShouldExpand(true);
        // 设置Tab的分割线是透明的
        mPagerTab.setDividerColor(Color.TRANSPARENT);
        // 设置Tab底部线的高度
        mPagerTab.setUnderlineHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 1, dm));
        // 设置Tab Indicator的高度
        mPagerTab.setIndicatorHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 4, dm));
        // 设置Tab标题文字的大小
        mPagerTab.setTextSize((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 16, dm));
        // 设置Tab Indicator的颜色
        mPagerTab.setIndicatorColor(Color.parseColor("#45c01a"));
        // 取消点击Tab时的背景色
        mPagerTab.setTabBackground(0);
    }

    private void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mCustomAdapter = new CustomFragmentAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mCustomAdapter);
        mViewPager.setCurrentItem(0);

        mPagerTab = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        //设置pagerTabStrip
        setTabsValue();
        mPagerTab.setViewPager(mViewPager);
        mPagerTab.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mPagerPosition = position;
                mPagerOffsetPixels = positionOffsetPixels;
            }
        });

        mMenuDrawer = (MenuDrawer) findViewById(R.id.drawer);
        mLeftMenuListView = (RecyclerView) findViewById(R.id.left_recycler_view);
        initLeftMenu();

        mMenuDrawer.setTouchMode(MenuDrawer.TOUCH_MODE_FULLSCREEN);
        mMenuDrawer.setSlideDrawable(R.drawable.ic_drawer);
        mMenuDrawer.setOnInterceptMoveEventListener(new MenuDrawer.OnInterceptMoveEventListener() {
            @Override
            public boolean isViewDraggable(View v, int dx, int x, int y) {
                if (v == mViewPager) {
                    return !(mPagerPosition == 0 && mPagerOffsetPixels == 0) || dx < 0;
                }

                return false;
            }
        });
    }

    class CustomFragmentAdapter extends FragmentPagerAdapter {

        public CustomFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch(i) {
                case 0:
                    mOneFragment = OneFragment.newInstance(mCurrentFragmentPos);
                    return mOneFragment;
                case 1:
                    return TwoFragment.newInstance();
                case 2:
                    return ThirdFragment.newInstance();
            }
            return OneFragment.newInstance(mCurrentFragmentPos);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }
    }
}
