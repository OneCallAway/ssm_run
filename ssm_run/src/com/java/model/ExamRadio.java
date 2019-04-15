package com.java.model;

public class ExamRadio {
    private Integer id;

    private Integer examId;

    private Integer radioId;

    private Integer score;
    
    private Exam exam;
    private Radio radio;
    private Pages page;
    public Pages getPage() {
		return page;
	}

	public void setPage(Pages page) {
		this.page = page;
	}
    public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public Radio getRadio() {
		return radio;
	}

	public void setRadio(Radio radio) {
		this.radio = radio;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public Integer getRadioId() {
        return radioId;
    }

    public void setRadioId(Integer radioId) {
        this.radioId = radioId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}