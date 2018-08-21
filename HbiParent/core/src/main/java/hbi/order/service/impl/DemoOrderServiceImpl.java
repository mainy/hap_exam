package hbi.order.service.impl;

import com.hand.hap.code.rule.exception.CodeRuleException;
import com.hand.hap.code.rule.service.ISysCodeRuleProcessService;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hbi.order.dto.DemoOrder;
import hbi.order.service.IDemoOrderService;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class DemoOrderServiceImpl extends BaseServiceImpl<DemoOrder> implements IDemoOrderService{
    @Autowired
    ISysCodeRuleProcessService codeRuleProcessService;
    @Autowired
    private IDemoOrderService service;
    @Override
    public List<DemoOrder> mybatchUpdate(IRequest requestCtx, List<DemoOrder> dto) throws CodeRuleException {
        if(dto!=null)
        {
            for(DemoOrder order:dto)
            {
                //判断是更新还是插入
                if(order.getOrderId()!=null&&!"".equals(order.getOrderId()))
                {
                    //更新
                    service.updateByPrimaryKey(requestCtx,order);
                }
                else {
                    //插入
                    //构建订单编号

                    String  companyName =order.getOrderCompany();
                    Map<String,String> map = new HashMap<>();
                    map.put("companyName",companyName);
                    String orderCode =  codeRuleProcessService.getRuleCode("DEMO_ORDER_CODE",map);
                    order.setOrderCode(orderCode);
                    service.insertSelective(requestCtx,order);
                }

            }

        }
        return dto;
    }
}