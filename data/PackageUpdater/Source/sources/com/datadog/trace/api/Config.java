package com.datadog.trace.api;

import com.datadog.android.webview.internal.log.WebViewLogEventConsumer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.SortedSet;
import java.util.UUID;
import java.util.regex.Pattern;

public class Config {
    public static final String AGENT_HOST = "agent.host";
    public static final String AGENT_PORT_LEGACY = "agent.port";
    public static final String AGENT_UNIX_DOMAIN_SOCKET = "trace.agent.unix.domain.socket";
    public static final String API_KEY = "api-key";
    public static final String API_KEY_FILE = "api-key-file";
    public static final String CONFIGURATION_FILE = "trace.config";
    public static final String DB_CLIENT_HOST_SPLIT_BY_INSTANCE = "trace.db.client.split-by-instance";
    public static final String DD_AGENT_WRITER_TYPE = "DDAgentWriter";
    public static final String DEFAULT_AGENT_HOST = "localhost";
    public static final String DEFAULT_AGENT_UNIX_DOMAIN_SOCKET = null;
    private static final String DEFAULT_AGENT_WRITER_TYPE = "DDAgentWriter";
    public static final float DEFAULT_ANALYTICS_SAMPLE_RATE = 1.0f;
    private static final boolean DEFAULT_DB_CLIENT_HOST_SPLIT_BY_INSTANCE = false;
    private static final Set<Integer> DEFAULT_HTTP_CLIENT_ERROR_STATUSES = parseIntegerRangeSet("400-499", "default");
    private static final boolean DEFAULT_HTTP_CLIENT_SPLIT_BY_DOMAIN = false;
    private static final boolean DEFAULT_HTTP_CLIENT_TAG_QUERY_STRING = false;
    private static final Set<Integer> DEFAULT_HTTP_SERVER_ERROR_STATUSES = parseIntegerRangeSet("500-599", "default");
    private static final boolean DEFAULT_HTTP_SERVER_TAG_QUERY_STRING = false;
    public static final boolean DEFAULT_INTEGRATIONS_ENABLED = true;
    private static final boolean DEFAULT_JMX_FETCH_ENABLED = true;
    public static final int DEFAULT_JMX_FETCH_STATSD_PORT = 8125;
    public static final boolean DEFAULT_LOGS_INJECTION_ENABLED = false;
    public static final boolean DEFAULT_METRICS_ENABLED = false;
    private static final int DEFAULT_PARTIAL_FLUSH_MIN_SPANS = 1000;
    private static final boolean DEFAULT_PRIORITY_SAMPLING_ENABLED = true;
    public static final boolean DEFAULT_PROFILING_ENABLED = false;
    public static final int DEFAULT_PROFILING_EXCEPTION_HISTOGRAM_MAX_COLLECTION_SIZE = 10000;
    public static final int DEFAULT_PROFILING_EXCEPTION_HISTOGRAM_TOP_ITEMS = 50;
    public static final int DEFAULT_PROFILING_EXCEPTION_SAMPLE_LIMIT = 10000;
    public static final int DEFAULT_PROFILING_PROXY_PORT = 8080;
    public static final int DEFAULT_PROFILING_START_DELAY = 10;
    public static final boolean DEFAULT_PROFILING_START_FORCE_FIRST = false;
    public static final String DEFAULT_PROFILING_UPLOAD_COMPRESSION = "on";
    public static final int DEFAULT_PROFILING_UPLOAD_PERIOD = 60;
    public static final int DEFAULT_PROFILING_UPLOAD_TIMEOUT = 30;
    private static final String DEFAULT_PROPAGATION_STYLE_EXTRACT = PropagationStyle.DATADOG.name();
    private static final String DEFAULT_PROPAGATION_STYLE_INJECT = PropagationStyle.DATADOG.name();
    private static final boolean DEFAULT_RUNTIME_CONTEXT_FIELD_INJECTION = true;
    private static final int DEFAULT_SCOPE_DEPTH_LIMIT = 100;
    public static final String DEFAULT_SERVICE_NAME = "unnamed-java-app";
    public static final String DEFAULT_SITE = "datadoghq.com";
    private static final String DEFAULT_SPLIT_BY_TAGS = "";
    public static final int DEFAULT_TRACE_AGENT_PORT = 8126;
    public static final boolean DEFAULT_TRACE_ANALYTICS_ENABLED = false;
    private static final String DEFAULT_TRACE_ANNOTATIONS = null;
    private static final boolean DEFAULT_TRACE_ENABLED = true;
    private static final String DEFAULT_TRACE_EXECUTORS = "";
    private static final boolean DEFAULT_TRACE_EXECUTORS_ALL = false;
    private static final String DEFAULT_TRACE_METHODS = null;
    public static final double DEFAULT_TRACE_RATE_LIMIT = 100.0d;
    private static final boolean DEFAULT_TRACE_REPORT_HOSTNAME = false;
    private static final boolean DEFAULT_TRACE_RESOLVER_ENABLED = true;
    private static final String ENV = "env";
    private static final Pattern ENV_REPLACEMENT = Pattern.compile("[^a-zA-Z0-9_]");
    @Deprecated
    public static final String GLOBAL_TAGS = "trace.global.tags";
    public static final String HEADER_TAGS = "trace.header.tags";
    public static final String HEALTH_METRICS_ENABLED = "trace.health.metrics.enabled";
    public static final String HEALTH_METRICS_STATSD_HOST = "trace.health.metrics.statsd.host";
    public static final String HEALTH_METRICS_STATSD_PORT = "trace.health.metrics.statsd.port";
    public static final String HOST_TAG = "host";
    public static final String HTTP_CLIENT_ERROR_STATUSES = "http.client.error.statuses";
    public static final String HTTP_CLIENT_HOST_SPLIT_BY_DOMAIN = "trace.http.client.split-by-domain";
    public static final String HTTP_CLIENT_TAG_QUERY_STRING = "http.client.tag.query-string";
    public static final String HTTP_SERVER_ERROR_STATUSES = "http.server.error.statuses";
    public static final String HTTP_SERVER_TAG_QUERY_STRING = "http.server.tag.query-string";
    private static final Config INSTANCE = new Config();
    public static final String INTEGRATIONS_ENABLED = "integrations.enabled";
    private static final String INTERNAL_HOST_NAME = "_dd.hostname";
    public static final String JMX_FETCH_CHECK_PERIOD = "jmxfetch.check-period";
    public static final String JMX_FETCH_CONFIG = "jmxfetch.config";
    public static final String JMX_FETCH_CONFIG_DIR = "jmxfetch.config.dir";
    public static final String JMX_FETCH_ENABLED = "jmxfetch.enabled";
    @Deprecated
    public static final String JMX_FETCH_METRICS_CONFIGS = "jmxfetch.metrics-configs";
    public static final String JMX_FETCH_REFRESH_BEANS_PERIOD = "jmxfetch.refresh-beans-period";
    public static final String JMX_FETCH_STATSD_HOST = "jmxfetch.statsd.host";
    public static final String JMX_FETCH_STATSD_PORT = "jmxfetch.statsd.port";
    public static final String JMX_TAGS = "trace.jmx.tags";
    public static final String LANGUAGE_TAG_KEY = "language";
    public static final String LANGUAGE_TAG_VALUE = "jvm";
    public static final String LOGGING_WRITER_TYPE = "LoggingWriter";
    public static final String LOGS_INJECTION_ENABLED = "logs.injection";
    public static final String PARTIAL_FLUSH_MIN_SPANS = "trace.partial.flush.min.spans";
    private static final String PREFIX = "dd.";
    public static final String PRIORITY_SAMPLING = "priority.sampling";
    @Deprecated
    public static final String PROFILING_API_KEY_FILE_OLD = "profiling.api-key-file";
    @Deprecated
    public static final String PROFILING_API_KEY_FILE_VERY_OLD = "profiling.apikey.file";
    @Deprecated
    public static final String PROFILING_API_KEY_OLD = "profiling.api-key";
    @Deprecated
    public static final String PROFILING_API_KEY_VERY_OLD = "profiling.apikey";
    public static final String PROFILING_ENABLED = "profiling.enabled";
    public static final String PROFILING_EXCEPTION_HISTOGRAM_MAX_COLLECTION_SIZE = "profiling.exception.histogram.max-collection-size";
    public static final String PROFILING_EXCEPTION_HISTOGRAM_TOP_ITEMS = "profiling.exception.histogram.top-items";
    public static final String PROFILING_EXCEPTION_SAMPLE_LIMIT = "profiling.exception.sample.limit";
    public static final String PROFILING_PROXY_HOST = "profiling.proxy.host";
    public static final String PROFILING_PROXY_PASSWORD = "profiling.proxy.password";
    public static final String PROFILING_PROXY_PORT = "profiling.proxy.port";
    public static final String PROFILING_PROXY_USERNAME = "profiling.proxy.username";
    public static final String PROFILING_START_DELAY = "profiling.start-delay";
    public static final String PROFILING_START_FORCE_FIRST = "profiling.experimental.start-force-first";
    public static final String PROFILING_TAGS = "profiling.tags";
    public static final String PROFILING_TEMPLATE_OVERRIDE_FILE = "profiling.jfr-template-override-file";
    public static final String PROFILING_UPLOAD_COMPRESSION = "profiling.upload.compression";
    public static final String PROFILING_UPLOAD_PERIOD = "profiling.upload.period";
    public static final String PROFILING_UPLOAD_TIMEOUT = "profiling.upload.timeout";
    @Deprecated
    public static final String PROFILING_URL = "profiling.url";
    public static final String PROFILING_URL_TEMPLATE = "https://intake.profile.%s/v1/input";
    public static final String PROPAGATION_STYLE_EXTRACT = "propagation.style.extract";
    public static final String PROPAGATION_STYLE_INJECT = "propagation.style.inject";
    public static final String RUNTIME_CONTEXT_FIELD_INJECTION = "trace.runtime.context.field.injection";
    public static final String RUNTIME_ID_TAG = "runtime-id";
    public static final String SCOPE_DEPTH_LIMIT = "trace.scope.depth.limit";
    public static final String SERVICE = "service";
    public static final String SERVICE_MAPPING = "service.mapping";
    public static final String SERVICE_NAME = "service.name";
    public static final String SERVICE_TAG = "service";
    public static final String SITE = "site";
    public static final String SPAN_TAGS = "trace.span.tags";
    private static final String SPLIT_BY_SPACE_OR_COMMA_REGEX = "[,\\s]+";
    public static final String SPLIT_BY_TAGS = "trace.split-by-tags";
    public static final String TAGS = "tags";
    public static final String TRACE_AGENT_PORT = "trace.agent.port";
    public static final String TRACE_ANALYTICS_ENABLED = "trace.analytics.enabled";
    public static final String TRACE_ANNOTATIONS = "trace.annotations";
    public static final String TRACE_CLASSES_EXCLUDE = "trace.classes.exclude";
    public static final String TRACE_ENABLED = "trace.enabled";
    public static final String TRACE_EXECUTORS = "trace.executors";
    public static final String TRACE_EXECUTORS_ALL = "trace.executors.all";
    public static final String TRACE_METHODS = "trace.methods";
    public static final String TRACE_RATE_LIMIT = "trace.rate.limit";
    public static final String TRACE_REPORT_HOSTNAME = "trace.report-hostname";
    public static final String TRACE_RESOLVER_ENABLED = "trace.resolver.enabled";
    public static final String TRACE_SAMPLE_RATE = "trace.sample.rate";
    public static final String TRACE_SAMPLING_OPERATION_RULES = "trace.sampling.operation.rules";
    public static final String TRACE_SAMPLING_SERVICE_RULES = "trace.sampling.service.rules";
    private static final String VERSION = "version";
    public static final String WRITER_TYPE = "writer.type";
    private static Properties propertiesFromConfigFile;
    private final String agentHost;
    private final int agentPort;
    private final String agentUnixDomainSocket;
    private final boolean dbClientSplitByInstance;
    private final List<String> excludedClasses;
    private final Map<String, String> headerTags;
    private final boolean healthMetricsEnabled;
    private final String healthMetricsStatsdHost;
    private final Integer healthMetricsStatsdPort;
    private final Set<Integer> httpClientErrorStatuses;
    private final boolean httpClientSplitByDomain;
    private final boolean httpClientTagQueryString;
    private final Set<Integer> httpServerErrorStatuses;
    private final boolean httpServerTagQueryString;
    private final boolean integrationsEnabled;
    private final Integer jmxFetchCheckPeriod;
    private final String jmxFetchConfigDir;
    private final List<String> jmxFetchConfigs;
    private final boolean jmxFetchEnabled;
    @Deprecated
    private final List<String> jmxFetchMetricsConfigs;
    private final Integer jmxFetchRefreshBeansPeriod;
    private final String jmxFetchStatsdHost;
    private final Integer jmxFetchStatsdPort;
    private final Map<String, String> jmxTags;
    private final boolean logsInjectionEnabled;
    private final Integer partialFlushMinSpans;
    private final boolean prioritySamplingEnabled;
    private final boolean profilingEnabled;
    private final int profilingExceptionHistogramMaxCollectionSize;
    private final int profilingExceptionHistogramTopItems;
    private final int profilingExceptionSampleLimit;
    private final String profilingProxyHost;
    private final String profilingProxyPassword;
    private final int profilingProxyPort;
    private final String profilingProxyUsername;
    private final int profilingStartDelay;
    private final boolean profilingStartForceFirst;
    private final Map<String, String> profilingTags;
    private final String profilingTemplateOverrideFile;
    private final String profilingUploadCompression;
    private final int profilingUploadPeriod;
    private final int profilingUploadTimeout;
    @Deprecated
    private final String profilingUrl;
    private final Set<PropagationStyle> propagationStylesToExtract;
    private final Set<PropagationStyle> propagationStylesToInject;
    private final boolean reportHostName;
    private final boolean runtimeContextFieldInjection;
    private final String runtimeId;
    private final Integer scopeDepthLimit;
    private final Map<String, String> serviceMapping;
    private final String serviceName;
    private final String site;
    private final Map<String, String> spanTags;
    private final Set<String> splitByTags;
    private final Map<String, String> tags;
    private final boolean traceAnalyticsEnabled;
    private final String traceAnnotations;
    private final boolean traceEnabled;
    private final List<String> traceExecutors;
    private final boolean traceExecutorsAll;
    private final String traceMethods;
    private final Double traceRateLimit;
    private final boolean traceResolverEnabled;
    private final Double traceSampleRate;
    private final Map<String, String> traceSamplingOperationRules;
    private final Map<String, String> traceSamplingServiceRules;
    private final String writerType;

