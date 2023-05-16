package com.gabb.packageupdater.p005di;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/* renamed from: com.gabb.packageupdater.di.NetworkModule_ProvideRetrofitFactory */
public final class NetworkModule_ProvideRetrofitFactory implements Factory<Retrofit> {
    private final Provider<OkHttpClient> okHttpClientProvider;

    public NetworkModule_ProvideRetrofitFactory(Provider<OkHttpClient> provider) {
        this.okHttpClientProvider = provider;
    }

    public Retrofit get() {
        return provideRetrofit(this.okHttpClientProvider.get());
    }

    public static NetworkModule_ProvideRetrofitFactory create(Provider<OkHttpClient> provider) {
        return new NetworkModule_ProvideRetrofitFactory(provider);
    }

    public static Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return (Retrofit) Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideRetrofit(okHttpClient));
    }
}
