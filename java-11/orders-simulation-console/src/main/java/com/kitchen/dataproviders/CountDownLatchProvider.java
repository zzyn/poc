package com.kitchen.dataproviders;

import java.util.concurrent.CountDownLatch;

public interface CountDownLatchProvider {

    CountDownLatch getLatch();
}
