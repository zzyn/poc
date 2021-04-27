package com.kitchen.dataproviders.impl;

import com.kitchen.dataproviders.EventStorageProvider;
import com.kitchen.dataproviders.dto.Event;
import com.kitchen.utils.MockUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Slf4j
public class EventStorageProviderImpl implements EventStorageProvider {

    private final String EVENT_FILE = "data/events.json";

    @Override
    public synchronized void store(Event event) {
        event.setId(UUID.randomUUID());
        log.info(event.toString());
        List<Event> events = getALl();
        events.add(event);
        MockUtils.writeObjectAsFile(EVENT_FILE, events);
    }

    @Override
    public List<Event> getALl() {
        List<Event> events = MockUtils.mockListFromFile(EVENT_FILE, Event.class);
        return events;
    }

    @Override
    public void clear() {
        MockUtils.writeListAsFile(EVENT_FILE, Collections.EMPTY_LIST);
    }
}
