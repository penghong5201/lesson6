package com.biz.lesson.util;

import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by defei on 8/28/16.
 */
public abstract class SimpleTimerDataLoader<T> {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private final ScheduledThreadPoolExecutor schedual;

    private T data;

    public T getData() {
        return data;
    }

    /**
     * 时间任务调用的数据加载方法，其返回的数据会直接应用到 {@linkplain SimpleTimerDataLoader#getData()} 返回的数据结果。
     * @return
     */
    protected abstract T loadDataByTimerTask();

    /**
     * 手动更新数据
     * @param t 当前设置的数据会直接应用到 {@linkplain SimpleTimerDataLoader#getData()} 返回的数据结果。
     */
    public final void updateData(T t){

        data = t;
    }

    /**
     * @param name 任务名称
     * @param delay 延时执行(毫秒)
     * @param period 执行时间间隔(毫秒)
     * @param loadDataImmediately true:创建当前{@linkplain SimpleTimerDataLoader}成功后立即执行一次数据加载。
     */
    public SimpleTimerDataLoader(String name, long delay, long period, TimeUnit timeUnit, Boolean loadDataImmediately) {

        this(name, delay, period, timeUnit);
        if(loadDataImmediately){
            doLoadData();
        }
    }

    private SimpleTimerDataLoader(final String name, long delay, long period, TimeUnit timeUnit) {
        schedual = new ScheduledThreadPoolExecutor(1, new ThreadFactory() {
            private AtomicInteger atoInteger = new AtomicInteger(0);
            public Thread newThread(Runnable r) {
                logger.debug("Create a new thread for {}", name);
                Thread t = new Thread(r);
                t.setName(name + "-Thread " + atoInteger.getAndIncrement());
                return t;
            }
        });
        schedual.scheduleAtFixedRate(new TimerTask() {
            @Override public void run() {
                doLoadData();
            }
        }, delay, period, timeUnit);
    }

    private void doLoadData(){
        try {
            data = loadDataByTimerTask();
        } catch (Throwable e) {
            logger.info("Load data failed.", e);
        }
    }

}
