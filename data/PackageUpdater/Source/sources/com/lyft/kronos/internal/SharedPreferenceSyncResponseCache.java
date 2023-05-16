package com.lyft.kronos.internal;

import android.content.SharedPreferences;
import com.lyft.kronos.SyncResponseCache;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20733bv = {1, 0, 3}, mo20734d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0012\u001a\u00020\u0013H\u0016R$\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068V@VX\u000e¢\u0006\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR$\u0010\f\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068V@VX\u000e¢\u0006\f\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000bR$\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068V@VX\u000e¢\u0006\f\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, mo20735d2 = {"Lcom/lyft/kronos/internal/SharedPreferenceSyncResponseCache;", "Lcom/lyft/kronos/SyncResponseCache;", "sharedPreferences", "Landroid/content/SharedPreferences;", "(Landroid/content/SharedPreferences;)V", "value", "", "currentOffset", "getCurrentOffset", "()J", "setCurrentOffset", "(J)V", "currentTime", "getCurrentTime", "setCurrentTime", "elapsedTime", "getElapsedTime", "setElapsedTime", "clear", "", "Companion", "kronos-android_release"}, mo20736k = 1, mo20737mv = {1, 4, 0})
/* compiled from: SharedPreferenceSyncResponseCache.kt */
public final class SharedPreferenceSyncResponseCache implements SyncResponseCache {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String KEY_CURRENT_TIME = "com.lyft.kronos.cached_current_time";
    public static final String KEY_ELAPSED_TIME = "com.lyft.kronos.cached_elapsed_time";
    public static final String KEY_OFFSET = "com.lyft.kronos.cached_offset";
    public static final String SHARED_PREFERENCES_NAME = "com.lyft.kronos.shared_preferences";
    private final SharedPreferences sharedPreferences;

    public SharedPreferenceSyncResponseCache(SharedPreferences sharedPreferences2) {
        Intrinsics.checkNotNullParameter(sharedPreferences2, "sharedPreferences");
        this.sharedPreferences = sharedPreferences2;
    }

    public long getCurrentTime() {
        return this.sharedPreferences.getLong(KEY_CURRENT_TIME, 0);
    }

    public void setCurrentTime(long j) {
        this.sharedPreferences.edit().putLong(KEY_CURRENT_TIME, j).apply();
    }

    public long getElapsedTime() {
        return this.sharedPreferences.getLong(KEY_ELAPSED_TIME, 0);
    }

    public void setElapsedTime(long j) {
        this.sharedPreferences.edit().putLong(KEY_ELAPSED_TIME, j).apply();
    }

    public long getCurrentOffset() {
        return this.sharedPreferences.getLong(KEY_OFFSET, 0);
    }

    public void setCurrentOffset(long j) {
        this.sharedPreferences.edit().putLong(KEY_OFFSET, j).apply();
    }

    public void clear() {
        this.sharedPreferences.edit().clear().apply();
    }

    @Metadata(mo20733bv = {1, 0, 3}, mo20734d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\b"}, mo20735d2 = {"Lcom/lyft/kronos/internal/SharedPreferenceSyncResponseCache$Companion;", "", "()V", "KEY_CURRENT_TIME", "", "KEY_ELAPSED_TIME", "KEY_OFFSET", "SHARED_PREFERENCES_NAME", "kronos-android_release"}, mo20736k = 1, mo20737mv = {1, 4, 0})
    /* compiled from: SharedPreferenceSyncResponseCache.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
