package com.sdx.lx.common.utils;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ListeningExecutorService;

/**
 * 〈一句话功能简述〉多线程并发处理模板方法<br> 
 * 〈功能详细描述〉
 *
 * @author rilk
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public abstract class MultiThreadTemplate<T,E> implements MultiThread<T,E> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MultiThreadTemplate.class);
    
    private ListeningExecutorService service;
    
    private List<T> dataList;
    
    private List<Future<E>> futures;
    
    private void invokeAll() {
        try {
            futures= service.invokeAll(Lists.transform(dataList, new Function<T, Callable<E>>(){

                @Override
                public Callable<E> apply(final T input) {
                    return new Callable<E>(){

                        @Override
                        public E call() {
                            if(null != input){
                                try {
                                   E e = processData(input);
                                   return e;
                                } catch (Throwable e) {
                                    LOGGER.error("processData is error input = {}, e = {}",input.toString(), e.getMessage());
                                }
                            }
                            return null;
                        }
                        
                    };
                }}));
        } catch (Exception e) {
            LOGGER.error("invokeAll is error e = {}", e.getMessage());
        }
    }
    /**
     * 功能描述: 钩子方法，自行实现数据处理，并返回想要的结果<br>
     * 〈功能详细描述〉
     *
     * @param data
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public abstract E processData(T data);
    
    @Override
    final public List<Future<E>> execute(List<T> dataList){
        Preconditions.checkNotNull(dataList);
        setDataList(dataList);
        setListeningExecutorService(ListeningExecutorFactory.getService());
        invokeAll();
        return getFutures();
    }
    
    private void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
    private void setListeningExecutorService(ListeningExecutorService service) {
        this.service = service;
    }
    private List<Future<E>> getFutures() {
        return futures;
    }
}
