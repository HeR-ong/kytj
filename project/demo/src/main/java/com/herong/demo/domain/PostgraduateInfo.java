package com.herong.demo.domain;

import java.util.Date;

public class PostgraduateInfo {
    private Long id;

    /**
     * 年份
     */
    private String year;

    /**
     * 学校名称
     */
    private String name;

    /**
     * 院系名称
     */
    private String classify;

    /**
     * 专业编码
     */
    private String subjectCode;

    /**
     * 专业名称
     */
    private String subject;

    /**
     * 总分
     */
    private String sumScore;

    /**
     * 科目一分数线
     */
    private String one;

    /**
     * 科目二分数线
     */
    private String two;

    /**
     * 科目三分数线
     */
    private String three;

    /**
     * 科目四分数线
     */
    private String four;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModify;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode == null ? null : subjectCode.trim();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public String getSumScore() {
        return sumScore;
    }

    public void setSumScore(String sumScore) {
        this.sumScore = sumScore == null ? null : sumScore.trim();
    }

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one == null ? null : one.trim();
    }

    public String getTwo() {
        return two;
    }

    public void setTwo(String two) {
        this.two = two == null ? null : two.trim();
    }

    public String getThree() {
        return three;
    }

    public void setThree(String three) {
        this.three = three == null ? null : three.trim();
    }

    public String getFour() {
        return four;
    }

    public void setFour(String four) {
        this.four = four == null ? null : four.trim();
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }
}