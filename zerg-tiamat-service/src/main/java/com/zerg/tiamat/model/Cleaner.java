package com.zerg.tiamat.model;

import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author : xuyang
 * @date : 2019-12-27 10:37
 */

public class Cleaner {

    private static final ScheduledExecutorService EXECUTOR_SERVICE = Executors.newScheduledThreadPool(1);
    private static final Long ONE_SECONDS_MILLI = 1000L;
    private static final Long FIVE_MINUTES_MILLI  = 5 * 60 * 1000L;
    private WhiteHouse whiteHouse;

    public Cleaner(WhiteHouse whiteHouse){
        this.whiteHouse = whiteHouse;
    }

    public void startClean() {
        EXECUTOR_SERVICE.scheduleAtFixedRate(()->{
            long now = Instant.now().toEpochMilli();
            whiteHouse.getConferenceRooms().forEach(room->{
                long sub = now - room.getOpenTime();
                if (sub > FIVE_MINUTES_MILLI){
                    whiteHouse.getConferenceRooms().remove(room);
                }
            });
        }, ONE_SECONDS_MILLI, ONE_SECONDS_MILLI, TimeUnit.MILLISECONDS);
    }
}
