<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (session.getAttribute("admin") == null)
        response.sendRedirect("${pageContext.request.contextPath}/pages/admin_login.jsp");
%>
<html>
<head>
    <title>商品列表</title>
</head>
<body>
<table id="dg_productinfo" title="商品信息" class="easyui-datagrid" fitColumns="true"
       pagination="true" rownumbers="true" url="${pageContext.request.contextPath}/productinfo/list" fit="true"
       toolbar="#tb_productinfo">
    <thead>
    <tr>
        <th data-options="field:'id' , align:'center' , checkbox:'true'"></th>
        <th data-options="field:'name' , width:'200' ">商品名称</th>
        <th data-options="field:'type' , width:'100' , formatter:formatType ">商品类型</th>
        <th data-options="field:'status' , width:'100' , formatter:formatStatus ">商品状态</th>
        <th data-options="field:'code' , width:'100' ">商品编码</th>
        <th data-options="field:'brand' , width:'120' ">品牌</th>
        <th data-options="field:'price' , width:'50' ">价格</th>
        <th data-options="field:'num' , width:'50' ">库存</th>
        <th data-options="field:'intro' , width:'450' ">商品描述</th>
    </tr>
    </thead>
</table>
<div id="tb_productinfo" style="padding: 2px 5px;">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true"
       onclick="addProduct();">添加</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true"
       onclick="editProduct();">修改</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
       onclick="removeProduct();">删除</a>

    <div id="searchtb_productinfo" style="padding: 2px 5px;">
        <form id="searchForm_productinfo" method="post">
            <div style="padding: 3px">
                商品编号&nbsp;&nbsp;<input class="easyui-textbox" name="productinfo_search_code"
                                       id="productinfo_search_code" style="width: 110px"/>
                商品名称&nbsp;&nbsp;<input class="easyui-textbox" name="productinfo_search_name"
                                       id="productinfo_search_name" style="width: 110px"/>
                商品类型&nbsp;&nbsp;<input class="easyui-combobox" name="productinfo_search_tid" id="productinfo_search_tid"
                                       style="width:110px" data-options="valueField:'id',textField:'name',
                                       url:'${pageContext.request.contextPath}/type/getType/1'" value="0"/>
                商品品牌&nbsp;&nbsp;<input class="easyui-textbox" name="productinfo_search_brand"
                                       id="productinfo_search_brand" style="width: 110px"/>
                商品价格&nbsp;&nbsp;<input class="easyui-numberbox" name="productinfo_search_priceFrom"
                                       id="productinfo_search_priceFrom" style="width: 80px"/>~
                <input class="easyui-numberbox" name="productinfo_search_priceTo" id="productinfo_search_priceTo"
                       style="width: 80px"/>
                &nbsp;&nbsp; <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true"
                                onclick="searchProduct();">查找</a>
            </div>
        </form>
    </div>
</div>

