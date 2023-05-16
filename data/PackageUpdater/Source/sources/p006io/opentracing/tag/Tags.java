package p006io.opentracing.tag;

import com.datadog.android.log.LogAttributes;

/* renamed from: io.opentracing.tag.Tags */
public final class Tags {
    public static final StringTag COMPONENT = new StringTag("component");
    public static final StringTag DB_INSTANCE = new StringTag(LogAttributes.DB_INSTANCE);
    public static final StringTag DB_STATEMENT = new StringTag(LogAttributes.DB_STATEMENT);
    public static final StringTag DB_TYPE = new StringTag("db.type");
    public static final StringTag DB_USER = new StringTag(LogAttributes.DB_USER);
    public static final BooleanTag ERROR = new BooleanTag("error");
    public static final StringTag HTTP_METHOD = new StringTag(LogAttributes.HTTP_METHOD);
    public static final IntTag HTTP_STATUS = new IntTag(LogAttributes.HTTP_STATUS_CODE);
    public static final StringTag HTTP_URL = new StringTag(LogAttributes.HTTP_URL);
    public static final StringTag MESSAGE_BUS_DESTINATION = new StringTag("message_bus.destination");
    public static final StringTag PEER_HOSTNAME = new StringTag("peer.hostname");
    public static final IntOrStringTag PEER_HOST_IPV4 = new IntOrStringTag("peer.ipv4");
    public static final StringTag PEER_HOST_IPV6 = new StringTag("peer.ipv6");
    public static final IntTag PEER_PORT = new IntTag("peer.port");
    public static final StringTag PEER_SERVICE = new StringTag("peer.service");
    public static final IntTag SAMPLING_PRIORITY = new IntTag("sampling.priority");
    public static final StringTag SPAN_KIND = new StringTag("span.kind");
    public static final String SPAN_KIND_CLIENT = "client";
    public static final String SPAN_KIND_CONSUMER = "consumer";
    public static final String SPAN_KIND_PRODUCER = "producer";
    public static final String SPAN_KIND_SERVER = "server";

    private Tags() {
    }
}
