package com.example.bysj_1.service;

import com.example.bysj_1.dao.LaboratoryMapper;
import com.example.bysj_1.moduls.response.Laboratory;
import com.example.bysj_1.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * 预约服务类
 */
@Service
public class LaboratoryService {
    private SqlSession sqlSession = MyBatisUtils.getSession(true);
    private LaboratoryMapper laboratoryMapper = sqlSession.getMapper(LaboratoryMapper.class);

    public List<Laboratory> getLabInfo(int pageNo, int pageSize){
        List<Laboratory> laboratoryList = laboratoryMapper.findLaboratory(pageNo,pageSize);
        return laboratoryList;
    }

    public HashMap getLabNum() {
        HashMap map = new HashMap();
        int num = laboratoryMapper.getLabNum();
        map.put("labNum",num);
        return map;
    }
}
