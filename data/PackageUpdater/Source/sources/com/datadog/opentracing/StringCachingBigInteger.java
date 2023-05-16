package com.datadog.opentracing;

import java.math.BigInteger;
import java.util.Random;

public class StringCachingBigInteger extends BigInteger {
    private String cachedString;

    public StringCachingBigInteger(byte[] bArr) {
        super(bArr);
    }

    public StringCachingBigInteger(int i, byte[] bArr) {
        super(i, bArr);
    }

    public StringCachingBigInteger(String str, int i) {
        super(str, i);
    }

    public StringCachingBigInteger(String str) {
        super(str);
    }

    public StringCachingBigInteger(int i, Random random) {
        super(i, random);
    }

    public StringCachingBigInteger(int i, int i2, Random random) {
        super(i, i2, random);
    }

    public String toString() {
        if (this.cachedString == null) {
            this.cachedString = super.toString();
        }
        return this.cachedString;
    }

    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public int hashCode() {
        return super.hashCode();
    }
}
