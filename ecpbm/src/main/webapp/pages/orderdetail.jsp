<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%><html>
<head>
    <title>Title</title>
</head>
<body>
<table id="editodbox"></table>

<div id="editordertb" style="padding: 2px 5px;">
    <div id="editdivOrderInfo" style="padding: 2px 5px;">
        <div style="padding: 3px">
            客户名称&nbsp;&nbsp;
            <input type="text"  name="edit_uid" id="edit_uid" style="width: 115px;"
                   class="easyui-textbox" value="${oi.ui.userName }"
                   readonly="readonly"/>&nbsp;&nbsp;
            订单金额&nbsp;&nbsp;
            <input type="text"  name="edit_orderprice" id="edit_orderprice" style="width: 115px;"
                   class="easyui-textbox" value="${oi.orderprice }"
                   readonly="readonly"/>&nbsp;&nbsp;
            订单日期&nbsp;&nbsp;
            <input style="width: 115px;" class="easyui-datebox" name="edit_ordertime" id="edit_ordertime"
                   readonly="readonly" value="${oi.orderprice }"/>
            订单状态&nbsp;&nbsp;
            <input style="width: 115px;" class="easyui-textbox" name="edit_status" id="edit_status"
                   readonly="readonly" value="${oi.status }"/>
        </div>
    </div>
</div>
<script type="text/javascript">
    var $editodbox=$('#editodbox');
    $(function () {
        $editodbox.datagrid({
            url:'${pageContext.request.contextPath}/orderinfo/getOrderDetails?oid=${requestScope.oi.id }',
            rownumbers:true,
            singleSelect:false,
            fit:true,
            toolbar:'#editordertb',
            columns:[[{
                field:'pid',
                title:'商品名称',
                width:300,
                formatter:function (value,row,index) {
                    if(row.pi){
                        return row.pi.name;
                    }else {
                        return value;
                    }
                }
            },{
                field:'price',
                title:'单价',
                width:80,
            },{
                field:'num',
                title:'数量',
                width:50,
            },{
                field:'totalprice',
                title:'小计',
                width:100,
            }]]
        });
    });
</script>
</body>
</html>
