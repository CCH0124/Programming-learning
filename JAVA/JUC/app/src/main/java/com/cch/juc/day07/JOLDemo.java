package com.cch.juc.day07;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

public class JOLDemo {
    public static void main(String[] args) {
        System.out.println(VM.current().details());
        Object o = new Object();
        o.hashCode();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }
}
