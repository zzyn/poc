package com.webflux.cassandra.demo.core;

public class CoreConstants {

    public static final String ID_TOKEN_HEADER = "X-EF-TOKEN";

    public static final String ID_TOKEN_DESC = "Token";

    public static final String ACCESS_TOKEN_HEADER = "X-EF-ACCESS";

    public static final String DEFAULT_API_ROUTE = "/api/";

    public static final String CLIENT_IP_HEADER = "X-CLIENT-IP";

    public static final String CLIENT_IP_DESC = "Client Ip Address";

    public static final String REDIS_SVC_PREFIX = "plan";

    public static final String CONSISTENT_LEVEL_HEADER = "X-CONSISTENT-LEVEL";

    public static final String CONSISTENT_LEVEL_DESC = "consistency level of transaction.\n" +
            "  <div class=\"enum\">" +
            "  <span> consistency level enums : </span>" +
            "  <li>ANY</li>" +
            "  <li>ONE</li>" +
            "  <li>TWO</li>" +
            "  <li>THREE</li>" +
            "  <li>QUORUM</li>" +
            "  <li>ALL</li>" +
            "  <li>LOCAL_QUORUM</li>" +
            "  <li>EACH_QUORUM</li>" +
            "  <li>SERIAL</li>" +
            "  <li>LOCAL_SERIAL</li>" +
            "  <li>LOCAL_ONE</li>" +
            "  </div>";
}
