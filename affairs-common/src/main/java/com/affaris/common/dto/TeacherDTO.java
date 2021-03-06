package com.affaris.common.dto;

import java.io.Serializable;

/**
 * Teacher远程调用传输类
 *
 * @author Vulgarities
 */
public class TeacherDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 教工号
     */
    private Integer teaId;

    /**
     * 姓名
     */
    private String teaName;

    /**
     * 性别
     */
    private String teaSex;

    /**
     * 教师登录密码
     */
    private String teaPassword;

    private long current;

    private long size;

    public TeacherDTO() {
    }

    public Integer getTeaId() {
        return teaId;
    }

    public void setTeaId(Integer teaId) {
        this.teaId = teaId;
    }

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName;
    }

    public String getTeaSex() {
        return teaSex;
    }

    public void setTeaSex(String teaSex) {
        this.teaSex = teaSex;
    }

    public String getTeaPassword() {
        return teaPassword;
    }

    public void setTeaPassword(String teaPassword) {
        this.teaPassword = teaPassword;
    }

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "TeacherTo{" +
                "teaId=" + teaId +
                ", teaName='" + teaName + '\'' +
                ", teaSex='" + teaSex + '\'' +
                ", teaPassword='" + teaPassword + '\'' +
                ", current=" + current +
                ", size=" + size +
                '}';
    }
}
