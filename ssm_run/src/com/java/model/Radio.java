package com.java.model;

public class Radio {
    private Integer id;

    private Integer newsId;
    private News news;
    private String title;
    
    private String a;

    private String b;

    private String c;

    private String d;

    private String right;

    private Integer grade;
    private String gradetext;
    public String getGradetext() {
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

	private Pages page;

	public Pages getPage() {
		return page;
	}

	public void setPage(Pages page) {
		this.page = page;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a == null ? null : a.trim();
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b == null ? null : b.trim();
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c == null ? null : c.trim();
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d == null ? null : d.trim();
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right == null ? null : right.trim();
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}