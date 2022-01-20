<%@ page language="java" contentType="text/html"%>
<%@ page import="java.text.*,java.util.*,com.onlineExam.school.*" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Standard Creation</title>
	<link rel="stylesheet" type="text/css" href="standardCreation.css">
	<script src="https://kit.fontawesome.com/a50c5d99bb.js" crossorigin="anonymous"></script>
	<script type="text/javascript">
		function standardCreation() {
			var standardform = document.stdform;
			var standard = standardform.std.value;
			if (standard == "") {
				alert('Please Enter Your Standard');
			}
			else if(standard > 12){
				alert('Please Enter Valid Standard');
			}
			else{
				stdform.submit();
			}
		}
		function editstd(editstd) {
			var editstd = document.getElementById(editstd).getElementsByTagName('td');
			var standardId = editstd[0].innerText;
			var standard = editstd[1].innerText;
			window.location.href = "./../EditStandard/editStandard.jsp?standardId="+standardId+"&standard="+standard;
		}
		function delstd(delstd) {
			var delstd = document.getElementById(delstd).getElementsByTagName('td');
			var standardId = delstd[0].innerText;
			var standard = delstd[1].innerText;
			window.location.href = "./../DeleteStandard/deleteStandard.jsp?standardId="+standardId+"&standard="+standard;
		}
		function nextPage() {
			window.location.href = "./../../Section/SectionCreation/sectionCreation.jsp";
		}
		function stdback() {
			window.location.href = "./../../SchoolHome.html";
		}
	</script>
</head>
<body>
	<h2>ZYXW Higher Secondary School</h2>
	<h3>Create the Standard</h3>
	<div class="standardbox">
		<form name="stdform" action="standardCreation.jsp" method="POST">
			<input type="text" name="std" id="std" placeholder="Enter Your Standard"><br><br>
			<input type="button" id="setbtn" value="Create" onclick="standardCreation();"><br><br>
		</form>
	</div>
	<div class="table">
	<table border="1">
	<tr>
		<td>Standard Id</td>
		<td>Standard</td>
		<td>Edit</td>
		<td>Delete</td>
	</tr>

	<%
	List<Standard> standards = StandardUtil.showStandard();
		
	for (int i=0; i<standards.size(); i++) {

		Standard standard = standards.get(i);
			
		String rowId = "row" + i;
	%>	
	<tr id="<%=rowId%>">
		<td><%=standard.getStandardId()%></td>
		<td><%=standard.getStandardName()%></td>
		<td><button class="fas fa-pen" onclick="editstd('<%=rowId%>');"></button></td>
		<td><button class="fas fa-times" onclick="delstd('<%=rowId%>');"></button></td>
	</tr>
	<%
	}
	%>
	<tr>
		<td class="back"><input type="button" id="back" value="Back" onclick="stdback();"></td>
		<td class="null"></td>
		<td class="null"></td>
		<td class="next"><input type="button" id="next" value="Next" onclick="nextPage();"></td>
	</tr>
	</table>
	</div>
</body>
</html>