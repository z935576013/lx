<div class="topmenu">
    <a onclick="location='${base}/need/needlist.do'" >需货单</a>
    <a onclick="location='${base}/deliver/deliverlist.do'">发货单</a>
    <h3 onclick="location='${base}/product/productlist.do'">库存</h3>
    <#if sessionUser.userType==0>
    <a onclick="location='${base}/user/userlist.do'">账号</a>
    </#if>
     <a onclick="location='${base}/item/itemlist.do'">商品维护</a>
    <a onclick="location='${base}/contact/contactlist.do'">联系人维护</a>
</div>
