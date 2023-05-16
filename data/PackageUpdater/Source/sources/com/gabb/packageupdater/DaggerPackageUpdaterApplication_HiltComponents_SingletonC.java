package com.gabb.packageupdater;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.hilt.work.HiltWorkerFactory;
import androidx.hilt.work.HiltWrapper_WorkerFactoryModule;
import androidx.hilt.work.WorkerAssistedFactory;
import androidx.hilt.work.WorkerFactoryModule_ProvideFactoryFactory;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.work.ListenableWorker;
import androidx.work.WorkManager;
import androidx.work.WorkerParameters;
import com.gabb.data.GabbDatabase;
import com.gabb.data.dao.AppDao;
import com.gabb.packageupdater.PackageUpdaterApplication_HiltComponents;
import com.gabb.packageupdater.data.inject.DatabaseDaoModule;
import com.gabb.packageupdater.data.inject.DatabaseDaoModule_ProvidesAppDaoFactory;
import com.gabb.packageupdater.data.inject.RoomDatabaseModule;
import com.gabb.packageupdater.data.inject.RoomDatabaseModule_ProvideDatabaseFactory;
import com.gabb.packageupdater.domain.packagemanagement.PackageHandler;
import com.gabb.packageupdater.domain.packagemanagement.PackageStateModifier;
import com.gabb.packageupdater.domain.packagemanagement.PrivilegedPackageInstaller;
import com.gabb.packageupdater.domain.packagemanagement.PrivilegedPackageRemover;
import com.gabb.packageupdater.domain.packagemanagement.VersionChecker;
import com.gabb.packageupdater.domain.vendor.VendorManager;
import com.gabb.packageupdater.model.UpdatedApps;
import com.gabb.packageupdater.network.interceptors.BaseUrlInterceptor;
import com.gabb.packageupdater.network.interfaces.FileDownloadApi;
import com.gabb.packageupdater.network.interfaces.StoreApi;
import com.gabb.packageupdater.notifications.Notifier;
import com.gabb.packageupdater.p005di.AppModule;
import com.gabb.packageupdater.p005di.AppModule_ProvidesConnectivityManagerFactory;
import com.gabb.packageupdater.p005di.AppModule_ProvidesContentResolverFactory;
import com.gabb.packageupdater.p005di.AppModule_ProvidesMixpanelApiFactory;
import com.gabb.packageupdater.p005di.AppModule_ProvidesNotificationManagerFactory;
import com.gabb.packageupdater.p005di.AppModule_ProvidesPackageManagerFactory;
import com.gabb.packageupdater.p005di.AppModule_ProvidesWorkManagerFactory;
import com.gabb.packageupdater.p005di.DomainModule;
import com.gabb.packageupdater.p005di.DomainModule_ProvideApkCacheFactory;
import com.gabb.packageupdater.p005di.DomainModule_ProvidePackageStateModifierFactory;
import com.gabb.packageupdater.p005di.DomainModule_ProvideUpdatedAppsFactory;
import com.gabb.packageupdater.p005di.DomainModule_ProvideVersionCheckerFactory;
import com.gabb.packageupdater.p005di.NetworkModule;
import com.gabb.packageupdater.p005di.NetworkModule_ProvideFileDownloadApiFactory;
import com.gabb.packageupdater.p005di.NetworkModule_ProvideOkHttpClientBaseUrlChangeableFactory;
import com.gabb.packageupdater.p005di.NetworkModule_ProvideOkHttpClientFactory;
import com.gabb.packageupdater.p005di.NetworkModule_ProvideRetrofitBaseUrlChangeableFactory;
import com.gabb.packageupdater.p005di.NetworkModule_ProvideRetrofitFactory;
import com.gabb.packageupdater.p005di.NetworkModule_ProvideStoreApiFactory;
import com.gabb.packageupdater.p005di.NetworkModule_ProvideTokenManagerFactory;
import com.gabb.packageupdater.receiver.BootCompleteReceiver;
import com.gabb.packageupdater.receiver.BootCompleteReceiver_MembersInjector;
import com.gabb.packageupdater.repository.ApkCache;
import com.gabb.packageupdater.repository.AppDownloader;
import com.gabb.packageupdater.repository.AppRepository;
import com.gabb.packageupdater.service.UpdateService;
import com.gabb.packageupdater.service.UpdateService_MembersInjector;
import com.gabb.packageupdater.util.PackageCertificateManager;
import com.gabb.packageupdater.work.CheckUpdatesWorker;
import com.gabb.packageupdater.work.CheckUpdatesWorker_AssistedFactory;
import com.gabb.packageupdater.work.PeriodicUpdateWorker;
import com.gabb.packageupdater.work.PeriodicUpdateWorker_AssistedFactory;
import com.gabb.packageupdater.work.SingleShotInstallWorker;
import com.gabb.packageupdater.work.SingleShotInstallWorker_AssistedFactory;
import com.gabb.services.tokens.TokenManager;
import com.mixpanel.android.mpmetrics.MixpanelAPI;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.flags.HiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_Lifecycle_Factory;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideApplicationFactory;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DoubleCheck;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import dagger.internal.SingleCheck;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.LoggingEventListener;
import retrofit2.Retrofit;

