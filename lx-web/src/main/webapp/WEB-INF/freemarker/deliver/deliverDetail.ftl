<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>发货单</title>
<link rel="stylesheet" type="text/css" href="${resRoot}/css/css.css" media="all">
<link rel="stylesheet" type="text/css" href="${resRoot}/css/demo.css">

</head>

<body>
 <#include "/common/top.ftl"/>
<#include "/common/delivertop.ftl"/>
<div class="main mt">
	<div class="tips"><a href="${base}/deliver/deliverlist.do">发货单列表</a><span>》</span><span>发货详情</span></div>
	<div class="search mtop">
    	<h4><span>品名：${item.productName!}</span><span>需货单号：${need.needOrderCode!}</span></h4>
    </div>
    
    <ul class="title titpd">
    	<li class="t8">发货日期</li>
        <li class="t8">批号</li>
        <li class="t8">编码</li>
        <li class="t8">规格（盒/瓶）</li>
        <li class="t8">数量（箱）</li>
        <li class="t3">发往地</li>
        <li class="t6">物流编号</li>
        <li class="t5">操作</li>
    </ul>
    
    <div class="single2">
    	<#list deliverList as deliver>
			<div class="a_dt">
                <ul class="s_tit titpd2">
                    <li class="t8"><#if deliver.deliverDate ?? >${deliver.deliverDate?string("yyyy/MM/dd")}</#if></li>
                    <li class="t8">${deliver.batchNum!}</li>
                    <li class="t8">${deliver.productCode!}</li>
                    <li class="t8">${deliver.spec!}</li>
                    <li class="t8">${deliver.num!}</li>
                    <li class="t3">${deliver.deliverCity!}</li>
                    <li class="t7">${deliver.logisticsCode!}</li>
                </ul>
               
                <div class="s_bar">
                	<div class="s_cont2">
                		<ul class="s_t">
                		<#if deliver.smsNeed ?? >
			    		<#if deliver.smsNeed ==1 >
                        	<Li class="tz"><span>短信通知联系人：</span><span>${deliver.smsName!}</span><span>${deliver.smsMobile!}</span></Li>
                        </#if>
						</#if>
						<#if deliver.note ?? >
			    		<#if deliver.note !="" >
                            <Li>备注：${deliver.note!}</Li>
						</#if>
						</#if>
                        </ul>
                	</div>
                </div>
                
			</div>
		</#list>	
	</div>
	  
</div> 

<div class="f_b">
    苏ICP备15024526号 ©2016ABCD
</div>
</body>
</html>
