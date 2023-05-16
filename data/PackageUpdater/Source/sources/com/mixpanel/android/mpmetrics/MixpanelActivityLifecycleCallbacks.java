package com.mixpanel.android.mpmetrics;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import java.lang.ref.WeakReference;
import org.json.JSONException;
import org.json.JSONObject;

class MixpanelActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
    public static final int CHECK_DELAY = 500;
    /* access modifiers changed from: private */
    public static Double sStartSessionTime;
    private Runnable check;
    /* access modifiers changed from: private */
    public final MPConfig mConfig;
    private WeakReference<Activity> mCurrentActivity;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public boolean mIsForeground = false;
    /* access modifiers changed from: private */
    public final MixpanelAPI mMpInstance;
    /* access modifiers changed from: private */
    public boolean mPaused = true;

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }

    public MixpanelActivityLifecycleCallbacks(MixpanelAPI mixpanelAPI, MPConfig mPConfig) {
        this.mMpInstance = mixpanelAPI;
        this.mConfig = mPConfig;
        if (sStartSessionTime == null) {
            sStartSessionTime = Double.valueOf((double) System.currentTimeMillis());
        }
    }

    public void onActivityPaused(Activity activity) {
        this.mPaused = true;
        Runnable runnable = this.check;
        if (runnable != null) {
            this.mHandler.removeCallbacks(runnable);
        }
        this.mCurrentActivity = null;
        Handler handler = this.mHandler;
        C13131 r0 = new Runnable() {
            public void run() {
                if (MixpanelActivityLifecycleCallbacks.this.mIsForeground && MixpanelActivityLifecycleCallbacks.this.mPaused) {
                    boolean unused = MixpanelActivityLifecycleCallbacks.this.mIsForeground = false;
                    try {
                        double currentTimeMillis = ((double) System.currentTimeMillis()) - MixpanelActivityLifecycleCallbacks.sStartSessionTime.doubleValue();
                        if (currentTimeMillis >= ((double) MixpanelActivityLifecycleCallbacks.this.mConfig.getMinimumSessionDuration()) && currentTimeMillis < ((double) MixpanelActivityLifecycleCallbacks.this.mConfig.getSessionTimeoutDuration()) && MixpanelActivityLifecycleCallbacks.this.mMpInstance.getTrackAutomaticEvents().booleanValue()) {
                            double round = ((double) Math.round((currentTimeMillis / 1000.0d) * 10.0d)) / 10.0d;
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put(AutomaticEvents.SESSION_LENGTH, round);
                            MixpanelActivityLifecycleCallbacks.this.mMpInstance.getPeople().increment(AutomaticEvents.TOTAL_SESSIONS, 1.0d);
                            MixpanelActivityLifecycleCallbacks.this.mMpInstance.getPeople().increment(AutomaticEvents.TOTAL_SESSIONS_LENGTH, round);
                            MixpanelActivityLifecycleCallbacks.this.mMpInstance.track(AutomaticEvents.SESSION, jSONObject, true);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    MixpanelActivityLifecycleCallbacks.this.mMpInstance.onBackground();
                }
            }
        };
        this.check = r0;
        handler.postDelayed(r0, 500);
    }

    public void onActivityResumed(Activity activity) {
        this.mCurrentActivity = new WeakReference<>(activity);
        this.mPaused = false;
        boolean z = !this.mIsForeground;
        this.mIsForeground = true;
        Runnable runnable = this.check;
        if (runnable != null) {
            this.mHandler.removeCallbacks(runnable);
        }
        if (z) {
            sStartSessionTime = Double.valueOf((double) System.currentTimeMillis());
            this.mMpInstance.onForeground();
        }
    }

    /* access modifiers changed from: protected */
    public boolean isInForeground() {
        return this.mIsForeground;
    }
}
