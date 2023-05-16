package com.gabb.packageupdater.p005di;

import android.content.Context;
import com.gabb.packageupdater.domain.packagemanagement.VersionChecker;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* renamed from: com.gabb.packageupdater.di.DomainModule_ProvideVersionCheckerFactory */
public final class DomainModule_ProvideVersionCheckerFactory implements Factory<VersionChecker> {
    private final Provider<Context> contextProvider;

    public DomainModule_ProvideVersionCheckerFactory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public VersionChecker get() {
        return provideVersionChecker(this.contextProvider.get());
    }

    public static DomainModule_ProvideVersionCheckerFactory create(Provider<Context> provider) {
        return new DomainModule_ProvideVersionCheckerFactory(provider);
    }

    public static VersionChecker provideVersionChecker(Context context) {
        return (VersionChecker) Preconditions.checkNotNullFromProvides(DomainModule.INSTANCE.provideVersionChecker(context));
    }
}
