package com.gabb.packageupdater.repository;

import com.gabb.packageupdater.network.model.Release;
import java.util.function.Predicate;

public final /* synthetic */ class AppRepository$$ExternalSyntheticLambda0 implements Predicate {
    public static final /* synthetic */ AppRepository$$ExternalSyntheticLambda0 INSTANCE = new AppRepository$$ExternalSyntheticLambda0();

    private /* synthetic */ AppRepository$$ExternalSyntheticLambda0() {
    }

    public final boolean test(Object obj) {
        return AppRepository.m168getAppsFromReleases$lambda8((Release) obj);
    }
}
