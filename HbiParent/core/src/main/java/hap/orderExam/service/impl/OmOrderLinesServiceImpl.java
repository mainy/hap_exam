package hap.orderExam.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import hap.orderExam.mapper.OmOrderLinesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hap.orderExam.dto.OmOrderLines;
import hap.orderExam.service.IOmOrderLinesService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class OmOrderLinesServiceImpl extends BaseServiceImpl<OmOrderLines> implements IOmOrderLinesService{
    @Autowired
    private OmOrderLinesMapper mapper;

    @Override
    public List<OmOrderLines> selectAllLines(IRequest request, OmOrderLines line, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return mapper.selectAllLines(line);
    }

    @Override
    public Long findMaxSeqNum(Long headerId) {
        Long seqNum = null;
        seqNum = mapper.findMaxSeqNum(headerId);
        if(seqNum==null){
            return Long.parseLong("1");
        }else {
            return seqNum;
        }
    }

}