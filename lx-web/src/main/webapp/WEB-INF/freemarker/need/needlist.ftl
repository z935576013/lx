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
<script type="text/javascript"  src="${resRoot}/js/paging.js"></script>
<#import "/macro/paging.ftl" as page>
</head>

<body>
 <#include "/common/top.ftl"/>
<#include "/common/needtop.ftl"/>
<div class="main mt">
	<div class="search">
	 <form  name="queryForm" method="post" action="${base}/need/needlist.do">
    	<div class="s_bar"><input type="text2" value="${keyword!}" name="keyword" class="text2" /><a onclick="document.queryForm.submit() ;" class="s_btn">搜索</a></div>
        <span>“需货单号、品名、规格、日期（如：2016/09/12）”</span>
        <a href="${base}/need/add.do" class="adddate">新增需货单</a>
        <a target="_blank" href="${base}/need/export.do" class="exportexcel">导出</a>
        <@page.pageInput PageParam.startNum PageParam.maxCount />
      </form>  
    </div>
    
    <ul class="title">
    	<li class="t1">需货编号</li>
        <li class="t2">需货单位</li>
        <li class="t3">订单日期</li>
        <li class="t4">需货日期</li>
        <li class="t5">操作</li>
    </ul>
    
    <div class="single">

	<div class="accordion">
		<#list list as order >
		<dl id="need${order.id!}">
			<dt class="a_dt">
				<a href="#accordion1" aria-expanded="true" aria-controls="accordion1" class="accordion-title accordionTitle js-accordionTrigger dw"></a>
                <ul class="s_tit">
                    <li class="t1">${order.needOrderCode!}</li>
                    <li class="t2">${order.needCompany!}</li>
                    <li class="t3"><#if order.orderDate ?? >${order.orderDate?string("yyyy/MM/dd")}</#if></li>
                    <li class="t4"><#if order.needDate ?? ><a href="#" class="data1">${order.needDate?string("yyyy/MM/dd")}<b></b></a></#if></li>
                     <#if order.deliverStatus ?? ><#if order.deliverStatus==0>
                    <li class="t5"><a href="${base}/need/add.do?id=${order.id!}" class="check">编辑</a></li>
                    <li class="t5"><a onclick="del(${order.id!});" class="export">删除</a></li>
                    </#if></#if>
                </ul>
                <div class="s_bar">
                	<div class="s_cont">
                		<ul class="s_l">
                		<!--
                        	<Li class="map">单位名称：${order.companyName!}</Li>
                        -->	
                            <Li class="map">收货地址：${order.receiveAddress!}</Li>
                            <Li>邮编：${order.zipCode!}</Li>
                        </ul>
                        <div class="s_r">
                        	<p>收货人：${order.receiveName!}</p>
                            <p><b>${order.receiveMobile!}</b></p>
                        </div>
                	</div>
                </div>
			</dt>
			<dd class="accordion-content accordionItem is-expanded" id="accordion1" aria-hidden="false">
				<ul class="s_main">
				<#list order.items as item >
                	<li id="needItem${item.id!}">
                    	<p><span>品名&nbsp</span> ${item.productName!}<span> 规格</span> ${item.spec!}</p>
                        <p><span>需货数量</span>${item.num!}</p>
                        <p><span>已发货数量</span>${item.deliverNum!}</p>
                        <#if item.num ??>
                        <p><span>未发货数量</span>${item.num-item.deliverNum}</p>
                        </#if>
                        <p class="btm">
                        <#if item.deliverStatus==1>
                        	<span class="y">已发货</span>
                            <#if sessionUser.userType!=1>
                        	<a href="${base}/deliver/deliverDetail.do?needOrderItemId=${item.id!}">发货详情></a>
                        	</#if>
                        </#if>
                        <#if item.deliverStatus==0>
                            <#if sessionUser.userType!=1>
                        	<span class="n">未发货</span><a href="${base}/deliver/add.do?needOrderItemId=${item.id!}">新建发货单</a>
                        	</#if>
                        	&nbsp&nbsp
                        </#if>
                         <#if item.deliverNum==0>
                        	<a onclick="delItem(${item.id!})">删除</a>
                         </#if>	
                        </p>
                    </li>
                  </#list>  
                </ul>
			</dd>
            </dl>
		</#list>
		
        <div class="page">
        <@page.pageList pageIndex=PageParam.pageIndex pageCount=PageParam.pageCount />
    	
    </div>
	</div>
	
</div>    
</div> 
<script type="text/javascript">
function del(id){
	if(confirm('确认删除吗?')){
		$.get("${base}/need/delete.do?id="+id, function(result){
	    	$("#need"+id).remove();
	  	});
	}
}

function delItem(id){
	if(confirm('确认删除吗?')){
		$.get("${base}/need/deleteItem.do?id="+id, function(result){
	    	$("#needItem"+id).remove();
	  	});
	}
}


//uses classList, setAttribute, and querySelectorAll
//if you want this to work in IE8/9 youll need to polyfill these
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
    
 <#include "/common/foot.ftl"/>
</body>
</html>
