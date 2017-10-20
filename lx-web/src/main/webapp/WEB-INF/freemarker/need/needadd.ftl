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
<#include "/common/needtop.ftl"/>
<div class="main mt">
 <form method="post" id="addFrom" action="${base}/need/save.do">
 	 <input type="hidden" value="${order.id!}" name="id"  />
	<div class="tips"><a href="#">需货单列表</a><span>》</span><span><#if order.id ?? >修改需货单<#else>新增需货单</#if></span></div>
	<div class="search mtop">
    	<h5><p class="tit"><#if order.id ?? >修改需货单<#else>新增需货单</#if></p><p>输入以下数据，全部完成后点击保存</p></h5>
        <a onclick="$('#addFrom').submit();" class="dc">保存</a>
        <a onclick="history.back();" class="qx">取消</a>
    </div>
    
   
    <div class="single">

	<div class="accordion">
		<dl>
			<dt class="a_dt2">
				<a href="#accordion1" aria-expanded="false" aria-controls="accordion1" class="accordion-title accordionTitle js-accordionTrigger dw"></a>
                <h3 class="y2">基本信息</h3>
               
			</dt>
			<dd class="accordion-content accordionItem is-expanded" id="accordion1" aria-hidden="true">
                <div class="s_main3">
                	<div class="jg">
                        <dl class="s_d sd1">
                            <dt>需货单位</dt>
                            <dd><input type="text2" value="<#if order.needCompany ??>${order.needCompany!}<#else>${sessionUser.company!}</#if>" class="text5" name="needCompany" /></dd>
                        </dl>
                        <#if contactList ??>
                        <#if (contactList?size>0) >
                        <dl class="s_d sd2">
                            <dt>联系人选择</dt>
                            <dd>
                            <select id="contactSelect" class="select1" onchange="changeContact();">
                                <option value="">--请选择--</option>
                                <#list  contactList as contact>
                                <option value="${contact.id!}">${contact.receiveName!}</option>
                                </#list>
                            </select>
                            </dd>
                        </dl>
                        </#if>
                        </#if>
                    </div>
                    <div class="jg">
                        <dl class="s_d sd3">
                            <dt>订单日期</dt>
                            <dd><input type="text2" value="<#if order.orderDate ?? >${order.orderDate?string("yyyy-MM-dd")}</#if>" class="text6 dateSelect" name="orderDate" /></dd>
                        </dl>
                        <dl class="s_d sd3">
                            <dt>需货日期</dt>
                            <dd><input type="text2" value="<#if order.orderDate ?? >${order.orderDate?string("yyyy-MM-dd")}</#if>" class="text6 dateSelect" name="needDate" /></dd>
                        </dl>
                        <!--
                        <dl class="s_d sd2">
                            <dt>单位名称</dt>
                            <dd><input type="text2" value="${order.companyName!}" class="text5" name="companyName" /></dd>
                        </dl>
                        -->
                        <dl class="s_d sd3">
                            <dt>邮编</dt>
                            <dd><input type="text2" id="zipCode" value="${order.zipCode!}" class="text5" name="zipCode" /></dd>
                        </dl>
                        <dl class="s_d sd3">
                            <dt>收货地址</dt>
                            <dd><input type="text2" id="receiveAddress" value="${order.receiveAddress!}" class="text5" name="receiveAddress" /></dd>
                        </dl>
                        <dl class="s_d sd3">
                            <dt>收货人</dt>
                            <dd><input type="text2" id="receiveName" value="${order.receiveName!}" class="text5" name="receiveName" /></dd>
                        </dl>
                        <dl class="s_d sd3">
                            <dt>收货人电话</dt>
                            <dd><input type="text2" id="receiveMobile" value="${order.receiveMobile!}" class="text5" name="receiveMobile" /></dd>
                        </dl>
                    </div>
                    
                   <div class="jg">
                    <dl class="s_d sd6">
                            <dt>备注</dt>
                            <dd><input type="text2" value="${order.note!}" name="note" class="text5" /></dd>
                    </dl>
                    </div>
                  
                </div>
			</dd>
            </dl>
            <#list order.items as item >
            <dl>
            <input type="hidden" value="${item.id!}" name="itemId"  />
			<dt class="a_dt2">
				<a href="#accordion1" aria-expanded="false" aria-controls="accordion2" class="accordion-title accordionTitle js-accordionTrigger dw"></a>
                <h3 class="y2">产品信息</h3>
               
			</dt>
			<dd class="accordion-content accordionItem is-expanded" id="accordion2" aria-hidden="true">
                <div class="s_main3">
                	<div class="jg">
                	   <dl class="s_d sd3">
                            <dt>产品选择</dt>
                            <dd>
                                <select id="itemSelect${item.id!}" class="select1 changeItem">
                                    <option value="">--请选择--</option>
                                    <#list  itemList as item>
                                    <option value="${item.id!}">${item.productName!}_${item.spec!}</option>
                                    </#list>
                                </select>
                            </dd>
                        </dl>
                        <dl class="s_d sd3">
                            <dt>产品名称</dt>
                            <dd><input type="text2" id="productName${item.id!}" value="${item.productName!}" class="text5" name="productName" /></dd>
                        </dl>
                        <dl class="s_d sd3">
                            <dt>规格</dt>
                            <dd><input type="text2" id="spec${item.id!}" value="${item.spec!}" class="text5" name="spec" /></dd>
                        </dl>
                        <dl class="s_d sd3">
                            <dt>数量</dt>
                            <dd><input type="text2" id="num${item.id!}"  value="${item.num!}" class="text5" name="num"  /></dd>
                        </dl>
                    </div>
                    
                    <div class="jg">
                    <dl class="s_d sd6">
                            <dt>备注</dt>
                            <dd><input type="text2" value="${item.itemNote!}" class="text5" name="itemNote"  /></dd>
                    </dl>
                    </div>
                </div>
			</dd>
            </dl>
           </#list>
           
           <dl>
            <input type="hidden" value="" name="itemId"  />
			<dt class="a_dt2">
				<a href="#accordion1" aria-expanded="false" aria-controls="accordion2" class="accordion-title accordionTitle js-accordionTrigger dw"></a>
                <h3 class="y2">产品信息</h3>
               
			</dt>
			<dd class="accordion-content accordionItem is-expanded" id="accordion2" aria-hidden="true">
                <div class="s_main3">
                	<div class="jg">
                	   <dl class="s_d sd3">
                            <dt>产品选择</dt>
                            <dd>
                                <select id="itemSelect0" class="select1 changeItem" >
                                    <option value="">--请选择--</option>
                                    <#list  itemList as item>
                                    <option value="${item.id!}">${item.productName!}_${item.spec!}</option>
                                    </#list>
                                </select>
                            </dd>
                        </dl>
                        <dl class="s_d sd3">
                            <dt>产品名称</dt>
                            <dd><input type="text2"   id="productName0"  value="" class="text5" name="productName" /></dd>
                        </dl>
                        <dl class="s_d sd3">
                            <dt>规格</dt>
                            <dd><input type="text2"  id="spec0" value="" class="text5" name="spec" /></dd>
                        </dl>
                        <dl class="s_d sd3">
                            <dt>数量</dt>
                            <dd><input type="text2" id="num0"  value="" class="text5" name="num"  /></dd>
                        </dl>
                    </div>
                    
                    <div class="jg">
                    <dl class="s_d sd6">
                            <dt>备注</dt>
                            <dd><input type="text2" value="" class="text5" name="itemNote"  /></dd>
                    </dl>
                    </div>
                </div>
			</dd>
            </dl>
           
           
            <dl id="itemtemp" style="display:none">
            <input type="hidden" name="itemId" value=""/>
			<dt class="a_dt2">
				<a href="#accordion1" aria-expanded="false"   class="accordion-title"></a>
                <h3 class="y2">产品信息</h3>
               
			</dt>
			<dd  id="accordion3">
               <div class="s_main3">
                	<div class="jg">
                	    <dl class="s_d sd3">
                            <dt>产品选择</dt>
                            <dd>
                                <select id="itemSelect0" class="select1 changeItem" >
                                    <option value="">--请选择--</option>
                                    <#list  itemList as item>
                                    <option value="${item.id!}">${item.productName!}_${item.spec!}</option>
                                    </#list>
                                </select>
                            </dd>
                        </dl>
                        <dl class="s_d sd3">
                            <dt>产品名称</dt>
                            <dd><input type="text2" value="" class="text5" name="productName" /></dd>
                        </dl>
                        <dl class="s_d sd3">
                            <dt>规格</dt>
                            <dd><input type="text2" value="" class="text5" name="spec" /></dd>
                        </dl>
                        <dl class="s_d sd3">
                            <dt>数量</dt>
                            <dd><input type="text2" value="" class="text5" name="num"  /></dd>
                        </dl>
                    </div>
                    
                    <div class="jg">
                    <dl class="s_d sd6">
                            <dt>备注</dt>
                            <dd><input type="text2" value="" class="text5" name="itemNote"  /></dd>
                    </dl>
                    </div>
                </div>
			</dd>
            </dl>
            
             <div class="search mtop">
             <a onclick="addItem();"  class="qx">增加产品</a>
            </div>
            
	
