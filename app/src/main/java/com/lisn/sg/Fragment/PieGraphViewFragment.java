package com.lisn.sg.Fragment;


import android.os.Bundle;

import com.lisn.sg.R;
import com.lisn.sg.View.PieGraph;

import java.util.ArrayList;
import java.util.List;

/**
 * PieGraphView饼状图
 */
public class PieGraphViewFragment extends BaseFragment {


    private PieGraph mPieGraph;
    private List<PieGraph.PieDataHolder> mPieceDataHolders;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_pie_graph_view;
    }

    @Override
    protected void initView() {
        super.initView();
        setToolbarTitle("PieGraphView饼状图");
        mPieGraph = (PieGraph) findViewById(R.id.pie_graph);
        mPieGraph.setPieData(mPieceDataHolders);
    }

    @Override
    protected void initData(Bundle arguments) {

        mPieceDataHolders = new ArrayList<>();
        mPieceDataHolders.add(new PieGraph.PieDataHolder(10, 0xFFFFFF99, "江西"));
        mPieceDataHolders.add(new PieGraph.PieDataHolder(10, 0xFF663300, "南昌"));
        mPieceDataHolders.add(new PieGraph.PieDataHolder(10, 0xFF999933, "萍乡"));
        mPieceDataHolders.add(new PieGraph.PieDataHolder(10, 0xFFCC3333, "赣州"));
        mPieceDataHolders.add(new PieGraph.PieDataHolder(100, 0xFFFFFF00, "上饶"));
        mPieceDataHolders.add(new PieGraph.PieDataHolder(100, 0xFF336699, "小曾"));
        mPieceDataHolders.add(new PieGraph.PieDataHolder(170, 0xFF333399, "小吴"));
        mPieceDataHolders.add(new PieGraph.PieDataHolder(200, 0xFFCC3366, "小谢"));
        mPieceDataHolders.add(new PieGraph.PieDataHolder(200, 0xFFCC9999, "小明"));
        mPieceDataHolders.add(new PieGraph.PieDataHolder(200, 0xFF336633, "小高"));
        mPieceDataHolders.add(new PieGraph.PieDataHolder(200, 0xFFFFFF00, "今天"));
        mPieceDataHolders.add(new PieGraph.PieDataHolder(200, 0xFFFF6666, "明天"));
        mPieceDataHolders.add(new PieGraph.PieDataHolder(130, 0xFF993399, "后天"));
    }
}
