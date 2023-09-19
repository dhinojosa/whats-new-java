package com.xyzcorp.scopedvalues;

import java.time.LocalDate;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Framework {
    public final static ScopedValue<Rate> rateScopedValue =
        ScopedValue.newInstance();
    public final static ScopedValue<LocalDate> dateScopedValue =
        ScopedValue.newInstance();
    private final Rate normalRate = new Rate(10);
    private final ExecutorService executorService = Executors.newCachedThreadPool();


    public void performCalculation() {
        ScopedValue
            .where(rateScopedValue, normalRate)
            .run(Server::processing);
    }

    public String performAnotherCalculation() {
        return ScopedValue
            .where(dateScopedValue, LocalDate.now())
            .get(Server::processDate);
    }

    /** This is the same as above **/
    public String letsDoAnother() {
        return ScopedValue.getWhere(dateScopedValue, LocalDate.now(), Server::processDate);
    }

    public Future<String> performingInAnotherThread() {
        return executorService.submit(() ->
            ScopedValue.getWhere(dateScopedValue, LocalDate.now(), Server::processDate));
    }

    public void performingWithRunWhere() {
        ScopedValue.runWhere(rateScopedValue, new Rate(10), Server::calculatingRate);
    }

    public String performingWithCallWhere() throws Exception {
        return ScopedValue.callWhere(rateScopedValue, new Rate(10), () -> rateScopedValue.orElse(new Rate(0)).toString());
    }
}
