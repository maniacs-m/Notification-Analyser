package com.tierep.notificationanalyser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tierep.notificationanalyser.models.NotificationItem;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * An adapter for displaying NotificationItem objects in a ListView.
 *
 * Created by pieter on 19/11/14.
 */
public class NotificationItemAdapter extends ArrayAdapter<NotificationItem> {
    private SimpleDateFormat dateFormatToday = new SimpleDateFormat("HH:mm");
    private SimpleDateFormat dateFormatOlder = new SimpleDateFormat("yyyy-MM-dd");

    public NotificationItemAdapter(Context context, List<NotificationItem> objects) {
        super(context, 0, objects);
        this.sort(new Comparator<NotificationItem>() {
            @Override
            public int compare(NotificationItem lhs, NotificationItem rhs) {
                return rhs.getDate().compareTo(lhs.getDate());
            }
        });
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_app_detail_el, null);
        TextView txtMessage = (TextView) view.findViewById(R.id.app_detail_message);
        TextView txtDate = (TextView) view.findViewById(R.id.app_detail_date);


        NotificationItem item = this.getItem(position);
        String message = item.getMessage();
        if (message == null || "".equals(message)) {
            message = getContext().getResources().getString(R.string.app_detail_no_message);
        }
        txtMessage.setText(message);
        txtDate.setText(formatDate(item.getDate()));

        return view;
    }

    private String formatDate(Date date) {
        Date currentDay = new Date();
        if (dateFormatOlder.format(currentDay).equals(dateFormatOlder.format(date))) {
            // Same day
            return dateFormatToday.format(date);
        } else {
            return dateFormatOlder.format(date);
        }
    }
}