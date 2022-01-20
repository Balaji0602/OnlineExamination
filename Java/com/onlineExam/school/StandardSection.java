package com.onlineExam.school;

public class StandardSection {
	private String standardId;
	private String standardName;
	private String sectionId;
	private String sectionName;
	private int stdsecId;

	public String getStandardId() {
		return standardId;
	}

	public String getStandardName() {
		return standardName;
	}
	
	public int getStandardSectionId(){
		return stdsecId;
	}

	public String getSectionId() {
		return sectionId;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setStandardId(String Standard_id) {
		this.standardId = Standard_id;
	}

	public void setStandardName(String Standard_name) {
		this.standardName = Standard_name;
	}

	public void setSectionId(String Section_id) {
		this.sectionId = Section_id;
	}
	
	public void setStandardSectionId(int stdsecId){
		this.stdsecId = stdsecId;
	}

	public void setSectionName(String Section_name) {
		this.sectionName = Section_name;
	}
}
