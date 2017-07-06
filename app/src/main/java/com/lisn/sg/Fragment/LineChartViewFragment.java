package com.lisn.sg.Fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lisn.sg.R;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * LineChartView曲线图
 */
public class LineChartViewFragment extends BaseFragment {


    private LineChartView chart;
    private LineChartData data;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_line_chart_view;
    }

    @Override
    protected void initData(Bundle arguments) {
        List<Line> lines = new ArrayList<Line>();

        List<PointValue> values = new ArrayList<PointValue>();
        values.add(new PointValue(0, 0));
        values.add(new PointValue(1, 15));
        values.add(new PointValue(2, 10));
        values.add(new PointValue(3, 23));
        values.add(new PointValue(4, 8));
        values.add(new PointValue(5, 53));
        values.add(new PointValue(6.5f, 48));
        values.add(new PointValue(7, 60));

        Line line = new Line(values);
        line.setColor(Color.WHITE);
        line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.DIAMOND）
        line.setCubic(false);//曲线是否平滑，即是曲线还是折线
        line.setFilled(false);//是否填充曲线的面积
        line.setHasLabels(false);//曲线的数据坐标是否加上备注
        line.setHasLabelsOnlyForSelected(false);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);//是否用线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）
        lines.add(line);

        data = new LineChartData(lines);
        data.setBaseValue(Float.NEGATIVE_INFINITY);

    }

    @Override
    protected void initView() {
        setToolbarTitle("LineChartView曲线图");
        chart = findViewById(R.id.chart);
        chart.setLineChartData(data);
    }
}
