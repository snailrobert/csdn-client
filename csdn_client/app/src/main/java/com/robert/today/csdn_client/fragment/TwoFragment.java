package com.robert.today.csdn_client.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.robert.today.csdn_client.R;
import com.robert.today.csdn_client.model.event.FirstEventMessage;
import com.robert.today.csdn_client.utils.ImageLoaderTools;

import de.greenrobot.event.EventBus;

/**
 * Created by chenjun06 on 2014/12/25.
 */
public class TwoFragment extends BaseFragment {

    private static TwoFragment mTwoFragment;
    private AQuery mAq;

    public static TwoFragment newInstance() {
        if(null == mTwoFragment) {
            mTwoFragment = new TwoFragment();
            mTwoFragment.setType("TwoFragment");
        }
        return mTwoFragment;
    }

    @Override
    public void onBackPress() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, null);
        initViews(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    private void initViews(View view) {
        mAq = new AQuery(view);
        mAq.id(R.id.introduceId).text("---test test---").visible().clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "---clicked test---", Toast.LENGTH_SHORT).show();
            }
        });
        ImageView image = (ImageView) view.findViewById(R.id.pictureId);
        String imageUrl1 = "http://g.hiphotos.baidu.com/image/pic/item/7c1ed21b0ef41bd5d3a0899f52da81cb38db3ded.jpg";
        String imageUrl2 = "http://e.hiphotos.baidu.com/image/pic/item/f636afc379310a55872b28abb44543a982261065.jpg";
        String imageUrl3 = "http://c.hiphotos.baidu.com/image/pic/item/4bed2e738bd4b31c85f0afb585d6277f9f2ff8eb.jpg";
        mAq.id(R.id.pictureId2).image(imageUrl2, true, true
            , 0, R.drawable.ic_drawer).visible();
        mAq.id(R.id.webview).progress(R.id.progressbar).webImage(imageUrl3);
        ImageLoaderTools.getInstance().displayOneImage(imageUrl1, image);

    }

    // receive event and execute event
    public void onEventMainThread(FirstEventMessage firstEvent) {
        if(firstEvent.isShowImage()) {
            mAq.id(R.id.pictureId2).visible();
        } else {
            mAq.id(R.id.pictureId2).gone();
        }
    }
}
