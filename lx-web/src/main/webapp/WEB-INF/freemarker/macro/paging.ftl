<#-- 分页组件

 使用方式：
    <#import "/macro/paging.ftl" as page>
    <@page.pageInput condition.startIndex condition.maxCount />
    <@page.pageList pageIndex=PageParam.pageIndex pageCount=PageParam.pageCount />
、
属性说明：
   startIndex     查询开始记录序号
   maxCount			每页最大显示条数
   
   pageIndex     当前页码
   pageCount	总页码


 -->

<#macro pageInput startIndex=0 maxCount=8 > 
                <input type="hidden" name="startIndex" id="startIndex" class="" value="${startIndex}">
                <input type="hidden" name="maxCount" class="" id="maxCount" value="${maxCount}">
</#macro>


<#macro pageList pageIndex=1 pageCount=0> 

		 <div class="pg">
		 <#if pageIndex gt 1 >
		 
			 <a  class="pre" href="javascript:void(0)"  onclick=" prePage() ; "></a>
		 
		 </#if>
		 
		 
		 
		 <#if pageIndex gt 2 >
		 
			 <a href="javascript:void(0)" onclick=" getPage(${pageIndex-2}) ; " >${pageIndex-2}</a>
		 
		 </#if>
		 
		<#if pageIndex gt 1 >
		 
			 <a  href="javascript:void(0)" onclick=" getPage(${pageIndex-1}) ; " >${pageIndex-1}</a>
		 
		 </#if>
		 
		 <a class="current">${pageIndex}</a>
		 
		 <#if pageIndex lt pageCount>
			 <a  href="javascript:void(0)" onclick=" getPage(${pageIndex+1}) ; " >${pageIndex+1}</a>
		 </#if>
		 
		 <#if pageIndex+1 lt pageCount>
		 	<a  href="javascript:void(0)" onclick=" getPage(${pageIndex+2}) ; " >${pageIndex+2}</a>
		 </#if>
		 <#if pageIndex lt pageCount>
		 	<a href="javascript:void(0)"  onclick=" nextPage() ; "  class="next" ></a>
		  </#if>
		 </div>
		 
		 
 </#macro>