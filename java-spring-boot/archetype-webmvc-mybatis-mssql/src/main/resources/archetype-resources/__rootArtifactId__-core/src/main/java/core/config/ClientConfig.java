package ${groupId}.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ClientConfig {

    @Value("${client.connection-timeout}")
    private Integer connectionTimeout;

    public Integer getConnectionTimeout() {
        return connectionTimeout;
    }

    public ClientConfig setConnectionTimeout(Integer connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
        return this;
    }
}
