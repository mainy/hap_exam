package hap.orderExam.mapper;

import com.hand.hap.mybatis.common.Mapper;
import hap.orderExam.dto.OmOrderLines;

import java.util.List;

public interface OmOrderLinesMapper extends Mapper<OmOrderLines>{
    //查询行表记录
    List<OmOrderLines> selectAllLines(OmOrderLines line);

    //根据HEADER_ID查找SEQ_NUM最大值+1
    Long findMaxSeqNum(Long headerId);

}