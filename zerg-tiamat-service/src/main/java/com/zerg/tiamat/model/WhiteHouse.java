package com.zerg.tiamat.model;

import lombok.Getter;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author : xuyang
 * @date : 2019-12-27 10:36
 */


public class WhiteHouse {

    @Getter
    private final CopyOnWriteArrayList<ConferenceRoom> conferenceRooms = new CopyOnWriteArrayList<>();
    private static final Long FIVE_MINUTES_MILLI  = 5 * 60 * 1000L;
    private Cleaner cleaner;



    public WhiteHouse(){
        cleaner = new Cleaner(this);
        cleaner.startClean();
    }


}
