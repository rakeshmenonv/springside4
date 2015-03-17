<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div id="three">
	<div class="contenttable">
		<h3>
			<spring:message code="employeeinfo_title" />
		</h3>
		<div class="contenttable1">
			<table class="content" style="width: 99%;" >
				 				<tr>
					<td class="biao_bt3"><spring:message
							code="employeeinfo_name" /></td>
					<td>${ employeeinfo.name }</td>
				</tr>
				  				<tr>
					<td class="biao_bt3"><spring:message
							code="employeeinfo_age" /></td>
					<td>${ employeeinfo.age }</td>
				</tr>
				  				<tr>
					<td class="biao_bt3"><spring:message
							code="employeeinfo_address" /></td>
					<td>${ employeeinfo.address }</td>
				</tr>
				  				<tr>
					<td class="biao_bt3"><spring:message
							code="employeeinfo_email" /></td>
					<td>${ employeeinfo.email }</td>
				</tr>
				  				<tr>
					<td class="biao_bt3"><spring:message
							code="employeeinfo_phoneNumber" /></td>
					<td>${ employeeinfo.phoneNumber }</td>
				</tr>
				   			</table>
		</div>
	</div>
</div>
<script type="text/javascript">
$ .parser.onComplete = function() {
	parent.$ .messager.progress('close');
};
</script>



