package com.sdx.lx.common.utils;


import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 随机码生成器
 * 替代UUID
 * @author yeegor
 *
 */
public class GeneratorCode {
	
	private static final String[] key = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P"
			,"Q","R","S","T","U","V","W","X","Y","Z"};
	
    /**
     * A-Z : 65 - 90
     * P(26,8) = 62990928000
     * @return
     */
    public static String generator() {

    	//long initCurrentTime = System.nanoTime();
    	
        int selectCount = 8;
        
    	List<String> keyList = new ArrayList<String>();
    	for (String k : key) {
    		keyList.add(k);
    	}
    	
    	List<Integer> randomIndexes = new ArrayList<Integer>();
    	for (int i=0; i<selectCount; i++) {
    		randomIndexes.add(i);
    	}
    	
    	//LOGGER.info("[" + Thread.currentThread().getName() + "]算法条件初始化耗时: " + (System.nanoTime()-initCurrentTime) + "微秒");
    	
    	//字母随机索引
    	int index = 0;
    	
    	//long uuidCurrentTime = System.nanoTime();
    	//StringBuffer buffer = new StringBuffer(UUID.randomUUID().toString()); //使用了30+毫秒
    	//LOGGER.info("[" + Thread.currentThread().getName() + "]uuid生成耗时: " + (System.nanoTime()-uuidCurrentTime) + "微秒");
    	
    	StringBuffer buffer = new StringBuffer();
    	
        SortedMap<Integer, String> codeMap = new TreeMap<Integer, String>();
        
        int size = key.length;
        int randomIndexCount = selectCount; //随机索引数量
        Random random = new Random();
        
        //long dowhileCurrentNanotime = System.nanoTime();
    	while(true) {
        	index = random.nextInt(size);
        	String code = keyList.get(index);
			// 对字符随机排序
			int randomIndex_idx = random.nextInt(randomIndexCount);
			int randomIndex = randomIndexes.get(randomIndex_idx);
			codeMap.put(randomIndex, code);
			keyList.remove(index); // 删除字母元素
			randomIndexes.remove(randomIndex_idx); // 删除随机排序索引元素
			size--;
			randomIndexCount--;
			if (randomIndexCount == 0) {
				break;
			}
    	} 
    	//LOGGER.info("[" + Thread.currentThread().getName() + "]do while用时 : " + (System.nanoTime()-dowhileCurrentNanotime) + "微秒");
    	
    	Set<Entry<Integer, String>> entries= codeMap.entrySet();
    	for (Entry<Integer, String> entry : entries) {
    		buffer.append(entry.getValue());
    	}
    	buffer.append(System.nanoTime());
    			
    	return buffer.toString();
    }

}
