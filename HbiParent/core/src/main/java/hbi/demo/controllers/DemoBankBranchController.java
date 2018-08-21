package hbi.demo.controllers;

import hbi.demo.dto.DemoBank;
import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import hbi.demo.dto.DemoBankBranch;
import hbi.demo.service.IDemoBankBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.BindingResult;
import java.util.List;

    @Controller
    public class DemoBankBranchController extends BaseController{

    @Autowired
    private IDemoBankBranchService service;


    @RequestMapping(value = "/hbi/demo/bank/branch/query")
    @ResponseBody
    public ResponseData query(DemoBank dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        DemoBankBranch demoBankBranch = new DemoBankBranch();
        demoBankBranch.setBankId(dto.getBankId());
        return new ResponseData(service.select(requestContext,demoBankBranch,page,pageSize));
    }

    @RequestMapping(value = "/hbi/demo/bank/branch/submit")
    @ResponseBody
    public ResponseData update(@RequestBody List<DemoBankBranch> dto, BindingResult result, HttpServletRequest request){
        getValidator().validate(dto, result);
        if (result.hasErrors()) {
        ResponseData responseData = new ResponseData(false);
        responseData.setMessage(getErrorMessage(result, request));
        return responseData;
        }
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/hbi/demo/bank/branch/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<DemoBankBranch> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    }