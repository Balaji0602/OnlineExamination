<%@ page language="java" contentType="text/html"%>
<%@ page import="java.text.*,java.util.*,com.onlineExam.school.*" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title></title>
</head>
<body>
	<%
	String standard = request.getParameter("standard");
	String section = request.getParameter("section");
	String examMode = request.getParameter("examMode");
	String subject = request.getParameter("subject");
	String question = request.getParameter("question");
	String option1 = request.getParameter("option1");
	String option2 = request.getParameter("option2");
	String option3 = request.getParameter("option3");
	String option4 = request.getParameter("option4");
	String answer = request.getParameter("ans");

	String errorMessage = null;
	if (standard == null  || standard == "") {
		%><script type="text/javascript">
			alert('Standard is Missing');
		</script><%
	}
	else if(section == null  || section == ""){
		%><script type="text/javascript">
			alert('Section is Missing');
		</script><%
	}
	else if (subject == null  || subject == "") {
		%><script type="text/javascript">
			alert('Subject is Missing');
		</script><%
	}
	else if (examMode == null  || examMode == "") {
		%><script type="text/javascript">
			alert('Examination Mode is Missing');
		</script><%
	}
	else if (question == null  || question == "") {
		%><script type="text/javascript">
			alert('Question is Missing');
		</script><%
	}
	else if (option1 == null  || option1 == "") {
		%><script type="text/javascript">
			alert('Option 1 is Missing');
		</script><%
	}
	else if (option2 == null  || option2 == "") {
		%><script type="text/javascript">
			alert('Option 2 is Missing');
		</script><%
	}
	else if (option3 == null  || option3 == "") {
		%><script type="text/javascript">
			alert('Option 3 is Missing');
		</script><%
	}
	else if (option4 == null  || option4 == "") {
		%><script type="text/javascript">
			alert('Option 4 is Missing');
		</script><%
	}
	else if (answer == null  || answer == "") {
		%><script type="text/javascript">
			alert('Answer is Missing');
		</script><%
	}
	else{
	QuestionUtil questionAns = new QuestionUtil();
	Question questions = new Question();
	questions.setStandard(standard);
	questions.setSection(section);
	questions.setExamMode(examMode);
	questions.setSubject(subject);
	questions.setQuestion(question);
	questions.setOption1(option1);
	questions.setOption2(option2);
	questions.setOption3(option3);
	questions.setOption4(option4);
	questions.setAnswer(answer);

	questionAns.createQuestion(questions);
	}

	if (errorMessage == null) {
		%>
		<form  name="form" action="QuestionPreparation.jsp" method="POST">
			<input type="hidden" name="std" value="<%=standard%>">
			<input type="hidden" name="sec" value="<%=section%>">
			<input type="hidden" name="exam" value="<%=examMode%>">
			<input type="hidden" name="sub" value="<%=subject%>">
		</form>
		<script type="text/javascript">
				form.submit();
		</script>
		<%
		}
	else{
		%>
		<form  name="form" action="QuestionPreparation.jsp" method="POST">
			<input type="hidden" name="std" value="<%=standard%>">
			<input type="hidden" name="sec" value="<%=section%>">
			<input type="hidden" name="exam" value="<%=examMode%>">
			<input type="hidden" name="sub" value="<%=subject%>">
		</form>
		<script type="text/javascript">
				form.submit();
				alert('<%=errorMessage%>');
		</script>
		<%
		}
		%>
</body>
</html>