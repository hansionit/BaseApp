package com.roboocean.baseapp.ui;

import android.os.CountDownTimer;
import android.widget.TextView;

import com.roboocean.baseapp.R;
import com.roboocean.baseapp.contract.SplashContract;
import com.roboocean.baseapp.presenter.SplashPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class SplashActivity extends BaseActivity<SplashActivity, SplashPresenter> implements SplashContract.ISplash {


    @BindView(R.id.timer_tv)
    TextView mTimerTv;

    //time left params
    private String mTimeHint;
    private byte mTimeLeft;
    private CountDownTimer mCountDownTimer;


    @Override
    protected int initContentView() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        mTimeHint = getResources().getString(R.string.timer_seconds);
        mCountDownTimer = new CountDownTimer(4000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeft = (byte) (millisUntilFinished / 1000);
                mTimerTv.setText(String.format(mTimeHint, mTimeLeft));
            }

            @Override
            public void onFinish() {
                mTimeLeft = 0;
                mTimerTv.setText(String.format(mTimeHint, mTimeLeft));
                mPresenter.isAppFirstRun();
            }
        };
        mCountDownTimer.start();
    }

    @Override
    protected SplashPresenter createPresenter() {
        return new SplashPresenter();
    }

    @OnClick(R.id.timer_tv)
    public void onClick() {
        mCountDownTimer.cancel();
        mPresenter.isAppFirstRun();
    }

    @Override
    public void goHome() {
        switchTo(this,MainActivity.class,true);
    }

    @Override
    public void goGuide() {
        switchTo(this,GuideActivity.class,true);
    }
}
