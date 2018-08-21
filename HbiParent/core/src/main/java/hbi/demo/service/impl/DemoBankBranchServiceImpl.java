package hbi.demo.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import hbi.demo.dto.DemoBankBranch;
import hbi.demo.service.IDemoBankBranchService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class DemoBankBranchServiceImpl extends BaseServiceImpl<DemoBankBranch> implements IDemoBankBranchService{

}