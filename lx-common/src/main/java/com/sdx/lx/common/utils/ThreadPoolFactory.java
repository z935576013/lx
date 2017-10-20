package com.sdx.lx.common.utils;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * thread pool factory<br>
 * 
 * @author Yeegor
 */
public class ThreadPoolFactory {

    private static final ThreadPoolFactory factory = new ThreadPoolFactory();

    private static final Logger LOGGER = LoggerFactory
            .getLogger(ThreadPoolFactory.class);

    /**
     * get ThreadPoolExecutor instance with LinkedBlockingQueue <br>
     * coreSize 20 ; maxSize 50
     **/
    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(
            256, 256, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new BasicThreadFactory.Builder().daemon(true)
                    .namingPattern("io-help-worker-%d").build(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    private ThreadPoolFactory() {
    }

    public static ThreadPoolFactory factory() {
        return factory;
    }

    public static ThreadPoolExecutor getExecutor() {
        return executor;
    }

    /**
     * Thread pool destory in which previously submitted tasks are executed, but
     * no new tasks will be accepted
     */
    public void destory() {
        executor.shutdown();
    }

    /**
     * execute runnable <br>
     * set thread daemon
     * 
     * @param task
     */
    public void execute(final Runnable task) {
        /**
         * 避免高并发下线程频繁报错导致线程频繁创建的开销
         */
        executor.execute(new Runnable() {

            @Override
            public void run() {
                try {
                    task.run();
                } catch (Throwable th) {
                    // 防御性容错，避免高并发下的一些问题
                    LOGGER.error("ThreadPoolFactory|Throwable:", th);
                }

            }
        });
    }

}
