package com.gabb.packageupdater.util;

import android.content.Context;
import android.net.Uri;
import androidx.core.content.FileProvider;
import com.gabb.packageupdater.BuildConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001\u001a\u001e\u0010\u0004\u001a\u00020\u0005*\u00020\u00022\n\u0010\u0006\u001a\u00060\u0007j\u0002`\b2\u0006\u0010\u0003\u001a\u00020\u0001¨\u0006\t"}, mo20735d2 = {"getFileProviderURI", "Landroid/net/Uri;", "Landroid/content/Context;", "uri", "grantPermission", "", "packageName", "", "Lcom/gabb/packageupdater/domain/packagemanagement/PackageName;", "app_productionRelease"}, mo20736k = 2, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: uri.kt */
public final class UriKt {
    public static final Uri getFileProviderURI(Context context, Uri uri) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(uri, "uri");
        Uri uriForFile = FileProvider.getUriForFile(context, BuildConfig.FILE_PROVIDER, androidx.core.net.UriKt.toFile(uri));
        Intrinsics.checkNotNullExpressionValue(uriForFile, "getUriForFile(this, Buil…nfig.FILE_PROVIDER, file)");
        return uriForFile;
    }

    public static final void grantPermission(Context context, String str, Uri uri) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(str, "packageName");
        Intrinsics.checkNotNullParameter(uri, "uri");
        context.grantUriPermission(str, uri, 1);
    }
}