public final class DaggerPackageUpdaterApplication_HiltComponents_SingletonC extends PackageUpdaterApplication_HiltComponents.SingletonC {
    /* access modifiers changed from: private */
    public Provider<AppDownloader> appDownloaderProvider;
    /* access modifiers changed from: private */
    public Provider<AppRepository> appRepositoryProvider;
    /* access modifiers changed from: private */
    public final ApplicationContextModule applicationContextModule;
    private Provider<CheckUpdatesWorker_AssistedFactory> checkUpdatesWorker_AssistedFactoryProvider;
    private Provider<PeriodicUpdateWorker_AssistedFactory> periodicUpdateWorker_AssistedFactoryProvider;
    /* access modifiers changed from: private */
    public Provider<ApkCache> provideApkCacheProvider;
    private Provider<GabbDatabase> provideDatabaseProvider;
    /* access modifiers changed from: private */
    public Provider<FileDownloadApi> provideFileDownloadApiProvider;
    /* access modifiers changed from: private */
    public Provider<LoggingEventListener.Factory> provideHttpEventListenerProvider;
    /* access modifiers changed from: private */
    public Provider<HttpLoggingInterceptor> provideHttpLoggingInterceptorProvider;
    /* access modifiers changed from: private */
    public Provider<OkHttpClient> provideOkHttpClientBaseUrlChangeableProvider;
    /* access modifiers changed from: private */
    public Provider<OkHttpClient> provideOkHttpClientProvider;
    private Provider<PackageStateModifier> providePackageStateModifierProvider;
    /* access modifiers changed from: private */
    public Provider<Retrofit> provideRetrofitBaseUrlChangeableProvider;
    /* access modifiers changed from: private */
    public Provider<Retrofit> provideRetrofitProvider;
    /* access modifiers changed from: private */
    public Provider<StoreApi> provideStoreApiProvider;
    /* access modifiers changed from: private */
    public Provider<TokenManager> provideTokenManagerProvider;
    /* access modifiers changed from: private */
    public Provider<UpdatedApps> provideUpdatedAppsProvider;
    private Provider<VersionChecker> provideVersionCheckerProvider;
    private Provider<ConnectivityManager> providesConnectivityManagerProvider;
    private Provider<ContentResolver> providesContentResolverProvider;
    private Provider<MixpanelAPI> providesMixpanelApiProvider;
    private Provider<PackageManager> providesPackageManagerProvider;
    private Provider<SingleShotInstallWorker_AssistedFactory> singleShotInstallWorker_AssistedFactoryProvider;
    private final DaggerPackageUpdaterApplication_HiltComponents_SingletonC singletonC;

    private DaggerPackageUpdaterApplication_HiltComponents_SingletonC(ApplicationContextModule applicationContextModule2) {
        this.singletonC = this;
        this.applicationContextModule = applicationContextModule2;
        initialize(applicationContextModule2);
    }

    public static Builder builder() {
        return new Builder();
    }

    private NotificationManager notificationManager() {
        return AppModule_ProvidesNotificationManagerFactory.providesNotificationManager(ApplicationContextModule_ProvideContextFactory.provideContext(this.applicationContextModule));
    }

    private Notifier notifier() {
        return new Notifier(ApplicationContextModule_ProvideContextFactory.provideContext(this.applicationContextModule), notificationManager(), this.providesPackageManagerProvider.get());
    }

    /* access modifiers changed from: private */
    public BaseUrlInterceptor baseUrlInterceptor() {
        return new BaseUrlInterceptor(ApplicationContextModule_ProvideContextFactory.provideContext(this.applicationContextModule));
    }

    /* access modifiers changed from: private */
    public AppDao appDao() {
        return DatabaseDaoModule_ProvidesAppDaoFactory.providesAppDao(this.provideDatabaseProvider.get());
    }

    private PrivilegedPackageInstaller privilegedPackageInstaller() {
        return new PrivilegedPackageInstaller(ApplicationContextModule_ProvideContextFactory.provideContext(this.applicationContextModule));
    }

    private PrivilegedPackageRemover privilegedPackageRemover() {
        return new PrivilegedPackageRemover(ApplicationContextModule_ProvideContextFactory.provideContext(this.applicationContextModule));
    }

    private PackageCertificateManager packageCertificateManager() {
        return new PackageCertificateManager(this.providesPackageManagerProvider.get(), this.providesContentResolverProvider.get());
    }

    /* access modifiers changed from: private */
    public PackageHandler packageHandler() {
        return new PackageHandler(privilegedPackageInstaller(), privilegedPackageRemover(), this.providePackageStateModifierProvider.get(), packageCertificateManager(), this.provideVersionCheckerProvider.get());
    }

