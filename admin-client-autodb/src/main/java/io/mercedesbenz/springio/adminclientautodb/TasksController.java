package io.mercedesbenz.springio.adminclientautodb;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
public class TasksController {

    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewScheduledTask() {
        LocalDateTime dateTime = LocalDateTime.now().plusMinutes(10);
        this.taskScheduler.schedule(() -> System.out.println("Task running"), dateTime.toInstant(ZoneOffset.UTC));
    }

}
