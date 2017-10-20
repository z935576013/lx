<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>登录</title>
<link rel="stylesheet" type="text/css" href="${resRoot}/css/css.css" media="all">
<link rel="stylesheet" type="text/css" href="${resRoot}/css/style.css" media="all">
<script type="text/javascript"  src="${resRoot}/js/jquery.min.js"></script>
</head>

<body>
<div class="top">
        <div class="logo"><a href="#" class="logo_pic">logo</a></div>
        <ul class="menu">
        	<li><a href="#">登录</a>
        </ul>
</div>

<div class="main">
	<dl class="sign">
	<dt>登录</dt>
    <dd>
    <form id="form1" method="post" action="${base}/login.do">
    	<ul class="biao">
            <li><div class="mbar"><input type="text" value="${mobile!}" placeholder="手机号" id="mobile" name="mobile" class="text" /><a onclick="sendSms();" class="yzm" id="sms">发送验证码</a></div></li>
            <li><div class="mbar"><input type="text" placeholder="验证码" name="code" class="text" /></div>
           
            <div class="er" id="errorMsg"><#if error ??>验证码不正确 </#if></div></li>
           
            <li><button type="button" onclick="subForm();" class="btn" >登录</button></li>
        </ul>
     </form>   
    </dd>
</dl>   
</div> 

    
<div class="f_b">
    苏ICP备15024526号 ©2016ABCD
</div>
</body>
<script type="text/javascript">
function subForm(){
	if(checkMobile()){
		$('#form1').submit();
	}
}


function sendSms(){
	if(checkMobile()){
		var mobile=$('#mobile').val();
		$.get("${base}/sendValCode.do?mobile="+mobile, function(result){
	    	$("#sms").html("重新发送");
	    	alert("发送成功");
	    	
	  	});
	}
}

function checkMobile(){
	var mobile=$('#mobile').val();
	var reg = /^0?1[0-9][0-9]\d{8}$/;
	 if (reg.test(mobile)) {
	 	$('#errorMsg').html('');
	    return true;
	 }else{
	    $('#errorMsg').html('请输入正确的手机号');
		return false;
	 };
}
</script>
</html>