    /* access modifiers changed from: private */
    public CheckUpdatesWorker checkUpdatesWorker(Context context, WorkerParameters workerParameters) {
        return new CheckUpdatesWorker(context, workerParameters, notifier(), this.appRepositoryProvider.get());
    }

    /* access modifiers changed from: private */
    public PeriodicUpdateWorker periodicUpdateWorker(Context context, WorkerParameters workerParameters) {
        return new PeriodicUpdateWorker(context, workerParameters, this.providesConnectivityManagerProvider.get(), this.appRepositoryProvider.get(), packageHandler(), notifier(), this.provideUpdatedAppsProvider.get(), this.providesMixpanelApiProvider.get());
    }

    /* access modifiers changed from: private */
    public SingleShotInstallWorker singleShotInstallWorker(Context context, WorkerParameters workerParameters) {
        return new SingleShotInstallWorker(context, workerParameters, this.providesConnectivityManagerProvider.get(), this.appRepositoryProvider.get(), packageHandler(), notifier(), this.provideUpdatedAppsProvider.get(), this.providesMixpanelApiProvider.get());
    }

    private Map<String, Provider<WorkerAssistedFactory<? extends ListenableWorker>>> mapOfStringAndProviderOfWorkerAssistedFactoryOf() {
        return MapBuilder.newMapBuilder(3).put("com.gabb.packageupdater.work.CheckUpdatesWorker", this.checkUpdatesWorker_AssistedFactoryProvider).put("com.gabb.packageupdater.work.PeriodicUpdateWorker", this.periodicUpdateWorker_AssistedFactoryProvider).put("com.gabb.packageupdater.work.SingleShotInstallWorker", this.singleShotInstallWorker_AssistedFactoryProvider).build();
    }

    private HiltWorkerFactory hiltWorkerFactory() {
        return WorkerFactoryModule_ProvideFactoryFactory.provideFactory(mapOfStringAndProviderOfWorkerAssistedFactoryOf());
    }

    private WorkManager workManager() {
        return AppModule_ProvidesWorkManagerFactory.providesWorkManager(ApplicationContextModule_ProvideContextFactory.provideContext(this.applicationContextModule));
    }

    private void initialize(ApplicationContextModule applicationContextModule2) {
        this.providesPackageManagerProvider = DoubleCheck.provider(new SwitchingProvider(this.singletonC, 1));
        this.provideHttpLoggingInterceptorProvider = DoubleCheck.provider(new SwitchingProvider(this.singletonC, 6));
        this.provideHttpEventListenerProvider = DoubleCheck.provider(new SwitchingProvider(this.singletonC, 7));
        this.provideOkHttpClientBaseUrlChangeableProvider = DoubleCheck.provider(new SwitchingProvider(this.singletonC, 5));
        this.provideRetrofitBaseUrlChangeableProvider = DoubleCheck.provider(new SwitchingProvider(this.singletonC, 4));
        this.provideStoreApiProvider = DoubleCheck.provider(new SwitchingProvider(this.singletonC, 3));
        this.provideDatabaseProvider = DoubleCheck.provider(new SwitchingProvider(this.singletonC, 8));
        this.provideOkHttpClientProvider = DoubleCheck.provider(new SwitchingProvider(this.singletonC, 12));
        this.provideRetrofitProvider = DoubleCheck.provider(new SwitchingProvider(this.singletonC, 11));
        this.provideFileDownloadApiProvider = DoubleCheck.provider(new SwitchingProvider(this.singletonC, 10));
        this.provideApkCacheProvider = DoubleCheck.provider(new SwitchingProvider(this.singletonC, 13));
        this.appDownloaderProvider = DoubleCheck.provider(new SwitchingProvider(this.singletonC, 9));
        this.providePackageStateModifierProvider = DoubleCheck.provider(new SwitchingProvider(this.singletonC, 14));
        this.providesContentResolverProvider = DoubleCheck.provider(new SwitchingProvider(this.singletonC, 15));
        this.provideVersionCheckerProvider = DoubleCheck.provider(new SwitchingProvider(this.singletonC, 16));
        this.provideTokenManagerProvider = DoubleCheck.provider(new SwitchingProvider(this.singletonC, 17));
        this.appRepositoryProvider = DoubleCheck.provider(new SwitchingProvider(this.singletonC, 2));
        this.checkUpdatesWorker_AssistedFactoryProvider = SingleCheck.provider(new SwitchingProvider(this.singletonC, 0));
        this.providesConnectivityManagerProvider = DoubleCheck.provider(new SwitchingProvider(this.singletonC, 19));
        this.provideUpdatedAppsProvider = DoubleCheck.provider(new SwitchingProvider(this.singletonC, 20));
        this.providesMixpanelApiProvider = DoubleCheck.provider(new SwitchingProvider(this.singletonC, 21));
        this.periodicUpdateWorker_AssistedFactoryProvider = SingleCheck.provider(new SwitchingProvider(this.singletonC, 18));
        this.singleShotInstallWorker_AssistedFactoryProvider = SingleCheck.provider(new SwitchingProvider(this.singletonC, 22));
    }