    public enum PropagationStyle {
        DATADOG,
        B3,
        HAYSTACK
    }

    Config() {
        propertiesFromConfigFile = loadConfigurationFile();
        this.runtimeId = UUID.randomUUID().toString();
        this.site = getSettingFromEnvironment(SITE, DEFAULT_SITE);
        this.serviceName = getSettingFromEnvironment("service", getSettingFromEnvironment("service.name", DEFAULT_SERVICE_NAME));
        this.traceEnabled = getBooleanSettingFromEnvironment(TRACE_ENABLED, true).booleanValue();
        this.integrationsEnabled = getBooleanSettingFromEnvironment(INTEGRATIONS_ENABLED, true).booleanValue();
        this.writerType = getSettingFromEnvironment(WRITER_TYPE, "DDAgentWriter");
        this.agentHost = getSettingFromEnvironment(AGENT_HOST, DEFAULT_AGENT_HOST);
        this.agentPort = getIntegerSettingFromEnvironment(TRACE_AGENT_PORT, getIntegerSettingFromEnvironment(AGENT_PORT_LEGACY, Integer.valueOf(DEFAULT_TRACE_AGENT_PORT))).intValue();
        this.agentUnixDomainSocket = getSettingFromEnvironment(AGENT_UNIX_DOMAIN_SOCKET, DEFAULT_AGENT_UNIX_DOMAIN_SOCKET);
        this.prioritySamplingEnabled = getBooleanSettingFromEnvironment(PRIORITY_SAMPLING, true).booleanValue();
        this.traceResolverEnabled = getBooleanSettingFromEnvironment(TRACE_RESOLVER_ENABLED, true).booleanValue();
        this.serviceMapping = getMapSettingFromEnvironment(SERVICE_MAPPING, (String) null);
        HashMap hashMap = new HashMap(getMapSettingFromEnvironment(GLOBAL_TAGS, (String) null));
        hashMap.putAll(getMapSettingFromEnvironment(TAGS, (String) null));
        this.tags = getMapWithPropertiesDefinedByEnvironment(hashMap, "env", "version");
        this.spanTags = getMapSettingFromEnvironment(SPAN_TAGS, (String) null);
        this.jmxTags = getMapSettingFromEnvironment(JMX_TAGS, (String) null);
        this.excludedClasses = getListSettingFromEnvironment(TRACE_CLASSES_EXCLUDE, (String) null);
        this.headerTags = getMapSettingFromEnvironment(HEADER_TAGS, (String) null);
        this.httpServerErrorStatuses = getIntegerRangeSettingFromEnvironment(HTTP_SERVER_ERROR_STATUSES, DEFAULT_HTTP_SERVER_ERROR_STATUSES);
        this.httpClientErrorStatuses = getIntegerRangeSettingFromEnvironment(HTTP_CLIENT_ERROR_STATUSES, DEFAULT_HTTP_CLIENT_ERROR_STATUSES);
        this.httpServerTagQueryString = getBooleanSettingFromEnvironment(HTTP_SERVER_TAG_QUERY_STRING, false).booleanValue();
        this.httpClientTagQueryString = getBooleanSettingFromEnvironment(HTTP_CLIENT_TAG_QUERY_STRING, false).booleanValue();
        this.httpClientSplitByDomain = getBooleanSettingFromEnvironment(HTTP_CLIENT_HOST_SPLIT_BY_DOMAIN, false).booleanValue();
        this.dbClientSplitByInstance = getBooleanSettingFromEnvironment(DB_CLIENT_HOST_SPLIT_BY_INSTANCE, false).booleanValue();
        this.splitByTags = Collections.unmodifiableSet(new LinkedHashSet(getListSettingFromEnvironment(SPLIT_BY_TAGS, "")));
        this.scopeDepthLimit = getIntegerSettingFromEnvironment(SCOPE_DEPTH_LIMIT, 100);
        this.partialFlushMinSpans = getIntegerSettingFromEnvironment(PARTIAL_FLUSH_MIN_SPANS, 1000);
        this.runtimeContextFieldInjection = getBooleanSettingFromEnvironment(RUNTIME_CONTEXT_FIELD_INJECTION, true).booleanValue();
        this.propagationStylesToExtract = getPropagationStyleSetSettingFromEnvironmentOrDefault(PROPAGATION_STYLE_EXTRACT, DEFAULT_PROPAGATION_STYLE_EXTRACT);
        this.propagationStylesToInject = getPropagationStyleSetSettingFromEnvironmentOrDefault(PROPAGATION_STYLE_INJECT, DEFAULT_PROPAGATION_STYLE_INJECT);
        this.jmxFetchEnabled = getBooleanSettingFromEnvironment(JMX_FETCH_ENABLED, true).booleanValue();
        this.jmxFetchConfigDir = getSettingFromEnvironment(JMX_FETCH_CONFIG_DIR, (String) null);
        this.jmxFetchConfigs = getListSettingFromEnvironment(JMX_FETCH_CONFIG, (String) null);
        this.jmxFetchMetricsConfigs = getListSettingFromEnvironment(JMX_FETCH_METRICS_CONFIGS, (String) null);
        this.jmxFetchCheckPeriod = getIntegerSettingFromEnvironment(JMX_FETCH_CHECK_PERIOD, (Integer) null);
        this.jmxFetchRefreshBeansPeriod = getIntegerSettingFromEnvironment(JMX_FETCH_REFRESH_BEANS_PERIOD, (Integer) null);
        this.jmxFetchStatsdHost = getSettingFromEnvironment(JMX_FETCH_STATSD_HOST, (String) null);
        this.jmxFetchStatsdPort = getIntegerSettingFromEnvironment(JMX_FETCH_STATSD_PORT, Integer.valueOf(DEFAULT_JMX_FETCH_STATSD_PORT));
        this.healthMetricsEnabled = getBooleanSettingFromEnvironment(HEALTH_METRICS_ENABLED, false).booleanValue();
        this.healthMetricsStatsdHost = getSettingFromEnvironment(HEALTH_METRICS_STATSD_HOST, (String) null);
        this.healthMetricsStatsdPort = getIntegerSettingFromEnvironment(HEALTH_METRICS_STATSD_PORT, (Integer) null);
        this.logsInjectionEnabled = getBooleanSettingFromEnvironment(LOGS_INJECTION_ENABLED, false).booleanValue();
        this.reportHostName = getBooleanSettingFromEnvironment(TRACE_REPORT_HOSTNAME, false).booleanValue();
        this.traceAnnotations = getSettingFromEnvironment(TRACE_ANNOTATIONS, DEFAULT_TRACE_ANNOTATIONS);
        this.traceMethods = getSettingFromEnvironment(TRACE_METHODS, DEFAULT_TRACE_METHODS);
        this.traceExecutorsAll = getBooleanSettingFromEnvironment(TRACE_EXECUTORS_ALL, false).booleanValue();
        this.traceExecutors = getListSettingFromEnvironment(TRACE_EXECUTORS, "");
        this.traceAnalyticsEnabled = getBooleanSettingFromEnvironment(TRACE_ANALYTICS_ENABLED, false).booleanValue();
        this.traceSamplingServiceRules = getMapSettingFromEnvironment(TRACE_SAMPLING_SERVICE_RULES, (String) null);
        this.traceSamplingOperationRules = getMapSettingFromEnvironment(TRACE_SAMPLING_OPERATION_RULES, (String) null);
        this.traceSampleRate = getDoubleSettingFromEnvironment(TRACE_SAMPLE_RATE, (Double) null);
        this.traceRateLimit = getDoubleSettingFromEnvironment(TRACE_RATE_LIMIT, Double.valueOf(100.0d));
        this.profilingEnabled = getBooleanSettingFromEnvironment(PROFILING_ENABLED, false).booleanValue();
        this.profilingUrl = getSettingFromEnvironment(PROFILING_URL, (String) null);
        this.profilingTags = getMapSettingFromEnvironment(PROFILING_TAGS, (String) null);
        this.profilingStartDelay = getIntegerSettingFromEnvironment(PROFILING_START_DELAY, 10).intValue();
        this.profilingStartForceFirst = getBooleanSettingFromEnvironment(PROFILING_START_FORCE_FIRST, false).booleanValue();
        this.profilingUploadPeriod = getIntegerSettingFromEnvironment(PROFILING_UPLOAD_PERIOD, 60).intValue();
        this.profilingTemplateOverrideFile = getSettingFromEnvironment(PROFILING_TEMPLATE_OVERRIDE_FILE, (String) null);
        this.profilingUploadTimeout = getIntegerSettingFromEnvironment(PROFILING_UPLOAD_TIMEOUT, 30).intValue();
        this.profilingUploadCompression = getSettingFromEnvironment(PROFILING_UPLOAD_COMPRESSION, "on");
        this.profilingProxyHost = getSettingFromEnvironment(PROFILING_PROXY_HOST, (String) null);
        this.profilingProxyPort = getIntegerSettingFromEnvironment(PROFILING_PROXY_PORT, Integer.valueOf(DEFAULT_PROFILING_PROXY_PORT)).intValue();
        this.profilingProxyUsername = getSettingFromEnvironment(PROFILING_PROXY_USERNAME, (String) null);
        this.profilingProxyPassword = getSettingFromEnvironment(PROFILING_PROXY_PASSWORD, (String) null);
        this.profilingExceptionSampleLimit = getIntegerSettingFromEnvironment(PROFILING_EXCEPTION_SAMPLE_LIMIT, 10000).intValue();
        this.profilingExceptionHistogramTopItems = getIntegerSettingFromEnvironment(PROFILING_EXCEPTION_HISTOGRAM_TOP_ITEMS, 50).intValue();
        this.profilingExceptionHistogramMaxCollectionSize = getIntegerSettingFromEnvironment(PROFILING_EXCEPTION_HISTOGRAM_MAX_COLLECTION_SIZE, 10000).intValue();
    }

