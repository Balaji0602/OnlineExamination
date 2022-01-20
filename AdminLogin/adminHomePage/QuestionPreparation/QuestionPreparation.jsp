<%@ page language="java" contentType="text/html"%>
<%@ page import="java.text.*,java.util.*,com.onlineExam.school.*" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Question Preparation</title>
	<script src="https://kit.fontawesome.com/a50c5d99bb.js" crossorigin="anonymous"></script>
	<script type="text/javascript">
		function validateForm() {
			var question = document.getElementById('questionbox').value;
			var option1 = document.getElementById('option1').value;
			var option2 = document.getElementById('option2').value;
			var option3 = document.getElementById('option3').value;
			var option4 = document.getElementById('option4').value;
			var answerId = document.getElementById('answer').selectedIndex;
			var answer = document.getElementById('answer').options[answerId].id;
			questionform.ans.value = answer;

			if (question == "") {
				alert('Enter the Question');
			}
			else if (option1 == "") {
				alert('Enter the Option 1');
			}
			else if (option2 == "") {
				alert('Enter the Option 2');
			}
			else if (option3 == "") {
				alert('Enter the Option 3');
			}
			else if (option4 == "") {
				alert('Enter the Option 4');
			}
			else if (answer == -1) {
				alert('Enter the Answer');
			}
			else{
				questionform.submit();
			}
		}
		function getOptions(){
			var question = document.getElementById('questionbox').value;
			var option1 = document.getElementById('option1').value;
			var option2 = document.getElementById('option2').value;
			var option3 = document.getElementById('option3').value;
			var option4 = document.getElementById('option4').value;	
			var answerId = document.getElementById('answer').selectedIndex;
			var answer = document.getElementById('answer').options[answerId].id;

			if (question == "") {
				alert('Enter the Question');
			}
			else if (option1 == "") {
				alert('Enter the Option 1');
			}
			else if (option2 == "") {
				alert('Enter the Option 2');
			}
			else if (option3 == "") {
				alert('Enter the Option 3');
			}
			else if (option4 == "") {
				alert('Enter the Option 4');
			}
			else{
			var option1 = document.getElementById('option1').value;
			var option2 = document.getElementById('option2').value;
			var option3 = document.getElementById('option3').value;
			var option4 = document.getElementById('option4').value;
			document.getElementById('1').innerHTML = option1;
			document.getElementById('2').innerHTML = option2;
			document.getElementById('3').innerHTML = option3;
			document.getElementById('4').innerHTML = option4;
			}
		}
	</script>
</head>
<body>
	<%
	String standard = request.getParameter("std");
	String section  = request.getParameter("sec");
	String examMode = request.getParameter("exam");
	String subject  = request.getParameter("sub");
	%>
	<form name="questionform" action="transferQuestion.jsp" method="POST">
		<input type="hidden" name="standard" value="<%=standard%>">
		<input type="hidden" name="section" value="<%=section%>">
		<input type="hidden" name="examMode" value="<%=examMode%>">
		<input type="hidden" name="subject" value="<%=subject%>">

		<p>Question</p>
		<textarea id="questionbox" name="question" placeholder="Enter your question here"></textarea>
		<p>Options</p>
		<input type="text" name="option1" id="option1" placeholder="Option 1">
		<input type="text" name="option2" id="option2" placeholder="Option 2">
		<input type="text" name="option3" id="option3" placeholder="Option 3">
		<input type="text" name="option4" id="option4" placeholder="Option 4">
		<p>Answer</p>
		<select id="answer" onclick="getOptions()">
		<option id="-1">-----Answer-----</option>
		<option id="1"></option>
		<option id="2"></option>
		<option id="3"></option>
		<option id="4"></option>
		</select>
		<input type="hidden" name="ans">
		<input type="button" name="create" value="create" onclick="validateForm();">
	</form>
	<table border="1">
	<form>
	<tr>
		<td>Question No</td>
		<td>Questions</td>
		<td>Edit Question</td>
		<td>Question Status</td>
	</tr>
	<%
		List<Question> showQuestion = QuestionUtil.showQuestion();
		
		for(int i = 0;i<showQuestion.size();i++){
			Question question = showQuestion.get(i);
		
			String rowId = "row"+i;
	%>
	<tr id="<%=rowId%>">
		<td><%=question.getQuestionId()%></td>
		<td><%=question.getQuestion()%></td>
		<td><button class="fas fa-pen" onclick="editQuestion('<%=rowId%>');"></button></td>
		<td>
			<select>
				<option id="1">Active</option>
				<option id="2">Inactive</option>
			</select>
		</td>
	</tr>
	<%
	}
	%>
	</form>
	</table>
</body>
</html>