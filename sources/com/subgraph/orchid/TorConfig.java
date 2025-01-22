package com.subgraph.orchid;

import com.subgraph.orchid.circuits.p002hs.HSDescriptorCookie;
import com.subgraph.orchid.config.TorConfigBridgeLine;
import com.subgraph.orchid.data.HexDigest;
import com.subgraph.orchid.data.IPv4Address;
import java.io.File;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/TorConfig.class */
public interface TorConfig {

    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/TorConfig$AutoBoolValue.class */
    public enum AutoBoolValue {
        TRUE,
        FALSE,
        AUTO
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/TorConfig$ConfigVar.class */
    public @interface ConfigVar {
        String defaultValue() default "";

        ConfigVarType type();
    }

    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/TorConfig$ConfigVarType.class */
    public enum ConfigVarType {
        INTEGER,
        STRING,
        HS_AUTH,
        BOOLEAN,
        INTERVAL,
        PORTLIST,
        STRINGLIST,
        PATH,
        AUTOBOOL,
        BRIDGE_LINE
    }

    void addBridge(IPv4Address iPv4Address, int i);

    void addBridge(IPv4Address iPv4Address, int i, HexDigest hexDigest);

    void addHidServAuth(String str, String str2);

    @ConfigVar(type = ConfigVarType.BRIDGE_LINE)
    List<TorConfigBridgeLine> getBridges();

    @ConfigVar(defaultValue = "60 seconds", type = ConfigVarType.INTERVAL)
    long getCircuitBuildTimeout();

    @ConfigVar(defaultValue = "1 hour", type = ConfigVarType.INTERVAL)
    long getCircuitIdleTimeout();

    @ConfigVar(defaultValue = "0", type = ConfigVarType.INTERVAL)
    long getCircuitStreamTimeout();

    @ConfigVar(defaultValue = "true", type = ConfigVarType.BOOLEAN)
    boolean getClientRejectInternalAddress();

    @ConfigVar(defaultValue = "~/.orchid", type = ConfigVarType.PATH)
    File getDataDirectory();

    @ConfigVar(defaultValue = "true", type = ConfigVarType.BOOLEAN)
    boolean getEnforceDistinctSubnets();

    @ConfigVar(type = ConfigVarType.STRINGLIST)
    List<String> getEntryNodes();

    @ConfigVar(type = ConfigVarType.STRINGLIST)
    List<String> getExcludeExitNodes();

    @ConfigVar(type = ConfigVarType.STRINGLIST)
    List<String> getExcludeNodes();

    @ConfigVar(type = ConfigVarType.STRINGLIST)
    List<String> getExitNodes();

    @ConfigVar(defaultValue = "false", type = ConfigVarType.BOOLEAN)
    boolean getFascistFirewall();

    @ConfigVar(defaultValue = "80,443", type = ConfigVarType.PORTLIST)
    List<Integer> getFirewallPorts();

    @ConfigVar(defaultValue = "true", type = ConfigVarType.BOOLEAN)
    boolean getHandshakeV2Enabled();

    @ConfigVar(defaultValue = "true", type = ConfigVarType.BOOLEAN)
    boolean getHandshakeV3Enabled();

    @ConfigVar(type = ConfigVarType.HS_AUTH)
    HSDescriptorCookie getHidServAuth(String str);

    @ConfigVar(defaultValue = "21,22,706,1863,5050,5190,5222,5223,6523,6667,6697,8300", type = ConfigVarType.PORTLIST)
    List<Integer> getLongLivedPorts();

    @ConfigVar(defaultValue = "10 minutes", type = ConfigVarType.INTERVAL)
    long getMaxCircuitDirtiness();

    @ConfigVar(defaultValue = "32", type = ConfigVarType.INTEGER)
    int getMaxClientCircuitsPending();

    @ConfigVar(defaultValue = "30 seconds", type = ConfigVarType.INTERVAL)
    long getNewCircuitPeriod();

    @ConfigVar(defaultValue = "3", type = ConfigVarType.INTEGER)
    int getNumEntryGuards();

    @ConfigVar(defaultValue = "true", type = ConfigVarType.BOOLEAN)
    boolean getSafeLogging();

    @ConfigVar(defaultValue = "false", type = ConfigVarType.BOOLEAN)
    boolean getSafeSocks();

    @ConfigVar(defaultValue = "2 minutes", type = ConfigVarType.INTERVAL)
    long getSocksTimeout();

    @ConfigVar(defaultValue = "false", type = ConfigVarType.BOOLEAN)
    boolean getStrictNodes();

    @ConfigVar(defaultValue = "false", type = ConfigVarType.BOOLEAN)
    boolean getUseBridges();

    @ConfigVar(defaultValue = "true", type = ConfigVarType.BOOLEAN)
    boolean getUseEntryGuards();

    @ConfigVar(defaultValue = "auto", type = ConfigVarType.AUTOBOOL)
    AutoBoolValue getUseMicrodescriptors();

    @ConfigVar(defaultValue = "auto", type = ConfigVarType.AUTOBOOL)
    AutoBoolValue getUseNTorHandshake();

    @ConfigVar(defaultValue = "true", type = ConfigVarType.BOOLEAN)
    boolean getWarnUnsafeSocks();

    void setCircuitBuildTimeout(long j, TimeUnit timeUnit);

    void setCircuitIdleTimeout(long j, TimeUnit timeUnit);

    void setCircuitStreamTimeout(long j, TimeUnit timeUnit);

    void setClientRejectInternalAddress(boolean z);

    void setDataDirectory(File file);

    void setEnforceDistinctSubnets(boolean z);

    void setEntryNodes(List<String> list);

    void setExcludeExitNodes(List<String> list);

    void setExcludeNodes(List<String> list);

    void setExitNodes(List<String> list);

    void setFascistFirewall(boolean z);

    void setFirewallPorts(List<Integer> list);

    void setHandshakeV2Enabled(boolean z);

    void setHandshakeV3Enabled(boolean z);

    void setLongLivedPorts(List<Integer> list);

    void setMaxCircuitDirtiness(long j, TimeUnit timeUnit);

    void setMaxClientCircuitsPending(int i);

    void setNewCircuitPeriod(long j, TimeUnit timeUnit);

    void setNumEntryGuards(int i);

    void setSafeLogging(boolean z);

    void setSafeSocks(boolean z);

    void setSocksTimeout(long j);

    void setStrictNodes(boolean z);

    void setUseBridges(boolean z);

    void setUseEntryGuards(boolean z);

    void setUseMicrodescriptors(AutoBoolValue autoBoolValue);

    void setUseNTorHandshake(AutoBoolValue autoBoolValue);

    void setWarnUnsafeSocks(boolean z);
}
