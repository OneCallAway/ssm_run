package com.java.model;

public class Teacher {
    private Integer id;

    private String name;

    private Integer teachertypeId;

    private Pages page;
    private TeacherType teachertype;
    private String login;
    private String pwd;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Pages getPage() {
		return page;
	}

	public void setPage(Pages page) {
		this.page = page;
	}

	public TeacherType getTeachertype() {
		return teachertype;
	}

	public void setTeachertype(TeacherType teachertype) {
		this.teachertype = teachertype;
	}



    private String img;

    public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}



	private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getTeachertypeId() {
        return teachertypeId;
    }

    public void setTeachertypeId(Integer teachertypeId) {
        this.teachertypeId = teachertypeId;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}