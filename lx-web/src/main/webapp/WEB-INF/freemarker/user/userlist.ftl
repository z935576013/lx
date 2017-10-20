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
<#include "/common/usertop.ftl"/>
<div class="main mt">
	<div class="search">
	 <form  name="queryForm" method="post" action="${base}/user/userlist.do">
    	<div class="s_bar"></div>
        <@page.pageInput PageParam.startNum PageParam.maxCount />
         <a href="${base}/user/edit.do" class="adddate">新增账号</a>
        </form>  
    </div>
    
    <ul class="title titpd">
   		<li class="t4">账号</li>
    	<li class="t3">所属单位</li>
        <li class="t4">类型</li>
    </ul>
  <ul class="xhli">
  <#list list as user >
        <li id="user${user.id!}">
            <ul class="s_tit">
                <li class="t3">${user.userName!}</li>
                <li class="t4">${user.company!}</li>
                <li class="t4">
                <#if user.userType ??>
                    <#if user.userType ==0 >
                    管理员
                    <#elseif  user.userType==1 >
                    普通用户
                    <#elseif  user.userType==2 >
                    仓库管理员
                    </#if>
                </#if>
                </li>
                <li class="t5">
                <a href="${base}/user/edit.do?id=${user.id!}" class="check">编辑</a>
                <a onclick="del(${user.id!});" class="export">删除</a>
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
		$.get("${base}/user/delete.do?id="+id, function(result){
	    	$("#user"+id).remove();
	  	});
	}
}
</script>
</html>
