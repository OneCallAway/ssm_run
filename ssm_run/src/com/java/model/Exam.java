package com.java.model;

public class Exam {
    private Integer id;

    private String title;

    private Integer grade;

    private Integer teacherId;
    private Teacher teacher;
    private String gradetext;
    private Pages page;
    public Pages getPage() {
		return page;
	}

	public void setPage(Pages page) {
		this.page = page;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getGradetext() {
		if(grade<0)
			return "混合";
		if(grade==0)
			return "特级";
		else if(grade==1)
			return "高";
		else if(grade==2)
			return "中";
		else
			return "低";
	}

	public void setGradetext(String gradetext) {
		this.gradetext = gradetext;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }
}