    private Config(Properties properties, Config config) {
        this.runtimeId = config.runtimeId;
        this.site = properties.getProperty(SITE, config.site);
        this.serviceName = properties.getProperty("service", properties.getProperty("service.name", config.serviceName));
        this.traceEnabled = getPropertyBooleanValue(properties, TRACE_ENABLED, Boolean.valueOf(config.traceEnabled)).booleanValue();
        this.integrationsEnabled = getPropertyBooleanValue(properties, INTEGRATIONS_ENABLED, Boolean.valueOf(config.integrationsEnabled)).booleanValue();
        this.writerType = properties.getProperty(WRITER_TYPE, config.writerType);
        this.agentHost = properties.getProperty(AGENT_HOST, config.agentHost);
        this.agentPort = getPropertyIntegerValue(properties, TRACE_AGENT_PORT, getPropertyIntegerValue(properties, AGENT_PORT_LEGACY, Integer.valueOf(config.agentPort))).intValue();
        this.agentUnixDomainSocket = properties.getProperty(AGENT_UNIX_DOMAIN_SOCKET, config.agentUnixDomainSocket);
        this.prioritySamplingEnabled = getPropertyBooleanValue(properties, PRIORITY_SAMPLING, Boolean.valueOf(config.prioritySamplingEnabled)).booleanValue();
        this.traceResolverEnabled = getPropertyBooleanValue(properties, TRACE_RESOLVER_ENABLED, Boolean.valueOf(config.traceResolverEnabled)).booleanValue();
        this.serviceMapping = getPropertyMapValue(properties, SERVICE_MAPPING, config.serviceMapping);
        HashMap hashMap = new HashMap(getPropertyMapValue(properties, GLOBAL_TAGS, Collections.emptyMap()));
        hashMap.putAll(getPropertyMapValue(properties, TAGS, config.tags));
        this.tags = overwriteKeysFromProperties(hashMap, properties, "env", "version");
        this.spanTags = getPropertyMapValue(properties, SPAN_TAGS, config.spanTags);
        this.jmxTags = getPropertyMapValue(properties, JMX_TAGS, config.jmxTags);
        this.excludedClasses = getPropertyListValue(properties, TRACE_CLASSES_EXCLUDE, config.excludedClasses);
        this.headerTags = getPropertyMapValue(properties, HEADER_TAGS, config.headerTags);
        this.httpServerErrorStatuses = getPropertyIntegerRangeValue(properties, HTTP_SERVER_ERROR_STATUSES, config.httpServerErrorStatuses);
        this.httpClientErrorStatuses = getPropertyIntegerRangeValue(properties, HTTP_CLIENT_ERROR_STATUSES, config.httpClientErrorStatuses);
        this.httpServerTagQueryString = getPropertyBooleanValue(properties, HTTP_SERVER_TAG_QUERY_STRING, Boolean.valueOf(config.httpServerTagQueryString)).booleanValue();
        this.httpClientTagQueryString = getPropertyBooleanValue(properties, HTTP_CLIENT_TAG_QUERY_STRING, Boolean.valueOf(config.httpClientTagQueryString)).booleanValue();
        this.httpClientSplitByDomain = getPropertyBooleanValue(properties, HTTP_CLIENT_HOST_SPLIT_BY_DOMAIN, Boolean.valueOf(config.httpClientSplitByDomain)).booleanValue();
        this.dbClientSplitByInstance = getPropertyBooleanValue(properties, DB_CLIENT_HOST_SPLIT_BY_INSTANCE, Boolean.valueOf(config.dbClientSplitByInstance)).booleanValue();
        this.splitByTags = Collections.unmodifiableSet(new LinkedHashSet(getPropertyListValue(properties, SPLIT_BY_TAGS, new ArrayList(config.splitByTags))));
        this.scopeDepthLimit = getPropertyIntegerValue(properties, SCOPE_DEPTH_LIMIT, config.scopeDepthLimit);
        this.partialFlushMinSpans = getPropertyIntegerValue(properties, PARTIAL_FLUSH_MIN_SPANS, config.partialFlushMinSpans);
        this.runtimeContextFieldInjection = getPropertyBooleanValue(properties, RUNTIME_CONTEXT_FIELD_INJECTION, Boolean.valueOf(config.runtimeContextFieldInjection)).booleanValue();
        Set<PropagationStyle> propagationStyleSetFromPropertyValue = getPropagationStyleSetFromPropertyValue(properties, PROPAGATION_STYLE_EXTRACT);
        this.propagationStylesToExtract = propagationStyleSetFromPropertyValue == null ? config.propagationStylesToExtract : propagationStyleSetFromPropertyValue;
        Set<PropagationStyle> propagationStyleSetFromPropertyValue2 = getPropagationStyleSetFromPropertyValue(properties, PROPAGATION_STYLE_INJECT);
        this.propagationStylesToInject = propagationStyleSetFromPropertyValue2 == null ? config.propagationStylesToInject : propagationStyleSetFromPropertyValue2;
        this.jmxFetchEnabled = getPropertyBooleanValue(properties, JMX_FETCH_ENABLED, Boolean.valueOf(config.jmxFetchEnabled)).booleanValue();
        this.jmxFetchConfigDir = properties.getProperty(JMX_FETCH_CONFIG_DIR, config.jmxFetchConfigDir);
        this.jmxFetchConfigs = getPropertyListValue(properties, JMX_FETCH_CONFIG, config.jmxFetchConfigs);
        this.jmxFetchMetricsConfigs = getPropertyListValue(properties, JMX_FETCH_METRICS_CONFIGS, config.jmxFetchMetricsConfigs);
        this.jmxFetchCheckPeriod = getPropertyIntegerValue(properties, JMX_FETCH_CHECK_PERIOD, config.jmxFetchCheckPeriod);
        this.jmxFetchRefreshBeansPeriod = getPropertyIntegerValue(properties, JMX_FETCH_REFRESH_BEANS_PERIOD, config.jmxFetchRefreshBeansPeriod);
        this.jmxFetchStatsdHost = properties.getProperty(JMX_FETCH_STATSD_HOST, config.jmxFetchStatsdHost);
        this.jmxFetchStatsdPort = getPropertyIntegerValue(properties, JMX_FETCH_STATSD_PORT, config.jmxFetchStatsdPort);
        this.healthMetricsEnabled = getPropertyBooleanValue(properties, HEALTH_METRICS_ENABLED, false).booleanValue();
        this.healthMetricsStatsdHost = properties.getProperty(HEALTH_METRICS_STATSD_HOST, config.healthMetricsStatsdHost);
        this.healthMetricsStatsdPort = getPropertyIntegerValue(properties, HEALTH_METRICS_STATSD_PORT, config.healthMetricsStatsdPort);
        this.logsInjectionEnabled = getBooleanSettingFromEnvironment(LOGS_INJECTION_ENABLED, false).booleanValue();
        this.reportHostName = getPropertyBooleanValue(properties, TRACE_REPORT_HOSTNAME, Boolean.valueOf(config.reportHostName)).booleanValue();
        this.traceAnnotations = properties.getProperty(TRACE_ANNOTATIONS, config.traceAnnotations);
        this.traceMethods = properties.getProperty(TRACE_METHODS, config.traceMethods);
        this.traceExecutorsAll = getPropertyBooleanValue(properties, TRACE_EXECUTORS_ALL, Boolean.valueOf(config.traceExecutorsAll)).booleanValue();
        this.traceExecutors = getPropertyListValue(properties, TRACE_EXECUTORS, config.traceExecutors);
        this.traceAnalyticsEnabled = getPropertyBooleanValue(properties, TRACE_ANALYTICS_ENABLED, Boolean.valueOf(config.traceAnalyticsEnabled)).booleanValue();
        this.traceSamplingServiceRules = getPropertyMapValue(properties, TRACE_SAMPLING_SERVICE_RULES, config.traceSamplingServiceRules);
        this.traceSamplingOperationRules = getPropertyMapValue(properties, TRACE_SAMPLING_OPERATION_RULES, config.traceSamplingOperationRules);
        this.traceSampleRate = getPropertyDoubleValue(properties, TRACE_SAMPLE_RATE, config.traceSampleRate);
        this.traceRateLimit = getPropertyDoubleValue(properties, TRACE_RATE_LIMIT, config.traceRateLimit);
        this.profilingEnabled = getPropertyBooleanValue(properties, PROFILING_ENABLED, Boolean.valueOf(config.profilingEnabled)).booleanValue();
        this.profilingUrl = properties.getProperty(PROFILING_URL, config.profilingUrl);
        this.profilingTags = getPropertyMapValue(properties, PROFILING_TAGS, config.profilingTags);
        this.profilingStartDelay = getPropertyIntegerValue(properties, PROFILING_START_DELAY, Integer.valueOf(config.profilingStartDelay)).intValue();
        this.profilingStartForceFirst = getPropertyBooleanValue(properties, PROFILING_START_FORCE_FIRST, Boolean.valueOf(config.profilingStartForceFirst)).booleanValue();
        this.profilingUploadPeriod = getPropertyIntegerValue(properties, PROFILING_UPLOAD_PERIOD, Integer.valueOf(config.profilingUploadPeriod)).intValue();
        this.profilingTemplateOverrideFile = properties.getProperty(PROFILING_TEMPLATE_OVERRIDE_FILE, config.profilingTemplateOverrideFile);
        this.profilingUploadTimeout = getPropertyIntegerValue(properties, PROFILING_UPLOAD_TIMEOUT, Integer.valueOf(config.profilingUploadTimeout)).intValue();
        this.profilingUploadCompression = properties.getProperty(PROFILING_UPLOAD_COMPRESSION, config.profilingUploadCompression);
        this.profilingProxyHost = properties.getProperty(PROFILING_PROXY_HOST, config.profilingProxyHost);
        this.profilingProxyPort = getPropertyIntegerValue(properties, PROFILING_PROXY_PORT, Integer.valueOf(config.profilingProxyPort)).intValue();
        this.profilingProxyUsername = properties.getProperty(PROFILING_PROXY_USERNAME, config.profilingProxyUsername);
        this.profilingProxyPassword = properties.getProperty(PROFILING_PROXY_PASSWORD, config.profilingProxyPassword);
        this.profilingExceptionSampleLimit = getPropertyIntegerValue(properties, PROFILING_EXCEPTION_SAMPLE_LIMIT, Integer.valueOf(config.profilingExceptionSampleLimit)).intValue();
        this.profilingExceptionHistogramTopItems = getPropertyIntegerValue(properties, PROFILING_EXCEPTION_HISTOGRAM_TOP_ITEMS, Integer.valueOf(config.profilingExceptionHistogramTopItems)).intValue();
        this.profilingExceptionHistogramMaxCollectionSize = getPropertyIntegerValue(properties, PROFILING_EXCEPTION_HISTOGRAM_MAX_COLLECTION_SIZE, Integer.valueOf(config.profilingExceptionHistogramMaxCollectionSize)).intValue();
    }

