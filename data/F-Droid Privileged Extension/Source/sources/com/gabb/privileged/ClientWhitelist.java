package com.gabb.privileged;

import android.util.Pair;
import java.util.Arrays;
import java.util.HashSet;

public class ClientWhitelist {
    public static HashSet<Pair<String, String>> whitelist = new HashSet<>(Arrays.asList(new Pair[]{new Pair("com.gabb.packageupdater", "0d429746e2caf854152a46d4725e24b6186819a8f0f7b03bea3a5c676810ab84")}));
}
