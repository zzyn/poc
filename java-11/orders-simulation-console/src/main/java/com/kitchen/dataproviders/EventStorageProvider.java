package com.kitchen.dataproviders;

import com.kitchen.dataproviders.dto.Event;

import java.util.List;

public interface EventStorageProvider {

    void store(Event event);

    List<Event> getALl();

    void clear();
}
