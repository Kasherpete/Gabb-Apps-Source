package com.datadog.android.sqlite;

import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import com.datadog.android.rum.GlobalRum;
import com.datadog.android.rum.RumAttributes;
import com.datadog.android.rum.RumErrorSource;
import com.datadog.android.rum.RumMonitor;
import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

@Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016R\u0014\u0010\u0002\u001a\u00020\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u000b"}, mo20735d2 = {"Lcom/datadog/android/sqlite/DatadogDatabaseErrorHandler;", "Landroid/database/DatabaseErrorHandler;", "defaultErrorHandler", "(Landroid/database/DatabaseErrorHandler;)V", "getDefaultErrorHandler$dd_sdk_android_release", "()Landroid/database/DatabaseErrorHandler;", "onCorruption", "", "dbObj", "Landroid/database/sqlite/SQLiteDatabase;", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: DatadogDatabaseErrorHandler.kt */
public final class DatadogDatabaseErrorHandler implements DatabaseErrorHandler {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String DATABASE_CORRUPTION_ERROR_MESSAGE = "Corruption reported by sqlite database: %s";
    private final DatabaseErrorHandler defaultErrorHandler;

    public DatadogDatabaseErrorHandler() {
        this((DatabaseErrorHandler) null, 1, (DefaultConstructorMarker) null);
    }

    public DatadogDatabaseErrorHandler(DatabaseErrorHandler databaseErrorHandler) {
        Intrinsics.checkNotNullParameter(databaseErrorHandler, "defaultErrorHandler");
        this.defaultErrorHandler = databaseErrorHandler;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ DatadogDatabaseErrorHandler(DatabaseErrorHandler databaseErrorHandler, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new DefaultDatabaseErrorHandler() : databaseErrorHandler);
    }

    public final DatabaseErrorHandler getDefaultErrorHandler$dd_sdk_android_release() {
        return this.defaultErrorHandler;
    }

    public void onCorruption(SQLiteDatabase sQLiteDatabase) {
        Intrinsics.checkNotNullParameter(sQLiteDatabase, "dbObj");
        this.defaultErrorHandler.onCorruption(sQLiteDatabase);
        RumMonitor rumMonitor = GlobalRum.get();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(Locale.US, DATABASE_CORRUPTION_ERROR_MESSAGE, Arrays.copyOf(new Object[]{sQLiteDatabase.getPath()}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        rumMonitor.addError(format, RumErrorSource.SOURCE, (Throwable) null, MapsKt.mapOf(TuplesKt.m78to(RumAttributes.ERROR_DATABASE_PATH, sQLiteDatabase.getPath()), TuplesKt.m78to(RumAttributes.ERROR_DATABASE_VERSION, Integer.valueOf(sQLiteDatabase.getVersion()))));
    }

    @Metadata(mo20734d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, mo20735d2 = {"Lcom/datadog/android/sqlite/DatadogDatabaseErrorHandler$Companion;", "", "()V", "DATABASE_CORRUPTION_ERROR_MESSAGE", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: DatadogDatabaseErrorHandler.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
