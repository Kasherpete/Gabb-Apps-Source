package com.gabb.packageupdater.repository;

import android.content.Context;
import com.gabb.data.dao.AppDao;
import com.gabb.packageupdater.domain.packagemanagement.PackageHandler;
import com.gabb.packageupdater.domain.vendor.VendorManager;
import com.gabb.packageupdater.network.interfaces.StoreApi;
import com.gabb.services.tokens.TokenManager;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class AppRepository_Factory implements Factory<AppRepository> {
    private final Provider<AppDownloader> apkDownloaderProvider;
    private final Provider<AppDao> appDAOProvider;
    private final Provider<Context> contextProvider;
    private final Provider<PackageHandler> pacmanProvider;
    private final Provider<StoreApi> storeApiProvider;
    private final Provider<TokenManager> tokenManagerProvider;
    private final Provider<VendorManager> vendorManagerProvider;

    public AppRepository_Factory(Provider<Context> provider, Provider<StoreApi> provider2, Provider<AppDao> provider3, Provider<AppDownloader> provider4, Provider<VendorManager> provider5, Provider<PackageHandler> provider6, Provider<TokenManager> provider7) {
        this.contextProvider = provider;
        this.storeApiProvider = provider2;
        this.appDAOProvider = provider3;
        this.apkDownloaderProvider = provider4;
        this.vendorManagerProvider = provider5;
        this.pacmanProvider = provider6;
        this.tokenManagerProvider = provider7;
    }

    public AppRepository get() {
        return newInstance(this.contextProvider.get(), this.storeApiProvider.get(), this.appDAOProvider.get(), this.apkDownloaderProvider.get(), this.vendorManagerProvider.get(), this.pacmanProvider.get(), this.tokenManagerProvider.get());
    }

    public static AppRepository_Factory create(Provider<Context> provider, Provider<StoreApi> provider2, Provider<AppDao> provider3, Provider<AppDownloader> provider4, Provider<VendorManager> provider5, Provider<PackageHandler> provider6, Provider<TokenManager> provider7) {
        return new AppRepository_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static AppRepository newInstance(Context context, StoreApi storeApi, AppDao appDao, AppDownloader appDownloader, VendorManager vendorManager, PackageHandler packageHandler, TokenManager tokenManager) {
        return new AppRepository(context, storeApi, appDao, appDownloader, vendorManager, packageHandler, tokenManager);
    }
}
