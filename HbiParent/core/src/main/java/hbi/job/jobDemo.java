package hbi.job;
import com.hand.hap.job.AbstractJob;
import com.hand.hap.job.dto.JobCreateDto;
import com.hand.hap.mail.service.IEmailService;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 10416 on 2018/8/7.
 */
public class jobDemo extends AbstractJob{
    @Autowired
    private IEmailService emailService;
    @Override
    public void safeExecute(JobExecutionContext context) throws Exception {
       // emailService.
        JobDetail jobDetail = context.getJobDetail();
        //JobDetail
       // jobDetail.get
        JobCreateDto jobCreateDto ;
        System.out.println("=====================吴星星 =================================19750");
    }
}
