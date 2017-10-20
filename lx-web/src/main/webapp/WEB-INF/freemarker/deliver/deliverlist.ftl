<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>发货单</title>
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
<#include "/common/delivertop.ftl"/>
<div class="main mt">
	<div class="search">
	 <form  name="queryForm" method="post" action="${base}/deliver/deliverlist.do">
    	<div class="s_bar"><input type="text" value="${keyword!}" name="keyword" class="text2" /><a onclick="document.queryForm.submit() ;" class="s_btn">搜索</a></div>
        <span>“需货单号、品名、规格、日期（如：2016/09/12）”</span>
        <a target="_blank" href="${base}/deliver/export.do" class="exportexcel">导出</a>
        <@page.pageInput PageParam.startNum PageParam.maxCount />
        </form>  
    </div>
    
    <ul class="title titpd">
   		<li class="t6">品名</li>
    	<li class="t3">规格（盒/瓶）</li>
        <li class="t4">需货单号</li>
        <li class="t3">需货单位</li>
        <li class="t4">发往地</li>
        <li class="t5">操作</li>
    </ul>
  <ul class="xhli">
  <#list list as order >
        <li>
            <ul class="s_tit">
                <li class="t4">${order.productName!}</li>
                <li class="t3">${order.spec!}</li>
                <li class="t4">${order.needOrderCode!}</li>
                <li class="t3">${order.needCompany!}</li>
                <li class="t4">${order.deliverCity!}</li>
                <li class="t5">
                <a href="${base}/deliver/edit.do?id=${order.id!}" class="check">编辑</a>
                <a href="${base}/deliver/deliverDetail.do?needOrderItemId=${order.needOrderItemId!}" class="check">查看</a>
                </li>
            </ul>
        </li>
     </#list>   
    </ul>
     <div class="page">
        <@page.pageList pageIndex=PageParam.pageIndex pageCount=PageParam.pageCount />
    	
    </div>
	</div>
    
    
</div> 

    
<div class="f_b">
    苏ICP备15024526号 ©2016ABCD
</div>
</body>
</html>