</div>    
</div> 
</form>
<script type="text/javascript">
//uses classList, setAttribute, and querySelectorAll
//if you want this to work in IE8/9 youll need to polyfill these

$(function(){
 init();

			$('.dateSelect').datetimepicker({
						lang: 'ch',
						timepicker: false,
						format: 'Y-m-d',
						formatDate: 'Y/m/d'
					});
			
			

			bindChangeItem();		
			
}); 

function bindChangeItem(){
        $('.changeItem').change(
             function(){
                 var itemId= $(this).val();
                  <#list itemList as item>
                    if(itemId=='${item.id!}'){
                      $(this).parent().parent().next().find('dd').find('input').val('${item.productName!}');
                      $(this).parent().parent().next().next().find('dd').find('input').val('${item.spec!}');
                     return;
                    }
                    </#list>  
            });
}


function addItem(){
    $("#itemtemp").clone().removeAttr("id").show().insertBefore("#itemtemp"); 
    bindChangeItem();
   
}

function changeContact(){
    var contactId= $("#contactSelect").val();
    if(contactId==''){
        return;
    }
    <#list contactList as contact>
    if(contactId=='${contact.id!}'){
       $("#zipCode").val('${contact.zipCode!}');
       $("#receiveAddress").val('${contact.receiveAddress!}');
       $("#receiveName").val('${contact.receiveName!}');
       $("#receiveMobile").val('${contact.receiveMobile!}');
       return;
    }
    </#list>
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
