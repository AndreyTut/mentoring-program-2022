package com.example.eventserviceimpl.repository;

import com.example.eventserviceimpl.Entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {

    @Query("select e from EventEntity e" +
            " where e.title = :title")
    Set<EventEntity> getByTitle(@Param("title") String title);
}
