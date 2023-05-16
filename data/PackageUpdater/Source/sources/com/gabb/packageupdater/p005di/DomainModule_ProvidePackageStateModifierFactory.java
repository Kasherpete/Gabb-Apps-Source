package com.gabb.packageupdater.p005di;

import android.content.Context;
import com.gabb.packageupdater.domain.packagemanagement.PackageStateModifier;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* renamed from: com.gabb.packageupdater.di.DomainModule_ProvidePackageStateModifierFactory */
public final class DomainModule_ProvidePackageStateModifierFactory implements Factory<PackageStateModifier> {
    private final Provider<Context> contextProvider;

    public DomainModule_ProvidePackageStateModifierFactory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public PackageStateModifier get() {
        return providePackageStateModifier(this.contextProvider.get());
    }

    public static DomainModule_ProvidePackageStateModifierFactory create(Provider<Context> provider) {
        return new DomainModule_ProvidePackageStateModifierFactory(provider);
    }

    public static PackageStateModifier providePackageStateModifier(Context context) {
        return (PackageStateModifier) Preconditions.checkNotNullFromProvides(DomainModule.INSTANCE.providePackageStateModifier(context));
    }
}
