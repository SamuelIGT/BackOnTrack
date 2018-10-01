package br.ufc.samuel.backontrack.util;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * Created by samue on 29/07/2017.
 */

public class Chronometer {
    private TextView timer;
    private Context mContext;

    private int min, s, ms;
    private boolean isRunning;
    private long startTime, timeMS, timeSB, update;
    Handler h = new Handler();

    public Chronometer(TextView timer, Context context){
        this.timer = timer;
        mContext = context;
    }

    //Functions
    public void startTimer(long startTime) {
        setStartTime(startTime);
        h.postDelayed(r, 0);
        setRunning(true);
    }

    public void stopTimer(){
        setTimeSB(getTimeSB()+getTimeMS());//timeSB += timeMS;
        h.removeCallbacks(r);
        setRunning(false);
    }

    public void resetTimer(){
        setStartTime(0);
        setTimeMS(0);//timeMS = 0;
        setTimeSB(0);//timeSB = 0;
        setS(0);//s = 0;
        setMin(0);//min = 0;
        setMs(0);//ms = 0;
        this.h.removeCallbacks(r);
        getTimer().setText("0:00:000");//timer.setText("0:00:000");
    }

//Getters and Setters
    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public TextView getTimer() {
        return timer;
    }

    public void setTimer(TextView timer) {
        this.timer = timer;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }

    public int getMs() {
        return ms;
    }

    public void setMs(int ms) {
        this.ms = ms;
    }

    public long getTimeMS() {
        return timeMS;
    }

    public void setTimeMS(long timeMS) {
        this.timeMS = timeMS;
    }

    public long getTimeSB() {
        return timeSB;
    }

    public void setTimeSB(long timeSB) {
        this.timeSB = timeSB;
    }

    public long getUpdate() {
        return update;
    }

    public void setUpdate(long update) {
        this.update = update;
    }

    public int[] getTimerInt() {
        String timeText = timer.getText().toString();
        String[] timeString = timeText.split(":");
        int[] time = {Integer.parseInt(timeString[0]), Integer.parseInt(timeString[1]), Integer.parseInt(timeString[2])};
        return  time;
    }

    public int getTimerSec() {
        String timeText = timer.getText().toString();
        String[] timeString = timeText.split(":");
        int[] time = {Integer.parseInt(timeString[0]), Integer.parseInt(timeString[1]), Integer.parseInt(timeString[2])};


        return time[1] + (time[0] * 60);
    }
    //---------------------------Runnable--------------------------------------
    Runnable r = new Runnable() {
        @Override
        public void run() {
            DecimalFormat df = new DecimalFormat("00");

            timeMS = SystemClock.uptimeMillis() - startTime;
            update = timeSB + timeMS;
            s = (int) (update / 1000);
            min = s / 60;
            s = s % 60;
            ms = (int)(((int)update % 1000) / 10);

            timer.setText("" + /*String.format("%02d", min)*/df.format(min) + ":" + /*String.format("%02d", s)*/df.format(s) + ":" + /*String.format("%02d", ms)*/df.format(ms));
            h.postDelayed(this, 0);
        }
    };
}
