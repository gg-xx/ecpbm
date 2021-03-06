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
    <table id="odbox" ></table>
<div id="ordertb" style="padding: 2px 5px;">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true"
       onclick="addOrderDetail();">添加订单</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
       onclick="removeOrderDetail();">删除订单</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true"
       onclick="saveOrder();">保存订单</a>

    <div id="divOrderInfo" style="padding: 2px 5px;">
            <div style="padding: 3px">
                客户名称&nbsp;&nbsp;<input style="width: 115px;" class="easyui-combobox" name="create_uid" id="create_uid" value="0"
                data-options="valueField:'id',textField:'userName',url:'${pageContext.request.contextPath}/userinfo/getValidUser'"/>&nbsp;&nbsp;
                订单金额&nbsp;&nbsp;<input  type="text" class="easyui-textbox" name="create_orderprice"
                                       id="create_orderprice" readonly="readonly" style="width: 115px"/>&nbsp;&nbsp;
            </div>
            <div style="padding: 3px">
                订单日期&nbsp;&nbsp;<input type="text"  name="create_ordertime" id="create_ordertime" style="width: 115px;" class="easyui-datebox" value="<%=new Date().toLocaleString()%>"/>&nbsp;&nbsp;
                订单状态&nbsp;&nbsp;<select id="create_status" name="create_status" class="easyui-combobox" style="width: 115px">
                                    <option value="未付款" selected>未付款</option>
                                    <option value="已付款">已付款</option>
                                    <option value="待发货">待发货</option>
                                    <option value="已发货">已发货</option>
                                    <option value="已完成">已完成</option>
                                  </select>
            </div>
    </div>
</div>

<script type="text/javascript">
    var $odbox = $('#odbox');
    $(function () {
        $('#odbox').datagrid({
            rownumbers:true,
            singleSelect:false,
            fit:true,
            toolbar:'#ordertb',
            columns:[[{
                title:'序号',
                field:'',
                align:'center',
                checkbox:true
            },{
                field:'pid',
                title:'商品名称',
                width:300,
                editor:{
                    type:'combobox',
                    options:{
                        valueField:'id',
                        textField:'name',
                        url:'${pageContext.request.contextPath}/productinfo/getOnSaleProduct',
                        onChange:function (newValue,oldValue) {
                            var rows = $odbox.datagrid('getRows');
                            var orderprice = 0;
                            for(var i = 0;i<rows.length;i++){
                                var pidEd = $('#odbox').datagrid('getEditor',{
                                    index:i,
                                    field:'pid'
                                });
                                var priceEd = $('#odbox').datagrid('getEditor',{
                                    index:i,
                                    field:'price'
                                });
                                var totalpriceEd = $('#odbox').datagrid('getEditor',{
                                    index:i,
                                    field:'totalprice'
                                });
                                var numEd = $('#odbox').datagrid('getEditor',{
                                    index:i,
                                    field:'num'
                                });
                                if(pidEd!=null){
                                    var pid = $(pidEd.target).combobox('getValue');
                                    $.ajax({
                                        type:'POST',
                                        url:'${pageContext.request.contextPath}/productinfo/getPriceById',
                                        data:{pid:pid},
                                        success:function (result) {
                                            $(priceEd.target).numberbox('setValue',result);
                                            $(totalpriceEd.target).numberbox('setValue',result*$(numEd.target).numberbox('getValue'));
                                            orderprice=Number(orderprice)+Number($(totalpriceEd.target).numberbox('getValue'));
                                        },
                                        dataType:'json',
                                        async:false
                                    });
                                }
                            }
                            $('#create_orderprice').textbox('setValue',orderprice);
                        }
                    }
                }
            },{
                field:'price',
                title:'单价',
                width:80,
                editor: {
                    type: 'numberbox',
                    options: {
                        editable:false
                    }
                }
            },{
                field:'num',
                title:'数量',
                width:50,
                editor: {
                    type: 'numberbox',
                    options: {
                        onChange:function (newValue,oldValue) {
                            var rows = $odbox.datagrid('getRows');
                            var orderprice = 0;
                            for(var i = 0;i<rows.length;i++){
                                var priceEd = $('#odbox').datagrid('getEditor',{
                                    index:i,
                                    field:'price'
                                });
                                var totalpriceEd = $('#odbox').datagrid('getEditor',{
                                    index:i,
                                    field:'totalprice'
                                });
                                var numEd = $('#odbox').datagrid('getEditor',{
                                    index:i,
                                    field:'num'
                                });
                                $(totalpriceEd.target).numberbox('setValue',$(priceEd.target).numberbox('getValue')*$(numEd.target).numberbox('getValue'));
                                orderprice=Number(orderprice)+Number($(totalpriceEd.target).numberbox('getValue'));
                            }
                            $('#create_orderprice').textbox('setValue',orderprice);
                        }
                    }
                }
            },{
                field:'totalprice',
                title:'小计',
                width:100,
                editor: {
                    type: 'numberbox',
                    options: {
                        editable:false
                    }
                }
            }]]
        });
    });

    function addOrderDetail() {
        $odbox.datagrid('appendRow',{
            num:1,
            price:0,
            totalprice:'0'
        });
        var rows = $odbox.datagrid('getRows');
        $odbox.datagrid('beginEdit',rows.length - 1);
    }

    function removeOrderDetail() {
        var rows = $odbox.datagrid('getSelections');
        if(rows.length>0){
            var create_orderprice = $('#create_orderprice').textbox('getValue');
            for(var i=0;i<rows.length;i++){
                var index = $odbox.datagrid('getRowIndex',rows[i]);
                var totalpriceEd = $('#odbox').datagrid('getEditor',{
                    index:index,
                    field:'totalprice'
                });
                create_orderprice = create_orderprice-Number($(totalpriceEd.target).numberbox('getValue'));
                $odbox.datagrid('deleteRow',index);
            }
            $('#create_orderprice').textbox('setValue',create_orderprice);
        }else {
            $.messager.alert('提示','请选择要删除的行',info);
        }
    }

    function saveOrder() {
        var uid=$('#create_uid').combobox('getValue');
        if(uid==0){
            $.messager.alert('提示','请选择客户名称','info');
        }else {
            create_endEdit();
            var orderinfo = [];
            var ordertime = $('#create_ordertime').datebox('getValue');
            var status = $('#create_status').datebox('getValue');
            var orderprice = $('#create_orderprice').textbox('getValue');
            orderinfo.push({
               ordertime:ordertime,
               uid:uid,
               status:status,
               orderprice:orderprice
            });
            if($odbox.datagrid('getChanges').length){
                var inserted = $odbox.datagrid('getChanges','inserted');
                var deleted = $odbox.datagrid('getChanges','deleted');
                var updated = $odbox.datagrid('getChanges','updated');
                var effectRow = new Object();
                if(inserted.length){
                    effectRow['inserted']=JSON.stringify(inserted);
                }
                effectRow['orderinfo']=JSON.stringify(orderinfo);
                $.post(
                    '${pageContext.request.contextPath}/orderinfo/commitOrder',
                    effectRow,
                    function (data) {
                        if(data){
                            $.messager.alert('提示','创建成功！');
                            $odbox.datagrid('acceptChanges');
                            if($('#tabs').tabs('exists','创建订单')){
                                $('#tabs').tabs('close','创建订单');
                            }
                            $('#orderDg').datagrid('reload');
                        }else{
                            $.messager.alert('提示','创建失败！');
                        }
                    });
            }
        }
    }

    function create_endEdit() {
        var rows = $odbox.datagrid('getRows');
    }
