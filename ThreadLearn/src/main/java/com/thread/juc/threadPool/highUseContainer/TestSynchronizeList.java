package com.thread.juc.threadPool.highUseContainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * SynchronizeList的使用
 *
 * 同步list
 */
public class TestSynchronizeList {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        List<String> syncList = Collections.synchronizedList(list);
    }
}
