package com.sdx.lx.common.file;

public interface FileClient {

	/**
	 * 业务类型：酒店图片
	 */
	public static String BIZTYPE_HOTEL_PIC = "hotel";

	/**
	 * 酒店大图
	 */
	public static String BIZTYPE_HOTEL_BIGIMG = "hotelBigimg";

	/**
	 * 酒店文件
	 */
	public static String BIZTYPE_HOTEL_DOC = "hotelDoc";

	/**
	 * 酒店上传的合同文件
	 */
	public static String BIZTYPE_HOTEL_CONTRACT_DOC = "hotelContractDoc";

	/**
	 * 合同文件
	 */
	public static String BIZTYPE_CONTRACT = "contract";
	
	/**
	 * 票据文件
	 */
	public static String BIZTYPE_TICKET = "ticket";

	/**
	 * 联系人头像
	 */
	public static String BIZTYPE_CONTACT_INFO_AVATAR_IMAGE = "avatarImage";

	/**
	 * 公司或酒店上传LOGO
	 */
	public static String BIZTYPE_LOGO = "logo";
	
	/**
	 * 新闻图片
	 */
	public static String BIZTYPE_NEWS_IMAGE = "newsImage";

	/**
	 * 业务类型：im
	 */
	public static String BIZTYPE_IM = "im";
	
	/**
	 * 业务类型：fit
	 */
	public static String BIZTYPE_FIT = "fit";

	
	/**
	 * 
	 * 保存一个本地文件，返回文件path，失败返回null
	 * 
	 * @param localFileName
	 *            本地文件名
	 * @param bizType
	 *            业务类型
	 * @param asyn
	 *            是否异步 ，默认同步
	 * @param suffix
	 *            文件后缀， 默认.jpg
	 * @param mark
	 *            水印文字
	 * @return 文件path
	 */
	String saveFile(String localFileName, String bizType, boolean asyn,
			String suffix, String mark);

	/**
	 * 
	 * 保存一个本地文件，返回文件path，失败返回null,默认同步，.jpg后缀
	 * 
	 * @param localFileName
	 *            本地文件名
	 * @param bizType
	 *            业务类型
	 * @return 文件path
	 */
	String saveFile(String localFileName, String bizType);

	/**
	 * 
	 * 保存一个本地文件，返回文件path，失败返回null,默认同步，.jpg后缀
	 * 
	 * @param localFileName
	 *            本地文件名
	 * @param bizType
	 *            业务类型
	 * @param mark
	 *            水印文字
	 * @return 文件path
	 */
	String saveFile(String localFileName, String bizType, String mark);

	/**
	 * 删除文件
	 * 
	 * @param localPathName
	 * @return
	 */
	public boolean deleteFile(String localPathName);

	/**
	 * 
	 * 获取完整http访问路径
	 * 
	 * @param path
	 *            文件path
	 * @return 完整路径
	 */
	String getFullPath(String path);

}
