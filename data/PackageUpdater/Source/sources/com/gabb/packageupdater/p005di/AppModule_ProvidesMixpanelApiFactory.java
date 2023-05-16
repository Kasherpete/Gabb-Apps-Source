package com.gabb.packageupdater.p005di;

import android.content.Context;
import com.mixpanel.android.mpmetrics.MixpanelAPI;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* renamed from: com.gabb.packageupdater.di.AppModule_ProvidesMixpanelApiFactory */
public final class AppModule_ProvidesMixpanelApiFactory implements Factory<MixpanelAPI> {
    private final Provider<Context> contextProvider;

    public AppModule_ProvidesMixpanelApiFactory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public MixpanelAPI get() {
        return providesMixpanelApi(this.contextProvider.get());
    }

    public static AppModule_ProvidesMixpanelApiFactory create(Provider<Context> provider) {
        return new AppModule_ProvidesMixpanelApiFactory(provider);
    }

    public static MixpanelAPI providesMixpanelApi(Context context) {
        return (MixpanelAPI) Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.providesMixpanelApi(context));
    }
}
