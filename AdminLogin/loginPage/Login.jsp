<%@ page language="java" contentType="text/html"%>
<%@ page import="java.text.*,java.util.*,com.onlineExam.school.*" %>


<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title></title>
</head>
<script type="text/javascript"></script>
<body>
	<%
		String registerNumber = request.getParameter("adminid");
		String password = request.getParameter("adminpassword");
		String errorMessage = null;
		try{
		AuthenticationUtil validlogin = new AuthenticationUtil();
		LoginBean login = new LoginBean();
		login.setRegisterNumber(registerNumber);
		login.setPassword(password);

		validlogin.adminValidation(login);
		}catch(Exception ex){
			errorMessage = ex.getMessage();
			ex.printStackTrace();
		}	
		if (errorMessage == null) {
			%><script type="text/javascript">
				window.location.href = "./../adminHomePage/SchoolHome.html";
			</script><%
			}
		else{
			%><script type="text/javascript">
				window.location.href = "AdminLogin.html";				
				alert('<%=errorMessage%>');
			</script><%
		}
	%>
</body>
</html>
