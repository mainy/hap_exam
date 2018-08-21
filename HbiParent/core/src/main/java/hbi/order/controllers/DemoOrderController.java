package hbi.order.controllers;

import com.hand.hap.code.rule.exception.CodeRuleException;
import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import hbi.order.dto.DemoOrder;
import hbi.order.service.IDemoOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.BindingResult;
import java.util.List;

    @Controller
    public class DemoOrderController extends BaseController{

    @Autowired
    private IDemoOrderService service;


    @RequestMapping(value = "/hbi/demo/order/query")
    @ResponseBody
    public ResponseData query(DemoOrder dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/hbi/demo/order/submit")
    @ResponseBody
    public ResponseData update(@RequestBody List<DemoOrder> dto, BindingResult result, HttpServletRequest request) throws CodeRuleException {
        getValidator().validate(dto, result);
        if (result.hasErrors()) {
        ResponseData responseData = new ResponseData(false);
        responseData.setMessage(getErrorMessage(result, request));
        return responseData;
        }
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.mybatchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/hbi/demo/order/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<DemoOrder> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    }