    public Map<String, String> getLocalRootSpanTags() {
        String hostName;
        HashMap hashMap = new HashMap(getRuntimeTags());
        hashMap.put(LANGUAGE_TAG_KEY, LANGUAGE_TAG_VALUE);
        if (this.reportHostName && (hostName = getHostName()) != null && !hostName.isEmpty()) {
            hashMap.put(INTERNAL_HOST_NAME, hostName);
        }
        return Collections.unmodifiableMap(hashMap);
    }

    public Map<String, String> getMergedSpanTags() {
        Map<String, String> newHashMap = newHashMap(getGlobalTags().size() + this.spanTags.size());
        newHashMap.putAll(getGlobalTags());
        newHashMap.putAll(this.spanTags);
        return Collections.unmodifiableMap(newHashMap);
    }

    public Map<String, String> getMergedJmxTags() {
        Map<String, String> runtimeTags = getRuntimeTags();
        Map<String, String> newHashMap = newHashMap(getGlobalTags().size() + this.jmxTags.size() + runtimeTags.size() + 1);
        newHashMap.putAll(getGlobalTags());
        newHashMap.putAll(this.jmxTags);
        newHashMap.putAll(runtimeTags);
        newHashMap.put("service", this.serviceName);
        return Collections.unmodifiableMap(newHashMap);
    }

