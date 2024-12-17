package com.devnot.CampistsIbod.modules.event.service;

import com.devnot.CampistsIbod.infra.security.repository.UserRepository;
import com.devnot.CampistsIbod.infra.security.service.JwtService;
import com.devnot.CampistsIbod.modules.event.dto.EventCreateDTO;
import com.devnot.CampistsIbod.modules.event.infra.model.Event;
import com.devnot.CampistsIbod.modules.event.infra.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    private final UserRepository userRepository;

    private final JwtService jwtService;

    public EventService(EventRepository eventRepository, UserRepository userRepository, JwtService jwtService) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }


    public ResponseEntity<List<Event>> findAllByBranch(String jwt) {
        try{
            String username = jwtService.getSubjectFromTokenWithoutValidation(jwt);
            Integer branchId = userRepository.findByUsername(username).get().getBranchId();
            return ResponseEntity.ok(eventRepository.findAllByBranch(branchId));

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<Event> findById(String jwt, Integer id) {
        try {
            String username = jwtService.getSubjectFromTokenWithoutValidation(jwt);
            Integer branchId = userRepository.findByUsername(username).get().getBranchId();
            return ResponseEntity.ok(eventRepository.findByIdAndBranch(id, branchId));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<String> update(EventCreateDTO dto, String jwt, Integer id) {
        try {

            String username = jwtService.getSubjectFromTokenWithoutValidation(jwt);
            Integer branchId = userRepository.findByUsername(username).get().getBranchId();
            var event = eventRepository.findByIdAndBranch(id, branchId);
            event.setTitle(dto.title());
            eventRepository.save(event);

            return ResponseEntity.ok("Successfully updated");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<String> create(EventCreateDTO dto, String jwt) {
        try {

            String username = jwtService.getSubjectFromTokenWithoutValidation(jwt);
            Integer branchId = userRepository.findByUsername(username).get().getBranchId();
            var event = new Event();
            event.setTitle(dto.title());
            event.setBranchId(branchId);
            eventRepository.save(event);

            return ResponseEntity.ok("Successfully created");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
