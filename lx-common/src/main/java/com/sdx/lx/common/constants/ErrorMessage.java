package com.sdx.lx.common.constants;

/**
 * 错误消息常量
 *
 * @author zhuliang
 */
public enum ErrorMessage {
	//酒店profile
//	ERROR_1001("1001", "请求参数异常?"),
//	ERROR_1002("1002", "无权操作"),
//	ERROR_1003("1003", "上传文件类型错误"),
//	ERROR_1004("1004", "由酒店方保存合同条款"),
//	ERROR_1005("1005", "RFP 不存在"),
//	ERROR_1006("1006", "酒店房间报价的时候房间数不应该大于其总有的房间数"),
//	ERROR_1007("1007", "有进行中的rfp不能修改合同样板"),
//	ERROR_1008("1008", "月份参数异常"),

//	//公共异常信息
//	ERROR_0000("0000", "系统内部错误"),
//	ERROR_0001("0001", "用户名已被注册"),
//	ERROR_0002("0002", "验证码不正确"),
//	ERROR_0004("0004", "用户不存在"),
//	ERROR_0005("0005", "原密码不正确"),
//	ERROR_0013("0013", "参数类型不正确"),
//	ERROR_0014("0014", "需要登录"),

//
//	ERROR_0034("0034", "上传失败"),
//	ERROR_HOTEL_NOT_FOUND("H001", "酒店?不存在"),
//
//	ERROR_0037("0037", "请求参数校验失败"),
//	ERROR_0038("0038", "请求失败"),
//	ERROR_0039("0039", "对方设置不能自动添加好友"),
//	ERROR_0040("0040", "已经是好友"),
//	ERROR_0041("0041", "申请邮箱已被占用") ,
//	ERROR_0042("0042", "没有权限访问当前页面"),
//	ERROR_0043("0043","没有管理员用户") ;
	
	//酒店profile
	ERROR_1001("1001", "Request parameter exception?"),
	ERROR_1002("1002", "Unauthorized operation"),
	ERROR_1003("1003", "Upload file type error"),
	ERROR_1004("1004", "Contract terms by the hotel party"),
	ERROR_1005("1005", "RFP is not existing"),
	ERROR_1006("1006", "Room number should not exceed the total number of rooms in the hotel room"),
	ERROR_1007("1007", "rfp  in progressing ,can't modify contract templet"),
	ERROR_1008("1008", "month parameter exception"),
	ERROR_1009("1009", "This link is valid, please click the forget password button on the login page to get a new one."),
	ERROR_1010("1010", "The current link is incorrect, please check the link or click modify password button to operate in the merak system mail"),
	ERROR_1011("1011", "Only 3 Selection Permitted!"),
	ERROR_1012("1012", "can't delete !"),
	
	//公共异常信息
	ERROR_0000("0000", "System internal error"),
	ERROR_0001("0001", "User name already registered"),
	ERROR_0002("0002", "Verification code is not correct"),
	ERROR_0004("0004", "Users do not exist"),
	ERROR_0005("0005", "The original password is not correct"),
	ERROR_0006("0006", "请求参数校验失败"),
	ERROR_0007("0007", "密码不正确"),
	ERROR_0013("0013", "Parameter type is incorrect"),
	ERROR_0014("0014", "Need to log in"),
	ERROR_0015("0015", "Your account has been in other places, is about to return to the landing page."),
	ERROR_0016("0016", "The account has been frozen!"),
	ERROR_0017("0017", "The account is inavailable!"),

	ERROR_0034("0034", "Upload failed"),
	ERROR_HOTEL_NOT_FOUND("H001", "Hotel ? does not exist"),

	ERROR_0037("0037", "Request parameter check failed"),
	ERROR_0038("0038", "Request failure"),
	ERROR_0039("0039", "The other settings can not automatically add friends"),
	ERROR_0040("0040", "Has been a good friend"),
	ERROR_0041("0041", "Application mail has been occupied"),
	
	ERROR_0042("0042", "No Authority"),
	
	ERROR_0043("0043","no admin user") ,
	ERROR_0044("0044", "No permission send budget approval , please add another user"),
	ERROR_0045("0045", "身份码验证失败!"),
	ERROR_0052("0052","no superAdmin user") ,
	
	//模板异常信息
	ERROR_0046("0046", "模板数量已到最大限制!"),
	ERROR_0047("0047", "模板不存在!"),
	ERROR_0048("0048", "用户没有部门，请加入部门后才能提交模板审核!"),
	ERROR_0049("0049", "模板名称已存在，请重新命名!"),
	ERROR_0050("0050", "模板撤销失败!"),
	ERROR_0051("0051", "您选择的模板已经被移除，刷新页面，重新选择模板"),
	
	ERROR_0060("0060", "您不是该公司或酒店的成员"),
	
	//sso
//	ERROR_1000("1000", "无效token"),
	
	/** 后台权限错误 **/
	ERROR_2000("2000", "后台账号已冻结"),
	ERROR_2001("2001", "已存在角色名"),
	ERROR_2002("2002", "删除角色失败"),
	ERROR_2003("2003", "角色信息更新失败"),
	
	ERROR_2004("2004", "已存在岗位名"),
	ERROR_2005("2005", "删除岗位失败"),
	ERROR_2006("2006", "岗位信息更新失败"),
	
	ERROR_9000("9000", "状态不正确");
	
	/**
	 * 错误编码
	 */
	private String errorCode;
	/**
	 * 错误描述
	 */
	private String errorMessage;

	/**
	 * 
	 * @param errorCode
	 * @param errorMessage
	 */
	private ErrorMessage(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

}
