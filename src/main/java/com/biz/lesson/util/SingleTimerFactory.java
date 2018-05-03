package com.biz.lesson.util;

import java.util.Timer;

/**
 * 反回一个单例的{@link Timer}
 * Created by defei on 10/26/15.
 */
public class SingleTimerFactory {

    public static synchronized Timer getTimer() {

        if (timer == null) {
            timer = new Timer();
        }
        return timer;
    }

    private static Timer timer;
}
