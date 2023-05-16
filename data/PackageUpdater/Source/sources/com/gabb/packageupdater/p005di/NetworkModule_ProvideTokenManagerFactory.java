package com.gabb.packageupdater.p005di;

import android.content.Context;
import com.gabb.services.tokens.TokenManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* renamed from: com.gabb.packageupdater.di.NetworkModule_ProvideTokenManagerFactory */
public final class NetworkModule_ProvideTokenManagerFactory implements Factory<TokenManager> {
    private final Provider<Context> contextProvider;

    public NetworkModule_ProvideTokenManagerFactory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public TokenManager get() {
        return provideTokenManager(this.contextProvider.get());
    }

    public static NetworkModule_ProvideTokenManagerFactory create(Provider<Context> provider) {
        return new NetworkModule_ProvideTokenManagerFactory(provider);
    }

    public static TokenManager provideTokenManager(Context context) {
        return (TokenManager) Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideTokenManager(context));
    }
}
