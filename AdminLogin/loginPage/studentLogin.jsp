<%@ page language="java" contentType="text/html"%>
<%@ page import="java.text.*,java.util.*,com.onlineExam.school.*" %>


<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title></title>
</head>
<body>
	<%
		String loginId = request.getParameter("studentid");
		String password = request.getParameter("studentpassword");
		
		if (loginId == null || loginId.equals("")){
			%>
			<script type="text/javascript">
				alert('Please Enter the Details');
				window.location.href = "./AdminLogin.html";
			</script>
			<%
		}
		if (password == null || password.equals("")){
			%>
			<script type="text/javascript">
				alert('Please Enter the Details ');
				window.location.href = "./AdminLogin.html";
			</script>
			<%
		}


		String errorMessage = null;
		try{

			AuthenticationUtil stuvalidlogin = new AuthenticationUtil();
			LoginBean stulogin = new LoginBean();
			stulogin.setRegisterNumber(loginId);
			stulogin.setPassword(password);

			stuvalidlogin.studentValidation(stulogin);
			
		}catch(Exception ex){
			errorMessage = ex.getMessage();
			ex.printStackTrace();
		}
		if (errorMessage == null) {
			%>
			<script type="text/javascript">
				window.location.href = "./../../StudentLogin/studentHomePage/studentSchoolHome.html";
			</script>
			<%
		}
		else{
		%>
		<script type="text/javascript">
			window.location.href = "AdminLogin.html";
			alert('<%=errorMessage%>');
		</script>
		<%
		}
	%>
</body>
</html>