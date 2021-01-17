package services;

import data.HealthCardID;
import exceptions.HealthCardException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ScheduledVisitAgendaSample implements ScheduledVisitAgenda {
    private Map<Date, HealthCardID> schedule = new HashMap<>();
    private final Date today = new Date();

    public ScheduledVisitAgendaSample() throws Exception {
        schedule.put(new Date(today.getYear(), today.getMonth(), today.getDay()), new HealthCardID("BBBBBBBBKQ728364923473928463"));
        schedule.put(new Date(today.getYear(), 2, 12), new HealthCardID("BBBBBBBBTR111364923473928463"));
        schedule.put(new Date(today.getYear(), 3, 13), new HealthCardID("BBBBBBBBGF222364923473928463"));
        schedule.put(new Date(today.getYear(), 4, 14), new HealthCardID("BBBBBBBBLN333364923473928463"));
        schedule.put(new Date(today.getYear(), 5, 15), new HealthCardID("BBBBBBBBAS444364923473928463"));
    }

    @Override
    public HealthCardID getHealthCardID() throws HealthCardException {
        if(!schedule.containsKey(new Date(today.getYear(), today.getMonth(), today.getDay()))) {
            throw new HealthCardException("");
        }
        return schedule.get(new Date(today.getYear(), today.getMonth(), today.getDay()));
    }
}