<div id="dlg_productinfo" class="easyui-dialog" title="添加商品" closed="true" style="width: 500px;">
    <div style="padding: 10px 60px 20px 60px">
        <form id="ff_productinfo" method="post" enctype="multipart/form-data" >
            <table cellpadding="5">
                <tr>
                    <td>商品状态:</td>
                    <td><select id="status" class="easyui-combobox" name="status" style="width: 150px;">
                        <option value="1">在售</option>
                        <option value="0">下架</option>
                    </select></td>
                </tr>
                <tr>
                    <td>商品类型:</td>
                    <td><input class="easyui-combobox" id="type.id" name="name.id"
                               data-options="valueField:'id',textField:'name',url:'${pageContext.request.contextPath}/type/getType/0'"/>
                    </td>
                </tr>
                <tr>
                    <td>商品名称:</td>
                    <td><input class="easyui-textbox" type="text" id="name" name="name" data-options="required:true"/>
                    </td>
                </tr>
                <tr>
                    <td>商品编码:</td>
                    <td><input class="easyui-textbox" type="text" id="code" name="code" data-options="required:true"/>
                    </td>
                </tr>
                <tr>
                    <td>商品品牌:</td>
                    <td><input class="easyui-textbox" type="text" id="brand" name="brand" data-options="required:true"/>
                    </td>
                </tr>
                <tr>
                    <td>商品数量:</td>
                    <td><input class="easyui-textbox" type="text" id="num" name="num" data-options="required:true"/>
                    </td>
                </tr>
                <tr>
                    <td>商品价格:</td>
                    <td><input class="easyui-textbox" type="text" id="price" name="price" data-options="required:true"/>
                    </td>
                </tr>
                <tr>
                    <td>商品描述:</td>
                    <td><input class="easyui-textbox" id="intro" name="intro" data-options="multiline:true"
                               style="height: 60px"/></td>
                </tr>
                <tr>
                    <td>商品图片:</td>
                    <td><input class="easyui-filebox" id="file" name="file" style="width: 200px;" value="选择图片"/></td>
                </tr>
            </table>
        </form>
        <div style="test-align:center;padding: 5px">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveProduct();">保存</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearFrom();">清空</a>
        </div>
    </div>
</div>

<script type="text/javascript">
    var urls;
    var data;

    function removeProduct() {
        var rows = $('#dg_productinfo').datagrid('getSelections');
        if(rows.length>0){
            $.messager.confirm("系统提示","您确认要下架这<font color=red>"+rows.length+"</font>条数据吗？",function(r){
                if(r){
                    var ids = "";
                    for(var i=0;i<rows.length;i++){
                        ids += rows[i].id + ",";
                    }
                    $.post("${pageContext.request.contextPath}/productinfo/deleteProduct",{
                        id:ids,
                        flag:0
                    },function(result){
                        if(result.success){
                            $.messager.alert("系统提示","您已成功下架<font color=red>"+result.delNums+"</font>条数据！");
                            $("#dg_productinfo").datagrid("reload");
                        }else{
                            $.messager.alert('系统提示',result.message);
                        }
                    },"json");
                }
            });
        }
    }

    function searchProduct(){
        var productinfo_search_code;
        if($('#productinfo_search_code').val()!=null&&$('#productinfo_search_code').val()!=""){
            productinfo_search_code=$('#productinfo_search_code').val();
        }
        var productinfo_search_name;
        if($('#productinfo_search_name').val()!=null&&$('#productinfo_search_name').val()!=""){
            productinfo_search_name=$('#productinfo_search_name').val();
        }
        var productinfo_search_tid;
        if($('#productinfo_search_tid').val()!=null&&$('#productinfo_search_tid').val()!=""){
            productinfo_search_tid=$('#productinfo_search_tid').combobox('getValue');
        }else {
            productinfo_search_tid="0";
        }
        var productinfo_search_brand;
        if($('#productinfo_search_brand').val()!=null&&$('#productinfo_search_brand').val()!=""){
            productinfo_search_brand=$('#productinfo_search_brand').val();
        }
        var productinfo_search_priceFrom;
        if($('#productinfo_search_priceFrom').val()!=null&&$('#productinfo_search_priceFrom').val()!=""){
            productinfo_search_priceFrom=$('#productinfo_search_priceFrom').val();
            //textbox("getValue");
        }else {
            productinfo_search_priceFrom="0";
        }
        var productinfo_search_priceTo;
        if($('#productinfo_search_priceTo').val()!=null&&$('#productinfo_search_priceTo').val()!=""){
            productinfo_search_priceTo=$('#productinfo_search_priceTo').val();
        }else {
            productinfo_search_priceTo="0";
        }
        $('#dg_productinfo').datagrid('load',{
             // 'code':productinfo_search_code,
             'name':productinfo_search_name,
             'type.id':productinfo_search_tid,
             'brand':productinfo_search_brand,
             'priceFrom':productinfo_search_priceFrom,
             'priceTo':productinfo_search_priceTo
        });
    }

    function addProduct() {
        $('#dlg_productinfo').dialog('open').dialog('setTitle','新增商品');
        $('#dlg_productinfo').form('clear');
        urls = '${pageContext.request.contextPath}/productinfo/addProduct';
    }
    function editProduct(){
        var rows = $("#dg_productinfo").datagrid('getSelections');
        if(rows.length==1){
            var row = $("#dg_productinfo").datagrid('getSelected');
            if(row){
                $("#dlg_productinfo").dialog('open').dialog('setTitle','修改商品信息');
                $('#ff_productinfo').form('load',{
                   "type.id":row.type.id,
                   "name":row.name,
                   "code":row.code,
                    "brand":row.brand,
                    "num":row.num,
                    "price":row.price,
                    "intro":row.intro,
                    "status":row.status
                });
                urls = '${pageContext.request.contextPath}/productinfo/updateProduct?id='+row.id;
            }
        }else{
            $.messager.alert('提示',"请选择要修改的行",'intro');
        }
    }

    function saveProduct() {
        $('#ff_productinfo').form('submit',{
           url:urls,
           success:function (result) {
                var result = eval('('+result+')');
                if(result.success=='true'){
                    $('#dg_productinfo').datagrid('reload');
                    $('#dlg_productinfo').dialog('close');
                }
                $.messager.show({
                    title:'提示信息',
                    msg:result.message
                });
           }
        });
    }

