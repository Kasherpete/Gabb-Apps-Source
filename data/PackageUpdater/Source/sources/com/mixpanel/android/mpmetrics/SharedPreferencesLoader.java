package com.mixpanel.android.mpmetrics;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

class SharedPreferencesLoader {
    private final Executor mExecutor = Executors.newSingleThreadExecutor();

    interface OnPrefsLoadedListener {
        void onPrefsLoaded(SharedPreferences sharedPreferences);
    }

    public Future<SharedPreferences> loadPreferences(Context context, String str, OnPrefsLoadedListener onPrefsLoadedListener) {
        FutureTask futureTask = new FutureTask(new LoadSharedPreferences(context, str, onPrefsLoadedListener));
        this.mExecutor.execute(futureTask);
        return futureTask;
    }

    private static class LoadSharedPreferences implements Callable<SharedPreferences> {
        private final Context mContext;
        private final OnPrefsLoadedListener mListener;
        private final String mPrefsName;

        public LoadSharedPreferences(Context context, String str, OnPrefsLoadedListener onPrefsLoadedListener) {
            this.mContext = context;
            this.mPrefsName = str;
            this.mListener = onPrefsLoadedListener;
        }

        public SharedPreferences call() {
            SharedPreferences sharedPreferences = this.mContext.getSharedPreferences(this.mPrefsName, 0);
            OnPrefsLoadedListener onPrefsLoadedListener = this.mListener;
            if (onPrefsLoadedListener != null) {
                onPrefsLoadedListener.onPrefsLoaded(sharedPreferences);
            }
            return sharedPreferences;
        }
    }
}
