package com.shri.spring.springaiaws.config;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Slf4j
@RequiredArgsConstructor
@Component
public class PetAdoptionScheduler {

    private final ObjectMapper objectMapper;

    @Tool(description = "Schedule an appointment to adopt a pet at Pets Heaven pet adoption agency")
    public String scheduleAdoptionAppointment(@ToolParam(description = "id of the pet to adopt") int id,
                                              @ToolParam(description = "name of the pet to adopt") String name) throws JsonProcessingException {
        log.info("{}Confirming appointment to [{}] and name [{}]", this.getClass(), id, name);
        var timeOfAppointment = Instant.now().plus(3, ChronoUnit.DAYS);
        return objectMapper.writeValueAsString(timeOfAppointment);
    }
}
