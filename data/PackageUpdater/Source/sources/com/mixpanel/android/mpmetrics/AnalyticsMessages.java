package com.mixpanel.android.mpmetrics;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import com.datadog.android.core.internal.CoreFeature;
import com.mixpanel.android.mpmetrics.MPDbAdapter;
import com.mixpanel.android.util.HttpService;
import com.mixpanel.android.util.MPLog;
import com.mixpanel.android.util.RemoteService;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

class AnalyticsMessages {
    private static final int CLEAR_ANONYMOUS_UPDATES = 7;
    private static final int EMPTY_QUEUES = 6;
    private static final int ENQUEUE_EVENTS = 1;
    private static final int ENQUEUE_GROUP = 3;
    private static final int ENQUEUE_PEOPLE = 0;
    private static final int FLUSH_QUEUE = 2;
    private static final int KILL_WORKER = 5;
    private static final String LOGTAG = "MixpanelAPI.Messages";
    private static final int PUSH_ANONYMOUS_PEOPLE_RECORDS = 4;
    private static final int REWRITE_EVENT_PROPERTIES = 8;
    private static final Map<Context, AnalyticsMessages> sInstances = new HashMap();
    protected final MPConfig mConfig;
    protected final Context mContext;
    private final Worker mWorker = createWorker();

    AnalyticsMessages(Context context) {
        this.mContext = context;
        this.mConfig = getConfig(context);
        getPoster().checkIsMixpanelBlocked();
    }

    /* access modifiers changed from: protected */
    public Worker createWorker() {
        return new Worker();
    }

    public static AnalyticsMessages getInstance(Context context) {
        AnalyticsMessages analyticsMessages;
        Map<Context, AnalyticsMessages> map = sInstances;
        synchronized (map) {
            Context applicationContext = context.getApplicationContext();
            if (!map.containsKey(applicationContext)) {
                analyticsMessages = new AnalyticsMessages(applicationContext);
                map.put(applicationContext, analyticsMessages);
            } else {
                analyticsMessages = map.get(applicationContext);
            }
        }
        return analyticsMessages;
    }

    public void eventsMessage(EventDescription eventDescription) {
        Message obtain = Message.obtain();
        obtain.what = 1;
        obtain.obj = eventDescription;
        this.mWorker.runMessage(obtain);
    }

    public void peopleMessage(PeopleDescription peopleDescription) {
        Message obtain = Message.obtain();
        obtain.what = 0;
        obtain.obj = peopleDescription;
        this.mWorker.runMessage(obtain);
    }

    public void groupMessage(GroupDescription groupDescription) {
        Message obtain = Message.obtain();
        obtain.what = 3;
        obtain.obj = groupDescription;
        this.mWorker.runMessage(obtain);
    }

    public void pushAnonymousPeopleMessage(PushAnonymousPeopleDescription pushAnonymousPeopleDescription) {
        Message obtain = Message.obtain();
        obtain.what = 4;
        obtain.obj = pushAnonymousPeopleDescription;
        this.mWorker.runMessage(obtain);
    }

    public void clearAnonymousUpdatesMessage(MixpanelDescription mixpanelDescription) {
        Message obtain = Message.obtain();
        obtain.what = 7;
        obtain.obj = mixpanelDescription;
        this.mWorker.runMessage(obtain);
    }

    public void postToServer(MixpanelDescription mixpanelDescription) {
        Message obtain = Message.obtain();
        obtain.what = 2;
        obtain.obj = mixpanelDescription.getToken();
        obtain.arg1 = 0;
        this.mWorker.runMessage(obtain);
    }

    public void emptyTrackingQueues(MixpanelDescription mixpanelDescription) {
        Message obtain = Message.obtain();
        obtain.what = 6;
        obtain.obj = mixpanelDescription;
        this.mWorker.runMessage(obtain);
    }

    public void updateEventProperties(UpdateEventsPropertiesDescription updateEventsPropertiesDescription) {
        Message obtain = Message.obtain();
        obtain.what = 8;
        obtain.obj = updateEventsPropertiesDescription;
        this.mWorker.runMessage(obtain);
    }

    public void hardKill() {
        Message obtain = Message.obtain();
        obtain.what = 5;
        this.mWorker.runMessage(obtain);
    }

    /* access modifiers changed from: package-private */
    public boolean isDead() {
        return this.mWorker.isDead();
    }

    /* access modifiers changed from: protected */
    public MPDbAdapter makeDbAdapter(Context context) {
        return MPDbAdapter.getInstance(context);
    }

    /* access modifiers changed from: protected */
    public MPConfig getConfig(Context context) {
        return MPConfig.getInstance(context);
    }

    /* access modifiers changed from: protected */
    public RemoteService getPoster() {
        return new HttpService();
    }

    static class EventDescription extends MixpanelMessageDescription {
        private final String mEventName;
        private final boolean mIsAutomatic;
        private final JSONObject mSessionMetadata;

        public EventDescription(String str, JSONObject jSONObject, String str2) {
            this(str, jSONObject, str2, false, new JSONObject());
        }

        public EventDescription(String str, JSONObject jSONObject, String str2, boolean z, JSONObject jSONObject2) {
            super(str2, jSONObject);
            this.mEventName = str;
            this.mIsAutomatic = z;
            this.mSessionMetadata = jSONObject2;
        }

        public String getEventName() {
            return this.mEventName;
        }

        public JSONObject getProperties() {
            return getMessage();
        }

        public JSONObject getSessionMetadata() {
            return this.mSessionMetadata;
        }

        public boolean isAutomatic() {
            return this.mIsAutomatic;
        }
    }

    static class PeopleDescription extends MixpanelMessageDescription {
        public PeopleDescription(JSONObject jSONObject, String str) {
            super(str, jSONObject);
        }

        public String toString() {
            return getMessage().toString();
        }

        public boolean isAnonymous() {
            return !getMessage().has("$distinct_id");
        }
    }

    static class GroupDescription extends MixpanelMessageDescription {
        public GroupDescription(JSONObject jSONObject, String str) {
            super(str, jSONObject);
        }

        public String toString() {
            return getMessage().toString();
        }
    }

    static class PushAnonymousPeopleDescription extends MixpanelDescription {
        private final String mDistinctId;

        public PushAnonymousPeopleDescription(String str, String str2) {
            super(str2);
            this.mDistinctId = str;
        }

        public String toString() {
            return this.mDistinctId;
        }

        public String getDistinctId() {
            return this.mDistinctId;
        }
    }

    static class MixpanelMessageDescription extends MixpanelDescription {
        private final JSONObject mMessage;

        public MixpanelMessageDescription(String str, JSONObject jSONObject) {
            super(str);
            if (jSONObject != null && jSONObject.length() > 0) {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    try {
                        jSONObject.get(next).toString();
                    } catch (AssertionError e) {
                        jSONObject.remove(next);
                        MPLog.m62e(AnalyticsMessages.LOGTAG, "Removing people profile property from update (see https://github.com/mixpanel/mixpanel-android/issues/567)", e);
                    } catch (JSONException unused) {
                    }
                }
            }
            this.mMessage = jSONObject;
        }