    public void injectPackageUpdaterApplication(PackageUpdaterApplication packageUpdaterApplication) {
        injectPackageUpdaterApplication2(packageUpdaterApplication);
    }

    public void injectBootCompleteReceiver(BootCompleteReceiver bootCompleteReceiver) {
        injectBootCompleteReceiver2(bootCompleteReceiver);
    }

    public Set<Boolean> getDisableFragmentGetContextFix() {
        return Collections.emptySet();
    }

    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
        return new ActivityRetainedCBuilder();
    }

    public ServiceComponentBuilder serviceComponentBuilder() {
        return new ServiceCBuilder();
    }

    private PackageUpdaterApplication injectPackageUpdaterApplication2(PackageUpdaterApplication packageUpdaterApplication) {
        PackageUpdaterApplication_MembersInjector.injectWorkerFactory(packageUpdaterApplication, hiltWorkerFactory());
        PackageUpdaterApplication_MembersInjector.injectWorkManager(packageUpdaterApplication, workManager());
        PackageUpdaterApplication_MembersInjector.injectNotifier(packageUpdaterApplication, notifier());
        PackageUpdaterApplication_MembersInjector.injectAppRepository(packageUpdaterApplication, this.appRepositoryProvider.get());
        return packageUpdaterApplication;
    }

    private BootCompleteReceiver injectBootCompleteReceiver2(BootCompleteReceiver bootCompleteReceiver) {
        BootCompleteReceiver_MembersInjector.injectWorkManager(bootCompleteReceiver, workManager());
        BootCompleteReceiver_MembersInjector.injectAppRepository(bootCompleteReceiver, this.appRepositoryProvider.get());
        return bootCompleteReceiver;
    }

    public static final class Builder {
        private ApplicationContextModule applicationContextModule;

        private Builder() {
        }

        @Deprecated
        public Builder appModule(AppModule appModule) {
            Preconditions.checkNotNull(appModule);
            return this;
        }

        public Builder applicationContextModule(ApplicationContextModule applicationContextModule2) {
            this.applicationContextModule = (ApplicationContextModule) Preconditions.checkNotNull(applicationContextModule2);
            return this;
        }

        @Deprecated
        public Builder databaseDaoModule(DatabaseDaoModule databaseDaoModule) {
            Preconditions.checkNotNull(databaseDaoModule);
            return this;
        }

        @Deprecated
        public Builder domainModule(DomainModule domainModule) {
            Preconditions.checkNotNull(domainModule);
            return this;
        }

        @Deprecated
        public Builder hiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule(HiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule hiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule) {
            Preconditions.checkNotNull(hiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule);
            return this;
        }

        @Deprecated
        public Builder hiltWrapper_WorkerFactoryModule(HiltWrapper_WorkerFactoryModule hiltWrapper_WorkerFactoryModule) {
            Preconditions.checkNotNull(hiltWrapper_WorkerFactoryModule);
            return this;
        }

        @Deprecated
        public Builder networkModule(NetworkModule networkModule) {
            Preconditions.checkNotNull(networkModule);
            return this;
        }

        @Deprecated
        public Builder roomDatabaseModule(RoomDatabaseModule roomDatabaseModule) {
            Preconditions.checkNotNull(roomDatabaseModule);
            return this;
        }

        public PackageUpdaterApplication_HiltComponents.SingletonC build() {
            Preconditions.checkBuilderRequirement(this.applicationContextModule, ApplicationContextModule.class);
            return new DaggerPackageUpdaterApplication_HiltComponents_SingletonC(this.applicationContextModule);
        }
    }

    private static final class ActivityRetainedCBuilder implements PackageUpdaterApplication_HiltComponents.ActivityRetainedC.Builder {
        private final DaggerPackageUpdaterApplication_HiltComponents_SingletonC singletonC;

        private ActivityRetainedCBuilder(DaggerPackageUpdaterApplication_HiltComponents_SingletonC daggerPackageUpdaterApplication_HiltComponents_SingletonC) {
            this.singletonC = daggerPackageUpdaterApplication_HiltComponents_SingletonC;
        }

        public PackageUpdaterApplication_HiltComponents.ActivityRetainedC build() {
            return new ActivityRetainedCImpl();
        }
    }

    private static final class ActivityCBuilder implements PackageUpdaterApplication_HiltComponents.ActivityC.Builder {
        private Activity activity;
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private final DaggerPackageUpdaterApplication_HiltComponents_SingletonC singletonC;

        private ActivityCBuilder(DaggerPackageUpdaterApplication_HiltComponents_SingletonC daggerPackageUpdaterApplication_HiltComponents_SingletonC, ActivityRetainedCImpl activityRetainedCImpl2) {
            this.singletonC = daggerPackageUpdaterApplication_HiltComponents_SingletonC;
            this.activityRetainedCImpl = activityRetainedCImpl2;
        }

        public ActivityCBuilder activity(Activity activity2) {
            this.activity = (Activity) Preconditions.checkNotNull(activity2);
            return this;
        }

        public PackageUpdaterApplication_HiltComponents.ActivityC build() {
            Preconditions.checkBuilderRequirement(this.activity, Activity.class);
            return new ActivityCImpl(this.activityRetainedCImpl, this.activity);
        }
    }

    private static final class FragmentCBuilder implements PackageUpdaterApplication_HiltComponents.FragmentC.Builder {
        private final ActivityCImpl activityCImpl;
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private Fragment fragment;
        private final DaggerPackageUpdaterApplication_HiltComponents_SingletonC singletonC;

        private FragmentCBuilder(DaggerPackageUpdaterApplication_HiltComponents_SingletonC daggerPackageUpdaterApplication_HiltComponents_SingletonC, ActivityRetainedCImpl activityRetainedCImpl2, ActivityCImpl activityCImpl2) {
            this.singletonC = daggerPackageUpdaterApplication_HiltComponents_SingletonC;
            this.activityRetainedCImpl = activityRetainedCImpl2;
            this.activityCImpl = activityCImpl2;
        }

        public FragmentCBuilder fragment(Fragment fragment2) {
            this.fragment = (Fragment) Preconditions.checkNotNull(fragment2);
            return this;
        }

        public PackageUpdaterApplication_HiltComponents.FragmentC build() {
            Preconditions.checkBuilderRequirement(this.fragment, Fragment.class);
            return new FragmentCImpl(this.activityRetainedCImpl, this.activityCImpl, this.fragment);
        }
    }

    private static final class ViewWithFragmentCBuilder implements PackageUpdaterApplication_HiltComponents.ViewWithFragmentC.Builder {
        private final ActivityCImpl activityCImpl;
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private final FragmentCImpl fragmentCImpl;
        private final DaggerPackageUpdaterApplication_HiltComponents_SingletonC singletonC;
        private View view;

        private ViewWithFragmentCBuilder(DaggerPackageUpdaterApplication_HiltComponents_SingletonC daggerPackageUpdaterApplication_HiltComponents_SingletonC, ActivityRetainedCImpl activityRetainedCImpl2, ActivityCImpl activityCImpl2, FragmentCImpl fragmentCImpl2) {
            this.singletonC = daggerPackageUpdaterApplication_HiltComponents_SingletonC;
            this.activityRetainedCImpl = activityRetainedCImpl2;
            this.activityCImpl = activityCImpl2;
            this.fragmentCImpl = fragmentCImpl2;
        }

        public ViewWithFragmentCBuilder view(View view2) {
            this.view = (View) Preconditions.checkNotNull(view2);
            return this;
        }

        public PackageUpdaterApplication_HiltComponents.ViewWithFragmentC build() {
            Preconditions.checkBuilderRequirement(this.view, View.class);
            return new ViewWithFragmentCImpl(this.activityRetainedCImpl, this.activityCImpl, this.fragmentCImpl, this.view);
        }
    }

    private static final class ViewCBuilder implements PackageUpdaterApplication_HiltComponents.ViewC.Builder {
        private final ActivityCImpl activityCImpl;
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private final DaggerPackageUpdaterApplication_HiltComponents_SingletonC singletonC;
        private View view;

        private ViewCBuilder(DaggerPackageUpdaterApplication_HiltComponents_SingletonC daggerPackageUpdaterApplication_HiltComponents_SingletonC, ActivityRetainedCImpl activityRetainedCImpl2, ActivityCImpl activityCImpl2) {
            this.singletonC = daggerPackageUpdaterApplication_HiltComponents_SingletonC;
            this.activityRetainedCImpl = activityRetainedCImpl2;
            this.activityCImpl = activityCImpl2;
        }

        public ViewCBuilder view(View view2) {
            this.view = (View) Preconditions.checkNotNull(view2);
            return this;
        }

        public PackageUpdaterApplication_HiltComponents.ViewC build() {
            Preconditions.checkBuilderRequirement(this.view, View.class);
            return new ViewCImpl(this.activityRetainedCImpl, this.activityCImpl, this.view);
        }
    }

    private static final class ViewModelCBuilder implements PackageUpdaterApplication_HiltComponents.ViewModelC.Builder {
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private SavedStateHandle savedStateHandle;
        private final DaggerPackageUpdaterApplication_HiltComponents_SingletonC singletonC;

        private ViewModelCBuilder(DaggerPackageUpdaterApplication_HiltComponents_SingletonC daggerPackageUpdaterApplication_HiltComponents_SingletonC, ActivityRetainedCImpl activityRetainedCImpl2) {
            this.singletonC = daggerPackageUpdaterApplication_HiltComponents_SingletonC;
            this.activityRetainedCImpl = activityRetainedCImpl2;
        }

        public ViewModelCBuilder savedStateHandle(SavedStateHandle savedStateHandle2) {
            this.savedStateHandle = (SavedStateHandle) Preconditions.checkNotNull(savedStateHandle2);
            return this;
        }

        public PackageUpdaterApplication_HiltComponents.ViewModelC build() {
            Preconditions.checkBuilderRequirement(this.savedStateHandle, SavedStateHandle.class);
            return new ViewModelCImpl(this.activityRetainedCImpl, this.savedStateHandle);
        }
    }

    private static final class ServiceCBuilder implements PackageUpdaterApplication_HiltComponents.ServiceC.Builder {
        private Service service;
        private final DaggerPackageUpdaterApplication_HiltComponents_SingletonC singletonC;

        private ServiceCBuilder(DaggerPackageUpdaterApplication_HiltComponents_SingletonC daggerPackageUpdaterApplication_HiltComponents_SingletonC) {
            this.singletonC = daggerPackageUpdaterApplication_HiltComponents_SingletonC;
        }

        public ServiceCBuilder service(Service service2) {
            this.service = (Service) Preconditions.checkNotNull(service2);
            return this;
        }

        public PackageUpdaterApplication_HiltComponents.ServiceC build() {
            Preconditions.checkBuilderRequirement(this.service, Service.class);
            return new ServiceCImpl(this.service);
        }
    }

    private static final class ViewWithFragmentCImpl extends PackageUpdaterApplication_HiltComponents.ViewWithFragmentC {
        private final ActivityCImpl activityCImpl;
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private final FragmentCImpl fragmentCImpl;
        private final DaggerPackageUpdaterApplication_HiltComponents_SingletonC singletonC;
        private final ViewWithFragmentCImpl viewWithFragmentCImpl;

        private ViewWithFragmentCImpl(DaggerPackageUpdaterApplication_HiltComponents_SingletonC daggerPackageUpdaterApplication_HiltComponents_SingletonC, ActivityRetainedCImpl activityRetainedCImpl2, ActivityCImpl activityCImpl2, FragmentCImpl fragmentCImpl2, View view) {
            this.viewWithFragmentCImpl = this;
            this.singletonC = daggerPackageUpdaterApplication_HiltComponents_SingletonC;
            this.activityRetainedCImpl = activityRetainedCImpl2;
            this.activityCImpl = activityCImpl2;
            this.fragmentCImpl = fragmentCImpl2;
        }
    }

    private static final class FragmentCImpl extends PackageUpdaterApplication_HiltComponents.FragmentC {
        private final ActivityCImpl activityCImpl;
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private final FragmentCImpl fragmentCImpl;
        private final DaggerPackageUpdaterApplication_HiltComponents_SingletonC singletonC;

        private FragmentCImpl(DaggerPackageUpdaterApplication_HiltComponents_SingletonC daggerPackageUpdaterApplication_HiltComponents_SingletonC, ActivityRetainedCImpl activityRetainedCImpl2, ActivityCImpl activityCImpl2, Fragment fragment) {
            this.fragmentCImpl = this;
            this.singletonC = daggerPackageUpdaterApplication_HiltComponents_SingletonC;
            this.activityRetainedCImpl = activityRetainedCImpl2;
            this.activityCImpl = activityCImpl2;
        }

        public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
            return this.activityCImpl.getHiltInternalFactoryFactory();
        }

        public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
            return new ViewWithFragmentCBuilder(this.activityRetainedCImpl, this.activityCImpl, this.fragmentCImpl);
        }
    }

    private static final class ViewCImpl extends PackageUpdaterApplication_HiltComponents.ViewC {
        private final ActivityCImpl activityCImpl;
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private final DaggerPackageUpdaterApplication_HiltComponents_SingletonC singletonC;
        private final ViewCImpl viewCImpl;

        private ViewCImpl(DaggerPackageUpdaterApplication_HiltComponents_SingletonC daggerPackageUpdaterApplication_HiltComponents_SingletonC, ActivityRetainedCImpl activityRetainedCImpl2, ActivityCImpl activityCImpl2, View view) {
            this.viewCImpl = this;
            this.singletonC = daggerPackageUpdaterApplication_HiltComponents_SingletonC;
            this.activityRetainedCImpl = activityRetainedCImpl2;
            this.activityCImpl = activityCImpl2;
        }
    }

    private static final class ActivityCImpl extends PackageUpdaterApplication_HiltComponents.ActivityC {
        private final ActivityCImpl activityCImpl;
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private final DaggerPackageUpdaterApplication_HiltComponents_SingletonC singletonC;

        private ActivityCImpl(DaggerPackageUpdaterApplication_HiltComponents_SingletonC daggerPackageUpdaterApplication_HiltComponents_SingletonC, ActivityRetainedCImpl activityRetainedCImpl2, Activity activity) {
            this.activityCImpl = this;
            this.singletonC = daggerPackageUpdaterApplication_HiltComponents_SingletonC;
            this.activityRetainedCImpl = activityRetainedCImpl2;
        }

        public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
            return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(ApplicationContextModule_ProvideApplicationFactory.provideApplication(this.singletonC.applicationContextModule), Collections.emptySet(), new ViewModelCBuilder(this.activityRetainedCImpl));
        }

        public Set<String> getViewModelKeys() {
            return Collections.emptySet();
        }

        public ViewModelComponentBuilder getViewModelComponentBuilder() {
            return new ViewModelCBuilder(this.activityRetainedCImpl);
        }

        public FragmentComponentBuilder fragmentComponentBuilder() {
            return new FragmentCBuilder(this.activityRetainedCImpl, this.activityCImpl);
        }

        public ViewComponentBuilder viewComponentBuilder() {
            return new ViewCBuilder(this.activityRetainedCImpl, this.activityCImpl);
        }
    }

    private static final class ViewModelCImpl extends PackageUpdaterApplication_HiltComponents.ViewModelC {
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private final DaggerPackageUpdaterApplication_HiltComponents_SingletonC singletonC;
        private final ViewModelCImpl viewModelCImpl;

        private ViewModelCImpl(DaggerPackageUpdaterApplication_HiltComponents_SingletonC daggerPackageUpdaterApplication_HiltComponents_SingletonC, ActivityRetainedCImpl activityRetainedCImpl2, SavedStateHandle savedStateHandle) {
            this.viewModelCImpl = this;
            this.singletonC = daggerPackageUpdaterApplication_HiltComponents_SingletonC;
            this.activityRetainedCImpl = activityRetainedCImpl2;
        }

        public Map<String, Provider<ViewModel>> getHiltViewModelMap() {
            return Collections.emptyMap();
        }
    }

    private static final class ActivityRetainedCImpl extends PackageUpdaterApplication_HiltComponents.ActivityRetainedC {
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private Provider lifecycleProvider;
        private final DaggerPackageUpdaterApplication_HiltComponents_SingletonC singletonC;

        private ActivityRetainedCImpl(DaggerPackageUpdaterApplication_HiltComponents_SingletonC daggerPackageUpdaterApplication_HiltComponents_SingletonC) {
            this.activityRetainedCImpl = this;
            this.singletonC = daggerPackageUpdaterApplication_HiltComponents_SingletonC;
            initialize();
        }

        private void initialize() {
            this.lifecycleProvider = DoubleCheck.provider(new SwitchingProvider(this.singletonC, this.activityRetainedCImpl, 0));
        }

        public ActivityComponentBuilder activityComponentBuilder() {
            return new ActivityCBuilder(this.activityRetainedCImpl);
        }

        public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
            return (ActivityRetainedLifecycle) this.lifecycleProvider.get();
        }

        private static final class SwitchingProvider<T> implements Provider<T> {
            private final ActivityRetainedCImpl activityRetainedCImpl;

            /* renamed from: id */
            private final int f153id;
            private final DaggerPackageUpdaterApplication_HiltComponents_SingletonC singletonC;

            SwitchingProvider(DaggerPackageUpdaterApplication_HiltComponents_SingletonC daggerPackageUpdaterApplication_HiltComponents_SingletonC, ActivityRetainedCImpl activityRetainedCImpl2, int i) {
                this.singletonC = daggerPackageUpdaterApplication_HiltComponents_SingletonC;
                this.activityRetainedCImpl = activityRetainedCImpl2;
                this.f153id = i;
            }

            public T get() {
                if (this.f153id == 0) {
                    return ActivityRetainedComponentManager_Lifecycle_Factory.newInstance();
                }
                throw new AssertionError(this.f153id);
            }
        }
    }

    private static final class ServiceCImpl extends PackageUpdaterApplication_HiltComponents.ServiceC {
        private final ServiceCImpl serviceCImpl;
        private final DaggerPackageUpdaterApplication_HiltComponents_SingletonC singletonC;

        private ServiceCImpl(DaggerPackageUpdaterApplication_HiltComponents_SingletonC daggerPackageUpdaterApplication_HiltComponents_SingletonC, Service service) {
            this.serviceCImpl = this;
            this.singletonC = daggerPackageUpdaterApplication_HiltComponents_SingletonC;
        }

        public void injectUpdateService(UpdateService updateService) {
            injectUpdateService2(updateService);
        }

        private UpdateService injectUpdateService2(UpdateService updateService) {
            UpdateService_MembersInjector.injectUpdatedApps(updateService, (UpdatedApps) this.singletonC.provideUpdatedAppsProvider.get());
            UpdateService_MembersInjector.injectAppRepository(updateService, (AppRepository) this.singletonC.appRepositoryProvider.get());
            return updateService;
        }
    }

    private static final class SwitchingProvider<T> implements Provider<T> {

        /* renamed from: id */
        private final int f154id;
        /* access modifiers changed from: private */
        public final DaggerPackageUpdaterApplication_HiltComponents_SingletonC singletonC;

        SwitchingProvider(DaggerPackageUpdaterApplication_HiltComponents_SingletonC daggerPackageUpdaterApplication_HiltComponents_SingletonC, int i) {
            this.singletonC = daggerPackageUpdaterApplication_HiltComponents_SingletonC;
            this.f154id = i;
        }

        public T get() {
            switch (this.f154id) {
                case 0:
                    return new CheckUpdatesWorker_AssistedFactory() {
                        public CheckUpdatesWorker create(Context context, WorkerParameters workerParameters) {
                            return SwitchingProvider.this.singletonC.checkUpdatesWorker(context, workerParameters);
                        }
                    };
                case 1:
                    return AppModule_ProvidesPackageManagerFactory.providesPackageManager(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonC.applicationContextModule));
                case 2:
                    return new AppRepository(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonC.applicationContextModule), (StoreApi) this.singletonC.provideStoreApiProvider.get(), this.singletonC.appDao(), (AppDownloader) this.singletonC.appDownloaderProvider.get(), new VendorManager(), this.singletonC.packageHandler(), (TokenManager) this.singletonC.provideTokenManagerProvider.get());
                case 3:
                    return NetworkModule_ProvideStoreApiFactory.provideStoreApi((Retrofit) this.singletonC.provideRetrofitBaseUrlChangeableProvider.get());
                case 4:
                    return NetworkModule_ProvideRetrofitBaseUrlChangeableFactory.provideRetrofitBaseUrlChangeable((OkHttpClient) this.singletonC.provideOkHttpClientBaseUrlChangeableProvider.get());
                case 5:
                    return NetworkModule_ProvideOkHttpClientBaseUrlChangeableFactory.provideOkHttpClientBaseUrlChangeable((HttpLoggingInterceptor) this.singletonC.provideHttpLoggingInterceptorProvider.get(), (LoggingEventListener.Factory) this.singletonC.provideHttpEventListenerProvider.get(), this.singletonC.baseUrlInterceptor(), ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonC.applicationContextModule));
                case 6:
                    return NetworkModule.INSTANCE.provideHttpLoggingInterceptor();
                case 7:
                    return NetworkModule.INSTANCE.provideHttpEventListener();
                case 8:
                    return RoomDatabaseModule_ProvideDatabaseFactory.provideDatabase(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonC.applicationContextModule));
                case 9:
                    return new AppDownloader((FileDownloadApi) this.singletonC.provideFileDownloadApiProvider.get(), (ApkCache) this.singletonC.provideApkCacheProvider.get());
                case 10:
                    return NetworkModule_ProvideFileDownloadApiFactory.provideFileDownloadApi((Retrofit) this.singletonC.provideRetrofitProvider.get());
                case 11:
                    return NetworkModule_ProvideRetrofitFactory.provideRetrofit((OkHttpClient) this.singletonC.provideOkHttpClientProvider.get());
                case 12:
                    return NetworkModule_ProvideOkHttpClientFactory.provideOkHttpClient((HttpLoggingInterceptor) this.singletonC.provideHttpLoggingInterceptorProvider.get(), (LoggingEventListener.Factory) this.singletonC.provideHttpEventListenerProvider.get(), ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonC.applicationContextModule));
                case 13:
                    return DomainModule_ProvideApkCacheFactory.provideApkCache(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonC.applicationContextModule));
                case 14:
                    return DomainModule_ProvidePackageStateModifierFactory.providePackageStateModifier(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonC.applicationContextModule));
                case 15:
                    return AppModule_ProvidesContentResolverFactory.providesContentResolver(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonC.applicationContextModule));
                case 16:
                    return DomainModule_ProvideVersionCheckerFactory.provideVersionChecker(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonC.applicationContextModule));
                case 17:
                    return NetworkModule_ProvideTokenManagerFactory.provideTokenManager(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonC.applicationContextModule));
                case 18:
                    return new PeriodicUpdateWorker_AssistedFactory() {
                        public PeriodicUpdateWorker create(Context context, WorkerParameters workerParameters) {
                            return SwitchingProvider.this.singletonC.periodicUpdateWorker(context, workerParameters);
                        }
                    };
                case 19:
                    return AppModule_ProvidesConnectivityManagerFactory.providesConnectivityManager(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonC.applicationContextModule));
                case 20:
                    return DomainModule_ProvideUpdatedAppsFactory.provideUpdatedApps();
                case 21:
                    return AppModule_ProvidesMixpanelApiFactory.providesMixpanelApi(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonC.applicationContextModule));
                case 22:
                    return new SingleShotInstallWorker_AssistedFactory() {
                        public SingleShotInstallWorker create(Context context, WorkerParameters workerParameters) {
                            return SwitchingProvider.this.singletonC.singleShotInstallWorker(context, workerParameters);
                        }
                    };
                default:
                    throw new AssertionError(this.f154id);
            }
        }
    }
}
