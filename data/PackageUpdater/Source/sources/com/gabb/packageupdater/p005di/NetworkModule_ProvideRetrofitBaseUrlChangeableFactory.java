package com.gabb.packageupdater.p005di;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/* renamed from: com.gabb.packageupdater.di.NetworkModule_ProvideRetrofitBaseUrlChangeableFactory */
public final class NetworkModule_ProvideRetrofitBaseUrlChangeableFactory implements Factory<Retrofit> {
    private final Provider<OkHttpClient> okHttpClientProvider;

    public NetworkModule_ProvideRetrofitBaseUrlChangeableFactory(Provider<OkHttpClient> provider) {
        this.okHttpClientProvider = provider;
    }

    public Retrofit get() {
        return provideRetrofitBaseUrlChangeable(this.okHttpClientProvider.get());
    }

    public static NetworkModule_ProvideRetrofitBaseUrlChangeableFactory create(Provider<OkHttpClient> provider) {
        return new NetworkModule_ProvideRetrofitBaseUrlChangeableFactory(provider);
    }

    public static Retrofit provideRetrofitBaseUrlChangeable(OkHttpClient okHttpClient) {
        return (Retrofit) Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideRetrofitBaseUrlChangeable(okHttpClient));
    }
}
