package services;

import data.HealthCardID;
import exceptions.HealthCardException;
import exceptions.NullObjectException;
import exceptions.WrongIdCodeFormat;

public interface ScheduledVisitAgenda {
    HealthCardID getHealthCardID() throws HealthCardException, NullObjectException, WrongIdCodeFormat;
}
