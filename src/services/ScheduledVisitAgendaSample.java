package services;

import data.HealthCardID;
import exceptions.HealthCardException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ScheduledVisitAgendaSample implements ScheduledVisitAgenda {
    private final Map<Date, HealthCardID> schedule = new HashMap<>();
    private final Date today = new Date();

    @Override
    public HealthCardID getHealthCardID() throws HealthCardException {
        if(!schedule.containsKey(new Date(today.getYear(), today.getMonth(), today.getDay()))) {
            throw new HealthCardException("");
        }
        return schedule.get(new Date(today.getYear(), today.getMonth(), today.getDay()));
    }

    public ScheduledVisitAgendaSample() throws Exception {
        schedule.put(new Date(today.getYear(), today.getMonth(), today.getDay()), new HealthCardID("BBBBBBBBQR648597807024000012"));
        schedule.put(new Date(today.getYear(), 2, 12), new HealthCardID("BBBBBBBBQR648597807024000013"));
        schedule.put(new Date(today.getYear(), 3, 13), new HealthCardID("BBBBBBBBQR648597807024000014"));
        schedule.put(new Date(today.getYear(), 4, 14), new HealthCardID("BBBBBBBBQR648597807024000015"));
        schedule.put(new Date(today.getYear(), 5, 15), new HealthCardID("BBBBBBBBQR648597807024000016"));
    }
}
