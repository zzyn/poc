package com.webflux.cassandra.demo.core.utility;

import com.datastax.driver.core.ConsistencyLevel;

import com.webflux.cassandra.demo.core.validator.ValidatorUtil;
import com.webflux.cassandra.demo.core.validator.businessenum.CustomEnum;
import com.webflux.cassandra.demo.domain.Constants;
import com.webflux.cassandra.demo.domain.model.PlanQueryModel;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.cassandra.core.InsertOptions;
import org.springframework.data.cassandra.core.UpdateOptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.webflux.cassandra.demo.domain.Constants.PLAN_MAX_LIMIT;
import static com.webflux.cassandra.demo.domain.Constants.PLAN_MAX_PAGE;


/**
 * PlanUtility class
 *
 * @author ef edtech
 */
public  class PlanUtility {
    private PlanUtility(){}
    public static final InsertOptions INSERT_QUROUM= InsertOptions.builder().consistencyLevel(ConsistencyLevel.QUORUM).build();
    public static final UpdateOptions UPDATE_QUROUM= UpdateOptions.builder().consistencyLevel(ConsistencyLevel.QUORUM).ifExists(true).build();

    public  static  boolean validateTimestampFormat(String originalDate)
    {
        boolean isValid=hasStringValue(originalDate);
        if(isValid) {
            Pattern pattern=Pattern.compile(Constants.CASSANDRA_TIMESTAMP_FORMAT_REGEX);
            Matcher matcher = pattern.matcher(originalDate);
            return matcher.matches();
        }
        return false;

    }
    public static boolean validateTimeuuidFormat(String originalString) {
        if (hasStringValue(originalString)) {
            Pattern pattern = Pattern.compile(Constants.TIMEUUID_FORMAT_REGEX);
            Matcher matcher = pattern.matcher(originalString);
            return matcher.matches();
        }
        return false;
    }
    public static boolean validateTimeStamp(Timestamp timestamp)
    {
        return timestamp!=null;
    }
    public static boolean hasStringValue(String originalString)
    {
        return originalString != null && !"".equals(originalString);
    }

    public static boolean hasUuidValue(UUID originalValue)
    {
        return originalValue != null;
    }

    public static boolean hasIntValue(int originalInt)
    {
        return originalInt >= 0;
    }

    public static String toDateTimeString(Timestamp timestamp) {
        if (validateTimeStamp(timestamp)) {
            return  timestamp.toString();
        }
        return "";
    }
    public static Timestamp toTimeStamp(String timestamp) {
        if (!validateTimestampFormat(timestamp)) {
            return null;
        }
        return Timestamp.valueOf(timestamp);
    }
    public static String toDateTimeString(long timestamp)
    {
        if (timestamp>0) {
            return toDateTimeString(new Timestamp(timestamp));
        }
        return "";
    }

    public static UUID toUuid(String systemKey) {
        if (systemKey == null || "".equals(systemKey) || !validateTimeuuidFormat(systemKey)) {
            return null;
        }
        return UUID.fromString(systemKey);
    }
    public static String generateRandomString(int length)
    {
      return   RandomStringUtils.randomAlphanumeric(length).toUpperCase();
    }

    public static boolean isValid(PlanQueryModel query) {
        if (query == null) {
            return false;
        }
        Map<String, StringBuffer> validationResult = ValidatorUtil.validate(query);
        return validationResult == null || validationResult.isEmpty();
    }

    public static int getLimit(Integer originalLimit) {
        if (originalLimit==null) {
            return PLAN_MAX_LIMIT;
        }
        if (originalLimit > PLAN_MAX_LIMIT || originalLimit <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return originalLimit;
    }
    public static void setDefaultTimeZone()
    {
        System.setProperty("user.timezone", "UTC");
        TimeZone.setDefault(null);
    }

    public static int getPage(Integer originalPage) {
        if (originalPage==null) {
            return PLAN_MAX_PAGE;
        }
        if (originalPage <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return originalPage;
    }
    public static <E extends Enum & CustomEnum> boolean isInList(int code, Class<E> clazz) {

        if (clazz == null) {
            return false;
        }
        E[] values = clazz.getEnumConstants();
        if (values == null) {
            return false;
        }
        for (E enumvalue : values) {
            if (enumvalue.toCode() == code) {
                return true;
            }
        }
        return false;
    }
}
