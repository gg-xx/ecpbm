<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" %>
<%
    if(session.getAttribute("admin")==null)
        response.sendRedirect("${pageContext.request.contextPath}/pages/admin_login.jsp");
%>
<html>
<head>
    <title>后台管理首页</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/EasyUI/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/EasyUI/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/EasyUI/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/EasyUI/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/EasyUI/locale/easyui-lang-zh_CN.js"></script>
</head>
<body class="easyui-layout">
    <div data-options="region:'north',border:false" style="height:60px;background: #B3DFDA;padding: 10px">
        <div align="left">
            <div style="font-family: Microsoft YaHei;font-size: 16px;">
                电商平台管理系统
            </div>
        </div>
        <div align="right">
            欢迎您，<font color="red">${sessionScope.admin.name}</font>
        </div>
    </div>
    <div data-options="region:'west',split:true,title:'功能菜单'" style="width: 180px">
        <ul id="tt"></ul>
    </div>
    <div data-options="region:'south',border:false" style="height: 50px;background: #A9FACD;padding: 10px;text-align:center">
        powered by miaoyong
    </div>
    <div data-options="region:'center'">
        <div id="tabs" data-options="fit:true" class="easyui-tabs" style="width: 500px;height: 250px">

        </div>
    </div>
    <script type="text/javascript">
        $('#tt').tree({
            url:'${pageContext.request.contextPath}/admin/getTree?adminid=${sessionScope.admin.id}'
        });
        $('#tt').tree({
            onClick:function (node) {
                if("商品列表"==node.text){
                    if($('#tabs').tabs('exists','商品列表')){
                        $('#tabs').tabs('select','商品列表');
                    }else {
                        $('#tabs').tabs('add',{
                            title:node.text,
                            href:'${pageContext.request.contextPath}/pages/productlist.jsp',
                            closable:true
                        });
                    }
                }else if("商品类型列表" == node.text){
                    if($('#tabs').tabs('exists','商品类型列表')){
                        $('#tabs').tabs('select','商品类型列表');
                    }else {
                        $('#tabs').tabs('add',{
                            title:node.text,
                            href:'${pageContext.request.contextPath}/pages/typelist.jsp',
                            closable:true
                        });
                    }
                }else if("查询订单" == node.text){
                    if($('#tabs').tabs('exists','查询订单')){
                        $('#tabs').tabs('select','查询订单');
                    }else {
                        $('#tabs').tabs('add',{
                            title:node.text,
                            href:'${pageContext.request.contextPath}/pages/searchorder.jsp',
                            closable:true
                        });
                    }
                }else if("创建订单" == node.text){
                    if($('#tabs').tabs('exists','创建订单')){
                        $('#tabs').tabs('select','创建订单');
                    }else {
                        $('#tabs').tabs('add',{
                            title:node.text,
                            href:'${pageContext.request.contextPath}/pages/createorder.jsp',
                            closable:true
                        });
                    }
                }else if("用户列表" == node.text){
                    if($('#tabs').tabs('exists','用户列表')){
                        $('#tabs').tabs('select','用户列表');
                    }else {
                        $('#tabs').tabs('add',{
                            title:node.text,
                            href:'${pageContext.request.contextPath}/pages/userlist.jsp',
                            closable:true
                        });
                    }
                }else if("退出系统" == node.text){
                    $.ajax({
                        url:'${pageContext.request.contextPath}/admin/logout',
                        success:function (data) {
                            window.location.href="${pageContext.request.contextPath}/pages/admin_login.jsp";
                        }
                    })
                }
            }
        });
    </script>
</body>
</html>
<%----%>