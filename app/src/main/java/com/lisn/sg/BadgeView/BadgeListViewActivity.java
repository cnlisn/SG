package com.lisn.sg.BadgeView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lisn.badgeviewlib.Badge;
import com.lisn.badgeviewlib.QBadgeView;
import com.lisn.sg.R;

import java.util.List;



public class BadgeListViewActivity extends AppCompatActivity {
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badge_list_view);
        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(new ListAdapter());
    }

    class ListAdapter extends BaseAdapter {
        private List<String> data;

        public ListAdapter() {
            data = new DataSupport().getData();
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            Holder holder;
            if (convertView == null) {
                holder = new Holder();
                convertView = LayoutInflater.from(BadgeListViewActivity.this).inflate(R.layout.item_badge_view, parent, false);
                holder.textView = (TextView) convertView.findViewById(R.id.tv_content);
                holder.badge = new QBadgeView(BadgeListViewActivity.this).bindTarget(convertView.findViewById(R.id.imageview));
                holder.badge.setBadgeTextSize(12, true);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }
            holder.textView.setText(data.get(position));
            holder.badge.setBadgeNumber(position);
            holder.badge.setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
                @Override
                public void onDragStateChanged(int dragState, Badge badge, View targetView) {
                    if (dragState == STATE_SUCCEED) {
                        Toast.makeText(BadgeListViewActivity.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            return convertView;
        }

        class Holder {
            TextView textView;
            Badge badge;
        }
    }
}
