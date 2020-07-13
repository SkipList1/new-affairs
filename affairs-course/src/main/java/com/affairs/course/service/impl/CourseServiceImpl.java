package com.affairs.course.service.impl;

import com.affairs.course.entity.Course;
import com.affairs.course.mapper.CourseMapper;
import com.affairs.course.service.ICourseService;
import com.affaris.common.vo.CourseVo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程信息 服务实现类
 * </p>
 *
 * @author Vulgarities
 * @since 2020-06-28
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public IPage<Course> getCoursesPageByTeaId(Page<Course> page, Integer teaId) {
        return baseMapper.getCoursesPageByTeaId(page, teaId);
    }

    @Override
    public List<String> getSelectedCoursesFromRedis(Integer stuId) {
        // 获取redis中选课表的全部内容
        return stringRedisTemplate.opsForList().range("killers:elective:" + stuId, 0, -1);
    }

    @Override
    public Page<CourseVo> getOptionalCoursesPageFromRedis(Long currentPage, long pageSize, LocalDateTime now) {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        // 获取redis中已上架的课程信息
        String courseVoStr = ops.get("killers:courseVos");
        List<CourseVo> courseVoListTmp = JSON.parseObject(courseVoStr, new TypeReference<List<CourseVo>>() {
        });
        List<CourseVo> courseVoList = new ArrayList<CourseVo>();
        if (courseVoListTmp != null) {
            /*
                redis中的数据是会获取到未来一天
                也就是说有一部分课程是当前还未开始选课的
                所以要将这部分课程剔除掉
             */
            for (CourseVo courseVo : courseVoListTmp) {
                LocalDateTime couTime = courseVo.getCouTime();
                if (couTime.isBefore(now)) {
                    courseVoList.add(courseVo);
                }
            }
        }
        // 构造分页对象
        long start = (currentPage - 1) * pageSize;
        long end = start + pageSize;
        if (end > courseVoList.size()) {
            end = courseVoList.size();
        }
        List<CourseVo> records = new ArrayList<CourseVo>();
        for (long i = start; i < end; i++) {
            records.add(courseVoList.get((int) i));
        }
        // 封装分页对象
        Page<CourseVo> courseVoPage = new Page<CourseVo>();
        courseVoPage.setTotal(courseVoList.size());
        courseVoPage.setCurrent(currentPage);
        courseVoPage.setRecords(records);
        courseVoPage.setSize(end - start);
        return courseVoPage;
    }
}
