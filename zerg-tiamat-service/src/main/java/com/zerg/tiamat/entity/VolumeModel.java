package com.zerg.tiamat.entity;

import com.google.common.base.Preconditions;
import com.google.common.collect.Queues;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Deque;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : xuyang
 * @date : 2019-12-26 13:30
 */


public class VolumeModel {

    private Lock lock = new ReentrantLock();
    private Deque<VolumeData> volumeDataDeque = Queues.newLinkedBlockingDeque();
    private Integer day;
    private BigDecimal sum = BigDecimal.ZERO;

    public VolumeModel(Integer day) {
        this.day = day;
    }


    public void replaceLast(VolumeData volumeData){
        checkArguments(volumeData);

        lock.lock();
        if (volumeDataDeque.isEmpty()){
            throw new IllegalStateException("无法替换成交量数据");
        }
        VolumeData last = volumeDataDeque.removeLast();
        sum.subtract(last.getVolume());
        volumeDataDeque.addLast(volumeData);
        sum.add(volumeData.getVolume());
        lock.unlock();
    }


    public void put(VolumeData volumeData) {
        checkArguments(volumeData);

        lock.lock();
        volumeDataDeque.addLast(volumeData);
        sum.add(volumeData.getVolume());
        if (volumeDataDeque.size() > day) {
            VolumeData first = volumeDataDeque.removeFirst();
            sum.subtract(first.getVolume());
        }
        lock.unlock();
    }

    public Long getLastTimestamp() {
        VolumeData peek = volumeDataDeque.peekLast();
        return peek == null ? 0L : peek.getTimestamp();
    }

    public BigDecimal getSum() {
        return sum;
    }

    public BigDecimal getAvg() {
        return sum.divide(BigDecimal.valueOf(day), 3, BigDecimal.ROUND_CEILING);
    }

    private void checkArguments(VolumeData volumeData) {
        Preconditions.checkArgument(Objects.nonNull(volumeData));
        Preconditions.checkArgument(Objects.nonNull(volumeData.getTimestamp()));
        Preconditions.checkArgument(Objects.nonNull(volumeData.getVolume()));
    }


    @Data
    @NoArgsConstructor
    public static class VolumeData {
        private Long timestamp;
        private BigDecimal volume;
    }


}
