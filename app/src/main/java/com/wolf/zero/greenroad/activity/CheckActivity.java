package com.wolf.zero.greenroad.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.orhanobut.logger.Logger;
import com.wolf.zero.greenroad.R;
import com.wolf.zero.greenroad.bean.GoodsChartBean;
import com.wolf.zero.greenroad.tools.DateUtil;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CheckActivity extends AppCompatActivity {

    private TextView mTvStandardWeight;
    private TextView mTvDeviation;
    private TextView mtvMoreWeight;
    private TextView mIsOverWeight;
    private TextView mCheck1;
    private TextView mCheck2;
    private TextView mCheck3;

    private ArrayList<GoodsChartBean> list;

    private LineChart lineChart;
    private ArrayList<String> chartList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT * 1 / 3;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        getWindow().setAttributes(params);
        setContentView(R.layout.activity_check);

        initView();

    }

    private void initChartData() {
        list = new ArrayList<>();
        if (chartList.isEmpty()) {
            list.add(new GoodsChartBean("", 0.0));
        } else {
            for (int i = 0; i < chartList.size(); i++) {
                String[] split = chartList.get(i).split("[:]");
                com.orhanobut.logger.Logger.d(split[0] + "gray" + split[1]);
                list.add(new GoodsChartBean(split[0], Double.valueOf(split[1])));
            }
        }
//        list.add(new GoodsChartBean("20180811", 35.5));
//        list.add(new GoodsChartBean("20180812", 31.5));
//        list.add(new GoodsChartBean("20180813", 32.5));
//        list.add(new GoodsChartBean("20180814", 33.5));
//        list.add(new GoodsChartBean("20180815", 34.5));
//        list.add(new GoodsChartBean("20180816", 35.5));
//        list.add(new GoodsChartBean("20180817", 36.5));
//        list.add(new GoodsChartBean("20180818", 37.5));
//        list.add(new GoodsChartBean("20180819", 38.5));
//        list.add(new GoodsChartBean("20180820", 39.5));
    }

    private void initView() {
        mTvStandardWeight = (TextView) findViewById(R.id.tv_standardWeight);
        mTvDeviation = (TextView) findViewById(R.id.tv_deviation);
        mtvMoreWeight = (TextView) findViewById(R.id.tv_moreWeight);
        mIsOverWeight = (TextView) findViewById(R.id.tv_showIsOverWeight);
        mCheck1 = (TextView) findViewById(R.id.tv_check1);
        mCheck2 = (TextView) findViewById(R.id.tv_check2);
        mCheck3 = (TextView) findViewById(R.id.tv_check3);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        double standardWeight = bundle.getDouble("standardWeight");
        double index = bundle.getDouble("index");
        double deviation = bundle.getDouble("deviation");
        chartList = bundle.getStringArrayList("chartList");
        Logger.d(chartList + "chartList");

        mTvStandardWeight.setText(Double.toString(standardWeight));
        mTvDeviation.setText(Integer.toString((int) (index * 100)) + "%");
        mtvMoreWeight.setText(Double.toString(deviation) + "吨   ");
        if (Math.abs(index) > 0.3) {
            mIsOverWeight.setText("超重了");
            mTvStandardWeight.setTextColor(Color.rgb(255, 0, 0));
            mTvDeviation.setTextColor(Color.rgb(255, 0, 0));
            mtvMoreWeight.setTextColor(Color.rgb(255, 0, 0));
            mIsOverWeight.setTextColor(Color.rgb(255, 0, 0));
            mCheck1.setTextColor(Color.rgb(255, 0, 0));
            mCheck2.setTextColor(Color.rgb(255, 0, 0));
            mCheck3.setTextColor(Color.rgb(255, 0, 0));
        } else {
            mIsOverWeight.setText("标准范围内");
            mTvStandardWeight.setTextColor(Color.rgb(45, 228, 32));
            mTvDeviation.setTextColor(Color.rgb(45, 228, 32));
            mtvMoreWeight.setTextColor(Color.rgb(45, 228, 32));
            mIsOverWeight.setTextColor(Color.rgb(45, 228, 32));
            mCheck1.setTextColor(Color.rgb(45, 228, 32));
            mCheck2.setTextColor(Color.rgb(45, 228, 32));
            mCheck3.setTextColor(Color.rgb(45, 228, 32));
        }
//        double sWeight = Double.valueOf(getIntent().getExtras().getString("sWeight"));
//        double sVolume = Double.valueOf(getIntent().getExtras().getString("sVolume"));
//        double sWeight = 11.0;
//        double sVolume = 23.1;
//        ScanFragment fragment = ScanFragment.newInstance(GlobalManager.TYPE_MAIN_ENTER_SHOW);
//        fragment.setCallBack(new ScanFragment.CallBack() {
//            @Override
//            public void setTexts(String sWeight, String sVolume) {
//                mtvMoreWeight.setText(Double.toString((Double.valueOf(sVolume) - Double.valueOf(sWeight))));
//                mTvDeviation.setText(Double.toString((Double.valueOf(sVolume) / Double.valueOf(sWeight))));
//                mtvMoreWeight.setText(Double.toString((Double.valueOf(sVolume) + Double.valueOf(sWeight))));
//            }
//        });

        lineChart = (LineChart) findViewById(R.id.lineChart);
        initChartData();
        initLineChart(list);

    }

    private void initLineChart(ArrayList<GoodsChartBean> list) {
        //显示边界
        lineChart.setDrawBorders(false);
        //设置数据
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            GoodsChartBean data = list.get(i);
            Entry entry = new Entry(i, (float) data.getWeight());
            entries.add(entry);
        }
        //一个LineDataSet就是一条线
        LineDataSet lineDataSet = new LineDataSet(entries, "");
        //线颜色
        lineDataSet.setColor(Color.parseColor("#F15A4A"));
        //线宽度
        lineDataSet.setLineWidth(1.6f);
        //不显示圆点
        lineDataSet.setDrawCircles(true);
        //线条平滑
        lineDataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        //设置折线图填充
