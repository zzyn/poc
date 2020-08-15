package com.baidu.fsg.uid.impl;

import com.baidu.fsg.uid.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.concurrent.TimeUnit;

@ConfigurationProperties(prefix = "uid")
public class UidProperties {

    private int timeBits = 28;

    private int workerBits = 22;

    private int seqBits = 13;

    private String epochStr = "2019-01-01";

    private long epochSeconds = TimeUnit.MILLISECONDS.toSeconds(1550592000000L);

    private boolean enableBackward = true;

    private long maxBackwardSeconds = 1L;

    public int getTimeBits() {
        return timeBits;
    }

    public void setTimeBits(int timeBits) {
        if (timeBits > 0) {
            this.timeBits = timeBits;
        }
    }

    public int getWorkerBits() {
        return workerBits;
    }

    public void setWorkerBits(int workerBits) {
        if (workerBits > 0) {
            this.workerBits = workerBits;
        }
    }

    public int getSeqBits() {
        return seqBits;
    }

    public void setSeqBits(int seqBits) {
        if (seqBits > 0) {
            this.seqBits = seqBits;
        }
    }

    public String getEpochStr() {
        return epochStr;
    }

    public void setEpochStr(String epochStr) {
        if (StringUtils.isNotBlank(epochStr)) {
            this.epochStr = epochStr;
            this.epochSeconds = TimeUnit.MILLISECONDS.toSeconds(DateUtils.parseByDayPattern(epochStr).getTime());
        }
    }

    public long getEpochSeconds() {
        return epochSeconds;
    }

    public boolean isEnableBackward() {
        return enableBackward;
    }

    public void setEnableBackward(boolean enableBackward) {
        this.enableBackward = enableBackward;
    }

    public long getMaxBackwardSeconds() {
        return maxBackwardSeconds;
    }

    public void setMaxBackwardSeconds(long maxBackwardSeconds) {
        this.maxBackwardSeconds = maxBackwardSeconds;
    }
}