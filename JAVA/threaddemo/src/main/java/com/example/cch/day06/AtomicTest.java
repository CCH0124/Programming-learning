package com.example.cch.day06;

import java.util.concurrent.atomic.AtomicInteger;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.AtomicTest")
public class AtomicTest {
    public static void main(String[] args) {
        // 無參數預設為 0
        AtomicInteger i = new AtomicInteger(2 );
        log.info("自增並回傳: {}", i.incrementAndGet()); // equal ++i
        log.info("自增並回傳: {}", i.getAndIncrement()); // i++

        log.info("{}", i.getAndAdd(5)); // i += 5 先回傳在 +5
        log.info("{}", i.addAndGet(5)); // i += 5 先 +5 在回傳

        log.info("{}", i.getAndUpdate(x -> x * 10)); // 先回傳在 *10
        log.info("{}", i.updateAndGet(x -> x * 10)); // 先 *10 回傳在 
    }
}
