package com.gabb.packageupdater.repository;

import com.gabb.packageupdater.sdk.UpdateCallbacks;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(mo20734d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@"}, mo20735d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "com.gabb.packageupdater.repository.AppRepository$requestSingleDownload$installUri$1", mo21449f = "AppRepository.kt", mo21450i = {}, mo21451l = {46}, mo21452m = "invokeSuspend", mo21453n = {}, mo21454s = {})
/* compiled from: AppRepository.kt */
final class AppRepository$requestSingleDownload$installUri$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
    final /* synthetic */ String $apkUrl;
    final /* synthetic */ Function1<Integer, Unit> $onDownloading;
    final /* synthetic */ String $packageName;
    final /* synthetic */ String $versionName;
    int label;
    final /* synthetic */ AppRepository this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AppRepository$requestSingleDownload$installUri$1(AppRepository appRepository, String str, String str2, String str3, Function1<? super Integer, Unit> function1, Continuation<? super AppRepository$requestSingleDownload$installUri$1> continuation) {
        super(2, continuation);
        this.this$0 = appRepository;
        this.$packageName = str;
        this.$versionName = str2;
        this.$apkUrl = str3;
        this.$onDownloading = function1;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AppRepository$requestSingleDownload$installUri$1(this.this$0, this.$packageName, this.$versionName, this.$apkUrl, this.$onDownloading, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
        return ((AppRepository$requestSingleDownload$installUri$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final UpdateCallbacks updateCallbacks = this.this$0.getCallbacks().get(this.$packageName);
            AppDownloader access$getApkDownloader$p = this.this$0.apkDownloader;
            String str = this.$packageName;
            String str2 = this.$versionName;
            String str3 = this.$apkUrl;
            final Function1<Integer, Unit> function1 = this.$onDownloading;
            this.label = 1;
            obj = access$getApkDownloader$p.downloadApk(str, str2, str3, new Function0<UpdateCallbacks>() {
                public final UpdateCallbacks invoke() {
                    final UpdateCallbacks updateCallbacks = updateCallbacks;
                    final Function1<Integer, Unit> function1 = function1;
                    return new UpdateCallbacks() {
                        public void onDownloadQueued() {
                            UpdateCallbacks updateCallbacks = updateCallbacks;
                            if (updateCallbacks != null) {
                                updateCallbacks.onDownloadQueued();
                            }
                        }

                        public void onDownloadStarted() {
                            UpdateCallbacks updateCallbacks = updateCallbacks;
                            if (updateCallbacks != null) {
                                updateCallbacks.onDownloadStarted();
                            }
                        }

                        public void onDownloading(int i) {
                            UpdateCallbacks updateCallbacks = updateCallbacks;
                            if (updateCallbacks != null) {
                                updateCallbacks.onDownloading(i);
                            }
                            function1.invoke(Integer.valueOf(i));
                        }

                        public void onInstalling() {
                            UpdateCallbacks updateCallbacks = updateCallbacks;
                            if (updateCallbacks != null) {
                                updateCallbacks.onInstalling();
                            }
                        }

                        public void onSuccess() {
                            UpdateCallbacks updateCallbacks = updateCallbacks;
                            if (updateCallbacks != null) {
                                updateCallbacks.onSuccess();
                            }
                        }

                        public void onCancelled() {
                            UpdateCallbacks updateCallbacks = updateCallbacks;
                            if (updateCallbacks != null) {
                                updateCallbacks.onCancelled();
                            }
                        }

                        public void onError(Throwable th) {
                            Intrinsics.checkNotNullParameter(th, "throwable");
                            UpdateCallbacks updateCallbacks = updateCallbacks;
                            if (updateCallbacks != null) {
                                updateCallbacks.onError(th);
                            }
                        }
                    };
                }
            }, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj;
    }
}
