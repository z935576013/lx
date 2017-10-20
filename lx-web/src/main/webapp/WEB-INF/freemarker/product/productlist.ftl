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
<#include "/common/producttop.ftl"/>
<div class="main mt">
	<div class="search">
	 <form  name="queryForm" method="post" action="${base}/product/productlist.do">
    	<div class="s_bar"><input type="text" value="${keyword!}" name="keyword" class="text2" /><a onclick="document.queryForm.submit() ;" class="s_btn">搜索</a></div>
        <@page.pageInput PageParam.startNum PageParam.maxCount />
         <a href="${base}/product/edit.do" class="adddate">新增库存</a>
        </form>  
    </div>
    
    <ul class="title titpd">
   		<li class="t4">类型</li>
    	<li class="t3">名称</li>
        <li class="t4">规格</li>
        <li class="t3">数量</li>
        <li class="t4">创建时间</li>
        <li class="t5">操作</li>
    </ul>
  <ul class="xhli">
  <#list list as product >
        <li id="product${product.id!}">
            <ul class="s_tit">
                <li class="t4">
                <#if product.type ??>
                	<#if product.type ==0 >
                	礼品盒
                	<#elseif  product.type==1 >
                	礼品袋
                	<#elseif  product.type==2 >
                	用户手册
                	</#if>
                </#if>
                </li>
                <li class="t3">${product.name!}</li>
                <li class="t4">${product.kind!}</li>
                <li class="t3">${product.num!}</li>
                <li class="t4">${product.createTime!}</li>
                <li class="t5">
                <a href="${base}/product/edit.do?id=${product.id!}" class="check">编辑</a>
                <a onclick="del(${product.id!});" class="export">删除</a>
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
<script type="text/javascript">
function del(id){
	if(confirm('确认删除吗?')){
		$.get("${base}/product/delete.do?id="+id, function(result){
	    	$("#product"+id).remove();
	  	});
	}
}
</script>
</html>
