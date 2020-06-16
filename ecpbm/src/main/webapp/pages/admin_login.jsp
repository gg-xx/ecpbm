<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>电子商务平台——后台登录页</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/EasyUI/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/EasyUI/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/EasyUI/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/EasyUI/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/EasyUI/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
    <script type="text/javascript">
        function clearFrom() {
            $('adminLoginFrom').form('clear');
        }

        function checkAdminLogin() {
            $('#adminLoginForm').form("submit",{
                url : '${pageContext.request.contextPath}/admin/login',
                success : function (result) {
                    var result = eval("("+result+")");
                    if(result.success == 'true'){
                        window.location.href = '${pageContext.request.contextPath}/pages/admin.jsp';
                        $('#adminLoginDlg').dialog('close');
                    }else{
                        $.messager.show({
                            title : '提示信息',
                            msg : result.message
                        });
                    }
                }
            });
        }
    </script>
    <div id="adminLoginDlg" class="easyui-dialog" title="后台登录"
         data-options="iconCls:'icon-save',buttons: '#bb'"
         style="left: 550px;top: 200px;width: 300px;height: 200px">
        <form id="adminLoginForm" method="post">
            <table style="margin: 20px;font-size: 13px;">
                <tr>
                    <th>用户名</th>
                    <td><input class="easyui-textbox" type="text" id="name" name="name"
                               data-options="required:true" ></td>
                </tr>
                <tr>
                    <th>密码</th>
                    <td><input class="easyui-textbox" type="password" id="pwd" name="pwd"
                               data-options="required:true"></td>
                </tr>
            </table>
        </form>

    </div>
    <div id="bb">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="checkAdminLogin();">登录</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearFrom();">重置</a>
    </div>
</body>
</html>
