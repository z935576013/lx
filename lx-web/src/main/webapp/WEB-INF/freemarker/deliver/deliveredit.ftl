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
<#include "/common/delivertop.ftl"/>
<div class="main mt">
	<form method="post" id="addFrom"  action="${base}/deliver/update.do">
	<input type="hidden" value="${deliver.id!}" name="id">
	<div class="tips"><a href="#">发货单详情</a><span>》</span><span>修改发货记录</span></div>
	<div class="search mtop">
    	<h5><p class="tit">修改发货记录</p><p>输入以下数据，全部完成后点击上传</p></h5>
        <a onclick="$('#addFrom').submit();"  class="dc">保存</a>
        <a <#if success ??>onclick="location='${base}/deliver/deliverlist.do'"<#else>onclick="history.back();"</#if> class="qx">取消</a>
    </div>
    
    
    <div class="single">

	<div class="accordion">
		<dl>
			<dt class="a_dt2">
				<a href="#accordion1" aria-expanded="false" aria-controls="accordion1" class="accordion-title accordionTitle js-accordionTrigger dw"></a>
                <h3 class="y1">基本信息</h3>
               
			</dt>
			<dd class="accordion-content accordionItem is-expanded" id="accordion1" aria-hidden="true">
                <div class="s_main2">
                <ul class="s_text">
                	<li><span>品名</span>${item.productName!}</li>
                	<li><span>数量</span>${item.num!}</li>
                    <li><span>需货编号</span>${need.needOrderCode!}</li>
                    <li><span>需货单位</span>${need.needCompany!}</li>
                    <li><span>订单日期</span><#if need.orderDate ?? >${need.orderDate?string("yyyy/MM/dd")}</#if></li>
                    <li><span>需货日期</span><#if need.needDate ?? >${need.needDate?string("yyyy/MM/dd")}</#if></li>
                </ul>
                <div class="s_bar">
                    <div class="s_cont">
                        <ul class="s_l">
                        <!--
                            <Li class="map">单位名称：${need.companyName!}</Li>
                        -->    
                            <Li>邮编：${need.zipCode!}</Li>
                            <Li>收货地址：${need.receiveAddress!}</Li>
                        </ul>
                        <div class="s_r">
                            <p>收货人：${need.receiveName!}</p>
                            <p><b>${need.receiveMobile!}</b></p>
                        </div>
                    </div>
                </div>
                </div>
			</dd>
            </dl>
			<dt class="a_dt2">
				<a href="#accordion1" aria-expanded="false" aria-controls="accordion3" class="accordion-title accordionTitle js-accordionTrigger dw"></a>
                <h3 class="y1">修改发货记录</h3>
               
			</dt>
			<dd class="accordion-content accordionItem is-expanded" id="accordion3" aria-hidden="true">
                <div class="s_main3">
                	
                    <div class="jg">
                        <dl class="s_d sd5">
                            <dt>发货日期</dt>
                            <dd><#if deliver.deliverDate ?? >${deliver.deliverDate?string("yyyy/MM/dd")}</#if></dd>
                        </dl>
                        <dl class="s_d sd5">
                            <dt>批号</dt>
                            <dd><input type="text2" value="${deliver.batchNum!}" class="text5" name="batchNum"  /></dd>
                        </dl>
                         <dl class="s_d sd5">
                            <dt>编码</dt>
                            <dd><input type="text2" value="${deliver.productCode!}" class="text5" name="productCode" /></dd>
                        </dl>
                         <dl class="s_d sd5">
                            <dt>规格</dt>
                            <dd><input type="text2" value="${deliver.spec!}" class="text5" name="spec" /></dd>
                        </dl>
                         <dl class="s_d sd5">
                            <dt>数量</dt>
                            <dd>${deliver.num!}</dd>
                        </dl>
                         <dl class="s_d sd5">
                            <dt>发往地</dt>
                            <dd><input type="text2" value="${deliver.deliverCity!}" class="text5" name="deliverCity" /></dd>
                        </dl>
                    </div>
                    
                   <div class="jg">
                    <dl class="s_d sd6">
                            <dt>备注</dt>
                            <dd><input type="text2" value="${deliver.note!}" class="text5" name="note" /></dd>
                    </dl>
					<dl class="s_d sd6">
                            <dt>物流编号</dt>
                            <dd><input type="text2" value="${deliver.logisticsCode!}" class="text5" name="logisticsCode" /></dd>
                    </dl>
                    </div>
                  <div class="jg"><span><input type="checkbox" name="smsNeed" value="1" <#if deliver.smsNeed ??><#if deliver.smsNeed==1>checked='checked'</#if></#if>  /></span><span>短信通知联系人：</span></div>
				  <dl class="s_d sd5">
                            <dd>姓名<input type="text2" value="${deliver.smsName!}" class="text5" name="smsName" />
                            电话<input type="text2" value="${deliver.smsMobile!}" class="text5" name="smsMobile" /></dd>
                   </dl>
                </div>
			</dd>
            </dl>
	
	
</div>    
</form>
</div> 
<script type="text/javascript">
//uses classList, setAttribute, and querySelectorAll
//if you want this to work in IE8/9 youll need to polyfill these

$(function(){
		<#if success ??>alert("保存成功");</#if>


			$('.dateSelect').datetimepicker({
						lang: 'ch',
						timepicker: false,
						format: 'Y-m-d',
						formatDate: 'Y/m/d'
					});
}); 


(function(){
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
})();
</script>
    
<div class="f_b">
    苏ICP备15024526号 ©2016ABCD
</div>
</body>
</html>
