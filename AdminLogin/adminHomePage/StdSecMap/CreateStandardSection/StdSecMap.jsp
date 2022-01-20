<%@ page language="java" contentType="text/html"%>
<%@ page import="java.text.*,java.util.*,com.onlineExam.school.*" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>StandardSection Mapping</title>
	<link rel="stylesheet" type="text/css" href="StdSecMap.css">
</head>
<body>
	<h2>ZYXW Higher Secondary School</h2>
	<select id="standard">
		<option id="-1">-----Select Standard-----</option>
	<%
	List<Standard> standards = StandardUtil.showStandard();
		
	for (int i=0; i<standards.size(); i++) {
			
		Standard standard = standards.get(i);

	%>	
	
		<option id="<%=standard.getStandardId()%>"><%=standard.getStandardName()%></option>
	<%
	}
	%>
	</select>
	<select id="section">
				<option id="-1">-----Select Section-----</option>
	<%
		List<Section> newsection = SectionUtil.showSection();
		for(int i=0;i<newsection.size(); i++){

		Section section = newsection.get(i);

		%>
		<option id="<%=section.getSectionId()%>"><%=section.getSectionName()%></option>
		<%
	}
	%>
</select>
<form name="stdSecMap" action="createStdSecMap.jsp" method="POST">
	<input type="hidden" name="std">
	<input type="hidden" name="sec">
	<input type="button" name="stdSec" value="Create" onclick="CreateSedSecMap();">
</form>
<script type="text/javascript">
	function CreateSedSecMap() {
		var standardIndex = document.getElementById('standard').selectedIndex;
		var standard = document.getElementById('standard').options[standardIndex].id;
		var sectionIndex = document.getElementById('section').selectedIndex;
		var section = document.getElementById('section').options[sectionIndex].id;
		stdSecMap.std.value = standard;
		stdSecMap.sec.value = section;

		if (standard == -1) {
			alert('Select Your Standard');
		}
		else if(section == -1){
			alert('Select Youe Section');
		}
		else{
			stdSecMap.submit();
		}
	}
</script>
	<table border="1">
		<tr>
			<td>Standard Section Id</td>
			<td>Standard</td>
			<td>Section</td>
			<td>Delete</td>
		</tr>
	<%
	List<StandardSection> stdsecs =  StandardSectionMapping.showStdSec();

	for(int i = 0;i<stdsecs.size(); i++){
		StandardSection  stdsec = stdsecs.get(i);

		String rowId =  "row" + i;
	
	%>
		<tr id="<%=rowId%>">
			<td><%=stdsec.getStandardSectionId()%></td>
			<td ><%=stdsec.getStandardName()%></td>
			<td><%=stdsec.getSectionName()%></td>
			<td><button class="fas fa-times" onclick="delbtn('<%=rowId%>');"></button></td>
		</tr>
	<%
	}
	%>
	<tr>
		<td><button onclick="backbtn();">Back</button></td>
		<td></td>
		<td></td>
		<td><button onclick="nextbtn();">Next</button></td>
	</tr>
	</table>

	<script type="text/javascript">
		function delbtn(del) {
			var delbtn = document.getElementById(del).getElementsByTagName('td');
			var stdsecId = delbtn[0].innerText;
			window.location.href = "./../DeleteStandardSection/deletestdsec.jsp?stdsecId="+stdsecId;
		}
		function backbtn() {
			window.location.href = "./../../Section/SectionCreation/sectionCreation.jsp";
		}
		function nextbtn() {
			window.location.href = "./../../Examination/CreateExamination/createExamination.jsp";
		}
	</script>
</body>
</html>