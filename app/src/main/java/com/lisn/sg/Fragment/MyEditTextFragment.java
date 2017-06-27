package com.lisn.sg.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.lisn.sg.HomeActivity;
import com.lisn.sg.R;

/**
 * 自定义EditText
 */
public class MyEditTextFragment extends Fragment {


    private HomeActivity mContext;

    public MyEditTextFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_my_edit_text, container, false);
        mContext = (HomeActivity) getActivity();
        ((Button) view.findViewById(R.id.clear)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((EditText) view.findViewById(R.id.biu1)).setText("");
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mContext.SetToolbarTitle("自定义EditText");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden) {// 不在最前端界面显示
        } else {// 重新显示到最前端中
            mContext.SetToolbarTitle("自定义EditText");
        }
    }
}
