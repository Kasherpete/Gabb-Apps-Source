package com.datadog.android.core.internal.net;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.HttpUrl;

@Metadata(mo20734d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0014\u0010\n\u001a\u00020\u000b2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003J\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0004J\u000e\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010R*\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0011"}, mo20735d2 = {"Lcom/datadog/android/core/internal/net/FirstPartyHostDetector;", "", "hosts", "", "", "(Ljava/util/List;)V", "<set-?>", "knownHosts", "getKnownHosts$dd_sdk_android_release", "()Ljava/util/List;", "addKnownHosts", "", "isEmpty", "", "isFirstPartyUrl", "url", "Lokhttp3/HttpUrl;", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: FirstPartyHostDetector.kt */
public final class FirstPartyHostDetector {
    private List<String> knownHosts;

    public FirstPartyHostDetector(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "hosts");
        Iterable<String> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (String lowerCase : iterable) {
            Locale locale = Locale.US;
            Intrinsics.checkNotNullExpressionValue(locale, "US");
            String lowerCase2 = lowerCase.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(locale)");
            arrayList.add(lowerCase2);
        }
        this.knownHosts = (List) arrayList;
    }

    public final List<String> getKnownHosts$dd_sdk_android_release() {
        return this.knownHosts;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0062 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean isFirstPartyUrl(okhttp3.HttpUrl r6) {
        /*
            r5 = this;
            java.lang.String r0 = "url"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.lang.String r6 = r6.host()
            java.util.List<java.lang.String> r5 = r5.knownHosts
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            boolean r0 = r5 instanceof java.util.Collection
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x001e
            r0 = r5
            java.util.Collection r0 = (java.util.Collection) r0
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x001e
        L_0x001c:
            r1 = r2
            goto L_0x0062
        L_0x001e:
            java.util.Iterator r5 = r5.iterator()
        L_0x0022:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L_0x001c
            java.lang.Object r0 = r5.next()
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r3 = "*"
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r3)
            if (r3 != 0) goto L_0x005f
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r0)
            if (r3 != 0) goto L_0x005f
            java.lang.String r3 = "host"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "."
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r0 = r3.append(r0)
            java.lang.String r0 = r0.toString()
            r3 = 2
            r4 = 0
            boolean r0 = kotlin.text.StringsKt.endsWith$default(r6, r0, r2, r3, r4)
            if (r0 == 0) goto L_0x005d
            goto L_0x005f
        L_0x005d:
            r0 = r2
            goto L_0x0060
        L_0x005f:
            r0 = r1
        L_0x0060:
            if (r0 == 0) goto L_0x0022
        L_0x0062:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.core.internal.net.FirstPartyHostDetector.isFirstPartyUrl(okhttp3.HttpUrl):boolean");
    }

    public final boolean isFirstPartyUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "url");
        HttpUrl parse = HttpUrl.parse(str);
        if (parse == null) {
            return false;
        }
        return isFirstPartyUrl(parse);
    }

    public final boolean isEmpty() {
        return this.knownHosts.isEmpty();
    }

    public final void addKnownHosts(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "hosts");
        Collection collection = this.knownHosts;
        Iterable<String> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (String lowerCase : iterable) {
            Locale locale = Locale.US;
            Intrinsics.checkNotNullExpressionValue(locale, "US");
            String lowerCase2 = lowerCase.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(locale)");
            arrayList.add(lowerCase2);
        }
        this.knownHosts = CollectionsKt.plus(collection, (List) arrayList);
    }
}
