package hap.orderExam.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import hap.orderExam.dto.OmOrderLines;

import java.util.List;

public interface IOmOrderLinesService extends IBaseService<OmOrderLines>, ProxySelf<IOmOrderLinesService>{
    //查询订单行信息
    List<OmOrderLines> selectAllLines(IRequest request, OmOrderLines line, int pageNum, int pageSize);
    //找到headerId对应的行记录中的最大行号
    Long findMaxSeqNum(Long headerId);

}