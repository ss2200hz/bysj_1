package com.example.bysj_1.service;

import com.example.bysj_1.dao.LaboratoryMapper;
import com.example.bysj_1.moduls.response.Laboratory;
import com.example.bysj_1.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 预约服务类
 */
@Service
public class LaboratoryService {
    private SqlSession sqlSession = MyBatisUtils.getSession(true);
    private LaboratoryMapper laboratoryMapper = sqlSession.getMapper(LaboratoryMapper.class);

    /**
     * 获取总分页数
     * @return
     */
    public int getLabNum(int pageSize) {
        int total = laboratoryMapper.getLabNum();//总记录数
        int pageNum;//总页数
        if (total % pageSize == 0) {
            pageNum = total / pageSize;
        } else {
            pageNum = total / pageSize + 1;
        }
        return pageNum;
    }

    /**
     * 获得实验室列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    public List<Laboratory> getLabInfo(int pageNo, int pageSize) {
        int min = 1;
        if (pageNo > 0) {
            min = (pageNo - 1) * pageSize;
        }
        List<Laboratory> laboratoryList = laboratoryMapper.findLaboratory(min, pageSize);
        return laboratoryList;
    }
}
