package hbi.activiti;

import com.hand.hap.activiti.custom.IActivitiBean;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

/**
 * Created by 10416 on 2018/8/7.
 */
@Component
public class CreditService implements JavaDelegate,IActivitiBean{
    @Override
    public void execute(DelegateExecution delegateExecution) {
         Integer amount= delegateExecution.getVariable("amount",Integer.class);
        Integer  credit =delegateExecution.getVariable("credit",Integer.class);
        if(amount>credit*1000) {
            delegateExecution.setVariable("accept",Boolean.FALSE);
        }
        else {
            delegateExecution.setVariable("accept",Boolean.TRUE);
        }
    }
    @Override
    public String getBeanName()
    {
        return "checkCredit";
    }
}
