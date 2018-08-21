package hap.orderExam.mapper;

import com.hand.hap.mybatis.common.Mapper;
import hap.orderExam.dto.OmOrderHeaders;

import java.util.List;

public interface OmOrderHeadersMapper extends Mapper<OmOrderHeaders>{
    //查询头表记录
    List<OmOrderHeaders> selectAll1(OmOrderHeaders head);

    //整单删除,参数为行id
    public boolean deleteAll(Long headerid);

    //判断订单编号是否重复
    public int selectByOrderNumber(String ordernumber);

    //根据headerId更新头记录
    public boolean updateHeader(OmOrderHeaders head);

    //根据用户id得到用户的角色
    public String getUserRoleByUserId(int userid);

}