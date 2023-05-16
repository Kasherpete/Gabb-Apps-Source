package com.gabb.packageupdater.model;

import com.gabb.packageupdater.api.App;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\n"}, mo20735d2 = {"Lcom/gabb/packageupdater/model/UpdatedApps;", "", "()V", "apps", "", "Lcom/gabb/packageupdater/api/App;", "getApps", "()Ljava/util/List;", "setApps", "(Ljava/util/List;)V", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: UpdatedApps.kt */
public final class UpdatedApps {
    private List<App> apps = CollectionsKt.toMutableList(CollectionsKt.emptyList());

    public final List<App> getApps() {
        return this.apps;
    }

    public final void setApps(List<App> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.apps = list;
    }
}