//        lineDataSet.setDrawFilled(true);
        LineData data = new LineData(lineDataSet);
        //无数据时显示的文字
        lineChart.setNoDataText("暂无数据");
        //折线图不显示数值
        data.setDrawValues(false);
        //得到X轴
        XAxis xAxis = lineChart.getXAxis();
        //设置X轴的位置（默认在上方)
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置X轴坐标之间的最小间隔
        xAxis.setGranularity(1f);
        //设置X轴的刻度数量，第二个参数为true,将会画出明确数量（带有小数点），但是可能值导致不均匀，默认（6，false）
        //也就是设置x轴的间距值
        xAxis.setLabelCount(6, false);
        //设置X轴的值（最小值、最大值、然后会根据设置的刻度数量自动分配刻度显示）
        xAxis.setAxisMinimum(0f);
        xAxis.setXOffset(-3);
        xAxis.setAxisMaximum((float) list.size() - 1);
        //不显示网格线
        xAxis.setDrawGridLines(false);
        // 标签倾斜
        xAxis.setLabelRotationAngle(45);
        //设置X轴值为字符串
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                String date = list.get((int) value % list.size()).getDate();
                return DateUtil.formatDateToMd(date);
//                return format.toString();
            }
        });
        //得到Y轴
        YAxis yAxis = lineChart.getAxisLeft();
        YAxis rightYAxis = lineChart.getAxisRight();
        //设置Y轴是否显示
        rightYAxis.setEnabled(false); //右侧Y轴不显示
        //设置y轴坐标之间的最小间隔
        //不显示网格线
        yAxis.setDrawGridLines(false);
        //设置Y轴坐标之间的最小间隔
        yAxis.setGranularity(1);
        //设置y轴的刻度数量
        //+2：最大值n就有n+1个刻度，在加上y轴多一个单位长度，为了好看，so+2
        yAxis.setLabelCount(8);
        //设置从Y轴值
        yAxis.setAxisMinimum(0f);
        //+1:y轴多一个单位长度，为了好看
//        yAxis.setAxisMaximum(8);

        //y轴
        yAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                double IValue = value;
                return String.valueOf(IValue);
            }
        });
        //图例：得到Lengend
        Legend legend = lineChart.getLegend();
        //隐藏Lengend
        legend.setEnabled(false);
        //隐藏描述
        Description description = new Description();
        description.setEnabled(false);
        lineChart.setDescription(description);
        //折线图点的标记
        MyMarkerView mv = new MyMarkerView(this, xAxis.getValueFormatter());
        lineChart.setMarker(mv);
        //设置数据
        lineChart.setData(data);
        //图标刷新
        lineChart.invalidate();
    }

    private class MyMarkerView extends MarkerView {
        private TextView tvContent;
        private DecimalFormat format = new DecimalFormat("##0");
        private IAxisValueFormatter iAxisValueFormatter;

        public MyMarkerView(Context context, IAxisValueFormatter iAxisValueFormatter) {
            super(context, R.layout.layout_markerview);//这个布局自己定义
            tvContent = (TextView) findViewById(R.id.tv_value0);

            this.iAxisValueFormatter = iAxisValueFormatter;
        }

        //显示的内容
        @Override
        public void refreshContent(Entry e, Highlight highlight) {
//            tvContent.setText(format(e.getX()) + "\n" + (e.getY()) + "吨");
            if (e.getY() == 0.0) {
                tvContent.setText("暂无该车牌近10天的数据");
                super.refreshContent(e, highlight);
            } else {
                tvContent.setText(iAxisValueFormatter.getFormattedValue(e.getX(), null) + "\n" + (e.getY()) + "吨");
                super.refreshContent(e, highlight);
            }
        }

        //标记相对于折线图的偏移量
        @Override
        public MPPointF getOffset() {
            return new MPPointF(-(getWidth() / 2), -getHeight());
        }

        //时间格式化（显示今日往前30天的每一天日期）
        public String format(float x) {
            Logger.d(x + "xxx");
            CharSequence format = DateFormat.format("MM月dd日",
                    System.currentTimeMillis());
            return format.toString();
        }

    }
}


