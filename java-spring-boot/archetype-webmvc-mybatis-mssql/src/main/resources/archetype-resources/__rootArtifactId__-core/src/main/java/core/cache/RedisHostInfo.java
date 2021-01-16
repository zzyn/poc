package ${groupId}.core.cache;

public class RedisHostInfo {

    private String host;
    private Integer port;

    public String getHost() {
        return host;
    }

    public RedisHostInfo setHost(String host) {
        this.host = host;
        return this;
    }

    public Integer getPort() {
        return port;
    }

    public RedisHostInfo setPort(Integer port) {
        this.port = port;
        return this;
    }
}
