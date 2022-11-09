package com.example.eventserviceapi.service;

import com.example.eventservicedto.dto.Event;
import java.util.Set;

public interface EventService {
    void createEvent(Event event);

    void updateEvent(Event event);

    Event getEvent(Long eventId);

    void deleteEvent(Long eventId);

    Set<Event> getAllEvents(int page);

    Set<Event> getAllEventsByTitle(String eventTitle);
}
