package com.mixpanel.android.mpmetrics;

import com.mixpanel.android.util.MPLog;
import java.security.SecureRandom;
import org.json.JSONException;
import org.json.JSONObject;

class SessionMetadata {
    private long mEventsCounter;
    private long mPeopleCounter;
    private final SecureRandom mRandom = new SecureRandom();
    private String mSessionID;
    private long mSessionStartEpoch;

    SessionMetadata() {
        initSession();
    }

    /* access modifiers changed from: protected */
    public void initSession() {
        this.mEventsCounter = 0;
        this.mPeopleCounter = 0;
        this.mSessionID = Long.toHexString(new SecureRandom().nextLong());
        this.mSessionStartEpoch = System.currentTimeMillis() / 1000;
    }

    public JSONObject getMetadataForEvent() {
        return getNewMetadata(true);
    }

    public JSONObject getMetadataForPeople() {
        return getNewMetadata(false);
    }

    private JSONObject getNewMetadata(boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("$mp_event_id", Long.toHexString(this.mRandom.nextLong()));
            jSONObject.put("$mp_session_id", this.mSessionID);
            jSONObject.put("$mp_session_seq_id", z ? this.mEventsCounter : this.mPeopleCounter);
            jSONObject.put("$mp_session_start_sec", this.mSessionStartEpoch);
            if (z) {
                this.mEventsCounter++;
            } else {
                this.mPeopleCounter++;
            }
        } catch (JSONException e) {
            MPLog.m62e(ConfigurationChecker.LOGTAG, "Cannot create session metadata JSON object", e);
        }
        return jSONObject;
    }
}
