package com.affairs.course.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 课程信息
 * </p>
 *
 * @author Vulgarities
 * @since 2020-06-28
 */
@TableName("cou_course")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 课程编号
     */
    @TableId(value = "cou_id", type = IdType.AUTO)
    private Integer couId;

    /**
     * 课程名称
     */
    private String couName;

    /**
     * 创建者
     */
    private String couBuilder;

    /**
     * 最大人数
     */
    private Integer couCount;

    /**
     * 选课开始时间
     */
    private LocalDateTime couTime;

    /**
     * 随机码
     */
    @TableField(exist = false)
    private String randomCode;

    public Integer getCouId() {
        return couId;
    }

    public void setCouId(Integer couId) {
        this.couId = couId;
    }

    public String getCouName() {
        return couName;
    }

    public void setCouName(String couName) {
        this.couName = couName;
    }

    public String getCouBuilder() {
        return couBuilder;
    }

    public void setCouBuilder(String couBuilder) {
        this.couBuilder = couBuilder;
    }

    public Integer getCouCount() {
        return couCount;
    }

    public void setCouCount(Integer couCount) {
        this.couCount = couCount;
    }

    public LocalDateTime getCouTime() {
        return couTime;
    }

    public void setCouTime(LocalDateTime couTime) {
        this.couTime = couTime;
    }

    @Override
    public String toString() {
        return "Course{" +
                "couId=" + couId +
                ", couName=" + couName +
                ", couBuilder=" + couBuilder +
                ", couCount=" + couCount +
                ", couTime=" + couTime +
                "}";
    }
}