        public JSONObject getMessage() {
            return this.mMessage;
        }
    }

    static class UpdateEventsPropertiesDescription extends MixpanelDescription {
        private final Map<String, String> mProps;

        public UpdateEventsPropertiesDescription(String str, Map<String, String> map) {
            super(str);
            this.mProps = map;
        }

        public Map<String, String> getProperties() {
            return this.mProps;
        }
    }

    static class MixpanelDescription {
        private final String mToken;

        public MixpanelDescription(String str) {
            this.mToken = str;
        }

        public String getToken() {
            return this.mToken;
        }
    }

    /* access modifiers changed from: private */
    public void logAboutMessageToMixpanel(String str) {
        MPLog.m65v(LOGTAG, str + " (Thread " + Thread.currentThread().getId() + ")");
    }

    /* access modifiers changed from: private */
    public void logAboutMessageToMixpanel(String str, Throwable th) {
        MPLog.m66v(LOGTAG, str + " (Thread " + Thread.currentThread().getId() + ")", th);
    }

    class Worker {
        private long mAveFlushFrequency = 0;
        private long mFlushCount = 0;
        /* access modifiers changed from: private */
        public Handler mHandler = restartWorkerThread();
        /* access modifiers changed from: private */
        public final Object mHandlerLock = new Object();
        private long mLastFlushTime = -1;
        /* access modifiers changed from: private */
        public SystemInformation mSystemInformation;

        public Worker() {
        }

        public boolean isDead() {
            boolean z;
            synchronized (this.mHandlerLock) {
                z = this.mHandler == null;
            }
            return z;
        }

        public void runMessage(Message message) {
            synchronized (this.mHandlerLock) {
                Handler handler = this.mHandler;
                if (handler == null) {
                    AnalyticsMessages.this.logAboutMessageToMixpanel("Dead mixpanel worker dropping a message: " + message.what);
                } else {
                    handler.sendMessage(message);
                }
            }
        }

        /* access modifiers changed from: protected */
        public Handler restartWorkerThread() {
            HandlerThread handlerThread = new HandlerThread("com.mixpanel.android.AnalyticsWorker", 10);
            handlerThread.start();
            return new AnalyticsMessageHandler(handlerThread.getLooper());
        }

        class AnalyticsMessageHandler extends Handler {
            private MPDbAdapter mDbAdapter = null;
            private int mFailedRetries;
            private final long mFlushInterval;
            private long mTrackEngageRetryAfter;

            public AnalyticsMessageHandler(Looper looper) {
                super(looper);
                SystemInformation unused = Worker.this.mSystemInformation = SystemInformation.getInstance(AnalyticsMessages.this.mContext);
                this.mFlushInterval = (long) AnalyticsMessages.this.mConfig.getFlushInterval();
            }

            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v29, resolved type: java.lang.Object} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v25, resolved type: java.lang.String} */
            /* JADX WARNING: Multi-variable type inference failed */
            /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
            /* JADX WARNING: Removed duplicated region for block: B:71:0x0289 A[Catch:{ RuntimeException -> 0x02c7 }] */
            /* JADX WARNING: Removed duplicated region for block: B:92:? A[Catch:{  }, RETURN, SYNTHETIC] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void handleMessage(android.os.Message r10) {
                /*
                    r9 = this;
                    com.mixpanel.android.mpmetrics.MPDbAdapter r0 = r9.mDbAdapter
                    if (r0 != 0) goto L_0x003e
                    com.mixpanel.android.mpmetrics.AnalyticsMessages$Worker r0 = com.mixpanel.android.mpmetrics.AnalyticsMessages.Worker.this
                    com.mixpanel.android.mpmetrics.AnalyticsMessages r0 = com.mixpanel.android.mpmetrics.AnalyticsMessages.this
                    com.mixpanel.android.mpmetrics.AnalyticsMessages$Worker r1 = com.mixpanel.android.mpmetrics.AnalyticsMessages.Worker.this
                    com.mixpanel.android.mpmetrics.AnalyticsMessages r1 = com.mixpanel.android.mpmetrics.AnalyticsMessages.this
                    android.content.Context r1 = r1.mContext
                    com.mixpanel.android.mpmetrics.MPDbAdapter r0 = r0.makeDbAdapter(r1)
                    r9.mDbAdapter = r0
                    long r1 = java.lang.System.currentTimeMillis()
                    com.mixpanel.android.mpmetrics.AnalyticsMessages$Worker r3 = com.mixpanel.android.mpmetrics.AnalyticsMessages.Worker.this
                    com.mixpanel.android.mpmetrics.AnalyticsMessages r3 = com.mixpanel.android.mpmetrics.AnalyticsMessages.this
                    com.mixpanel.android.mpmetrics.MPConfig r3 = r3.mConfig
                    long r3 = r3.getDataExpiration()
                    long r1 = r1 - r3
                    com.mixpanel.android.mpmetrics.MPDbAdapter$Table r3 = com.mixpanel.android.mpmetrics.MPDbAdapter.Table.EVENTS
                    r0.cleanupEvents(r1, r3)
                    com.mixpanel.android.mpmetrics.MPDbAdapter r0 = r9.mDbAdapter
                    long r1 = java.lang.System.currentTimeMillis()
                    com.mixpanel.android.mpmetrics.AnalyticsMessages$Worker r3 = com.mixpanel.android.mpmetrics.AnalyticsMessages.Worker.this
                    com.mixpanel.android.mpmetrics.AnalyticsMessages r3 = com.mixpanel.android.mpmetrics.AnalyticsMessages.this
                    com.mixpanel.android.mpmetrics.MPConfig r3 = r3.mConfig
                    long r3 = r3.getDataExpiration()
                    long r1 = r1 - r3
                    com.mixpanel.android.mpmetrics.MPDbAdapter$Table r3 = com.mixpanel.android.mpmetrics.MPDbAdapter.Table.PEOPLE
                    r0.cleanupEvents(r1, r3)
                L_0x003e:
                    r0 = -3
                    r1 = 0
                    int r2 = r10.what     // Catch:{ RuntimeException -> 0x02c7 }
                    r3 = 1
                    r4 = 2
                    if (r2 != 0) goto L_0x0094
                    java.lang.Object r10 = r10.obj     // Catch:{ RuntimeException -> 0x02c7 }
                    com.mixpanel.android.mpmetrics.AnalyticsMessages$PeopleDescription r10 = (com.mixpanel.android.mpmetrics.AnalyticsMessages.PeopleDescription) r10     // Catch:{ RuntimeException -> 0x02c7 }
                    boolean r0 = r10.isAnonymous()     // Catch:{ RuntimeException -> 0x02c7 }
                    if (r0 == 0) goto L_0x0053
                    com.mixpanel.android.mpmetrics.MPDbAdapter$Table r0 = com.mixpanel.android.mpmetrics.MPDbAdapter.Table.ANONYMOUS_PEOPLE     // Catch:{ RuntimeException -> 0x02c7 }
                    goto L_0x0055
                L_0x0053:
                    com.mixpanel.android.mpmetrics.MPDbAdapter$Table r0 = com.mixpanel.android.mpmetrics.MPDbAdapter.Table.PEOPLE     // Catch:{ RuntimeException -> 0x02c7 }
                L_0x0055:
                    com.mixpanel.android.mpmetrics.AnalyticsMessages$Worker r2 = com.mixpanel.android.mpmetrics.AnalyticsMessages.Worker.this     // Catch:{ RuntimeException -> 0x02c7 }
                    com.mixpanel.android.mpmetrics.AnalyticsMessages r2 = com.mixpanel.android.mpmetrics.AnalyticsMessages.this     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.String r5 = "Queuing people record for sending later"
                    r2.logAboutMessageToMixpanel(r5)     // Catch:{ RuntimeException -> 0x02c7 }
                    com.mixpanel.android.mpmetrics.AnalyticsMessages$Worker r2 = com.mixpanel.android.mpmetrics.AnalyticsMessages.Worker.this     // Catch:{ RuntimeException -> 0x02c7 }
                    com.mixpanel.android.mpmetrics.AnalyticsMessages r2 = com.mixpanel.android.mpmetrics.AnalyticsMessages.this     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ RuntimeException -> 0x02c7 }
                    r5.<init>()     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.String r6 = "    "
                    java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.String r6 = r10.toString()     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.String r5 = r5.toString()     // Catch:{ RuntimeException -> 0x02c7 }
                    r2.logAboutMessageToMixpanel(r5)     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.String r2 = r10.getToken()     // Catch:{ RuntimeException -> 0x02c7 }
                    com.mixpanel.android.mpmetrics.MPDbAdapter r5 = r9.mDbAdapter     // Catch:{ RuntimeException -> 0x02c7 }
                    org.json.JSONObject r6 = r10.getMessage()     // Catch:{ RuntimeException -> 0x02c7 }
                    int r0 = r5.addJSON(r6, r2, r0)     // Catch:{ RuntimeException -> 0x02c7 }
                    boolean r10 = r10.isAnonymous()     // Catch:{ RuntimeException -> 0x02c7 }
                    if (r10 == 0) goto L_0x023d
                    r10 = 0
                    r0 = r10
                    goto L_0x023d
                L_0x0094:
                    int r2 = r10.what     // Catch:{ RuntimeException -> 0x02c7 }
                    r5 = 3
                    if (r2 != r5) goto L_0x00d6
                    java.lang.Object r10 = r10.obj     // Catch:{ RuntimeException -> 0x02c7 }
                    com.mixpanel.android.mpmetrics.AnalyticsMessages$GroupDescription r10 = (com.mixpanel.android.mpmetrics.AnalyticsMessages.GroupDescription) r10     // Catch:{ RuntimeException -> 0x02c7 }
                    com.mixpanel.android.mpmetrics.AnalyticsMessages$Worker r0 = com.mixpanel.android.mpmetrics.AnalyticsMessages.Worker.this     // Catch:{ RuntimeException -> 0x02c7 }
                    com.mixpanel.android.mpmetrics.AnalyticsMessages r0 = com.mixpanel.android.mpmetrics.AnalyticsMessages.this     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.String r2 = "Queuing group record for sending later"
                    r0.logAboutMessageToMixpanel(r2)     // Catch:{ RuntimeException -> 0x02c7 }
                    com.mixpanel.android.mpmetrics.AnalyticsMessages$Worker r0 = com.mixpanel.android.mpmetrics.AnalyticsMessages.Worker.this     // Catch:{ RuntimeException -> 0x02c7 }
                    com.mixpanel.android.mpmetrics.AnalyticsMessages r0 = com.mixpanel.android.mpmetrics.AnalyticsMessages.this     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ RuntimeException -> 0x02c7 }
                    r2.<init>()     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.String r5 = "    "
                    java.lang.StringBuilder r2 = r2.append(r5)     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.String r5 = r10.toString()     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.StringBuilder r2 = r2.append(r5)     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.String r2 = r2.toString()     // Catch:{ RuntimeException -> 0x02c7 }
                    r0.logAboutMessageToMixpanel(r2)     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.String r2 = r10.getToken()     // Catch:{ RuntimeException -> 0x02c7 }
                    com.mixpanel.android.mpmetrics.MPDbAdapter r0 = r9.mDbAdapter     // Catch:{ RuntimeException -> 0x02c7 }
                    org.json.JSONObject r10 = r10.getMessage()     // Catch:{ RuntimeException -> 0x02c7 }
                    com.mixpanel.android.mpmetrics.MPDbAdapter$Table r5 = com.mixpanel.android.mpmetrics.MPDbAdapter.Table.GROUPS     // Catch:{ RuntimeException -> 0x02c7 }
                    int r0 = r0.addJSON(r10, r2, r5)     // Catch:{ RuntimeException -> 0x02c7 }
                    goto L_0x023d
                L_0x00d6:
                    int r2 = r10.what     // Catch:{ RuntimeException -> 0x02c7 }
                    if (r2 != r3) goto L_0x0139
                    java.lang.Object r10 = r10.obj     // Catch:{ RuntimeException -> 0x02c7 }
                    com.mixpanel.android.mpmetrics.AnalyticsMessages$EventDescription r10 = (com.mixpanel.android.mpmetrics.AnalyticsMessages.EventDescription) r10     // Catch:{ RuntimeException -> 0x02c7 }
                    org.json.JSONObject r2 = r9.prepareEventObject(r10)     // Catch:{ JSONException -> 0x0118 }
                    com.mixpanel.android.mpmetrics.AnalyticsMessages$Worker r5 = com.mixpanel.android.mpmetrics.AnalyticsMessages.Worker.this     // Catch:{ JSONException -> 0x0118 }
                    com.mixpanel.android.mpmetrics.AnalyticsMessages r5 = com.mixpanel.android.mpmetrics.AnalyticsMessages.this     // Catch:{ JSONException -> 0x0118 }
                    java.lang.String r6 = "Queuing event for sending later"
                    r5.logAboutMessageToMixpanel(r6)     // Catch:{ JSONException -> 0x0118 }
                    com.mixpanel.android.mpmetrics.AnalyticsMessages$Worker r5 = com.mixpanel.android.mpmetrics.AnalyticsMessages.Worker.this     // Catch:{ JSONException -> 0x0118 }
                    com.mixpanel.android.mpmetrics.AnalyticsMessages r5 = com.mixpanel.android.mpmetrics.AnalyticsMessages.this     // Catch:{ JSONException -> 0x0118 }
                    java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0118 }
                    r6.<init>()     // Catch:{ JSONException -> 0x0118 }
                    java.lang.String r7 = "    "
                    java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ JSONException -> 0x0118 }
                    java.lang.String r7 = r2.toString()     // Catch:{ JSONException -> 0x0118 }
                    java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ JSONException -> 0x0118 }
                    java.lang.String r6 = r6.toString()     // Catch:{ JSONException -> 0x0118 }
                    r5.logAboutMessageToMixpanel(r6)     // Catch:{ JSONException -> 0x0118 }
                    java.lang.String r5 = r10.getToken()     // Catch:{ JSONException -> 0x0118 }
                    com.mixpanel.android.mpmetrics.MPDbAdapter r6 = r9.mDbAdapter     // Catch:{ JSONException -> 0x0116 }
                    com.mixpanel.android.mpmetrics.MPDbAdapter$Table r7 = com.mixpanel.android.mpmetrics.MPDbAdapter.Table.EVENTS     // Catch:{ JSONException -> 0x0116 }
                    int r0 = r6.addJSON(r2, r5, r7)     // Catch:{ JSONException -> 0x0116 }
                    goto L_0x0136
                L_0x0116:
                    r2 = move-exception
                    goto L_0x011a
                L_0x0118:
                    r2 = move-exception
                    r5 = r1
                L_0x011a:
                    java.lang.String r6 = "MixpanelAPI.Messages"
                    java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ RuntimeException -> 0x02c7 }
                    r7.<init>()     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.String r8 = "Exception tracking event "
                    java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.String r10 = r10.getEventName()     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.StringBuilder r10 = r7.append(r10)     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.String r10 = r10.toString()     // Catch:{ RuntimeException -> 0x02c7 }
                    com.mixpanel.android.util.MPLog.m62e(r6, r10, r2)     // Catch:{ RuntimeException -> 0x02c7 }
                L_0x0136:
                    r2 = r5
                    goto L_0x023d
                L_0x0139:
                    int r2 = r10.what     // Catch:{ RuntimeException -> 0x02c7 }
                    r5 = 4
                    if (r2 != r5) goto L_0x0152
                    java.lang.Object r10 = r10.obj     // Catch:{ RuntimeException -> 0x02c7 }
                    com.mixpanel.android.mpmetrics.AnalyticsMessages$PushAnonymousPeopleDescription r10 = (com.mixpanel.android.mpmetrics.AnalyticsMessages.PushAnonymousPeopleDescription) r10     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.String r0 = r10.getDistinctId()     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.String r2 = r10.getToken()     // Catch:{ RuntimeException -> 0x02c7 }
                    com.mixpanel.android.mpmetrics.MPDbAdapter r10 = r9.mDbAdapter     // Catch:{ RuntimeException -> 0x02c7 }
                    int r0 = r10.pushAnonymousUpdatesToPeopleDb(r2, r0)     // Catch:{ RuntimeException -> 0x02c7 }
                    goto L_0x023d
                L_0x0152:
                    int r2 = r10.what     // Catch:{ RuntimeException -> 0x02c7 }
                    r5 = 7
                    if (r2 != r5) goto L_0x0168
                    java.lang.Object r10 = r10.obj     // Catch:{ RuntimeException -> 0x02c7 }
                    com.mixpanel.android.mpmetrics.AnalyticsMessages$MixpanelDescription r10 = (com.mixpanel.android.mpmetrics.AnalyticsMessages.MixpanelDescription) r10     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.String r2 = r10.getToken()     // Catch:{ RuntimeException -> 0x02c7 }
                    com.mixpanel.android.mpmetrics.MPDbAdapter r10 = r9.mDbAdapter     // Catch:{ RuntimeException -> 0x02c7 }
                    com.mixpanel.android.mpmetrics.MPDbAdapter$Table r5 = com.mixpanel.android.mpmetrics.MPDbAdapter.Table.ANONYMOUS_PEOPLE     // Catch:{ RuntimeException -> 0x02c7 }
                    r10.cleanupAllEvents(r5, r2)     // Catch:{ RuntimeException -> 0x02c7 }
                    goto L_0x023d
                L_0x0168:
                    int r2 = r10.what     // Catch:{ RuntimeException -> 0x02c7 }
                    r5 = 8
                    if (r2 != r5) goto L_0x019a
                    java.lang.Object r10 = r10.obj     // Catch:{ RuntimeException -> 0x02c7 }
                    com.mixpanel.android.mpmetrics.AnalyticsMessages$UpdateEventsPropertiesDescription r10 = (com.mixpanel.android.mpmetrics.AnalyticsMessages.UpdateEventsPropertiesDescription) r10     // Catch:{ RuntimeException -> 0x02c7 }
                    com.mixpanel.android.mpmetrics.MPDbAdapter r2 = r9.mDbAdapter     // Catch:{ RuntimeException -> 0x02c7 }
                    java.util.Map r5 = r10.getProperties()     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.String r10 = r10.getToken()     // Catch:{ RuntimeException -> 0x02c7 }
                    int r10 = r2.rewriteEventDataWithProperties(r5, r10)     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.String r2 = "MixpanelAPI.Messages"
                    java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ RuntimeException -> 0x02c7 }
                    r5.<init>()     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.StringBuilder r10 = r5.append(r10)     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.String r5 = " stored events were updated with new properties."
                    java.lang.StringBuilder r10 = r10.append(r5)     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.String r10 = r10.toString()     // Catch:{ RuntimeException -> 0x02c7 }
                    com.mixpanel.android.util.MPLog.m59d(r2, r10)     // Catch:{ RuntimeException -> 0x02c7 }
                    goto L_0x023c
                L_0x019a:
                    int r2 = r10.what     // Catch:{ RuntimeException -> 0x02c7 }
                    if (r2 != r4) goto L_0x01b8
                    com.mixpanel.android.mpmetrics.AnalyticsMessages$Worker r2 = com.mixpanel.android.mpmetrics.AnalyticsMessages.Worker.this     // Catch:{ RuntimeException -> 0x02c7 }
                    com.mixpanel.android.mpmetrics.AnalyticsMessages r2 = com.mixpanel.android.mpmetrics.AnalyticsMessages.this     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.String r5 = "Flushing queue due to scheduled or forced flush"
                    r2.logAboutMessageToMixpanel(r5)     // Catch:{ RuntimeException -> 0x02c7 }
                    com.mixpanel.android.mpmetrics.AnalyticsMessages$Worker r2 = com.mixpanel.android.mpmetrics.AnalyticsMessages.Worker.this     // Catch:{ RuntimeException -> 0x02c7 }
                    r2.updateFlushFrequency()     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.Object r10 = r10.obj     // Catch:{ RuntimeException -> 0x02c7 }
                    r2 = r10
                    java.lang.String r2 = (java.lang.String) r2     // Catch:{ RuntimeException -> 0x02c7 }
                    com.mixpanel.android.mpmetrics.MPDbAdapter r10 = r9.mDbAdapter     // Catch:{ RuntimeException -> 0x02c7 }
                    r9.sendAllData(r10, r2)     // Catch:{ RuntimeException -> 0x02c7 }
                    goto L_0x023d
                L_0x01b8:
                    int r2 = r10.what     // Catch:{ RuntimeException -> 0x02c7 }
                    r5 = 6
                    if (r2 != r5) goto L_0x01e2
                    java.lang.Object r10 = r10.obj     // Catch:{ RuntimeException -> 0x02c7 }
                    com.mixpanel.android.mpmetrics.AnalyticsMessages$MixpanelDescription r10 = (com.mixpanel.android.mpmetrics.AnalyticsMessages.MixpanelDescription) r10     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.String r2 = r10.getToken()     // Catch:{ RuntimeException -> 0x02c7 }
                    com.mixpanel.android.mpmetrics.MPDbAdapter r10 = r9.mDbAdapter     // Catch:{ RuntimeException -> 0x02c7 }
                    com.mixpanel.android.mpmetrics.MPDbAdapter$Table r5 = com.mixpanel.android.mpmetrics.MPDbAdapter.Table.EVENTS     // Catch:{ RuntimeException -> 0x02c7 }
                    r10.cleanupAllEvents(r5, r2)     // Catch:{ RuntimeException -> 0x02c7 }
                    com.mixpanel.android.mpmetrics.MPDbAdapter r10 = r9.mDbAdapter     // Catch:{ RuntimeException -> 0x02c7 }
                    com.mixpanel.android.mpmetrics.MPDbAdapter$Table r5 = com.mixpanel.android.mpmetrics.MPDbAdapter.Table.PEOPLE     // Catch:{ RuntimeException -> 0x02c7 }
                    r10.cleanupAllEvents(r5, r2)     // Catch:{ RuntimeException -> 0x02c7 }
                    com.mixpanel.android.mpmetrics.MPDbAdapter r10 = r9.mDbAdapter     // Catch:{ RuntimeException -> 0x02c7 }
                    com.mixpanel.android.mpmetrics.MPDbAdapter$Table r5 = com.mixpanel.android.mpmetrics.MPDbAdapter.Table.GROUPS     // Catch:{ RuntimeException -> 0x02c7 }
                    r10.cleanupAllEvents(r5, r2)     // Catch:{ RuntimeException -> 0x02c7 }
                    com.mixpanel.android.mpmetrics.MPDbAdapter r10 = r9.mDbAdapter     // Catch:{ RuntimeException -> 0x02c7 }
                    com.mixpanel.android.mpmetrics.MPDbAdapter$Table r5 = com.mixpanel.android.mpmetrics.MPDbAdapter.Table.ANONYMOUS_PEOPLE     // Catch:{ RuntimeException -> 0x02c7 }
                    r10.cleanupAllEvents(r5, r2)     // Catch:{ RuntimeException -> 0x02c7 }
                    goto L_0x023d
                L_0x01e2:
                    int r2 = r10.what     // Catch:{ RuntimeException -> 0x02c7 }
                    r5 = 5
                    if (r2 != r5) goto L_0x0224
                    java.lang.String r10 = "MixpanelAPI.Messages"
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ RuntimeException -> 0x02c7 }
                    r2.<init>()     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.String r5 = "Worker received a hard kill. Dumping all events and force-killing. Thread id "
                    java.lang.StringBuilder r2 = r2.append(r5)     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.Thread r5 = java.lang.Thread.currentThread()     // Catch:{ RuntimeException -> 0x02c7 }
                    long r5 = r5.getId()     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.StringBuilder r2 = r2.append(r5)     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.String r2 = r2.toString()     // Catch:{ RuntimeException -> 0x02c7 }
                    com.mixpanel.android.util.MPLog.m67w(r10, r2)     // Catch:{ RuntimeException -> 0x02c7 }
                    com.mixpanel.android.mpmetrics.AnalyticsMessages$Worker r10 = com.mixpanel.android.mpmetrics.AnalyticsMessages.Worker.this     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.Object r10 = r10.mHandlerLock     // Catch:{ RuntimeException -> 0x02c7 }
                    monitor-enter(r10)     // Catch:{ RuntimeException -> 0x02c7 }
                    com.mixpanel.android.mpmetrics.MPDbAdapter r2 = r9.mDbAdapter     // Catch:{ all -> 0x0221 }
                    r2.deleteDB()     // Catch:{ all -> 0x0221 }
                    com.mixpanel.android.mpmetrics.AnalyticsMessages$Worker r2 = com.mixpanel.android.mpmetrics.AnalyticsMessages.Worker.this     // Catch:{ all -> 0x0221 }
                    android.os.Handler unused = r2.mHandler = r1     // Catch:{ all -> 0x0221 }
                    android.os.Looper r2 = android.os.Looper.myLooper()     // Catch:{ all -> 0x0221 }
                    r2.quit()     // Catch:{ all -> 0x0221 }
                    monitor-exit(r10)     // Catch:{ all -> 0x0221 }
                    goto L_0x023c
                L_0x0221:
                    r0 = move-exception
                    monitor-exit(r10)     // Catch:{ all -> 0x0221 }
                    throw r0     // Catch:{ RuntimeException -> 0x02c7 }
                L_0x0224:
                    java.lang.String r2 = "MixpanelAPI.Messages"
                    java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ RuntimeException -> 0x02c7 }
                    r5.<init>()     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.String r6 = "Unexpected message received by Mixpanel worker: "
                    java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.StringBuilder r10 = r5.append(r10)     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.String r10 = r10.toString()     // Catch:{ RuntimeException -> 0x02c7 }
                    com.mixpanel.android.util.MPLog.m61e(r2, r10)     // Catch:{ RuntimeException -> 0x02c7 }
                L_0x023c:
                    r2 = r1
                L_0x023d:
                    com.mixpanel.android.mpmetrics.AnalyticsMessages$Worker r10 = com.mixpanel.android.mpmetrics.AnalyticsMessages.Worker.this     // Catch:{ RuntimeException -> 0x02c7 }
                    com.mixpanel.android.mpmetrics.AnalyticsMessages r10 = com.mixpanel.android.mpmetrics.AnalyticsMessages.this     // Catch:{ RuntimeException -> 0x02c7 }
                    com.mixpanel.android.mpmetrics.MPConfig r10 = r10.mConfig     // Catch:{ RuntimeException -> 0x02c7 }
                    int r10 = r10.getBulkUploadLimit()     // Catch:{ RuntimeException -> 0x02c7 }
                    if (r0 >= r10) goto L_0x024c
                    r10 = -2
                    if (r0 != r10) goto L_0x0281
                L_0x024c:
                    int r10 = r9.mFailedRetries     // Catch:{ RuntimeException -> 0x02c7 }
                    if (r10 > 0) goto L_0x0281
                    if (r2 == 0) goto L_0x0281
                    com.mixpanel.android.mpmetrics.AnalyticsMessages$Worker r10 = com.mixpanel.android.mpmetrics.AnalyticsMessages.Worker.this     // Catch:{ RuntimeException -> 0x02c7 }
                    com.mixpanel.android.mpmetrics.AnalyticsMessages r10 = com.mixpanel.android.mpmetrics.AnalyticsMessages.this     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ RuntimeException -> 0x02c7 }
                    r3.<init>()     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.String r4 = "Flushing queue due to bulk upload limit ("
                    java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.StringBuilder r0 = r3.append(r0)     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.String r3 = ") for project "
                    java.lang.StringBuilder r0 = r0.append(r3)     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.String r0 = r0.toString()     // Catch:{ RuntimeException -> 0x02c7 }
                    r10.logAboutMessageToMixpanel(r0)     // Catch:{ RuntimeException -> 0x02c7 }
                    com.mixpanel.android.mpmetrics.AnalyticsMessages$Worker r10 = com.mixpanel.android.mpmetrics.AnalyticsMessages.Worker.this     // Catch:{ RuntimeException -> 0x02c7 }
                    r10.updateFlushFrequency()     // Catch:{ RuntimeException -> 0x02c7 }
                    com.mixpanel.android.mpmetrics.MPDbAdapter r10 = r9.mDbAdapter     // Catch:{ RuntimeException -> 0x02c7 }
                    r9.sendAllData(r10, r2)     // Catch:{ RuntimeException -> 0x02c7 }
                    goto L_0x02f3
                L_0x0281:
                    if (r0 <= 0) goto L_0x02f3
                    boolean r10 = r9.hasMessages(r4, r2)     // Catch:{ RuntimeException -> 0x02c7 }
                    if (r10 != 0) goto L_0x02f3
                    com.mixpanel.android.mpmetrics.AnalyticsMessages$Worker r10 = com.mixpanel.android.mpmetrics.AnalyticsMessages.Worker.this     // Catch:{ RuntimeException -> 0x02c7 }
                    com.mixpanel.android.mpmetrics.AnalyticsMessages r10 = com.mixpanel.android.mpmetrics.AnalyticsMessages.this     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ RuntimeException -> 0x02c7 }
                    r5.<init>()     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.String r6 = "Queue depth "
                    java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.StringBuilder r0 = r5.append(r0)     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.String r5 = " - Adding flush in "
                    java.lang.StringBuilder r0 = r0.append(r5)     // Catch:{ RuntimeException -> 0x02c7 }
                    long r5 = r9.mFlushInterval     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.StringBuilder r0 = r0.append(r5)     // Catch:{ RuntimeException -> 0x02c7 }
                    java.lang.String r0 = r0.toString()     // Catch:{ RuntimeException -> 0x02c7 }
                    r10.logAboutMessageToMixpanel(r0)     // Catch:{ RuntimeException -> 0x02c7 }
                    long r5 = r9.mFlushInterval     // Catch:{ RuntimeException -> 0x02c7 }
                    r7 = 0
                    int r10 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
                    if (r10 < 0) goto L_0x02f3
                    android.os.Message r10 = android.os.Message.obtain()     // Catch:{ RuntimeException -> 0x02c7 }
                    r10.what = r4     // Catch:{ RuntimeException -> 0x02c7 }
                    r10.obj = r2     // Catch:{ RuntimeException -> 0x02c7 }
                    r10.arg1 = r3     // Catch:{ RuntimeException -> 0x02c7 }
                    long r2 = r9.mFlushInterval     // Catch:{ RuntimeException -> 0x02c7 }
                    r9.sendMessageDelayed(r10, r2)     // Catch:{ RuntimeException -> 0x02c7 }
                    goto L_0x02f3
                L_0x02c7:
                    r10 = move-exception
                    java.lang.String r0 = "MixpanelAPI.Messages"
                    java.lang.String r2 = "Worker threw an unhandled exception"
                    com.mixpanel.android.util.MPLog.m62e(r0, r2, r10)
                    com.mixpanel.android.mpmetrics.AnalyticsMessages$Worker r0 = com.mixpanel.android.mpmetrics.AnalyticsMessages.Worker.this
                    java.lang.Object r0 = r0.mHandlerLock
                    monitor-enter(r0)
                    com.mixpanel.android.mpmetrics.AnalyticsMessages$Worker r9 = com.mixpanel.android.mpmetrics.AnalyticsMessages.Worker.this     // Catch:{ all -> 0x02f4 }
                    android.os.Handler unused = r9.mHandler = r1     // Catch:{ all -> 0x02f4 }
                    android.os.Looper r9 = android.os.Looper.myLooper()     // Catch:{ Exception -> 0x02ea }
                    r9.quit()     // Catch:{ Exception -> 0x02ea }
                    java.lang.String r9 = "MixpanelAPI.Messages"
                    java.lang.String r1 = "Mixpanel will not process any more analytics messages"
                    com.mixpanel.android.util.MPLog.m62e(r9, r1, r10)     // Catch:{ Exception -> 0x02ea }
                    goto L_0x02f2
                L_0x02ea:
                    r9 = move-exception
                    java.lang.String r10 = "MixpanelAPI.Messages"
                    java.lang.String r1 = "Could not halt looper"
                    com.mixpanel.android.util.MPLog.m62e(r10, r1, r9)     // Catch:{ all -> 0x02f4 }
                L_0x02f2:
                    monitor-exit(r0)     // Catch:{ all -> 0x02f4 }
                L_0x02f3:
                    return
                L_0x02f4:
                    r9 = move-exception
                    monitor-exit(r0)     // Catch:{ all -> 0x02f4 }
                    throw r9
                */
                throw new UnsupportedOperationException("Method not decompiled: com.mixpanel.android.mpmetrics.AnalyticsMessages.Worker.AnalyticsMessageHandler.handleMessage(android.os.Message):void");
            }

            /* access modifiers changed from: protected */
            public long getTrackEngageRetryAfter() {
                return this.mTrackEngageRetryAfter;
            }

            private void sendAllData(MPDbAdapter mPDbAdapter, String str) {
                if (!AnalyticsMessages.this.getPoster().isOnline(AnalyticsMessages.this.mContext, AnalyticsMessages.this.mConfig.getOfflineMode())) {
                    AnalyticsMessages.this.logAboutMessageToMixpanel("Not flushing data to Mixpanel because the device is not connected to the internet.");
                    return;
                }
                sendData(mPDbAdapter, str, MPDbAdapter.Table.EVENTS, AnalyticsMessages.this.mConfig.getEventsEndpoint());
                sendData(mPDbAdapter, str, MPDbAdapter.Table.PEOPLE, AnalyticsMessages.this.mConfig.getPeopleEndpoint());
                sendData(mPDbAdapter, str, MPDbAdapter.Table.GROUPS, AnalyticsMessages.this.mConfig.getGroupsEndpoint());
            }

            /* JADX WARNING: Code restructure failed: missing block: B:23:0x00d8, code lost:
                r0 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:25:0x00e0, code lost:
                throw new java.lang.RuntimeException("UTF not supported on this platform?", r0);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:26:0x00e1, code lost:
                r0 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:27:0x00e2, code lost:
                com.mixpanel.android.mpmetrics.AnalyticsMessages.access$500(r1.this$1.this$0, "Cannot post message to " + r5 + ".", r0);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:28:0x00ff, code lost:
                r0 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:29:0x0100, code lost:
                com.mixpanel.android.mpmetrics.AnalyticsMessages.access$500(r1.this$1.this$0, "Cannot post message to " + r5 + ".", r0);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:30:0x011d, code lost:
                r0 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:31:0x011e, code lost:
                com.mixpanel.android.mpmetrics.AnalyticsMessages.access$500(r1.this$1.this$0, "Cannot post message to " + r5 + ".", r0);
                r1.mTrackEngageRetryAfter = (long) (r0.getRetryAfter() * 1000);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:32:0x0143, code lost:
                r10 = 0;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:33:0x0145, code lost:
                r0 = e;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:34:0x0146, code lost:
                r10 = 1;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:36:0x0164, code lost:
                r0 = e;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:37:0x0165, code lost:
                r10 = 1;
             */
            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Removed duplicated region for block: B:26:0x00e1 A[ExcHandler: IOException (r0v22 'e' java.io.IOException A[CUSTOM_DECLARE]), Splitter:B:9:0x0050] */
            /* JADX WARNING: Removed duplicated region for block: B:28:0x00ff A[ExcHandler: SocketTimeoutException (r0v21 'e' java.net.SocketTimeoutException A[CUSTOM_DECLARE]), Splitter:B:9:0x0050] */
            /* JADX WARNING: Removed duplicated region for block: B:30:0x011d A[ExcHandler: ServiceUnavailableException (r0v18 'e' com.mixpanel.android.util.RemoteService$ServiceUnavailableException A[CUSTOM_DECLARE]), Splitter:B:9:0x0050] */
            /* JADX WARNING: Removed duplicated region for block: B:40:0x0182  */
            /* JADX WARNING: Removed duplicated region for block: B:45:0x019f A[SYNTHETIC] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            private void sendData(com.mixpanel.android.mpmetrics.MPDbAdapter r17, java.lang.String r18, com.mixpanel.android.mpmetrics.MPDbAdapter.Table r19, java.lang.String r20) {
                /*
                    r16 = this;
                    r1 = r16
                    r2 = r17
                    r3 = r18
                    r4 = r19
                    r5 = r20
                    java.lang.String r6 = "MixpanelAPI.Messages"
                    java.lang.String r7 = "Cannot post message to "
                    java.lang.String r8 = "."
                    com.mixpanel.android.mpmetrics.AnalyticsMessages$Worker r0 = com.mixpanel.android.mpmetrics.AnalyticsMessages.Worker.this
                    com.mixpanel.android.mpmetrics.AnalyticsMessages r0 = com.mixpanel.android.mpmetrics.AnalyticsMessages.this
                    com.mixpanel.android.util.RemoteService r9 = r0.getPoster()
                    java.lang.String[] r0 = r2.generateDataString(r4, r3)
                    r10 = 0
                    java.lang.Integer r11 = java.lang.Integer.valueOf(r10)
                    r12 = 2
                    if (r0 == 0) goto L_0x002a
                    r11 = r0[r12]
                    java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
                L_0x002a:
                    if (r0 == 0) goto L_0x01f8
                    int r13 = r11.intValue()
                    if (r13 <= 0) goto L_0x01f8
                    r13 = r0[r10]
                    r14 = 1
                    r0 = r0[r14]
                    java.lang.String r15 = com.mixpanel.android.util.Base64Coder.encodeString(r0)
                    java.util.HashMap r14 = new java.util.HashMap
                    r14.<init>()
                    java.lang.String r12 = "data"
                    r14.put(r12, r15)
                    boolean r12 = com.mixpanel.android.mpmetrics.MPConfig.DEBUG
                    if (r12 == 0) goto L_0x0050
                    java.lang.String r12 = "verbose"
                    java.lang.String r15 = "1"
                    r14.put(r12, r15)
                L_0x0050:
                    com.mixpanel.android.mpmetrics.AnalyticsMessages$Worker r12 = com.mixpanel.android.mpmetrics.AnalyticsMessages.Worker.this     // Catch:{ OutOfMemoryError -> 0x0164, MalformedURLException -> 0x0145, ServiceUnavailableException -> 0x011d, SocketTimeoutException -> 0x00ff, IOException -> 0x00e1 }
                    com.mixpanel.android.mpmetrics.AnalyticsMessages r12 = com.mixpanel.android.mpmetrics.AnalyticsMessages.this     // Catch:{ OutOfMemoryError -> 0x0164, MalformedURLException -> 0x0145, ServiceUnavailableException -> 0x011d, SocketTimeoutException -> 0x00ff, IOException -> 0x00e1 }
                    com.mixpanel.android.mpmetrics.MPConfig r12 = r12.mConfig     // Catch:{ OutOfMemoryError -> 0x0164, MalformedURLException -> 0x0145, ServiceUnavailableException -> 0x011d, SocketTimeoutException -> 0x00ff, IOException -> 0x00e1 }
                    javax.net.ssl.SSLSocketFactory r12 = r12.getSSLSocketFactory()     // Catch:{ OutOfMemoryError -> 0x0164, MalformedURLException -> 0x0145, ServiceUnavailableException -> 0x011d, SocketTimeoutException -> 0x00ff, IOException -> 0x00e1 }
                    byte[] r12 = r9.performRequest(r5, r14, r12)     // Catch:{ OutOfMemoryError -> 0x0164, MalformedURLException -> 0x0145, ServiceUnavailableException -> 0x011d, SocketTimeoutException -> 0x00ff, IOException -> 0x00e1 }
                    if (r12 != 0) goto L_0x0086
                    com.mixpanel.android.mpmetrics.AnalyticsMessages$Worker r0 = com.mixpanel.android.mpmetrics.AnalyticsMessages.Worker.this     // Catch:{ OutOfMemoryError -> 0x0083, MalformedURLException -> 0x0080, ServiceUnavailableException -> 0x011d, SocketTimeoutException -> 0x00ff, IOException -> 0x00e1 }
                    com.mixpanel.android.mpmetrics.AnalyticsMessages r0 = com.mixpanel.android.mpmetrics.AnalyticsMessages.this     // Catch:{ OutOfMemoryError -> 0x0083, MalformedURLException -> 0x0080, ServiceUnavailableException -> 0x011d, SocketTimeoutException -> 0x00ff, IOException -> 0x00e1 }
                    java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ OutOfMemoryError -> 0x0083, MalformedURLException -> 0x0080, ServiceUnavailableException -> 0x011d, SocketTimeoutException -> 0x00ff, IOException -> 0x00e1 }
                    r12.<init>()     // Catch:{ OutOfMemoryError -> 0x0083, MalformedURLException -> 0x0080, ServiceUnavailableException -> 0x011d, SocketTimeoutException -> 0x00ff, IOException -> 0x00e1 }
                    java.lang.String r14 = "Response was null, unexpected failure posting to "
                    java.lang.StringBuilder r12 = r12.append(r14)     // Catch:{ OutOfMemoryError -> 0x0083, MalformedURLException -> 0x0080, ServiceUnavailableException -> 0x011d, SocketTimeoutException -> 0x00ff, IOException -> 0x00e1 }
                    java.lang.StringBuilder r12 = r12.append(r5)     // Catch:{ OutOfMemoryError -> 0x0083, MalformedURLException -> 0x0080, ServiceUnavailableException -> 0x011d, SocketTimeoutException -> 0x00ff, IOException -> 0x00e1 }
                    java.lang.StringBuilder r12 = r12.append(r8)     // Catch:{ OutOfMemoryError -> 0x0083, MalformedURLException -> 0x0080, ServiceUnavailableException -> 0x011d, SocketTimeoutException -> 0x00ff, IOException -> 0x00e1 }
                    java.lang.String r12 = r12.toString()     // Catch:{ OutOfMemoryError -> 0x0083, MalformedURLException -> 0x0080, ServiceUnavailableException -> 0x011d, SocketTimeoutException -> 0x00ff, IOException -> 0x00e1 }
                    r0.logAboutMessageToMixpanel(r12)     // Catch:{ OutOfMemoryError -> 0x0083, MalformedURLException -> 0x0080, ServiceUnavailableException -> 0x011d, SocketTimeoutException -> 0x00ff, IOException -> 0x00e1 }
                    goto L_0x0180
                L_0x0080:
                    r0 = move-exception
                    goto L_0x0147
                L_0x0083:
                    r0 = move-exception
                    goto L_0x0166
                L_0x0086:
                    java.lang.String r14 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x00d8 }
                    java.lang.String r15 = "UTF-8"
                    r14.<init>(r12, r15)     // Catch:{ UnsupportedEncodingException -> 0x00d8 }
                    int r12 = r1.mFailedRetries     // Catch:{ OutOfMemoryError -> 0x0164, MalformedURLException -> 0x0145, ServiceUnavailableException -> 0x011d, SocketTimeoutException -> 0x00ff, IOException -> 0x00e1 }
                    if (r12 <= 0) goto L_0x0097
                    r1.mFailedRetries = r10     // Catch:{ OutOfMemoryError -> 0x0164, MalformedURLException -> 0x0145, ServiceUnavailableException -> 0x011d, SocketTimeoutException -> 0x00ff, IOException -> 0x00e1 }
                    r12 = 2
                    r1.removeMessages(r12, r3)     // Catch:{ OutOfMemoryError -> 0x0164, MalformedURLException -> 0x0145, ServiceUnavailableException -> 0x011d, SocketTimeoutException -> 0x00ff, IOException -> 0x00e1 }
                L_0x0097:
                    com.mixpanel.android.mpmetrics.AnalyticsMessages$Worker r12 = com.mixpanel.android.mpmetrics.AnalyticsMessages.Worker.this     // Catch:{ OutOfMemoryError -> 0x0164, MalformedURLException -> 0x0145, ServiceUnavailableException -> 0x011d, SocketTimeoutException -> 0x00ff, IOException -> 0x00e1 }
                    com.mixpanel.android.mpmetrics.AnalyticsMessages r12 = com.mixpanel.android.mpmetrics.AnalyticsMessages.this     // Catch:{ OutOfMemoryError -> 0x0164, MalformedURLException -> 0x0145, ServiceUnavailableException -> 0x011d, SocketTimeoutException -> 0x00ff, IOException -> 0x00e1 }
                    java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ OutOfMemoryError -> 0x0164, MalformedURLException -> 0x0145, ServiceUnavailableException -> 0x011d, SocketTimeoutException -> 0x00ff, IOException -> 0x00e1 }
                    r15.<init>()     // Catch:{ OutOfMemoryError -> 0x0164, MalformedURLException -> 0x0145, ServiceUnavailableException -> 0x011d, SocketTimeoutException -> 0x00ff, IOException -> 0x00e1 }
                    java.lang.String r10 = "Successfully posted to "
                    java.lang.StringBuilder r10 = r15.append(r10)     // Catch:{ OutOfMemoryError -> 0x0164, MalformedURLException -> 0x0145, ServiceUnavailableException -> 0x011d, SocketTimeoutException -> 0x00ff, IOException -> 0x00e1 }
                    java.lang.StringBuilder r10 = r10.append(r5)     // Catch:{ OutOfMemoryError -> 0x0164, MalformedURLException -> 0x0145, ServiceUnavailableException -> 0x011d, SocketTimeoutException -> 0x00ff, IOException -> 0x00e1 }
                    java.lang.String r15 = ": \n"
                    java.lang.StringBuilder r10 = r10.append(r15)     // Catch:{ OutOfMemoryError -> 0x0164, MalformedURLException -> 0x0145, ServiceUnavailableException -> 0x011d, SocketTimeoutException -> 0x00ff, IOException -> 0x00e1 }
                    java.lang.StringBuilder r0 = r10.append(r0)     // Catch:{ OutOfMemoryError -> 0x0164, MalformedURLException -> 0x0145, ServiceUnavailableException -> 0x011d, SocketTimeoutException -> 0x00ff, IOException -> 0x00e1 }
                    java.lang.String r0 = r0.toString()     // Catch:{ OutOfMemoryError -> 0x0164, MalformedURLException -> 0x0145, ServiceUnavailableException -> 0x011d, SocketTimeoutException -> 0x00ff, IOException -> 0x00e1 }
                    r12.logAboutMessageToMixpanel(r0)     // Catch:{ OutOfMemoryError -> 0x0164, MalformedURLException -> 0x0145, ServiceUnavailableException -> 0x011d, SocketTimeoutException -> 0x00ff, IOException -> 0x00e1 }
                    com.mixpanel.android.mpmetrics.AnalyticsMessages$Worker r0 = com.mixpanel.android.mpmetrics.AnalyticsMessages.Worker.this     // Catch:{ OutOfMemoryError -> 0x0164, MalformedURLException -> 0x0145, ServiceUnavailableException -> 0x011d, SocketTimeoutException -> 0x00ff, IOException -> 0x00e1 }
                    com.mixpanel.android.mpmetrics.AnalyticsMessages r0 = com.mixpanel.android.mpmetrics.AnalyticsMessages.this     // Catch:{ OutOfMemoryError -> 0x0164, MalformedURLException -> 0x0145, ServiceUnavailableException -> 0x011d, SocketTimeoutException -> 0x00ff, IOException -> 0x00e1 }
                    java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ OutOfMemoryError -> 0x0164, MalformedURLException -> 0x0145, ServiceUnavailableException -> 0x011d, SocketTimeoutException -> 0x00ff, IOException -> 0x00e1 }
                    r10.<init>()     // Catch:{ OutOfMemoryError -> 0x0164, MalformedURLException -> 0x0145, ServiceUnavailableException -> 0x011d, SocketTimeoutException -> 0x00ff, IOException -> 0x00e1 }
                    java.lang.String r12 = "Response was "
                    java.lang.StringBuilder r10 = r10.append(r12)     // Catch:{ OutOfMemoryError -> 0x0164, MalformedURLException -> 0x0145, ServiceUnavailableException -> 0x011d, SocketTimeoutException -> 0x00ff, IOException -> 0x00e1 }
                    java.lang.StringBuilder r10 = r10.append(r14)     // Catch:{ OutOfMemoryError -> 0x0164, MalformedURLException -> 0x0145, ServiceUnavailableException -> 0x011d, SocketTimeoutException -> 0x00ff, IOException -> 0x00e1 }
                    java.lang.String r10 = r10.toString()     // Catch:{ OutOfMemoryError -> 0x0164, MalformedURLException -> 0x0145, ServiceUnavailableException -> 0x011d, SocketTimeoutException -> 0x00ff, IOException -> 0x00e1 }
                    r0.logAboutMessageToMixpanel(r10)     // Catch:{ OutOfMemoryError -> 0x0164, MalformedURLException -> 0x0145, ServiceUnavailableException -> 0x011d, SocketTimeoutException -> 0x00ff, IOException -> 0x00e1 }
                    r10 = 1
                    goto L_0x0180
                L_0x00d8:
                    r0 = move-exception
                    java.lang.RuntimeException r10 = new java.lang.RuntimeException     // Catch:{ OutOfMemoryError -> 0x0164, MalformedURLException -> 0x0145, ServiceUnavailableException -> 0x011d, SocketTimeoutException -> 0x00ff, IOException -> 0x00e1 }
                    java.lang.String r12 = "UTF not supported on this platform?"
                    r10.<init>(r12, r0)     // Catch:{ OutOfMemoryError -> 0x0164, MalformedURLException -> 0x0145, ServiceUnavailableException -> 0x011d, SocketTimeoutException -> 0x00ff, IOException -> 0x00e1 }
                    throw r10     // Catch:{ OutOfMemoryError -> 0x0164, MalformedURLException -> 0x0145, ServiceUnavailableException -> 0x011d, SocketTimeoutException -> 0x00ff, IOException -> 0x00e1 }
                L_0x00e1:
                    r0 = move-exception
                    com.mixpanel.android.mpmetrics.AnalyticsMessages$Worker r10 = com.mixpanel.android.mpmetrics.AnalyticsMessages.Worker.this
                    com.mixpanel.android.mpmetrics.AnalyticsMessages r10 = com.mixpanel.android.mpmetrics.AnalyticsMessages.this
                    java.lang.StringBuilder r12 = new java.lang.StringBuilder
                    r12.<init>()
                    java.lang.StringBuilder r12 = r12.append(r7)
                    java.lang.StringBuilder r12 = r12.append(r5)
                    java.lang.StringBuilder r12 = r12.append(r8)
                    java.lang.String r12 = r12.toString()
                    r10.logAboutMessageToMixpanel(r12, r0)
                    goto L_0x0143
                L_0x00ff:
                    r0 = move-exception
                    com.mixpanel.android.mpmetrics.AnalyticsMessages$Worker r10 = com.mixpanel.android.mpmetrics.AnalyticsMessages.Worker.this
                    com.mixpanel.android.mpmetrics.AnalyticsMessages r10 = com.mixpanel.android.mpmetrics.AnalyticsMessages.this
                    java.lang.StringBuilder r12 = new java.lang.StringBuilder
                    r12.<init>()
                    java.lang.StringBuilder r12 = r12.append(r7)
                    java.lang.StringBuilder r12 = r12.append(r5)
                    java.lang.StringBuilder r12 = r12.append(r8)
                    java.lang.String r12 = r12.toString()
                    r10.logAboutMessageToMixpanel(r12, r0)
                    goto L_0x0143
                L_0x011d:
                    r0 = move-exception
                    com.mixpanel.android.mpmetrics.AnalyticsMessages$Worker r10 = com.mixpanel.android.mpmetrics.AnalyticsMessages.Worker.this
                    com.mixpanel.android.mpmetrics.AnalyticsMessages r10 = com.mixpanel.android.mpmetrics.AnalyticsMessages.this
                    java.lang.StringBuilder r12 = new java.lang.StringBuilder
                    r12.<init>()
                    java.lang.StringBuilder r12 = r12.append(r7)
                    java.lang.StringBuilder r12 = r12.append(r5)
                    java.lang.StringBuilder r12 = r12.append(r8)
                    java.lang.String r12 = r12.toString()
                    r10.logAboutMessageToMixpanel(r12, r0)
                    int r0 = r0.getRetryAfter()
                    int r0 = r0 * 1000
                    long r14 = (long) r0
                    r1.mTrackEngageRetryAfter = r14
                L_0x0143:
                    r10 = 0
                    goto L_0x0180
                L_0x0145:
                    r0 = move-exception
                    r10 = 1
                L_0x0147:
                    java.lang.StringBuilder r12 = new java.lang.StringBuilder
                    r12.<init>()
                    java.lang.String r14 = "Cannot interpret "
                    java.lang.StringBuilder r12 = r12.append(r14)
                    java.lang.StringBuilder r12 = r12.append(r5)
                    java.lang.String r14 = " as a URL."
                    java.lang.StringBuilder r12 = r12.append(r14)
                    java.lang.String r12 = r12.toString()
                    com.mixpanel.android.util.MPLog.m62e(r6, r12, r0)
                    goto L_0x0180
                L_0x0164:
                    r0 = move-exception
                    r10 = 1
                L_0x0166:
                    java.lang.StringBuilder r12 = new java.lang.StringBuilder
                    r12.<init>()
                    java.lang.String r14 = "Out of memory when posting to "
                    java.lang.StringBuilder r12 = r12.append(r14)
                    java.lang.StringBuilder r12 = r12.append(r5)
                    java.lang.StringBuilder r12 = r12.append(r8)
                    java.lang.String r12 = r12.toString()
                    com.mixpanel.android.util.MPLog.m62e(r6, r12, r0)
                L_0x0180:
                    if (r10 == 0) goto L_0x019f
                    com.mixpanel.android.mpmetrics.AnalyticsMessages$Worker r0 = com.mixpanel.android.mpmetrics.AnalyticsMessages.Worker.this
                    com.mixpanel.android.mpmetrics.AnalyticsMessages r0 = com.mixpanel.android.mpmetrics.AnalyticsMessages.this
                    java.lang.String r10 = "Not retrying this batch of events, deleting them from DB."
                    r0.logAboutMessageToMixpanel(r10)
                    r2.cleanupEvents(r13, r4, r3)
                    java.lang.String[] r0 = r2.generateDataString(r4, r3)
                    r10 = 2
                    if (r0 == 0) goto L_0x019b
                    r11 = r0[r10]
                    java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
                L_0x019b:
                    r12 = r10
                    r10 = 0
                    goto L_0x002a
                L_0x019f:
                    r10 = 2
                    r1.removeMessages(r10, r3)
                    r4 = 4611686018427387904(0x4000000000000000, double:2.0)
                    int r0 = r1.mFailedRetries
                    double r6 = (double) r0
                    double r4 = java.lang.Math.pow(r4, r6)
                    long r4 = (long) r4
                    r6 = 60000(0xea60, double:2.9644E-319)
                    long r4 = r4 * r6
                    long r6 = r1.mTrackEngageRetryAfter
                    long r4 = java.lang.Math.max(r4, r6)
                    r1.mTrackEngageRetryAfter = r4
                    r6 = 600000(0x927c0, double:2.964394E-318)
                    long r4 = java.lang.Math.min(r4, r6)
                    r1.mTrackEngageRetryAfter = r4
                    android.os.Message r0 = android.os.Message.obtain()
                    r2 = 2
                    r0.what = r2
                    r0.obj = r3
                    long r2 = r1.mTrackEngageRetryAfter
                    r1.sendMessageDelayed(r0, r2)
                    int r0 = r1.mFailedRetries
                    r2 = 1
                    int r0 = r0 + r2
                    r1.mFailedRetries = r0
                    com.mixpanel.android.mpmetrics.AnalyticsMessages$Worker r0 = com.mixpanel.android.mpmetrics.AnalyticsMessages.Worker.this
                    com.mixpanel.android.mpmetrics.AnalyticsMessages r0 = com.mixpanel.android.mpmetrics.AnalyticsMessages.this
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder
                    r2.<init>()
                    java.lang.String r3 = "Retrying this batch of events in "
                    java.lang.StringBuilder r2 = r2.append(r3)
                    long r3 = r1.mTrackEngageRetryAfter
                    java.lang.StringBuilder r1 = r2.append(r3)
                    java.lang.String r2 = " ms"
                    java.lang.StringBuilder r1 = r1.append(r2)
                    java.lang.String r1 = r1.toString()
                    r0.logAboutMessageToMixpanel(r1)
                L_0x01f8:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.mixpanel.android.mpmetrics.AnalyticsMessages.Worker.AnalyticsMessageHandler.sendData(com.mixpanel.android.mpmetrics.MPDbAdapter, java.lang.String, com.mixpanel.android.mpmetrics.MPDbAdapter$Table, java.lang.String):void");
            }

            private JSONObject getDefaultEventProperties() throws JSONException {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("mp_lib", CoreFeature.DEFAULT_SOURCE_NAME);
                jSONObject.put("$lib_version", "7.0.1");
                jSONObject.put("$os", "Android");
                String str = "UNKNOWN";
                jSONObject.put("$os_version", Build.VERSION.RELEASE == null ? str : Build.VERSION.RELEASE);
                jSONObject.put("$manufacturer", Build.MANUFACTURER == null ? str : Build.MANUFACTURER);
                jSONObject.put("$brand", Build.BRAND == null ? str : Build.BRAND);
                if (Build.MODEL != null) {
                    str = Build.MODEL;
                }
                jSONObject.put("$model", str);
                DisplayMetrics displayMetrics = Worker.this.mSystemInformation.getDisplayMetrics();
                jSONObject.put("$screen_dpi", displayMetrics.densityDpi);
                jSONObject.put("$screen_height", displayMetrics.heightPixels);
                jSONObject.put("$screen_width", displayMetrics.widthPixels);
                String appVersionName = Worker.this.mSystemInformation.getAppVersionName();
                if (appVersionName != null) {
                    jSONObject.put("$app_version", appVersionName);
                    jSONObject.put("$app_version_string", appVersionName);
                }
                Integer appVersionCode = Worker.this.mSystemInformation.getAppVersionCode();
                if (appVersionCode != null) {
                    String valueOf = String.valueOf(appVersionCode);
                    jSONObject.put("$app_release", valueOf);
                    jSONObject.put("$app_build_number", valueOf);
                }
                Boolean valueOf2 = Boolean.valueOf(Worker.this.mSystemInformation.hasNFC());
                if (valueOf2 != null) {
                    jSONObject.put("$has_nfc", valueOf2.booleanValue());
                }
                Boolean valueOf3 = Boolean.valueOf(Worker.this.mSystemInformation.hasTelephony());
                if (valueOf3 != null) {
                    jSONObject.put("$has_telephone", valueOf3.booleanValue());
                }
                String currentNetworkOperator = Worker.this.mSystemInformation.getCurrentNetworkOperator();
                if (currentNetworkOperator != null && !currentNetworkOperator.trim().isEmpty()) {
                    jSONObject.put("$carrier", currentNetworkOperator);
                }
                Boolean isWifiConnected = Worker.this.mSystemInformation.isWifiConnected();
                if (isWifiConnected != null) {
                    jSONObject.put("$wifi", isWifiConnected.booleanValue());
                }
                Boolean isBluetoothEnabled = Worker.this.mSystemInformation.isBluetoothEnabled();
                if (isBluetoothEnabled != null) {
                    jSONObject.put("$bluetooth_enabled", isBluetoothEnabled);
                }
                String bluetoothVersion = Worker.this.mSystemInformation.getBluetoothVersion();
                if (bluetoothVersion != null) {
                    jSONObject.put("$bluetooth_version", bluetoothVersion);
                }
                return jSONObject;
            }

            private JSONObject prepareEventObject(EventDescription eventDescription) throws JSONException {
                JSONObject jSONObject = new JSONObject();
                JSONObject properties = eventDescription.getProperties();
                JSONObject defaultEventProperties = getDefaultEventProperties();
                defaultEventProperties.put(MPDbAdapter.KEY_TOKEN, eventDescription.getToken());
                if (properties != null) {
                    Iterator<String> keys = properties.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        defaultEventProperties.put(next, properties.get(next));
                    }
                }
                jSONObject.put("event", eventDescription.getEventName());
                jSONObject.put("properties", defaultEventProperties);
                jSONObject.put("$mp_metadata", eventDescription.getSessionMetadata());
                return jSONObject;
            }
        }

        /* access modifiers changed from: private */
        public void updateFlushFrequency() {
            long currentTimeMillis = System.currentTimeMillis();
            long j = this.mFlushCount;
            long j2 = 1 + j;
            long j3 = this.mLastFlushTime;
            if (j3 > 0) {
                long j4 = ((currentTimeMillis - j3) + (this.mAveFlushFrequency * j)) / j2;
                this.mAveFlushFrequency = j4;
                AnalyticsMessages.this.logAboutMessageToMixpanel("Average send frequency approximately " + (j4 / 1000) + " seconds.");
            }
            this.mLastFlushTime = currentTimeMillis;
            this.mFlushCount = j2;
        }
    }

    public long getTrackEngageRetryAfter() {
        return ((Worker.AnalyticsMessageHandler) this.mWorker.mHandler).getTrackEngageRetryAfter();
    }
}
