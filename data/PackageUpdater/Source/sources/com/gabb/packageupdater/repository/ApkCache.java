package com.gabb.packageupdater.repository;

import android.content.Context;
import com.datadog.android.rum.internal.domain.event.RumEventSerializer;
import dagger.hilt.android.qualifiers.ApplicationContext;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.p007io.FilesKt;
import kotlin.text.StringsKt;

@Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006J\u0006\u0010\n\u001a\u00020\bJ\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, mo20735d2 = {"Lcom/gabb/packageupdater/repository/ApkCache;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "directory", "", "delete", "", "packageName", "deleteAll", "targetStorageLocation", "Ljava/io/File;", "versionName", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: ApkCache.kt */
public final class ApkCache {
    private final Context context;
    private final String directory;

    @Inject
    public ApkCache(@ApplicationContext Context context2) {
        Intrinsics.checkNotNullParameter(context2, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        this.context = context2;
        this.directory = context2.getFilesDir() + "/apks";
    }

    public final File targetStorageLocation(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "packageName");
        Intrinsics.checkNotNullParameter(str2, "versionName");
        return new File(this.directory, str + '-' + str2 + ".apk");
    }

    public final void deleteAll() {
        FilesKt.deleteRecursively(new File(this.directory));
    }

    public final void delete(String str) {
        Intrinsics.checkNotNullParameter(str, "packageName");
        File file = new File(this.directory);
        if (file.exists()) {
            File[] listFiles = file.listFiles();
            List list = listFiles == null ? null : ArraysKt.toList((T[]) listFiles);
            if (list == null) {
                list = CollectionsKt.emptyList();
            }
            Collection arrayList = new ArrayList();
            for (Object next : list) {
                File file2 = (File) next;
                Intrinsics.checkNotNullExpressionValue(file2, "it");
                if (StringsKt.contains$default((CharSequence) FilesKt.getNameWithoutExtension(file2), (CharSequence) str, false, 2, (Object) null)) {
                    arrayList.add(next);
                }
            }
            for (File delete : (List) arrayList) {
                delete.delete();
            }
        }
    }
}
