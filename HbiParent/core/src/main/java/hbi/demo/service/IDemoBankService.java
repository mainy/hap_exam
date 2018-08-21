package hbi.demo.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import hbi.demo.dto.DemoBank;

import java.util.List;

public interface IDemoBankService extends IBaseService<DemoBank>, ProxySelf<IDemoBankService>{
    int mybatchDelete(IRequest request ,List<DemoBank> bankList) throws Exception;
    List<DemoBank> myBatchUpdate(IRequest iRequest, List<DemoBank> demoBanks);
}