<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
var employeeinfo_form_inputform_id = 'employeeinfo_form_inputForm';

$ .parser.onComplete = function() {
		parent. $ .messager.progress('close');
		$('#'+employeeinfo_form_inputform_id).form(
				{
					onSubmit : function() {
						parent. $ .messager.progress({
							title : '提示',
							text : '数据处理中，请稍后....'
						});
						var isValid = $(this).form('validate');
						if (!isValid) {
							parent. $ .messager.progress('close');
						}
						return isValid;
					},
					success : function(result) {
						parent. $ .messager.progress('close');
						result = $ .parseJSON(result);
						if (result.success) {
							parent. $ .modalDialog.openner_dataGrid
									.datagrid('reload');
							parent. $ .modalDialog.openner_dataGrid.datagrid(
									'uncheckAll').datagrid('unselectAll')
									.datagrid('clearSelections');
							parent. $ .modalDialog.handler.dialog('close');
							$ .messager.show({ // show error message
								title : '提示',
								msg : result.message
							});
						} else {
							$ .messager.alert('错误', result.message, 'error');
						}
					}
				});

	} ;
</script>

<form:form id="employeeinfo_form_inputForm" name="employeeinfo_form_inputForm" action="${ctx}/${action}"
		 modelAttribute="employeeinfo" method="post" class="form-horizontal">
	<input type="hidden" name="id" id="id" value="${ employeeinfo.id}" />
	<table class="content" style="width: 100%;">
	 		<tr>
			<td class="biao_bt3"><spring:message code="employeeinfo_name" /></td>
			<td><input type="text" name="name" id="name" value="${ employeeinfo.name }" class="easyui-validatebox" data-options="missingMessage:'<spring:message code="employeeinfo_name" />不能为空.',required:true"   />	</td>
		</tr>
	  		<tr>
			<td class="biao_bt3"><spring:message code="employeeinfo_age" /></td>
			<td><input type="text" name="age" id="age" value="${ employeeinfo.age }" class="easyui-validatebox" data-options="missingMessage:'<spring:message code="employeeinfo_age" />不能为空.',required:true"   />	</td>
		</tr>
	  		<tr>
			<td class="biao_bt3"><spring:message code="employeeinfo_address" /></td>
			<td><input type="text" name="address" id="address" value="${ employeeinfo.address }" class="easyui-validatebox" data-options="missingMessage:'<spring:message code="employeeinfo_address" />不能为空.',required:true"   />	</td>
		</tr>
	  		<tr>
			<td class="biao_bt3"><spring:message code="employeeinfo_email" /></td>
			<td><input type="text" name="email" id="email" value="${ employeeinfo.email }" class="easyui-validatebox" data-options="missingMessage:'<spring:message code="employeeinfo_email" />不能为空.',required:true"   />	</td>
		</tr>
	  		<tr>
			<td class="biao_bt3"><spring:message code="employeeinfo_phoneNumber" /></td>
			<td><input type="text" name="phoneNumber" id="phoneNumber" value="${ employeeinfo.phoneNumber }" class="easyui-validatebox" data-options="missingMessage:'<spring:message code="employeeinfo_phoneNumber" />不能为空.',required:true"   />	</td>
		</tr>
	   	</table>
</form:form>
	