package com.lisn.sg.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lisn.sg.Popup.PopupWindowActivity;
import com.lisn.sg.R;
import com.lisn.sg.Utils.LsUtils;

/**
 * Created by admin on 2017/6/19.
 */

public class PopFragment extends Fragment {
    private Context mContext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        return inflater.inflate(R.layout.fragment_pop,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tv_popup = (TextView) view.findViewById(R.id.tv_popup);
        tv_popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LsUtils.StarteActivity(mContext, PopupWindowActivity.class);
            }
        });
    }
}
