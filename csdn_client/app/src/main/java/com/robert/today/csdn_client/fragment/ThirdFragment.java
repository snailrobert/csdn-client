package com.robert.today.csdn_client.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
//import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.androidquery.AQuery;
import com.robert.library.volley.Response;
import com.robert.library.volley.VolleyError;
import com.robert.library.volley.toolbox.ImageLoader;
import com.robert.library.volley.toolbox.ImageRequest;
import com.robert.library.volley.toolbox.NetworkImageView;
import com.robert.library.volley.toolbox.StringRequest;
import com.robert.library.volley.toolbox.Volley;
import com.robert.today.csdn_client.R;
import com.robert.today.csdn_client.activity.TabActivity;
import com.robert.today.csdn_client.model.GlobalStagedData;
import com.robert.today.csdn_client.model.LruImageCache;
import com.robert.today.csdn_client.utils.ImageLoaderTools;

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
                executeVolleyTest();
            }
        }).visible();

        String imageUrl = "http://f.hiphotos.baidu.com/image/pic/item/342ac65c10385343264531289113b07eca808835.jpg";
        ImageView imageView = (ImageView) view.findViewById(R.id.one_image_id);
        readBitmapViaVolley(imageUrl, imageView);

        NetworkImageView netImageView = (NetworkImageView) view.findViewById(R.id.two_image_id);
        netImageView.setDefaultImageResId(R.drawable.ic_launcher);
        netImageView.setErrorImageResId(R.drawable.ic_drawer);
        netImageView.setImageUrl(imageUrl,
                new ImageLoader(GlobalStagedData.getInstance().getVolleyRequestQueue(),
                        LruImageCache.instance()));
    }

    private void readBitmapViaVolley(String imgUrl, final ImageView imageView) {
        ImageRequest imgRequest = new ImageRequest(imgUrl,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap arg0) {
                        // TODO Auto-generated method stub
                        imageView.setImageBitmap(arg0);
                    }
                },
                300,
                200,
                Bitmap.Config.ARGB_8888,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError arg0) {
                        imageView.setImageResource(R.drawable.ic_drawer);
                    }
                });
        GlobalStagedData.getInstance().getVolleyRequestQueue().add(imgRequest);
    }

    private void executeVolleyTest() {
        StringRequest stringRequest = new StringRequest("http://www.baidu.com/",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG_CH", "---response == " + response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG_CH", "---error == " + error);
            }
        });
        GlobalStagedData.getInstance().getVolleyRequestQueue().add(stringRequest);
    }

    private void startTabActivity() {
        Intent i = new Intent();
        i.setClass(getActivity(), TabActivity.class);
        startActivity(i);
    }
}
