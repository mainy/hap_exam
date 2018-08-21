package hbi.order.service;

import com.hand.hap.code.rule.exception.CodeRuleException;
import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import hbi.order.dto.DemoOrder;

import java.util.List;

public interface IDemoOrderService extends IBaseService<DemoOrder>, ProxySelf<IDemoOrderService>{
         List<DemoOrder>  mybatchUpdate(IRequest requestCtx,List<DemoOrder> dto) throws CodeRuleException;
}