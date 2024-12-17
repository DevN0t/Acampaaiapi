package com.devnot.CampistsIbod.modules.event.infra.repository;

import com.devnot.CampistsIbod.modules.event.infra.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    @Query("SELECT event FROM Event event WHERE event.branchId = :branchId")
    List<Event> findAllByBranch(Integer branchId);

    @Query("SELECT event FROM Event event WHERE event.id = :id AND event.branchId = :branchId")
    Event findByIdAndBranch(Integer id, Integer branchId);
}
