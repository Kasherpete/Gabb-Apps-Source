package com.datadog.android.core.internal.utils;

import com.datadog.android.log.Logger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000,\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\u001a,\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004H\u0000\u001a\u001e\u0010\b\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u0004H\u0000\u001a.\u0010\f\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\b\b\u0002\u0010\u000f\u001a\u00020\u00022\b\b\u0002\u0010\u0010\u001a\u00020\u0002H\u0000\u001a\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u0012*\u00020\u00022\u0006\u0010\u0013\u001a\u00020\nH\u0000¨\u0006\u0014"}, mo20735d2 = {"copyTo", "", "", "srcPos", "", "dest", "destPos", "length", "indexOf", "b", "", "startIndex", "join", "", "separator", "prefix", "suffix", "split", "", "delimiter", "dd-sdk-android_release"}, mo20736k = 2, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: ByteArrayExt.kt */
public final class ByteArrayExtKt {
    public static final List<byte[]> split(byte[] bArr, byte b) {
        int indexOf;
        int i;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        List<byte[]> arrayList = new ArrayList<>();
        int i2 = 0;
        do {
            indexOf = indexOf(bArr, b, i2);
            if (indexOf >= 0) {
                i = indexOf - i2;
            } else {
                i = bArr.length - i2;
            }
            if (i > 0) {
                byte[] bArr2 = new byte[i];
                copyTo(bArr, i2, bArr2, 0, i);
                arrayList.add(bArr2);
            }
            i2 = indexOf + 1;
        } while (indexOf != -1);
        return arrayList;
    }

    public static /* synthetic */ byte[] join$default(Collection collection, byte[] bArr, byte[] bArr2, byte[] bArr3, int i, Object obj) {
        if ((i & 2) != 0) {
            bArr2 = new byte[0];
        }
        if ((i & 4) != 0) {
            bArr3 = new byte[0];
        }
        return join(collection, bArr, bArr2, bArr3);
    }

    public static final byte[] join(Collection<byte[]> collection, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        Intrinsics.checkNotNullParameter(bArr, "separator");
        Intrinsics.checkNotNullParameter(bArr2, "prefix");
        Intrinsics.checkNotNullParameter(bArr3, "suffix");
        Iterable<byte[]> iterable = collection;
        int i = 0;
        for (byte[] length : iterable) {
            i += length.length;
        }
        byte[] bArr4 = new byte[(bArr2.length + i + (collection.isEmpty() ^ true ? bArr.length * (collection.size() - 1) : 0) + bArr3.length)];
        copyTo(bArr2, 0, bArr4, 0, bArr2.length);
        int length2 = bArr2.length + 0;
        for (IndexedValue indexedValue : CollectionsKt.withIndex(iterable)) {
            copyTo((byte[]) indexedValue.getValue(), 0, bArr4, length2, ((byte[]) indexedValue.getValue()).length);
            length2 += ((byte[]) indexedValue.getValue()).length;
            if (indexedValue.getIndex() != collection.size() - 1) {
                copyTo(bArr, 0, bArr4, length2, bArr.length);
                length2 += bArr.length;
            }
        }
        copyTo(bArr3, 0, bArr4, length2, bArr3.length);
        return bArr4;
    }

    public static /* synthetic */ int indexOf$default(byte[] bArr, byte b, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return indexOf(bArr, b, i);
    }

    public static final int indexOf(byte[] bArr, byte b, int i) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        int length = bArr.length;
        while (i < length) {
            int i2 = i + 1;
            if (bArr[i] == b) {
                return i;
            }
            i = i2;
        }
        return -1;
    }

    public static final boolean copyTo(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(bArr2, "dest");
        if (i2 + i3 > bArr2.length) {
            Logger.w$default(RuntimeUtilsKt.getSdkLogger(), "Cannot copy ByteArray, dest doesn't have enough space", (Throwable) null, (Map) null, 6, (Object) null);
            return false;
        } else if (i + i3 > bArr.length) {
            Logger.w$default(RuntimeUtilsKt.getSdkLogger(), "Cannot copy ByteArray, src doesn't have enough data", (Throwable) null, (Map) null, 6, (Object) null);
            return false;
        } else {
            System.arraycopy(bArr, i, bArr2, i2, i3);
            return true;
        }
    }
}
