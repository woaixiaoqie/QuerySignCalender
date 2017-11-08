package com.xiaoniup.signcal.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.xiaoniup.signcal.R;
import com.xiaoniup.signcal.views.CalendarCardView;
import com.xiaoniup.signcal.views.CustomDate;
import com.xiaoniup.signcal.views.DateUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by xiaoniup on 2017/11/8.
 */
public class SignCalenderActivity extends Activity {

    TextView mCurrentYearMonDat_tv;
    TextView mNeedDutyDay_tv;
    TextView mSignDutyDay_tv;
    CalendarCardView calendarCardView;

    List<String> mAlreadySignDate = new ArrayList<>();
    
    String default_year = "2017-";//默认的年份


    private CustomDate mCustomDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_sign_calender);

        mCurrentYearMonDat_tv = (TextView) findViewById(R.id.sign_calendar_card_current_day_tv);
        mNeedDutyDay_tv = (TextView) findViewById(R.id.sign_calendar_need_duty_day_tv);
        mSignDutyDay_tv = (TextView) findViewById(R.id.sign_calendar_sign_duty_day_tv);
        calendarCardView = (CalendarCardView) findViewById(R.id.sign_calendar_card_view);


        //初始化当前月份的日历
        mCustomDate = new CustomDate();
        calendarCardView.setNewMounth(mCustomDate);

        calendarCardView.setOnCellClickListener(new CalendarCardView.OnCellClickListener() {
            @Override
            public void clickDate(CustomDate date) {
            }
            @Override
            public void changeDate(CustomDate date) {
            }
            @Override
            public void currentTotalDay(int totalday) {
            }
            @Override
            public void touchToLastMounth(boolean isLastMounth) {
                if (isLastMounth){
                    leftSlide();//滑动上个月
                } else {
                    rightSlide();//滑动下个月
                }
            }
        });

        findViewById(R.id.sign_calendar_card_back_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        findViewById(R.id.sign_calendar_card_left_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leftSlide();
            }
        });

        findViewById(R.id.sign_calendar_card_right_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rightSlide();
            }
        });

        findViewById(R.id.sign_calendar_today_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCustomDate = new CustomDate();
                initData();
            }
        });

        initData();

    }

    // 从左往右划，上一个月
    public void leftSlide() {
        if (mCustomDate.month == 1) {
            mCustomDate.month = 12;
            mCustomDate.year -= 1;
        } else {
            mCustomDate.month -= 1;
        }

        initData();
    }

    // 从右往左划，下一个月
    public void rightSlide() {
        if (mCustomDate.month == 12) {
            mCustomDate.month = 1;
            mCustomDate.year += 1;
        } else {
            mCustomDate.month += 1;
        }

        initData();
    }


    //请求当月的签到数据
    public void initData() {
        mAlreadySignDate.clear();

        getData();//获取签到的数据

        initAlreadySignData(mAlreadySignDate);

    }

    public void getData(){
//        mAlreadySignDate.add("2017-11-8");

        mAlreadySignDate.add(default_year +"3-12");
        mAlreadySignDate.add(default_year +"4-13");
        mAlreadySignDate.add(default_year +"5-14");
        mAlreadySignDate.add(default_year +"6-6");
        mAlreadySignDate.add(default_year +"7-17");
        mAlreadySignDate.add(default_year +"8-12");
        mAlreadySignDate.add(default_year +"9-21");
        mAlreadySignDate.add(default_year +"10-24");
        mAlreadySignDate.add(default_year +"11-1");
        mAlreadySignDate.add(default_year +"11-2");
        mAlreadySignDate.add(default_year +"11-3");
        mAlreadySignDate.add(default_year +"11-4");
        mAlreadySignDate.add(default_year +"11-6");
        mAlreadySignDate.add(default_year +"11-7");
        mAlreadySignDate.add(default_year +"12-25");
    }


    //刷新界面
    public void setSignCalendarDate(){
        //设置后台返回的本月度签到数据
        calendarCardView.setSignDateList(mAlreadySignDate);

        //绘制显示当前月的信息
        calendarCardView.setNewMounth(mCustomDate);


        mSignDutyDay_tv.setText(mAlreadySignDate.size() + "天");
        mCurrentYearMonDat_tv.setText(mCustomDate.year + "年" + mCustomDate.month + "月");
        mNeedDutyDay_tv.setText(DateUtil.getMonthDays(mCustomDate.year, mCustomDate.month) + "天");


    }
    
    public void initAlreadySignData(List<String> list){
        mAlreadySignDate.addAll(list);

        //去刷新喽
        setSignCalendarDate();
    }



}
