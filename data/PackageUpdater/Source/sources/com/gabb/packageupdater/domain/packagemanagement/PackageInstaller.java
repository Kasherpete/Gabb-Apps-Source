package com.gabb.packageupdater.domain.packagemanagement;

import android.net.Uri;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;

@Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J2\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH¦@ø\u0001\u0000ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0006H&\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006\r"}, mo20735d2 = {"Lcom/gabb/packageupdater/domain/packagemanagement/PackageInstaller;", "", "install", "Lkotlin/Result;", "", "packageName", "", "rawUri", "Landroid/net/Uri;", "install-0E7RQCE", "(Ljava/lang/String;Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isInstalling", "", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: PackageInstaller.kt */
public interface PackageInstaller {
    /* renamed from: install-0E7RQCE  reason: not valid java name */
    Object m166install0E7RQCE(String str, Uri uri, Continuation<? super Result<Integer>> continuation);

    boolean isInstalling(String str);
}
