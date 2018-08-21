package hap.orderExam.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import hap.orderExam.dto.OmOrderHeaders;

import java.util.List;

public interface IOmOrderHeadersService extends IBaseService<OmOrderHeaders>, ProxySelf<IOmOrderHeadersService>{
    //查询订单汇总信息
    List<OmOrderHeaders> selectAll1(IRequest request, OmOrderHeaders head, int pageNum, int pageSize);
    //整单删除,参数为行id
    public boolean deleteAll(Long headerid);
    //判断订单编号是否重复
    public int selectByOrderNumber(String ordernumber);
    //根据headerId更新头信息
    public boolean updateHeader(OmOrderHeaders head);


}