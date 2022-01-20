<%@ page language="java" contentType="text/html"%>
<%@ page import="java.text.*,java.util.*,com.onlineExam.school.*" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Create Section</title>
	<link rel="stylesheet" type="text/css" href="sectionCreation.css">
	<script type="">
		function sectionValidation() {
			var createSection = document.sectionCreation;
			var section = createSection.section.value;
			if (section == "") {
				alert('Please Enter The Section');
			}
			else{
				sectionCreation.submit();
			}
			}
			function editsec(rowId) {
				var editsec = document.getElementById(rowId).getElementsByTagName('td');
				var sectionId = editsec[0].innerText;
				var section = editsec[1].innerText;
				window.location.href = "./../EditSection/editSection.jsp?sectionId=" + sectionId+"&section="+section;
			}
			function delsec(rowId) {
				var delsec = document.getElementById(rowId).getElementsByTagName('td');
				var sectionId = delsec[0].innerText;
				var section = delsec[1].innerText;
				if(confirm('Are You Sure to Delete the Section')){
				window.location.href = "./../DeleteSection/deleteSection.jsp?sectionId="+sectionId+"&section="+section;
				}
				else{
					alert('Section Not Deleted');
				}
			}
			function secback() {
				window.location.href = "./../../Standard/StandardCreation/standardCreationFE.jsp";
			}
			function secNext() {
				window.location.href = "./../../StdSecMap/CreateStandardSection/StdSecMap.jsp";
			}


		</script>
</head>
<body>
	<div class="sectionCreat">
		<h2>ZYXW Higher Secondary School</h2>
		<h3>Create Section</h3>
		<form name="sectionCreation" action="sectionCreationDB.jsp" method="POST">
			<input type="text" id="section" name="section" placeholder="Enter Your Section">
			<input type="button" id="createbtn" value="Create" onclick="sectionValidation();">
		</form>
			<table border="1">
				<tr>
					<td>Section Id</td>
					<td>Section</td>
					<td>Edit</td>
					<td>Delete</td>
				</tr>

				<%
				List<Section> newsection = SectionUtil.showSection();
				for(int i=0;i<newsection.size(); i++){

					Section section = newsection.get(i);

					String rowId = "row" + i;

				%>
				<tr id="<%=rowId%>">
					<td><%=section.getSectionId()%></td>
					<td><%=section.getSectionName()%></td>
					<td><button class="fas fa-pen" onclick="editsec('<%=rowId%>')"></button></td>
					<td><button class="fas fa-times" onclick="delsec('<%=rowId%>')"></button></td>
				</tr>
				<%
				}
				%>
				<tr>
					<div class="secback">
					<td id="backbtn"><input type="button" value="Back" onclick="secback();"></td>
					</div>
					<td></td>
					<td></td>
					<td id="btmbtn"><input type="button" value="Next" onclick="secNext();"></td>
				</tr>
			</table>
	</div>
</body>
</html>