</script>
</body>
</html>

<%--    <table id="odbox" title="订单信息" class="easyui-datagrid" fitColumns="true"--%>
<%--           data-options="--%>
<%--				iconCls: 'icon-edit',--%>
<%--				pagination: 'true',--%>
<%--				rownumbers: 'true',--%>
<%--				singleSelect: false,--%>
<%--				toolbar: '#ordertb',--%>
<%--&lt;%&ndash;				url: '${pageContext.request.contextPath}/productinfo/getOnSaleProduct',&ndash;%&gt;--%>
<%--				method: 'post'">--%>
<%--        <thead>--%>
<%--            <tr>--%>
<%--                <th data-options="field:'' , align:'center' , checkbox:'true'">序号</th>--%>
<%--                <th data-options="field:'id',width:200,--%>
<%--						formatter:function(value,row){--%>
<%--							return row.name;--%>
<%--						},--%>
<%--						editor:{--%>
<%--							type:'combobox',--%>
<%--							options:{--%>
<%--								valueField:'id',--%>
<%--								textField:'name',--%>
<%--								method:'post',--%>
<%--								url:'${pageContext.request.contextPath}/productinfo/getOnSaleProduct',--%>
<%--								required:true--%>
<%--							}--%>
<%--						}">商品名称</th>--%>

<%--                <th data-options="field:'price' , width:'80' ,align:'right',editor:'numberbox'">单价</th>--%>
<%--                <th data-options="field:'num' , width:'50' ,align:'right',editor:'numberbox' ">数量</th>--%>
<%--                <th data-options="field:'totalprice' , width:'100' ,align:'right',editor:'numberbox' ">小计</th>--%>

<%--            </tr>--%>
<%--        </thead>--%>
<%--    </table>--%>