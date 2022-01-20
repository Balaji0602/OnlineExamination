<%@ page language="java" contentType="text/html"%>
<%@ page import="java.text.*,java.util.*,com.onlineExam.school.*" %>


<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Prepare Question</title>
	<script type="text/javascript">
		function validateform() {
			var standardIndex = document.getElementById('standard').selectedIndex;
			var standard = document.getElementById('standard').options[standardIndex].id;
			var sectionIndex = document.getElementById('section').selectedIndex;
			var section = document.getElementById('section').options[sectionIndex].id;
			var examModeId = document.getElementById('examMode').selectedIndex;
			var examMode = document.getElementById('examMode').options[examModeId].id;
			var subjectIndex = document.getElementById('subject').selectedIndex;
			var subject = document.getElementById('subject').options[subjectIndex].id;

			questionIndex.std.value = standard;
			questionIndex.sec.value  = section;
			questionIndex.exam.value = examMode;
			questionIndex.sub.value = subject;

			if (standard == -1) {
				alert('Select Your Standard');
			}
			else if (section == -1) {
				alert('Select Your Section');
			}
			else if (examMode == -1) {
				alert('Select the Examination Mode');
			}
			else if (subject == -1) {
				alert('Select Your Subject');
			}
			else{
				questionIndex.submit();
			}
		}
	</script>
</head>
<body>
		<p>Standard</p>
		<select id="standard">
			<option id="-1">--------Select Standard--------</option>
		<%
			List<Standard> standards = StandardUtil.showStandard();

			for(int i=0;i<standards.size();i++){

				Standard standard = standards.get(i);
		%>
			<option id="<%=standard.getStandardId()%>"><%=standard.getStandardName()%></option>
		<%
		}
		%>
		</select>
		<p>Section</p>
		<select id="section">
			<option id="-1">---------Select Section---------</option>

		<%
			List<Section> sections = SectionUtil.showSection();

			for(int i=0;i<sections.size(); i++){

				Section section = sections.get(i);
		%>
			<option id="<%=section.getSectionId()%>"><%=section.getSectionName()%></option>
		<%
		}
		%>
		</select>
		<p>Examination Mode</p>
		<select id="examMode">
			<option id="-1">----Examination Mode-----</option>
			<%
			List<Examination> examMode = ExaminationMode.showExamMode();

			for(int i=0;i<examMode.size(); i++){

				Examination exam = examMode.get(i);
		%>
			<option id="<%=exam.getExaminationId()%>"><%=exam.getExaminationMode()%></option>
		<%
		}
		%>
		</select>
		</select>
		<p>Subject</p>
		<select id="subject">
			<option id="-1">----Subject-----</option>
			<%
			List<Subject> subject = SubjectUtil.showSubject();

			for(int i=0;i<subject.size(); i++){

				Subject sub = subject.get(i);
		%>
			<option id="<%=sub.getSubjectId()%>"><%=sub.getSubjectName()%></option>
		<%
		}
		%>
		</select>
		<form name="questionIndex" action="QuestionPreparation.jsp" method="POST">
			<input type="hidden" name="std">
			<input type="hidden" name="sec">
			<input type="hidden" name="exam">
			<input type="hidden" name="sub">
			<input type="button" name="prepareQuestion" value="Prepare Questions" onclick="validateform();">

	</form>
</body>
</html>