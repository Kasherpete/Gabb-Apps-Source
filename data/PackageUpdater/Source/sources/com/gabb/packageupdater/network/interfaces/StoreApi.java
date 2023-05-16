package com.gabb.packageupdater.network.interfaces;

import com.gabb.packageupdater.network.model.Release;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

@Metadata(mo20734d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J+\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\bJ+\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\b\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, mo20735d2 = {"Lcom/gabb/packageupdater/network/interfaces/StoreApi;", "", "getReleasesByDevice", "", "Lcom/gabb/packageupdater/network/model/Release;", "auth", "", "deviceType", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getThirdPartyApps", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: StoreApi.kt */
public interface StoreApi {
    @GET("/v2/releases/device/{deviceType}")
    Object getReleasesByDevice(@Header("Authorization") String str, @Path("deviceType") String str2, Continuation<? super List<Release>> continuation);

    @GET("/third-party-apps/{deviceType}")
    Object getThirdPartyApps(@Header("Authorization") String str, @Path("deviceType") String str2, Continuation<? super List<Release>> continuation);
}
