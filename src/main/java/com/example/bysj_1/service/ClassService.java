package com.example.bysj_1.service;

import com.example.bysj_1.dao.ClassMapper;
import com.example.bysj_1.utils.MyBatisUtils;
import com.example.bysj_1.utils.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.UUID;

/**
 * 课程管理服务类
 */
public class ClassService {
    private SqlSession sqlSession = MyBatisUtils.getSession(true);
    private ClassMapper classMapper = sqlSession.getMapper(ClassMapper.class);

    /**
     * 获得课程信息数量
     */
    public HashMap getClassNum(int pageSize) {
        HashMap map = new HashMap();
        int total = classMapper.getClassNum();//总记录数
        int pageNum;//总页数
        if (total % pageSize == 0) {
            pageNum = total / pageSize;
        } else {
            pageNum = total / pageSize + 1;
        }
        map.put("pageNum", pageNum);
        return map;
    }

    /**
     * 获得所有课程信息
     */
    public HashMap getClassList(int pageNo, int pageSize) {
        HashMap map = new HashMap();
        int min = 1;
        if (pageNo > 0) {
            min = (pageNo - 1) * pageSize;
        }
        List<HashMap<String, Object>> classList = classMapper.getClassList(min, pageSize);
        if (!CollectionUtils.isEmpty(classList) && classList.size() > 0) {
            for (HashMap<String, Object> hashMap : classList) {
                String classNo = (String) hashMap.get("classNo");
                List<HashMap<String, String>> teacherList = classMapper.getTeacherByClassNo(classNo);
                if (!CollectionUtils.isEmpty(teacherList) && teacherList.size() > 0) {
                    List<String> nameList = new ArrayList();
                    List<String> idList = new ArrayList();
                    for (HashMap teacher : teacherList) {
                        String name = (String) teacher.get("name");
                        nameList.add(name);
                        idList.add((String) teacher.get("teacherId"));
                    }
                    hashMap.put("teacherNames", StringUtils.List2String(nameList));
                    hashMap.put("teacherIds", idList);
                } else {
                    hashMap.put("teacherNames", "无");
                }
            }
        }
        map.put("resultList", classList);
        return map;
    }

    /**
     * 按条件查询课程信息
     */
    public HashMap getClassListByCondition(HashMap data) {
        HashMap map = new HashMap();
        List<HashMap<String, Object>> resultList = classMapper.getClassListByCondition(data);
        map.put("resultList", resultList);
        return map;
    }

    /**
     * 删除课程
     */
    public HashMap deleteClass(String classNo) {
        HashMap map = new HashMap();
        try {
            classMapper.deleteClassByClassNo(classNo);
            map.put("succeed", true);
        } catch (Exception e) {
            map.put("succeed", false);
            map.put("errorInfo", "unknow error");
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 加入课程
     */
    public HashMap joinClass(HashMap data) {
        HashMap map = new HashMap();
        String userid = (String) data.get("userid");
        String classNo = (String) data.get("classNo");
        String uuid = UUID.randomUUID().toString();
        try {
            classMapper.joinClass(uuid, classNo, userid);
            map.put("succeed", true);
        } catch (Exception e) {
            map.put("succeed", false);
            map.put("errorInfo", "unknow error");
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 删除课程
     */
    public HashMap exitClass(HashMap data) {
        HashMap map = new HashMap();
        String userid = (String) data.get("userid");
        String classNo = (String) data.get("classNo");
        try {
            classMapper.exitClass(classNo, userid);
            map.put("succeed", true);
        } catch (Exception e) {
            map.put("succeed", false);
            map.put("errorInfo", "unknow error");
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 查询某教师的所有课程
     */
    public HashMap getClassByUserId(String userid) {
        HashMap map = new HashMap();
        List<HashMap<String, String>> list = classMapper.getClassListByTeacher(userid);
        map.put("resultList",list);
        return map;
    }
}
