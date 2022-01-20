package com.onlineExam.school;

public class Question {
	private String standard;
	private String section;
	private String subject;
	private String examMode;
	private String questionNumber;
	private String questionId;
	private String question;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String answer;
	private String status;
	private String ssid;
	private String subjectid;

	public String getStandard(){
		return standard;
	}
	public String getSSid(){
		return ssid;
	}
	public String getSubjectId(){
		return subjectid;
	}
	public String getSection(){
		return section;
	}
	public String getSubject(){
		return subject;
	}
	public String getExamMode(){
		return examMode;
	}
	public String getQuestionNumber(){
		return questionNumber;
	}
	public String getQuestionId(){
		return questionId;
	}
	public String getQuestion(){
		return question;
	}
	public String getOption1(){
		return option1;
	}
	public String getOption2(){
		return option2;
	}
	public String getOption3(){
		return option3;
	}
	public String getOption4(){
		return option4;
	}
	public String getAnswer(){
		return answer;
	}
	public String getStatus(){
		return status;
	}
	
	public void setStandard(String standard){
		this.standard = standard;
	}
	public void setSection(String section){
		this.section = section;
	}
	public void setSubject(String subject){
		this.subject = subject;
	}
	public void setExamMode(String examMode){
		this.examMode = examMode;
	}
	public void setQuestionNumber(String questionNumber){
		this.questionNumber = questionNumber;
	}
	public void setQuestionId(String questionId){
		this.questionId = questionId;
	}
	public void setQuestion(String question){
		this.question = question;
	}
	public void setOption1(String option1){
		this.option1 = option1;
	}
	public void setOption2(String option2){
		this.option2 = option2;
	}
	public void setOption3(String option3){
		this.option3 = option3;
	}
	public void setOption4(String option4){
		this.option4 = option4;
	}
	public void setAnswer(String answer){
		this.answer = answer;
	}
	public void setStatus(String status){
		this.status = status;
	}
}
