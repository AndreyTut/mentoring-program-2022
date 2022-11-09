package com.example.eventservicerest.controller;

import com.example.eventserviceapi.service.EventService;
import com.example.eventservicedto.dto.Event;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/event")
public class EventController {

    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    @Operation(summary = "Get an event by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the event by id",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content)})
    @GetMapping("/{eventId}")
    public Event getById(@PathVariable Long eventId) {
        return service.getEvent(eventId);
    }

    @Operation(summary = "Get an event by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the event by title",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content)})
    @GetMapping("/title/{title}")
    public Set<Event> getByTitle(@PathVariable(name = "title") String eventTitle) {
        return service.getAllEventsByTitle(eventTitle);
    }

    @Operation(summary = "Get events list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the events",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content)})
    @GetMapping
    public Set<Event> getAll() {
        return service.getAllEvents(0);
    }

    @Operation(summary = "Delete an event")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event deleted",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content)})
    @DeleteMapping("/{eventId}")
    public void delete(@PathVariable Long eventId) {
        service.deleteEvent(eventId);
    }

    @Operation(summary = "Create a new event")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event created",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content)})
    @PostMapping
    public void create(@RequestBody Event event) {
        service.createEvent(event);
    }

    @Operation(summary = "Update event")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event updated",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content)})
    @PutMapping
    public void update(@RequestBody Event event) {
        service.updateEvent(event);
    }
}
