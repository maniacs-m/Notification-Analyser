package com.tierep.notificationanalyser;

import java.util.Date;

/**
 * Created by pieter on 25/09/14.
 */
public interface BarChartListener {
    public void onBarClick(Date date, int position);
    public void onIntervalChanged(Date first, Date end);

    /**
     * Called when the chart is finished drawing.
     */
    public void onChartDraw();
}