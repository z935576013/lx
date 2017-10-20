package com.sdx.lx.common.utils;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

public class ListeningExecutorFactory {
    private static final ListeningExecutorService service = MoreExecutors.listeningDecorator(ThreadPoolFactory.getExecutor());

    public static ListeningExecutorService getService() {
        return service;
    }
    private ListeningExecutorFactory(){}
    
}
