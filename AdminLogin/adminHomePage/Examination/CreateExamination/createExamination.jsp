<%@ page language="java" contentType="text/html"%>
<%@ page import="java.text.*,java.util.*,com.onlineExam.school.*" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title></title>
	<script type="text/javascript">
		function validateform() {
			var submitform = document.examMode;
			var ExamMode = submitform.examinationMode.value;
			if (ExamMode == "") {
				alert('Please Enter the Examination Mode');
			}
			else{
				submitform.submit();
			}
		}
		function editExamMode(editMode) {
			var editExamMode = document.getElementById(editMode).getElementsByTagName('td');
			var examModeId = document.getElementById(editMode).id;
			var examModeName = editExamMode[0].value;
			var editModeform = document.editdelform;
			examModeName.editDeleteform.submit();
		}
		function deleteExamMode(delMode) {
			var delExamMode = document.getElementById(delMode).getElementsByTagName('td');
			var examModeId = delExamMode[0].innerText;
			window.location.href = "./../DeleteExamMode/deleteExamMode.jsp?examModeId="+examModeId;
		}
		function nextbtn() {
			window.location.href ="./../../Subject/CreateSubject/createSubject.jsp";
		}
		function backbtn() {
			window.location.href ="./../../StdSecMap/CreateStandardSection/createStdSecMap.jsp";
		}
	</script>
</head>
<body>
	<form name="examMode" action="transferExamMode.jsp" method="POST">
	<p>Enter The Mode of Examinations</p>
	<input type="text" name="examinationMode" id="examinationMode" placeholder="Eg:Annual Exam">
	<input type="button" value="Create" onclick="validateform();">
	</form>
	<table border="1">
		<tr>
			<td>S No</td>
			<td>Examination Mode</td>
			<td>Edit</td>
			<td>Delete</td>
		</tr>
		<%
			List<Examination> examMode = ExaminationMode.showExamMode();

			for (int i=0;i<examMode.size();i++) {
				Examination exam = examMode.get(i);

			String rowId = "row" + i;
		%>
		<form name="editDeleteform" action="./../EditExamMode/editExamMode.jsp" method="POST">
		<tr id="<%=rowId%>">
			<td><%=exam.getExaminationId()%></td>
			<td><%=exam.getExaminationMode()%></td>
			<input type="hidden" name="examModeId" value="<%=exam.getExaminationId()%>">
			<input type="hidden" name="examMode" value="<%=exam.getExaminationMode()%>">
			<td><button class="fas fa-pen" onclick="editExamMode('<%=rowId%>');"></button></td>
			<td><button class="fas fa-times" onclick="deleteExamMode('<%=rowId%>')"></button></td>	
		</tr>
		</form>
		<%
		}
		%>
		<tr>
			<td onclick="backbtn();"><button>Back</button></td>
			<td></td>
			<td></td>
			<td onclick="nextbtn();"><button>Next</button></td>
		</tr>
	</table>
</body>
</html>