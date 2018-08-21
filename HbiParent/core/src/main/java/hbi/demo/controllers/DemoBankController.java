package hbi.demo.controllers;

import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import hbi.demo.dto.DemoBank;
import hbi.demo.service.IDemoBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.BindingResult;
import java.util.List;

    @Controller
    public class DemoBankController extends BaseController{

    @Autowired
    private IDemoBankService service;


    @RequestMapping(value = "/hbi/demo/bank/query")
    @ResponseBody
    public ResponseData query(DemoBank dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/hbi/demo/bank/submit")
    @ResponseBody
    public ResponseData update(@RequestBody List<DemoBank> dto, BindingResult result, HttpServletRequest request){
        getValidator().validate(dto, result);
        if (result.hasErrors()) {
        ResponseData responseData = new ResponseData(false);
        responseData.setMessage(getErrorMessage(result, request));
        return responseData;
        }
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.myBatchUpdate(requestCtx, dto));

    }

    @RequestMapping(value = "/hbi/demo/bank/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<DemoBank> dto) throws Exception {
        IRequest requestContext = createRequestContext(request);
        //service.batchDelete(dto);
        service.mybatchDelete(requestContext,dto);
        return new ResponseData();
    }
    }