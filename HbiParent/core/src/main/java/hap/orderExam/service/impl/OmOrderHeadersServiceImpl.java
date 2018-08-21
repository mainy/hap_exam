package hap.orderExam.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import hap.orderExam.mapper.OmOrderHeadersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hap.orderExam.dto.OmOrderHeaders;
import hap.orderExam.service.IOmOrderHeadersService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class OmOrderHeadersServiceImpl extends BaseServiceImpl<OmOrderHeaders> implements IOmOrderHeadersService{
    @Autowired
    private OmOrderHeadersMapper mapper;

    @Override
    public List<OmOrderHeaders> selectAll1(IRequest request, OmOrderHeaders head, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return mapper.selectAll1(head);
    }

    @Override
    public boolean deleteAll(Long headerid) {
        return mapper.deleteAll(headerid);
    }

    @Override
    public int selectByOrderNumber(String ordernumber) {
        return mapper.selectByOrderNumber(ordernumber);
    }

    @Override
    public boolean updateHeader(OmOrderHeaders head) {
        return mapper.updateHeader(head);
    }



}