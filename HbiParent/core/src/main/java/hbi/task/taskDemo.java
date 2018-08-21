package hbi.task;

import com.hand.hap.task.dto.TaskDetail;
import com.hand.hap.task.info.ExecutionInfo;
import com.hand.hap.task.service.ITask;
import com.hand.hap.task.service.ITaskCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by 10416 on 2018/8/7.
 */
public class taskDemo implements ITask {
    Logger logger = LoggerFactory.getLogger(ITask.class);
    @Override
    public void execute(ExecutionInfo executionInfo) throws Exception {
        Map<String, Object> param =executionInfo.getParam();
        TaskDetail taskDetail = executionInfo.getTaskDetail();
        System.out.println(param.toString());
        System.out.println("============================================================================================");
        logger.info( param.toString());
        logger.info(executionInfo.toString());
        logger.info(taskDetail.toString());
        executionInfo.getExecuteResultPath();


    }

    @Override
    public <T> T safeExecute(ITaskCallback<T> callback) throws Exception {
        return null;
    }
}
