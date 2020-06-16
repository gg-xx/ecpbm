<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>商品类型列表</title>
</head>
<body>
<table id="typeListDg" title="商品类型列表" class="easyui-datagrid"
       data-options="
                singleSelect: false,
                url: '${pageContext.request.contextPath}/type/list',
                queryParams:{},
                pagination: 'true',
                pageSize:10,
                pageList:[5,10,15],
                rownumbers: 'true',
                fit:true,
				iconCls: 'icon-edit',
				toolbar: '#typeListTb' ">
    <thead>
    <tr>
        <th data-options="field:'id' , align:'center' , checkbox:'true'">序号</th>
        <th data-options="field:'name' , width:'100'">类型名称</th>
    </tr>
    </thead>
</table>
<div id="typeListTb" style="padding: 2px 5px;">

    <div id="searchTypeListTb" style="padding: 4px 3px;">
        <form id="searchTypeListForm" method="post">
            <div style="padding: 3px">
                类型名称&nbsp;&nbsp;
                <input class="easyui-textbox" name="search_typeName"
                       id="search_typeName" style="width: 110px"/>&nbsp;&nbsp;
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true"
                   onclick="searchType();">查找</a>
            </div>
        </form>
    </div>
</div>
<script>
    var urls;
    var data;
    function searchType() {
      $.messager.alert('提示','别查了，有啥好查的','info');
    }
</script>
</body>
</html>
