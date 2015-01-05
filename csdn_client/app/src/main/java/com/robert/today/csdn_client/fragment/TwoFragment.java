package com.robert.today.csdn_client.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.robert.today.csdn_client.R;
import com.robert.today.csdn_client.utils.ImageLoaderTools;

/**
 * Created by chenjun06 on 2014/12/25.
 */
public class TwoFragment extends BaseFragment {

    private static TwoFragment mTwoFragment;

    public static TwoFragment newInstance() {
        if(null == mTwoFragment) {
            mTwoFragment = new TwoFragment();
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

    private void initViews(View view) {
        AQuery aq = new AQuery(view);
        aq.id(R.id.introduceId).text("---test test---").visible().clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "---clicked test---", Toast.LENGTH_SHORT).show();
            }
        });
        ImageView image = (ImageView) view.findViewById(R.id.pictureId);
        String imageUrl1 = "http://g.hiphotos.baidu.com/image/pic/item/7c1ed21b0ef41bd5d3a0899f52da81cb38db3ded.jpg";
        String imageUrl2 = "http://e.hiphotos.baidu.com/image/pic/item/f636afc379310a55872b28abb44543a982261065.jpg";
        String imageUrl3 = "http://c.hiphotos.baidu.com/image/pic/item/4bed2e738bd4b31c85f0afb585d6277f9f2ff8eb.jpg";
        aq.id(R.id.pictureId2).image(imageUrl2, true, true
            , 0, R.drawable.ic_drawer).visible();
        aq.id(R.id.webview).progress(R.id.progressbar).webImage(imageUrl3);
        ImageLoaderTools.getInstance().displayOneImage(imageUrl1, image);

    }
}
