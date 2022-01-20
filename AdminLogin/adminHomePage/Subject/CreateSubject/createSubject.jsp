<%@ page language="java" contentType="text/html"%>
<%@ page import="java.text.*,java.util.*,com.onlineExam.school.*" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title></title>
	<script type="text/javascript">
		function validateform() {
			var subjectForm = document.subjectform;
			var subject = subjectForm.subject;
			if (subject == "") {
				alert('Enter the Subject');
			}
			else{
				subjectForm.submit();
			}
		}
		function editSubject(editSub) {
			var editSubject = document.getElementById(editSub).getElementsByTagName('td');
			var subjectId = editSubject[0].innerText;
			var subject = editSubject[1].innerText;
			window.location.href = "./../EditSubject/editSubject.jsp?subjectId="+subjectId+"&subject="+subject;
		}
		function deleteSubject(delSub) {
			var deleteSub = document.getElementById(delSub).getElementsByTagName('td');
			var subjectId = deleteSub[0].innerText;
			var subject = deleteSub[1].innerText;
			window.location.href="./../Delete Subject/deleteSubject.jsp?subjectId="+subjectId+"&subject="+subject;
		}
	</script>
</head>
<body>
	<form name="subjectform" action="transferSubject.jsp" method="POST">
		<p>Enter the Subjects</p>
		<input type="text" id="subject" name="subject" placeholder="Enter Subjects">
		<input type="button" name="Create" onclick="validateform();" value="Create">
	</form>
	<table border="1">
		<tr>
			<td>S No</td>
			<td>Subject</td>
			<td>Edit</td>
			<td>Delete</td>
		</tr>
		<%
			List<Subject> subject = SubjectUtil.showSubject();

			for (int i=0;i<subject.size();i++) {
				Subject sub = subject.get(i);
				String rowId = "row" + i;			
		%>
		<form method="POST">
			<tr id="<%=rowId%>">
				<td><%=sub.getSubjectId()%></td>
				<td><%=sub.getSubjectName()%></td>
				<td><button class="fas fa-pen" onclick="editSubject('<%=rowId%>');"></td>
				<td><button class="fas fa-times" onclick="deleteSubject('<%=rowId%>')"></button></td>		
			</tr>
		</form>
		<%
		}
		%>
	</table>
</body>
</html>