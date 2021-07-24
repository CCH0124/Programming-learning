package com.example.cch.day04;

import org.openjdk.jol.info.ClassLayout;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.ThreadBiasedTest")
public class ThreadBiasedTest {
    public static void main(String[] args) {
        Person p = new Person();
        p.hashCode();
        log.debug("加鎖前：{}", ClassLayout.parseInstance(p).toPrintable()); 

        synchronized(p) {
            log.debug("加鎖後：{}", ClassLayout.parseInstance(p).toPrintable()); 
        }
        log.debug("解鎖後：{}", ClassLayout.parseInstance(p).toPrintable()); 
    }
}

class Person {

}
