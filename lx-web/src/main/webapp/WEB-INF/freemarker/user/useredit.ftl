<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>需货单</title>
<link rel="stylesheet" type="text/css" href="${resRoot}/css/css.css" media="all">
<link rel="stylesheet" type="text/css" href="${resRoot}/css/style.css" media="all">
<link rel="stylesheet" type="text/css" href="${resRoot}/css/demo.css">
<script src="${resRoot}/js/modernizr.js" type="text/javascript"></script>
<script type="text/javascript"  src="${resRoot}/js/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="${resRoot}/js/datetimepicker/jquery.datetimepicker.css">
<script type="text/javascript"  src="${resRoot}/js/datetimepicker/jquery.datetimepicker.js"></script>
</head>

<body>
<#include "/common/top.ftl"/>
<#include "/common/usertop.ftl"/>
<div class="main mt">
 <form method="post" id="addFrom" action="${base}/user/save.do">
 	<input type="hidden" name="id" value="${user.id!}" />
	<div class="tips"><a href="#">账号</a><span>》</span><span><#if user.id ?? >修改账号<#else>新增账号</#if></span></div>
	<div class="search mtop">
    	<h5><p class="tit"><#if user.id ?? >修改账号<#else>新增账号</#if></p><p>输入以下数据，全部完成后点击保存</p></h5>
        <a onclick="sub();" class="dc">保存</a>
        <a onclick="location='${base}/user/userlist.do'" class="qx">返回</a>
    </div>
    
   
    <div class="single">

	<div class="accordion">
		<dl>
			<dt class="a_dt2">
				<a href="#accordion1" aria-expanded="false" id="initButtion" aria-controls="accordion1" class="accordion-title accordionTitle js-accordionTrigger dw"></a>
                <h3 class="y2">账号信息</h3>
               
			</dt>
			<dd class="accordion-content accordionItem is-expanded" id="accordion1" aria-hidden="true">
                <div class="s_main3">
                    <div class="jg">
                       <dl class="s_d sd3">
                       <dt>类型</dt>
                       <dd>
                    		<select name="userType" class="select1" >
                            		<option value='1' <#if user.userType==1>selected="selected"</#if>  >普通用户</option>
                            		<option value='0' <#if user.userType==0>selected="selected"</#if>  >管理员</option>
                            		<option value='2' <#if user.userType==0>selected="selected"</#if>  >仓库管理员</option>
                            </select>
                        </dd>  
                        </dl>    	
                        <dl class="s_d sd3">
                            <dt>账号</dt>
                            <#if user.id ?? >
                            ${user.userName!}
                            <#else>
                            <dd><input id="userName" value="" class="text5" name="userName" /></dd>
                            </#if>
                        </dl>
                        <dl class="s_d sd3">
                            <dt>所属单位</dt>
                            <dd><input value="${user.company!}" class="text5" name="company" /></dd>
                        </dl>
                        <dl class="s_d sd3">
                            <dt>密码</dt>
                            <dd><input  type="password" value="${user.userPassword!}" class="text5" name="userPassword" /></dd>
                        </dl>
                    </div>
                </div>
			</dd>
            </dl>
            
            <dl>
            
	
</div>    
</div> 
</form>
<script type="text/javascript">
//uses classList, setAttribute, and querySelectorAll
//if you want this to work in IE8/9 youll need to polyfill these

$(function(){
	<#if success ?? >alert("保存成功");</#if>
 init();
}); 

function sub(){
    <#if user.id ?? >
         $('#addFrom').submit();
    <#else>
        var userName=$('#userName').val();
        if(userName.trim()==''){
            alert('请填写账号');
            return;
        }
        $.get("${base}/user/checkName.do?userName="+userName, function(result){
            if(result=='ok'){
                $('#addFrom').submit();
            }else{
                alert('该账号已存在');
                return;
            }
        });
    </#if>
}



function init(){ 
var d = document,
	accordionToggles = d.querySelectorAll('.js-accordionTrigger'),
	setAria,
	setAccordionAria,
	switchAccordion,
  touchSupported = ('ontouchstart' in window),
  pointerSupported = ('pointerdown' in window);
  
  skipClickDelay = function(e){
	e.preventDefault();
	e.target.click();
  }

		setAriaAttr = function(el, ariaType, newProperty){
		el.setAttribute(ariaType, newProperty);
	};
	setAccordionAria = function(el1, el2, expanded){
		switch(expanded) {
	  case "true":
		setAriaAttr(el1, 'aria-expanded', 'true');
		setAriaAttr(el2, 'aria-hidden', 'false');
		break;
	  case "false":
		setAriaAttr(el1, 'aria-expanded', 'false');
		setAriaAttr(el2, 'aria-hidden', 'true');
		break;
	  default:
				break;
		}
	};
//function
switchAccordion = function(e) {
  console.log("triggered");
	e.preventDefault();
	var thisAnswer = e.target.parentNode.nextElementSibling;
	var thisQuestion = e.target;
	if(thisAnswer.classList.contains('is-collapsed')) {
		setAccordionAria(thisQuestion, thisAnswer, 'true');
	} else {
		setAccordionAria(thisQuestion, thisAnswer, 'false');
	}
	thisQuestion.classList.toggle('is-collapsed');
	thisQuestion.classList.toggle('is-expanded');
		thisAnswer.classList.toggle('is-collapsed');
		thisAnswer.classList.toggle('is-expanded');
	
	thisAnswer.classList.toggle('animateIn');
	};
	for (var i=0,len=accordionToggles.length; i<len; i++) {
		if(touchSupported) {
	  accordionToggles[i].addEventListener('touchstart', skipClickDelay, false);
	}
	if(pointerSupported){
	  accordionToggles[i].addEventListener('pointerdown', skipClickDelay, false);
	}
	accordionToggles[i].addEventListener('click', switchAccordion, false);
  }


}
</script>
    
<div class="f_b">
    苏ICP备15024526号 ©2016ABCD
</div>
</body>
</html>
