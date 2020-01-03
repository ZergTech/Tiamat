package com.zerg.tiamat.model;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : xuyang
 * @date : 2019-12-27 10:37
 */

public class ConferenceRoom {
    @Getter
    private Long openTime;
    private final Set<Opinion> table = new HashSet<>();

    public Boolean join(Opinion opinion ){
        return table.add(opinion);
    }
}
