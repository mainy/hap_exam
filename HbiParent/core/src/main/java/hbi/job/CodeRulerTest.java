package hbi.job;

import com.hand.hap.code.rule.exception.CodeRuleException;
import com.hand.hap.code.rule.service.ISysCodeRuleProcessService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 10416 on 2018/8/8.
 */
public class CodeRulerTest {

    ISysCodeRuleProcessService codeRuleProcessService;

    public String getCode() throws CodeRuleException {
        Map<String,String> map = new HashMap<>();
        map.put("companyName","xingxingstore");
       String orderCode =  codeRuleProcessService.getRuleCode("DEMO_ORDER_CODE",map);

        return orderCode;
    }

}