    public Map<String, String> getMergedProfilingTags() {
        Map<String, String> runtimeTags = getRuntimeTags();
        String hostName = getHostName();
        Map<String, String> newHashMap = newHashMap(getGlobalTags().size() + this.profilingTags.size() + runtimeTags.size() + 3);
        newHashMap.put("host", hostName);
        newHashMap.putAll(getGlobalTags());
        newHashMap.putAll(this.profilingTags);
        newHashMap.putAll(runtimeTags);
        newHashMap.put("service", this.serviceName);
        newHashMap.put(LANGUAGE_TAG_KEY, LANGUAGE_TAG_VALUE);
        return Collections.unmodifiableMap(newHashMap);
    }

    public float getInstrumentationAnalyticsSampleRate(String... strArr) {
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            Float floatSettingFromEnvironment = getFloatSettingFromEnvironment(strArr[i] + ".analytics.sample-rate", (Float) null);
            if (floatSettingFromEnvironment != null) {
                return floatSettingFromEnvironment.floatValue();
            }
        }
        return 1.0f;
    }

    private Map<String, String> getGlobalTags() {
        return this.tags;
    }

    private Map<String, String> getRuntimeTags() {
        Map<String, String> newHashMap = newHashMap(2);
        newHashMap.put(RUNTIME_ID_TAG, this.runtimeId);
        return Collections.unmodifiableMap(newHashMap);
    }

    public String getFinalProfilingUrl() {
        String str = this.profilingUrl;
        if (str != null) {
            return str;
        }
        return String.format(Locale.US, PROFILING_URL_TEMPLATE, new Object[]{this.site});
    }

    public boolean isIntegrationEnabled(SortedSet<String> sortedSet, boolean z) {
        return integrationEnabled(sortedSet, z);
    }

    @Deprecated
    private static boolean integrationEnabled(SortedSet<String> sortedSet, boolean z) {
        boolean z2 = z;
        for (String str : sortedSet) {
            boolean booleanValue = getBooleanSettingFromEnvironment("integration." + str + ".enabled", Boolean.valueOf(z)).booleanValue();
            z2 = z ? z2 & booleanValue : z2 | booleanValue;
        }
        return z2;
    }

    public boolean isJmxFetchIntegrationEnabled(SortedSet<String> sortedSet, boolean z) {
        return jmxFetchIntegrationEnabled(sortedSet, z);
    }

    public boolean isRuleEnabled(String str) {
        if (!getBooleanSettingFromEnvironment("trace." + str + ".enabled", true).booleanValue() || !getBooleanSettingFromEnvironment("trace." + str.toLowerCase(Locale.US) + ".enabled", true).booleanValue()) {
            return false;
        }
        return true;
    }

    @Deprecated
    public static boolean jmxFetchIntegrationEnabled(SortedSet<String> sortedSet, boolean z) {
        boolean z2 = z;
        for (String str : sortedSet) {
            boolean booleanValue = getBooleanSettingFromEnvironment("jmxfetch." + str + ".enabled", Boolean.valueOf(z)).booleanValue();
            z2 = z ? z2 & booleanValue : z2 | booleanValue;
        }
        return z2;
    }

    public boolean isTraceAnalyticsIntegrationEnabled(SortedSet<String> sortedSet, boolean z) {
        return traceAnalyticsIntegrationEnabled(sortedSet, z);
    }

    @Deprecated
    public static boolean traceAnalyticsIntegrationEnabled(SortedSet<String> sortedSet, boolean z) {
        boolean z2 = z;
        for (String str : sortedSet) {
            boolean booleanValue = getBooleanSettingFromEnvironment(str + ".analytics.enabled", Boolean.valueOf(z)).booleanValue();
            z2 = z ? z2 & booleanValue : z2 | booleanValue;
        }
        return z2;
    }

    @Deprecated
    public static String getSettingFromEnvironment(String str, String str2) {
        String propertyNameToSystemPropertyName = propertyNameToSystemPropertyName(str);
        String property = System.getProperties().getProperty(propertyNameToSystemPropertyName);
        if (property != null) {
            return property;
        }
        String str3 = System.getenv(propertyNameToEnvironmentVariableName(str));
        if (str3 != null) {
            return str3;
        }
        String property2 = propertiesFromConfigFile.getProperty(propertyNameToSystemPropertyName);
        return property2 != null ? property2 : str2;
    }

    @Deprecated
    private static Map<String, String> getMapSettingFromEnvironment(String str, String str2) {
        return parseMap(getSettingFromEnvironment(str, str2), propertyNameToSystemPropertyName(str));
    }

    @Deprecated
    private static List<String> getListSettingFromEnvironment(String str, String str2) {
        return parseList(getSettingFromEnvironment(str, str2));
    }

    @Deprecated
    public static Boolean getBooleanSettingFromEnvironment(String str, Boolean bool) {
        return (Boolean) getSettingFromEnvironmentWithLog(str, Boolean.class, bool);
    }

    @Deprecated
    public static Float getFloatSettingFromEnvironment(String str, Float f) {
        return (Float) getSettingFromEnvironmentWithLog(str, Float.class, f);
    }

    @Deprecated
    private static Double getDoubleSettingFromEnvironment(String str, Double d) {
        return (Double) getSettingFromEnvironmentWithLog(str, Double.class, d);
    }

    private static Integer getIntegerSettingFromEnvironment(String str, Integer num) {
        return (Integer) getSettingFromEnvironmentWithLog(str, Integer.class, num);
    }

    private static <T> T getSettingFromEnvironmentWithLog(String str, Class<T> cls, T t) {
        try {
            return valueOf(getSettingFromEnvironment(str, (String) null), cls, t);
        } catch (NumberFormatException unused) {
            return t;
        }
    }

    private static Set<PropagationStyle> getPropagationStyleSetSettingFromEnvironmentOrDefault(String str, String str2) {
        Set<PropagationStyle> convertStringSetToPropagationStyleSet = convertStringSetToPropagationStyleSet(parseStringIntoSetOfNonEmptyStrings(getSettingFromEnvironment(str, str2)));
        return convertStringSetToPropagationStyleSet.isEmpty() ? convertStringSetToPropagationStyleSet(parseStringIntoSetOfNonEmptyStrings(str2)) : convertStringSetToPropagationStyleSet;
    }

    private static Set<Integer> getIntegerRangeSettingFromEnvironment(String str, Set<Integer> set) {
        String settingFromEnvironment = getSettingFromEnvironment(str, (String) null);
        if (settingFromEnvironment == null) {
            return set;
        }
        try {
            return parseIntegerRangeSet(settingFromEnvironment, str);
        } catch (NumberFormatException unused) {
            return set;
        }
    }

    private static String propertyNameToEnvironmentVariableName(String str) {
        return ENV_REPLACEMENT.matcher(propertyNameToSystemPropertyName(str).toUpperCase(Locale.US)).replaceAll("_");
    }

    private static String propertyNameToSystemPropertyName(String str) {
        return PREFIX + str;
    }

    private static <T> T valueOf(String str, Class<T> cls, T t) {
        if (str == null || str.trim().isEmpty()) {
            return t;
        }
        try {
            return cls.getMethod("valueOf", new Class[]{String.class}).invoke((Object) null, new Object[]{str});
        } catch (NumberFormatException e) {
            throw e;
        } catch (IllegalAccessException | NoSuchMethodException e2) {
            throw new NumberFormatException(e2.toString());
        } catch (Throwable th) {
            throw new NumberFormatException(th.toString());
        }
    }

    private static Map<String, String> getPropertyMapValue(Properties properties, String str, Map<String, String> map) {
        String property = properties.getProperty(str);
        return (property == null || property.trim().isEmpty()) ? map : parseMap(property, str);
    }

    private static List<String> getPropertyListValue(Properties properties, String str, List<String> list) {
        String property = properties.getProperty(str);
        return (property == null || property.trim().isEmpty()) ? list : parseList(property);
    }

    private static Boolean getPropertyBooleanValue(Properties properties, String str, Boolean bool) {
        return (Boolean) valueOf(properties.getProperty(str), Boolean.class, bool);
    }

    private static Integer getPropertyIntegerValue(Properties properties, String str, Integer num) {
        return (Integer) valueOf(properties.getProperty(str), Integer.class, num);
    }

    private static Double getPropertyDoubleValue(Properties properties, String str, Double d) {
        return (Double) valueOf(properties.getProperty(str), Double.class, d);
    }

    private static Set<PropagationStyle> getPropagationStyleSetFromPropertyValue(Properties properties, String str) {
        String property = properties.getProperty(str);
        if (property == null) {
            return null;
        }
        Set<PropagationStyle> convertStringSetToPropagationStyleSet = convertStringSetToPropagationStyleSet(parseStringIntoSetOfNonEmptyStrings(property));
        if (!convertStringSetToPropagationStyleSet.isEmpty()) {
            return convertStringSetToPropagationStyleSet;
        }
        return null;
    }

    private static Set<Integer> getPropertyIntegerRangeValue(Properties properties, String str, Set<Integer> set) {
        String property = properties.getProperty(str);
        if (property == null) {
            return set;
        }
        try {
            return parseIntegerRangeSet(property, str);
        } catch (NumberFormatException unused) {
            return set;
        }
    }

    private static Map<String, String> parseMap(String str, String str2) {
        if (str == null || str.trim().isEmpty()) {
            return Collections.emptyMap();
        }
        if (!str.matches("(([^,:]+:[^,:]*,)*([^,:]+:[^,:]*),?)?")) {
            return Collections.emptyMap();
        }
        String[] split = str.split(WebViewLogEventConsumer.DDTAGS_SEPARATOR, -1);
        Map<String, String> newHashMap = newHashMap(split.length);
        for (String split2 : split) {
            String[] split3 = split2.split(":", -1);
            if (split3.length == 2) {
                String trim = split3[0].trim();
                String trim2 = split3[1].trim();
                if (trim2.length() > 0) {
                    newHashMap.put(trim, trim2);
                }
            }
        }
        return Collections.unmodifiableMap(newHashMap);
    }

    private static Set<Integer> parseIntegerRangeSet(String str, String str2) throws NumberFormatException {
        String replaceAll = str.replaceAll("\\s", "");
        if (replaceAll.matches("\\d{3}(?:-\\d{3})?(?:,\\d{3}(?:-\\d{3})?)*")) {
            String[] split = replaceAll.split(WebViewLogEventConsumer.DDTAGS_SEPARATOR, -1);
            HashSet hashSet = new HashSet();
            for (String split2 : split) {
                String[] split3 = split2.split("-", -1);
                if (split3.length == 1) {
                    hashSet.add(Integer.valueOf(Integer.parseInt(split3[0])));
                } else if (split3.length == 2) {
                    int parseInt = Integer.parseInt(split3[0]);
                    int parseInt2 = Integer.parseInt(split3[1]);
                    int max = Math.max(parseInt, parseInt2);
                    for (int min = Math.min(parseInt, parseInt2); min <= max; min++) {
                        hashSet.add(Integer.valueOf(min));
                    }
                }
            }
            return Collections.unmodifiableSet(hashSet);
        }
        throw new NumberFormatException();
    }

    private static Map<String, String> newHashMap(int i) {
        return new HashMap(i + 1, 1.0f);
    }

    private static Map<String, String> getMapWithPropertiesDefinedByEnvironment(Map<String, String> map, String... strArr) {
        HashMap hashMap = new HashMap(map);
        for (String str : strArr) {
            String settingFromEnvironment = getSettingFromEnvironment(str, (String) null);
            if (settingFromEnvironment != null) {
                hashMap.put(str, settingFromEnvironment);
            }
        }
        return Collections.unmodifiableMap(hashMap);
    }

    private static Map<String, String> overwriteKeysFromProperties(Map<String, String> map, Properties properties, String... strArr) {
        HashMap hashMap = new HashMap(map);
        for (String str : strArr) {
            String property = properties.getProperty(str, (String) null);
            if (property != null) {
                hashMap.put(str, property);
            }
        }
        return Collections.unmodifiableMap(hashMap);
    }

    private static List<String> parseList(String str) {
        if (str == null || str.trim().isEmpty()) {
            return Collections.emptyList();
        }
        String[] split = str.split(WebViewLogEventConsumer.DDTAGS_SEPARATOR, -1);
        for (int i = 0; i < split.length; i++) {
            split[i] = split[i].trim();
        }
        return Collections.unmodifiableList(Arrays.asList(split));
    }

    private static Set<String> parseStringIntoSetOfNonEmptyStrings(String str) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (String str2 : str.split(SPLIT_BY_SPACE_OR_COMMA_REGEX)) {
            if (!str2.isEmpty()) {
                linkedHashSet.add(str2);
            }
        }
        return Collections.unmodifiableSet(linkedHashSet);
    }

    private static Set<PropagationStyle> convertStringSetToPropagationStyleSet(Set<String> set) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (String upperCase : set) {
            try {
                linkedHashSet.add(PropagationStyle.valueOf(upperCase.toUpperCase(Locale.US)));
            } catch (IllegalArgumentException unused) {
            }
        }
        return Collections.unmodifiableSet(linkedHashSet);
    }

    private static Properties loadConfigurationFile() {
        FileReader fileReader;
        Properties properties = new Properties();
        String property = System.getProperty(propertyNameToSystemPropertyName(CONFIGURATION_FILE));
        if (property == null) {
            property = System.getenv(propertyNameToEnvironmentVariableName(CONFIGURATION_FILE));
        }
        if (property == null) {
            return properties;
        }
        File file = new File(property.replaceFirst("^~", System.getProperty("user.home")));
        if (!file.exists()) {
            return properties;
        }
        try {
            fileReader = new FileReader(file);
            properties.load(fileReader);
            fileReader.close();
        } catch (FileNotFoundException | IOException unused) {
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        return properties;
        throw th;
    }

    private static String getHostName() {
        String str;
        BufferedReader bufferedReader;
        if (System.getProperty("os.name").startsWith("Windows")) {
            str = System.getenv("COMPUTERNAME");
        } else {
            str = System.getenv("HOSTNAME");
        }
        if (str != null && !str.isEmpty()) {
            return str.trim();
        }
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("hostname").getInputStream()));
            str = bufferedReader.readLine();
            bufferedReader.close();
        } catch (Exception unused) {
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        if (str != null && !str.isEmpty()) {
            return str.trim();
        }
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException unused2) {
            return null;
        }
        throw th;
    }

    public static Config get() {
        return INSTANCE;
    }

    public static Config get(Properties properties) {
        if (properties == null || properties.isEmpty()) {
            return INSTANCE;
        }
        return new Config(properties, INSTANCE);
    }

    public String getRuntimeId() {
        return this.runtimeId;
    }

    public String getSite() {
        return this.site;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public boolean isTraceEnabled() {
        return this.traceEnabled;
    }

    public boolean isIntegrationsEnabled() {
        return this.integrationsEnabled;
    }

    public String getWriterType() {
        return this.writerType;
    }

    public String getAgentHost() {
        return this.agentHost;
    }

    public int getAgentPort() {
        return this.agentPort;
    }

    public String getAgentUnixDomainSocket() {
        return this.agentUnixDomainSocket;
    }

    public boolean isPrioritySamplingEnabled() {
        return this.prioritySamplingEnabled;
    }

    public boolean isTraceResolverEnabled() {
        return this.traceResolverEnabled;
    }

    public Map<String, String> getServiceMapping() {
        return this.serviceMapping;
    }

    public List<String> getExcludedClasses() {
        return this.excludedClasses;
    }

    public Map<String, String> getHeaderTags() {
        return this.headerTags;
    }

    public Set<Integer> getHttpServerErrorStatuses() {
        return this.httpServerErrorStatuses;
    }

    public Set<Integer> getHttpClientErrorStatuses() {
        return this.httpClientErrorStatuses;
    }

    public boolean isHttpServerTagQueryString() {
        return this.httpServerTagQueryString;
    }

    public boolean isHttpClientTagQueryString() {
        return this.httpClientTagQueryString;
    }

    public boolean isHttpClientSplitByDomain() {
        return this.httpClientSplitByDomain;
    }

    public boolean isDbClientSplitByInstance() {
        return this.dbClientSplitByInstance;
    }

    public Set<String> getSplitByTags() {
        return this.splitByTags;
    }

    public Integer getScopeDepthLimit() {
        return this.scopeDepthLimit;
    }

    public Integer getPartialFlushMinSpans() {
        return this.partialFlushMinSpans;
    }

    public boolean isRuntimeContextFieldInjection() {
        return this.runtimeContextFieldInjection;
    }

    public Set<PropagationStyle> getPropagationStylesToExtract() {
        return this.propagationStylesToExtract;
    }

    public Set<PropagationStyle> getPropagationStylesToInject() {
        return this.propagationStylesToInject;
    }

    public boolean isJmxFetchEnabled() {
        return this.jmxFetchEnabled;
    }

    public String getJmxFetchConfigDir() {
        return this.jmxFetchConfigDir;
    }

    public List<String> getJmxFetchConfigs() {
        return this.jmxFetchConfigs;
    }

    public List<String> getJmxFetchMetricsConfigs() {
        return this.jmxFetchMetricsConfigs;
    }

    public Integer getJmxFetchCheckPeriod() {
        return this.jmxFetchCheckPeriod;
    }

    public Integer getJmxFetchRefreshBeansPeriod() {
        return this.jmxFetchRefreshBeansPeriod;
    }

    public String getJmxFetchStatsdHost() {
        return this.jmxFetchStatsdHost;
    }

    public Integer getJmxFetchStatsdPort() {
        return this.jmxFetchStatsdPort;
    }

    public boolean isHealthMetricsEnabled() {
        return this.healthMetricsEnabled;
    }

    public String getHealthMetricsStatsdHost() {
        return this.healthMetricsStatsdHost;
    }

    public Integer getHealthMetricsStatsdPort() {
        return this.healthMetricsStatsdPort;
    }

    public boolean isLogsInjectionEnabled() {
        return this.logsInjectionEnabled;
    }

    public boolean isReportHostName() {
        return this.reportHostName;
    }

    public String getTraceAnnotations() {
        return this.traceAnnotations;
    }

    public String getTraceMethods() {
        return this.traceMethods;
    }

    public boolean isTraceExecutorsAll() {
        return this.traceExecutorsAll;
    }

    public List<String> getTraceExecutors() {
        return this.traceExecutors;
    }

    public boolean isTraceAnalyticsEnabled() {
        return this.traceAnalyticsEnabled;
    }

    public Map<String, String> getTraceSamplingServiceRules() {
        return this.traceSamplingServiceRules;
    }

    public Map<String, String> getTraceSamplingOperationRules() {
        return this.traceSamplingOperationRules;
    }

    public Double getTraceSampleRate() {
        return this.traceSampleRate;
    }

    public Double getTraceRateLimit() {
        return this.traceRateLimit;
    }

    public boolean isProfilingEnabled() {
        return this.profilingEnabled;
    }

    public int getProfilingStartDelay() {
        return this.profilingStartDelay;
    }

    public boolean isProfilingStartForceFirst() {
        return this.profilingStartForceFirst;
    }

    public int getProfilingUploadPeriod() {
        return this.profilingUploadPeriod;
    }

    public String getProfilingTemplateOverrideFile() {
        return this.profilingTemplateOverrideFile;
    }

    public int getProfilingUploadTimeout() {
        return this.profilingUploadTimeout;
    }

    public String getProfilingUploadCompression() {
        return this.profilingUploadCompression;
    }

    public String getProfilingProxyHost() {
        return this.profilingProxyHost;
    }

    public int getProfilingProxyPort() {
        return this.profilingProxyPort;
    }

    public String getProfilingProxyUsername() {
        return this.profilingProxyUsername;
    }

    public String getProfilingProxyPassword() {
        return this.profilingProxyPassword;
    }

    public int getProfilingExceptionSampleLimit() {
        return this.profilingExceptionSampleLimit;
    }

    public int getProfilingExceptionHistogramTopItems() {
        return this.profilingExceptionHistogramTopItems;
    }

    public int getProfilingExceptionHistogramMaxCollectionSize() {
        return this.profilingExceptionHistogramMaxCollectionSize;
    }

    public String toString() {
        return "Config{runtimeId='" + this.runtimeId + '\'' + ", site='" + this.site + '\'' + ", serviceName='" + this.serviceName + '\'' + ", traceEnabled=" + this.traceEnabled + ", integrationsEnabled=" + this.integrationsEnabled + ", writerType='" + this.writerType + '\'' + ", agentHost='" + this.agentHost + '\'' + ", agentPort=" + this.agentPort + ", agentUnixDomainSocket='" + this.agentUnixDomainSocket + '\'' + ", prioritySamplingEnabled=" + this.prioritySamplingEnabled + ", traceResolverEnabled=" + this.traceResolverEnabled + ", serviceMapping=" + this.serviceMapping + ", tags=" + this.tags + ", spanTags=" + this.spanTags + ", jmxTags=" + this.jmxTags + ", excludedClasses=" + this.excludedClasses + ", headerTags=" + this.headerTags + ", httpServerErrorStatuses=" + this.httpServerErrorStatuses + ", httpClientErrorStatuses=" + this.httpClientErrorStatuses + ", httpServerTagQueryString=" + this.httpServerTagQueryString + ", httpClientTagQueryString=" + this.httpClientTagQueryString + ", httpClientSplitByDomain=" + this.httpClientSplitByDomain + ", dbClientSplitByInstance=" + this.dbClientSplitByInstance + ", splitByTags=" + this.splitByTags + ", scopeDepthLimit=" + this.scopeDepthLimit + ", partialFlushMinSpans=" + this.partialFlushMinSpans + ", runtimeContextFieldInjection=" + this.runtimeContextFieldInjection + ", propagationStylesToExtract=" + this.propagationStylesToExtract + ", propagationStylesToInject=" + this.propagationStylesToInject + ", jmxFetchEnabled=" + this.jmxFetchEnabled + ", jmxFetchConfigDir='" + this.jmxFetchConfigDir + '\'' + ", jmxFetchConfigs=" + this.jmxFetchConfigs + ", jmxFetchMetricsConfigs=" + this.jmxFetchMetricsConfigs + ", jmxFetchCheckPeriod=" + this.jmxFetchCheckPeriod + ", jmxFetchRefreshBeansPeriod=" + this.jmxFetchRefreshBeansPeriod + ", jmxFetchStatsdHost='" + this.jmxFetchStatsdHost + '\'' + ", jmxFetchStatsdPort=" + this.jmxFetchStatsdPort + ", healthMetricsEnabled=" + this.healthMetricsEnabled + ", healthMetricsStatsdHost='" + this.healthMetricsStatsdHost + '\'' + ", healthMetricsStatsdPort=" + this.healthMetricsStatsdPort + ", logsInjectionEnabled=" + this.logsInjectionEnabled + ", reportHostName=" + this.reportHostName + ", traceAnnotations='" + this.traceAnnotations + '\'' + ", traceMethods='" + this.traceMethods + '\'' + ", traceExecutorsAll=" + this.traceExecutorsAll + ", traceExecutors=" + this.traceExecutors + ", traceAnalyticsEnabled=" + this.traceAnalyticsEnabled + ", traceSamplingServiceRules=" + this.traceSamplingServiceRules + ", traceSamplingOperationRules=" + this.traceSamplingOperationRules + ", traceSampleRate=" + this.traceSampleRate + ", traceRateLimit=" + this.traceRateLimit + ", profilingEnabled=" + this.profilingEnabled + ", profilingUrl='" + this.profilingUrl + '\'' + ", profilingTags=" + this.profilingTags + ", profilingStartDelay=" + this.profilingStartDelay + ", profilingStartForceFirst=" + this.profilingStartForceFirst + ", profilingUploadPeriod=" + this.profilingUploadPeriod + ", profilingTemplateOverrideFile='" + this.profilingTemplateOverrideFile + '\'' + ", profilingUploadTimeout=" + this.profilingUploadTimeout + ", profilingUploadCompression='" + this.profilingUploadCompression + '\'' + ", profilingProxyHost='" + this.profilingProxyHost + '\'' + ", profilingProxyPort=" + this.profilingProxyPort + ", profilingProxyUsername='" + this.profilingProxyUsername + '\'' + ", profilingProxyPassword='" + this.profilingProxyPassword + '\'' + ", profilingExceptionSampleLimit=" + this.profilingExceptionSampleLimit + ", profilingExceptionHistogramTopItems=" + this.profilingExceptionHistogramTopItems + ", profilingExceptionHistogramMaxCollectionSize=" + this.profilingExceptionHistogramMaxCollectionSize + '}';
    }
}
