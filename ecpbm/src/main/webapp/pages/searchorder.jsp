<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>订单列表</title>
</head>

<body>
    <table id="orderDg" title="订单查询" class="easyui-datagrid"
           data-options="
                singleSelect: false,
                url: '${pageContext.request.contextPath}/orderinfo/list',
                queryParams:{},
                pagination: 'true',
                pageSize:10,
                pageList:[5,10,15],
                rownumbers: 'true',
                fit:true,
				iconCls: 'icon-search',
				toolbar: '#orderTb' ">
        <thead>
            <tr>
                <th data-options="field:'id' , align:'center' , checkbox:'true'">序号</th>
                <th data-options="field:'ui' , width:'100' ,align:'right',formatter:formatUi ">订单客户</th>
                <th data-options="field:'status' , width:'80' ,align:'right' ">订单状态</th>
                <th data-options="field:'ordertime' , width:'100' ,align:'right' ">订单时间</th>
                <th data-options="field:'orderprice' , width:'100' ,align:'right' ">订单金额</th>
            </tr>
        </thead>
    </table>

<div id="orderTb" style="padding: 2px 5px;">
    <a href="javascript:void(0)" class="easyui-linkbutton"
       iconCls="icon-edit" plain="true"
       onclick="editOder();">查看明细</a>
    <a href="javascript:void(0)" class="easyui-linkbutton"
       iconCls="icon-remove" plain="true"
       onclick="removeOrder();">删除订单</a>

    <div id="searchOrderTb" style="padding: 2px 5px;">
        <div style="padding: 3px">
            订单编号&nbsp;&nbsp;<input style="width: 110px;" class="easyui-txetbox" name="search_oid" id="search_oid" />&nbsp;&nbsp;
            客户名称&nbsp;&nbsp;<input type="text"  name="search_uid" id="search_uid" style="width: 115px;"
                                   class="easyui-combobox" value="0"
                                   data-options="valueField:'id',textField:'userName',url:'${pageContext.request.contextPath}/userinfo/getValidUser'"/>&nbsp;&nbsp;
            订单状态&nbsp;&nbsp;<select id="search_status" name="search_status" class="easyui-combobox" style="width: 115px">
            <option value="请选择" selected>请选择</option>
            <option value="未付款">未付款</option>
            <option value="已付款">已付款</option>
            <option value="待发货">待发货</option>
            <option value="已发货">已发货</option>
            <option value="已完成">已完成</option>
        </select>&nbsp;&nbsp;
            订单时间&nbsp;&nbsp;<input style="width: 115px;" class="easyui-datebox" name="orderTimeFrom" id="orderTimeFrom" />~
                               <input style="width: 115px;" class="easyui-datebox" name="orderTimeTo" id="orderTimeTo" />
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true"
               onclick="searchOrderInfo();">查找</a>
        </div>
    </div>
</div>
    <script>
        var urls;
        var data;
        function removeOrder() {
            var rows = $('#orderDg').datagrid('getSelections');
            if(rows.length>0){
                $.messager.confirm("系统提示","您确认要删除这<font color=red>"+rows.length+"</font>条数据吗？",function(r){
                    if(r){
                        var ids = "";
                        for(var i=0;i<rows.length;i++){
                            ids += rows[i].id + ",";
                        }
                        $.post("${pageContext.request.contextPath}/orderinfo/deleteOrder",{
                            oids:ids,
                        },function(result){
                            if(result.success){
                                $.messager.alert("系统提示","您已成功删除<font color=red>"+result.delNums+"</font>条数据！");
                                $("#orderDg").datagrid("reload");
                            }else{
                                $.messager.alert('系统提示',result.message);
                            }
                        },"json");
                    }
                });
            }else{
                $.messager.alert('提示','请选择要删除的行','intro')
            }
        }

        function editOder(){
            var rows = $("#orderDg").datagrid('getSelections');
            if(rows.length > 0){
                var row = $("#orderDg").datagrid('getSelected');
                if($('#tabs').tabs('exists','订单明细')){
                    $('#tabs').tabs('close','订单明细');
                }
                $('#tabs').tabs('add',{
                    title:'订单明细',
                    href:'${pageContext.request.contextPath}/orderinfo/getOrderInfo?oid=' + row.id,
                    closable:true
                });
            }else{
                $.messager.alert('提示','请选择要查看的订单','intro');
            }
        }

        function searchOrderInfo() {
            var oid = $('#search_oid').val();
            var status = $('#search_status').combobox('getValue');
            var uid = $('#search_uid').combobox('getValue');
            var orderTimeFrom = $('#orderTimeFrom').datebox('getValue');
            var orderTimeTo = $('#orderTimeTo').datebox('getValue');
            $('#orderDg').datagrid('load',{
                //id:oid,
                status:status,
                uid:uid,
                orderTimeFrom:orderTimeFrom,
                orderTimeTo:orderTimeTo
            });
        }


    </script>
<script>
    function formatUi(value, row, index) {
        if (row.ui) {
            return row.ui.userName;
        } else {
            return value;
        }
    }
</script>
</body>
</html>