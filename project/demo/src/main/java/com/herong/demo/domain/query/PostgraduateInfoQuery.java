package com.herong.demo.domain.query;

import com.herong.demo.domain.PostgraduateInfo;

/**
 * @author herong22384
 * @date 2020/4/19 12:26
 */
public class PostgraduateInfoQuery extends BasePageQuery<PostgraduateInfo> {
    /**
     * 年份
     */
    private String year;

    /**
     * 学校名称
     */
    private String name;

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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSumScore() {
        return sumScore;
    }

    public void setSumScore(String sumScore) {
        this.sumScore = sumScore;
    }
}
