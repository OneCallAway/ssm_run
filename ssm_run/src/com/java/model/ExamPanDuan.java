package com.java.model;

public class ExamPanDuan {
    private Integer id;

    private Integer examId;

    private Integer panduanId;

    private Integer score;
    
    private Exam exam;
    private PanDuan panduan;
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


    public Integer getPanduanId() {
		return panduanId;
	}

	public void setPanduanId(Integer panduanId) {
		this.panduanId = panduanId;
	}

	public PanDuan getPanduan() {
		return panduan;
	}

	public void setPanduan(PanDuan panduan) {
		this.panduan = panduan;
	}

	public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}