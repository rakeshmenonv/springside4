 <%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div data-options="fit:true" class="easyui-panel">
	<div class="easyui-layout" data-options="fit:true">
		<div
			data-options="region:'north',border:false,title:'查询条件',iconCls:'icon-find'"
			style="height: 65px;overflow: hidden;">
			<form id="employeeinfo_list_searchForm" method="post"
				style="width:100%;overflow:hidden;">
				<table class="search_table" style="width: 100%;">
					<tr>
						    						<th><spring:message code="employeeinfo_age" /></th>
						<td><input type="text" name="search_EQ_age"
							value="${ param.search_EQ_age}"
							id="search_EQ_age" /></td>   						<th><spring:message code="employeeinfo_address" /></th>
						<td><input type="text" name="search_EQ_address"
							value="${ param.search_EQ_address}"
							id="search_EQ_address" /></td>        						<th style="width:20%;">&nbsp;<a href="javascript:void(0);"
							id="employeeinfo_list_searchBtn">查询</a>&nbsp;<a
							href="javascript:void(0);" id="employeeinfo_list_clearBtn">清空</a></th>
					</tr>
				</table>
			</form>
		</div>
		<div data-options="region:'center',border:false">
			<table id="employeeinfo_list_dg" style="display: none;"></table>
		</div>
		<div id="employeeinfo_list_toolbar" style="display: none;">
				<a href="javascript:updateForm(employeeinfo_list_create_url,'employeeinfo_form_inputForm',employeeinfo_list_datagrid,{title:'新增信息'});" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:false">添加</a> 			
		  	  <a href="javascript:deleteBatch(employeeinfo_list_delete_url,employeeinfo_list_datagrid);" class="easyui-linkbutton"  data-options="iconCls:'icon-remove',plain:false">删除</a>
			</div> 
	</div>
</div>
<script type="text/javascript">
	//列表DataGrid
	var employeeinfo_list_datagrid;
	//列表DataGrid ID
	var employeeinfo_list_datagrid_id = 'employeeinfo_list_dg';
	//列表查询表单ID
	var employeeinfo_list_searchform_id = 'employeeinfo_list_searchForm';
	//列表toolbar ID
	var employeeinfo_list_toolbar_id = 'employeeinfo_list_toolbar';
	//操作链接
	var employeeinfo_list_create_url =  '${ctx}/create';
	var employeeinfo_list_update_url =  '${ctx}/';
	var employeeinfo_list_delete_url =  '${ctx}/delete';
	var employeeinfo_list_view_url =  '${ctx}/employeeinfo/view/';
	var employeeinfo_list_datagrid_load_url = '${ctx}/findList';
	
	//定义相关的操作按钮
	function employeeinfo_list_actionFormatter(value,row,index){
		var str = '';	
		str += formatString(
				'<img onclick="updateForm(\'{0}\',\'employeeinfo_form_inputForm\',employeeinfo_list_datagrid,{title:\'编辑信息\'});" src="{1}" title="编辑"/>',
				employeeinfo_list_update_url + row.id,
				'${ctx}/static/js/plugins/jquery-easyui-1.3.4/themes/icons/application_form_edit.png');
		str += '&nbsp;';
		str += formatString('<img onclick="deleteOne(\'{0}\',\'{1}\',employeeinfo_list_datagrid);" src="{2}" title="删除"/>',
		                    row.id,employeeinfo_list_delete_url,'${ctx}/static/js/plugins/jquery-easyui-1.3.4/themes/icons/application_form_delete.png');
		str += '&nbsp;';
		str += formatString(
				'<img onclick="view(\'{0}\',\'{1}\');" src="${ctx}/static/js/plugins/jquery-easyui-1.3.4/themes/icons/view.png" title="查看"/>',
				employeeinfo_list_view_url + row.id);
		str += '&nbsp;';
		return str;
	}
	
	//DataGrid字段设置
	var employeeinfo_list_datagrid_columns = [ [
	                    		{field : 'id',title : '编号',width : 150,checkbox : true,align:'center'},
	    	          					{field : 'name',title : '<spring:message code="employeeinfo_name" />',width : 150,align:'center'},
			          					{field : 'age',title : '<spring:message code="employeeinfo_age" />',width : 150,align:'center'},
			          					{field : 'address',title : '<spring:message code="employeeinfo_address" />',width : 150,align:'center'},
			          					{field : 'email',title : '<spring:message code="employeeinfo_email" />',width : 150,align:'center'},
			          					{field : 'phoneNumber',title : '<spring:message code="employeeinfo_phoneNumber" />',width : 150,align:'center'},
			          	                    	{field : 'action',title : '操作',width : 80,align : 'center',formatter : employeeinfo_list_actionFormatter} 
	                    		] ];
	/** 初始化DataGrid,加载数据 **/		
	function employeeinfo_list_loadDataGrid(){		 
		employeeinfo_list_datagrid = $('#'+employeeinfo_list_datagrid_id).datagrid({
			url : employeeinfo_list_datagrid_load_url,
			fit : true,
			border : false,
			fitColumns : true,
			singleSelect : false,
			striped : true,
			pagination : true,
			rownumbers : true,
			idField : 'id',
			pageSize : 15,
			pageList : [ 5, 10,15, 20, 30, 40, 50 ],
			columns : employeeinfo_list_datagrid_columns,
			toolbar:'#'+employeeinfo_list_toolbar_id,
			onLoadSuccess : function() {	
				$(this).datagrid('tooltip');
				$('#'+employeeinfo_list_searchform_id+' table').show();
				$('#'+employeeinfo_list_datagrid_id).show();
				$('#'+employeeinfo_list_toolbar_id).show();
				parent. $ .messager.progress('close');
			}
		});
	}
	$ .parser.onComplete = function() {
		//加载DataGrid数据
		employeeinfo_list_loadDataGrid();	
		//绑定按钮事件
		bindSearchBtn('employeeinfo_list_searchBtn','employeeinfo_list_clearBtn','employeeinfo_list_searchForm',employeeinfo_list_datagrid);
	};
</script>
