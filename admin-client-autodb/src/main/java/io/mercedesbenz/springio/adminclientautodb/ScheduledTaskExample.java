package io.mercedesbenz.springio.adminclientautodb;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTaskExample {

    private AtomicInteger counter = new AtomicInteger();

    @Scheduled(fixedRate=1000)
    public void builtCars(){
        System.out.println("Built cars: " + this.counter.incrementAndGet());
    }

    @Scheduled(fixedRate=1000)
    public void soldCars(){
        System.out.println("Sold cars: " + this.counter.get());
    }
}
