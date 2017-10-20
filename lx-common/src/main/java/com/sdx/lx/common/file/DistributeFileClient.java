package com.sdx.lx.common.file;

/**
 * 分布式文件
 * 
 * @author lz
 *
 */
public interface DistributeFileClient {

	/**
	 * 业务类型：im
	 */
	public static String BIZTYPE_IM = "im";

	/**
	 * 保存一个本地文件，返回文件path，失败返回null
	 * 
	 * @param localFileName
	 * @param bizType
	 * @return
	 */
	String saveFile(String localFileName, String bizType);

}
