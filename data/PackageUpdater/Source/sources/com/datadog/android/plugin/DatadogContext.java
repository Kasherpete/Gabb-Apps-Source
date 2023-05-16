package com.datadog.android.plugin;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, mo20735d2 = {"Lcom/datadog/android/plugin/DatadogContext;", "", "rum", "Lcom/datadog/android/plugin/DatadogRumContext;", "(Lcom/datadog/android/plugin/DatadogRumContext;)V", "getRum", "()Lcom/datadog/android/plugin/DatadogRumContext;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: DatadogContext.kt */
public final class DatadogContext {
    private final DatadogRumContext rum;

    public DatadogContext() {
        this((DatadogRumContext) null, 1, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ DatadogContext copy$default(DatadogContext datadogContext, DatadogRumContext datadogRumContext, int i, Object obj) {
        if ((i & 1) != 0) {
            datadogRumContext = datadogContext.rum;
        }
        return datadogContext.copy(datadogRumContext);
    }

    public final DatadogRumContext component1() {
        return this.rum;
    }

    public final DatadogContext copy(DatadogRumContext datadogRumContext) {
        return new DatadogContext(datadogRumContext);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof DatadogContext) && Intrinsics.areEqual((Object) this.rum, (Object) ((DatadogContext) obj).rum);
    }

    public int hashCode() {
        DatadogRumContext datadogRumContext = this.rum;
        if (datadogRumContext == null) {
            return 0;
        }
        return datadogRumContext.hashCode();
    }

    public String toString() {
        return "DatadogContext(rum=" + this.rum + ")";
    }

    public DatadogContext(DatadogRumContext datadogRumContext) {
        this.rum = datadogRumContext;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ DatadogContext(DatadogRumContext datadogRumContext, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : datadogRumContext);
    }

    public final DatadogRumContext getRum() {
        return this.rum;
    }
}
