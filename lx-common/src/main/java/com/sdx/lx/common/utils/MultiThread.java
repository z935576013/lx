package com.sdx.lx.common.utils;

import java.util.List;
import java.util.concurrent.Future;

/**
 * 〈一句话功能简述〉多线程，T 输入类型，E 输出类型<br> 
 * 〈功能详细描述〉
 *
 * @author rilk
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface MultiThread<T,E> {
    /**
     * 功能描述: 多线程并发执行，并返回执行结果，适用场景，调用频次不高，但是要提高执行速度的地方<br>
     * 〈功能详细描述〉
     *
     * @param eventList
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    List<Future<E>> execute(List<T> dataList);

}
