package com.zerg.tiamat.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.concurrent.*;
import java.util.function.Consumer;

/**
 * @author : xuyang
 * @date : 2019-11-08 11:27
 */

@Slf4j
public class ThreadUtils {

    private static final ExecutorService executorService = new ThreadPoolExecutor(2, 20,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>());

    public static void executeWithoutWait(Runnable command) {
        executorService.execute(command);
    }

    public static <T> void executeWithoutWait(Collection<T> collection, Consumer<? super T> consumer) {
        if (CollectionUtils.isEmpty(collection)) {
            return;
        }

        for (T t : collection) {
            executorService.execute(() -> consumer.accept(t));
        }
    }

    public static <T> void executeAndWait(Collection<T> collection, Consumer<? super T> consumer) {
        if (CollectionUtils.isEmpty(collection)) {
            return;
        }

        CountDownLatch countDownLatch = new CountDownLatch(collection.size());
        for (T t : collection) {
            executorService.execute(() -> {
                try {
                    consumer.accept(t);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            log.error("线程被打断", e);
            Thread.currentThread().interrupt();
        }
    }
}
