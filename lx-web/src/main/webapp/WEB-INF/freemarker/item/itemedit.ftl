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
<#include "/common/itemtop.ftl"/>
<div class="main mt">
 <form method="post" id="addFrom" action="${base}/item/save.do">
 	<input type="hidden" name="id" value="${item.id!}" />
	<div class="tips"><a href="#">商品</a><span>》</span><span><#if item.id ?? >修改商品<#else>新增商品</#if></span></div>
	<div class="search mtop">
    	<h5><p class="tit"><#if item.id ?? >修改商品<#else>新增商品</#if></p><p>输入以下数据，全部完成后点击保存</p></h5>
        <a onclick="sub();" class="dc">保存</a>
        <a onclick="location='${base}/item/itemlist.do'" class="qx">返回</a>
    </div>
    
   
    <div class="single">

	<div class="accordion">
		<dl>
			<dt class="a_dt2">
				<a href="#accordion1" aria-expanded="false" id="initButtion" aria-controls="accordion1" class="accordion-title accordionTitle js-accordionTrigger dw"></a>
                <h3 class="y2">商品信息</h3>
               
			</dt>
			<dd class="accordion-content accordionItem is-expanded" id="accordion1" aria-hidden="true">
                <div class="s_main3">
                    <div class="jg">
                        <dl class="s_d sd3">
                            <dt>商品名称</dt>
                            <dd><input value="${item.productName!}" id="productName" class="text5" name="productName" /></dd>
                        </dl>
                        <dl class="s_d sd3">
                            <dt>商品规格</dt>
                            <dd><input value="${item.spec!}" class="text5" name="spec" /></dd>
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
        var productName=$('#productName').val();
        if(productName.trim()==''){
            alert('请填写商品');
            return;
        }
        $('#addFrom').submit();
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
