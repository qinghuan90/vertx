package org.example.enums;/**
 * @fileName ConfigKey
 * @author pengfei
 * @date 2024/6/7
 * @description ConfigKey
 */

/**
 * @author pengfei
 * @date 2024/6/7
 * @description ConfigKey
 */
public enum ConfigKey {
    ELECT_PORT("elect.port"),
    SERVER_ID("server.id"),
    RULES("rules"),
    SERVER_PORT("server.port");

    private String key;

    ConfigKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
