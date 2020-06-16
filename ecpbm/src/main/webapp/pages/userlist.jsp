<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>用户列表</title>
</head>
<body>
    <table id="userListDg" title="客户列表" class="easyui-datagrid"
           data-options="
                singleSelect: false,
                url: '${pageContext.request.contextPath}/userinfo/list',
                queryParams:{},
                pagination: 'true',
                pageSize:10,
                pageList:[5,10,15],
                rownumbers: 'true',
                fit:true,
				iconCls: 'icon-search',
				toolbar: '#userListTb' ">
        <thead>
        <tr>
            <th data-options="field:'id' , align:'center' , checkbox:'true'">序号</th>
            <th data-options="field:'userName' , width:'100'">登录名</th>
            <th data-options="field:'realName' , width:'80' ,align:'right' ">真实姓名</th>
            <th data-options="field:'sex' , width:'50' ,align:'right' ">性别</th>
            <th data-options="field:'address' , width:'200' ,align:'right' ">住址</th>
            <th data-options="field:'email' , width:'150' ,align:'right' ">邮箱</th>
            <th data-options="field:'regDate' , width:'100' ,align:'right' ">注册日期</th>
            <th data-options="field:'status' , width:'100' ,align:'right' ,formatter:formatStatus ">客户状态</th>
        </tr>
        </thead>
    </table>
    <div id="userListTb" style="padding: 2px 5px;">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true"
           onclick="SetIsEnableUser(1);">启用用户</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
           onclick="SetIsEnableUser(0);">禁用用户</a>

        <div id="searchUserListTb" style="padding: 4px 3px;">
            <form id="searchUserListForm" method="post">
                <div style="padding: 3px">
                    客户名称&nbsp;&nbsp;
                    <input class="easyui-textbox" name="search_userName"
                           id="search_userName" style="width: 110px"/>&nbsp;&nbsp;
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true"
                                    onclick="searchUserInfo();">查找</a>
                </div>
            </form>
        </div>
    </div>
    <script>
        var urls;
        var data;
        function searchUserInfo() {
            var userName = $('#search_userName').textbox('getValue');
            $('#userListDg').datagrid('load',{
                userName:userName
            });
        }
        function SetIsEnableUser(flag) {
            var rows = $("#userListDg").datagrid('getSelections');
            if(rows.length>0){
                $.messager.confirm('Confirm','确认要设置吗？',function (r) {
                   if(r){
                       var uids = "";
                       for(var i = 0;i < rows.length; i++){
                           uids += rows[i].id + ",";
                       }
                       $.post('${pageContext.request.contextPath}/userinfo/setIsEnableUser',{
                          uids:uids,
                          flag:flag
                       },function (result) {
                            if(result.success){
                                $('#userListDg').datagrid('reload');
                                $.messager.show({
                                   title:'提示信息',
                                   msg:'更新'+result.delNums+'条信息状态'+result.message
                                });
                            }else{
                                $.messager.show({
                                    title:'提示信息',
                                    msg:result.message
                                });
                            }
                       },'json');
                   }
                });
            }else {
                $.messager.alert('提示','请选择要启用或者禁用的客户','info');
            }
        }

        function formatStatus(value, row, index) {
            if (row.status == 1) {
                return "启用";
            } else {
                return "禁用";
            }
        }
    </script>
</body>
</html>
