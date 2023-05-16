package com.mixpanel.android.util;

import kotlin.UByte;
import okio.Utf8;

public class Base64Coder {
    private static final char[] map1 = new char[64];
    private static final byte[] map2 = new byte[128];

    static {
        char c = 'A';
        int i = 0;
        while (c <= 'Z') {
            map1[i] = c;
            c = (char) (c + 1);
            i++;
        }
        char c2 = 'a';
        while (c2 <= 'z') {
            map1[i] = c2;
            c2 = (char) (c2 + 1);
            i++;
        }
        char c3 = '0';
        while (c3 <= '9') {
            map1[i] = c3;
            c3 = (char) (c3 + 1);
            i++;
        }
        char[] cArr = map1;
        cArr[i] = '+';
        cArr[i + 1] = '/';
        int i2 = 0;
        while (true) {
            byte[] bArr = map2;
            if (i2 >= bArr.length) {
                break;
            }
            bArr[i2] = -1;
            i2++;
        }
        for (int i3 = 0; i3 < 64; i3++) {
            map2[map1[i3]] = (byte) i3;
        }
    }

    public static String encodeString(String str) {
        return new String(encode(str.getBytes()));
    }

    public static char[] encode(byte[] bArr) {
        return encode(bArr, bArr.length);
    }

    public static char[] encode(byte[] bArr, int i) {
        int i2;
        byte b;
        byte b2;
        int i3 = ((i * 4) + 2) / 3;
        char[] cArr = new char[(((i + 2) / 3) * 4)];
        int i4 = 0;
        int i5 = 0;
        while (i4 < i) {
            int i6 = i4 + 1;
            byte b3 = bArr[i4] & UByte.MAX_VALUE;
            if (i6 < i) {
                i2 = i6 + 1;
                b = bArr[i6] & UByte.MAX_VALUE;
            } else {
                i2 = i6;
                b = 0;
            }
            if (i2 < i) {
                b2 = bArr[i2] & UByte.MAX_VALUE;
                i2++;
            } else {
                b2 = 0;
            }
            int i7 = b3 >>> 2;
            int i8 = ((b3 & 3) << 4) | (b >>> 4);
            int i9 = ((b & 15) << 2) | (b2 >>> 6);
            byte b4 = b2 & Utf8.REPLACEMENT_BYTE;
            int i10 = i5 + 1;
            char[] cArr2 = map1;
            cArr[i5] = cArr2[i7];
            int i11 = i10 + 1;
            cArr[i10] = cArr2[i8];
            char c = '=';
            cArr[i11] = i11 < i3 ? cArr2[i9] : '=';
            int i12 = i11 + 1;
            if (i12 < i3) {
                c = cArr2[b4];
            }
            cArr[i12] = c;
            i5 = i12 + 1;
            i4 = i2;
        }
        return cArr;
    }

    public static String decodeString(String str) {
        return new String(decode(str));
    }

    public static byte[] decode(String str) {
        return decode(str.toCharArray());
    }

    public static byte[] decode(char[] cArr) {
        int i;
        char c;
        char c2;
        int i2;
        int length = cArr.length;
        if (length % 4 == 0) {
            while (length > 0 && cArr[length - 1] == '=') {
                length--;
            }
            int i3 = (length * 3) / 4;
            byte[] bArr = new byte[i3];
            int i4 = 0;
            for (int i5 = 0; i5 < length; i5 = i2) {
                int i6 = i5 + 1;
                char c3 = cArr[i5];
                int i7 = i6 + 1;
                char c4 = cArr[i6];
                if (i7 < length) {
                    i = i7 + 1;
                    c = cArr[i7];
                } else {
                    i = i7;
                    c = 'A';
                }
                if (i < length) {
                    i2 = i + 1;
                    c2 = cArr[i];
                } else {
                    int i8 = i;
                    c2 = 'A';
                    i2 = i8;
                }
                if (c3 > 127 || c4 > 127 || c > 127 || c2 > 127) {
                    throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
                }
                byte[] bArr2 = map2;
                byte b = bArr2[c3];
                byte b2 = bArr2[c4];
                byte b3 = bArr2[c];
                byte b4 = bArr2[c2];
                if (b < 0 || b2 < 0 || b3 < 0 || b4 < 0) {
                    throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
                }
                int i9 = (b << 2) | (b2 >>> 4);
                int i10 = ((b2 & 15) << 4) | (b3 >>> 2);
                byte b5 = ((b3 & 3) << 6) | b4;
                int i11 = i4 + 1;
                bArr[i4] = (byte) i9;
                if (i11 < i3) {
                    bArr[i11] = (byte) i10;
                    i11++;
                }
                if (i11 < i3) {
                    bArr[i11] = (byte) b5;
                    i4 = i11 + 1;
                } else {
                    i4 = i11;
                }
            }
            return bArr;
        }
        throw new IllegalArgumentException("Length of Base64 encoded input string is not a multiple of 4.");
    }
}
