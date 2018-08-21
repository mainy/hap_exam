package hbi.demo.service.impl;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import hbi.demo.dto.DemoBankBranch;
import hbi.demo.service.IDemoBankBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hbi.demo.dto.DemoBank;
import hbi.demo.service.IDemoBankService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class DemoBankServiceImpl extends BaseServiceImpl<DemoBank> implements IDemoBankService {
    @Autowired
    IDemoBankBranchService demoBankBranchService;
    @Autowired
    IDemoBankService demoBankService;

    @Override
    public int mybatchDelete(IRequest request, List<DemoBank> bankList) throws Exception {
        int count = 0;
        //过滤参数
        if (null == bankList || bankList.isEmpty()) {
            throw new Exception("参数丢失为空");
        }
        for (DemoBank bank : bankList) {
            //查询银行编号
            Long bankId = bank.getBankId();
            DemoBankBranch demoBankBranchSelect = new DemoBankBranch();
            demoBankBranchSelect.setBankId(bankId);
            //查询分行并删除
            List<DemoBankBranch> selectList = demoBankBranchService.select(request, demoBankBranchSelect, 1, 0);
            if (null != selectList && !selectList.isEmpty()) {
                demoBankBranchService.batchDelete(selectList);
            }
            //删除总行
            int i = demoBankService.batchDelete(bankList);
            count += i;

        }

        return count;
    }

    @Override
    public List<DemoBank> myBatchUpdate(IRequest iRequest, List<DemoBank> demoBanks) {
        if (demoBanks != null && !demoBanks.isEmpty()) {
            for (int i = 0; i < demoBanks.size(); i++) {
                DemoBank demoBank = demoBanks.get(i);
                //根据是否拥bankId有判断是insert还是update
                Long bankId = demoBank.getBankId();
                if (bankId == null||bankId==0) {
                    //insert
                    //保存头
                    //demoBank.setBankId(null);
                    demoBankService.insertSelective(iRequest, demoBank);

                    //insert之后便有主键了
                    bankId = demoBank.getBankId();
                    //保存行
                    List<DemoBankBranch> demoBankBranchList = demoBank.getDemoBankBranchList();
                    for (int j = 0; j < demoBankBranchList.size(); j++) {
                        DemoBankBranch demoBankBranch = demoBankBranchList.get(j);
                        demoBankBranch.setBankId(bankId);
                        demoBankBranchService.insertSelective(iRequest, demoBankBranch);
                    }

                } else {
                    //update
                    //保存头
                    demoBankService.updateByPrimaryKeySelective(iRequest, demoBank);
                    //保存行的时候需要区分行是新建还是更新
                    List<DemoBankBranch> demoBankBranchList = demoBank.getDemoBankBranchList();
                    if (demoBankBranchList != null && !demoBankBranchList.isEmpty()) {
                        for (int j = 0; j < demoBankBranchList.size(); j++) {
                            DemoBankBranch demoBankBranch = demoBankBranchList.get(j);
                            Long bankBranchId = demoBankBranch.getBankBranchId();
                            if (bankBranchId == null) {
                                //分行是插入
                                demoBankBranch.setBankId(bankId);
                                demoBankBranchService.insertSelective(iRequest, demoBankBranch);
                            } else {
                                //分行是更新
                                demoBankBranchService.updateByPrimaryKeySelective(iRequest, demoBankBranch);
                            }
                        }
                    }
                }
            }
            return demoBanks;
        }
        return null;
    }
}
