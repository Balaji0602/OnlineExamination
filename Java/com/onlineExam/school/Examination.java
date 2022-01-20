package com.onlineExam.school;

public class Examination {
	private int examinationId;
	private String examinationMode;
	
	public int getExaminationId(){
		return examinationId;
	}
	public String getExaminationMode(){
		return examinationMode;
	}
	
	public void setExaminationId(int examination_Id){
		this.examinationId = examination_Id;
	}
	public void setExaminationMode(String examination_Mode){
		this.examinationMode = examination_Mode;
	}
}
