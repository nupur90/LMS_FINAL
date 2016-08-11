<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<sj:head jqueryui="true" jquerytheme="redmond" />


</head>
<body>
	<s:url var="gridMachine" action="gridMachine" />
	<sjg:grid href="%{gridMachine}" id="dispid" width="600" height="130"
		gridModel="expenseList" caption="Existing Materials" pager="true"
		rowNum="0" dataType="json" rownumbers="true" scroll="true"
		formIds="machine" reloadTopics="reloadGridM" scrollrows="true"
		viewrecords="true" navigator="true" navigatorDelete="true"
		rowList="10,15,20" onSelectRowTopics="rowselectMachine">


		<sjg:gridColumn name="idmachineMaster" index="machineName" title=""
			sortable="true" search="true" editable="true" width="500"
			hidden="true" />


		<sjg:gridColumn name="machineName" index="machineName"
			title="Machine Name" sortable="true" search="true" editable="true"
			width="500" />

		<sjg:gridColumn name="machineNo" index="machineNo" title="Machine No"
			sortable="false" search="true" editable="true" width="500" />

		<sjg:gridColumn name="machineDetails" index="machineDetails"
			title="Machine Details" sortable="false" search="true"
			editable="true" width="550" />



		<sjg:gridColumn name="idmachineMaster" formatter="ActionMachine"
			title="Actions" align="left" sortable="false" width="400" />


	</sjg:grid>



</body>
</html>