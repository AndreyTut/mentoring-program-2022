package com.example.eventserviceimpl.mapper;

import com.example.eventservicedto.dto.Event;
import com.example.eventserviceimpl.Entity.EventEntity;

public class EventMapper {

    public static Event eventEntityToEvent(EventEntity source) {
        return Event.builder()
                .eventId(source.getEventId())
                .eventType(source.getEventType())
                .dateTime(source.getDateTime())
                .title(source.getTitle())
                .place(source.getPlace())
                .speaker(source.getSpeaker())
                .build();
    }

    public static EventEntity eventToEventEntity(Event source) {
        return EventEntity.builder()
                .eventId(source.getEventId())
                .eventType(source.getEventType())
                .dateTime(source.getDateTime())
                .title(source.getTitle())
                .place(source.getPlace())
                .speaker(source.getSpeaker())
                .build();
    }
}