</script>
<script>
    function formatType(value, row, index) {
        if (row.type) {
            return row.type.name;
        } else {
            return value;
        }
    }

    function formatStatus(value, row, index) {
        if (row.status == 1) {
            return "在售";
        } else {
            return "下架";
        }
    }
</script>
</body>
</html>
<%--$(function () {--%>
<%--    $('#dg_productinfo').datagrid({--%>
<%--        singleSelect:false,--%>
<%--        url:'${pageContext.request.contextPath}/productinfo/list',--%>
<%--        pagination:true,--%>
<%--        pageSize:10,--%>
<%--        pagetList:[10,15,20],--%>
<%--        rownumbers:true,--%>
<%--        fit:true,--%>
<%--        toolbar:'#tb_productinfo',--%>
<%--        header:'#searchtb_productinfo',--%>
<%--        columns:[[{--%>
<%--            title:'序号',--%>
<%--            field:'id',--%>
<%--            align:'center',--%>
<%--            checkbox:true--%>
<%--        },{--%>
<%--            field: 'name',--%>
<%--            title: '商品名称',--%>
<%--            width: 200--%>
<%--        },{--%>
<%--            field: 'type',--%>
<%--            title: '商品类型',--%>
<%--            formatter : function(value,row,index) {--%>
<%--                if(row.type){--%>
<%--                    return row.type.name;--%>
<%--                }else{--%>
<%--                    return value;--%>
<%--                }--%>
<%--            },--%>
<%--            width: 60--%>
<%--        },{--%>
<%--            field:'status',--%>
<%--            title:'商品状态',--%>
<%--            formatter:function(value,row,index) {--%>
<%--                if(row.status==1){--%>
<%--                    return "在售";--%>
<%--                }else{--%>
<%--                    return "下架";--%>
<%--                }--%>
<%--            },--%>
<%--            width:60--%>
<%--        },{--%>
<%--            field:'code',--%>
<%--            title:'商品编码',--%>
<%--            width:100--%>
<%--        },{--%>
<%--            field:'brand',--%>
<%--            title:'品牌',--%>
<%--            width:120--%>
<%--        },,{--%>
<%--            field:'price',--%>
<%--            title:'价格',--%>
<%--            width:50--%>
<%--        },,{--%>
<%--            field:'num',--%>
<%--            title:'库存',--%>
<%--            width:50--%>
<%--        },{--%>
<%--            field:'intro',--%>
<%--            title:'商品描述',--%>
<%--            width:450--%>
<%--        }]]--%>
<%--    });--%>
<%--});--%>
