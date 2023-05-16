package com.gabb.packageupdater.work;

import com.gabb.packageupdater.api.App;
import java.util.function.Predicate;

public final /* synthetic */ class InstallerWorker$$ExternalSyntheticLambda0 implements Predicate {
    public final /* synthetic */ App f$0;

    public /* synthetic */ InstallerWorker$$ExternalSyntheticLambda0(App app) {
        this.f$0 = app;
    }

    public final boolean test(Object obj) {
        return InstallerWorker.m171addToUpdatedApps$lambda1(this.f$0, (App) obj);
    }
}
