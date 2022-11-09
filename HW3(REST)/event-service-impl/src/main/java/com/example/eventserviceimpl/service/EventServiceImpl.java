package com.example.eventserviceimpl.service;

import com.example.eventserviceapi.service.EventService;
import com.example.eventservicedto.dto.Event;
import com.example.eventserviceimpl.mapper.EventMapper;
import com.example.eventserviceimpl.repository.EventRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository repository;

    public EventServiceImpl(EventRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createEvent(Event event) {
        repository.save(EventMapper.eventToEventEntity(event));
    }

    @Override
    public void updateEvent(Event event) {
        repository.save(EventMapper.eventToEventEntity(event));
    }

    @Override
    public Event getEvent(Long eventId) {
        return EventMapper.eventEntityToEvent(repository.findById(eventId).orElseThrow(() -> new RuntimeException("Event with id = " + eventId + " not found")));
    }

    @Override
    public void deleteEvent(Long eventId) {
        repository.deleteById(eventId);
    }

    @Override
    public Set<Event> getAllEvents(int page) {
        return repository.findAll(PageRequest.of(page, 10)).stream()
                .map(EventMapper::eventEntityToEvent)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getAllEventsByTitle(String eventTitle) {
        return repository.getByTitle(eventTitle)
                .stream()
                .map(EventMapper::eventEntityToEvent)
                .collect(Collectors.toSet());
    }
}
