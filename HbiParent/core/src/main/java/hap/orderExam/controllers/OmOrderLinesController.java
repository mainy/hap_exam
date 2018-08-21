package hap.orderExam.controllers;

import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import hap.orderExam.dto.OmOrderLines;
import hap.orderExam.service.IOmOrderLinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

    @Controller
    public class OmOrderLinesController extends BaseController{

    @Autowired
    private IOmOrderLinesService service;

        /**
         * 查询订单行
         * @param dto
         * @param page
         * @param pageSize
         * @param request
         * @return
         */
    @RequestMapping(value = "/hap/om/order/lines/query")
    @ResponseBody
    public ResponseData query(OmOrderLines dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.selectAllLines(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/hap/om/order/lines/submit")
    @ResponseBody
    public ResponseData update(@RequestBody List<OmOrderLines> dto, BindingResult result, HttpServletRequest request){
        getValidator().validate(dto, result);
        if (result.hasErrors()) {
        ResponseData responseData = new ResponseData(false);
        responseData.setMessage(getErrorMessage(result, request));
        return responseData;
        }
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

        /**
         * 批量删除
         * @param request
         * @param dto
         * @return
         */
    @RequestMapping(value = "/hap/om/order/lines/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<OmOrderLines> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }

        /**
         * 订单行插入
         * @param request
         * @param line
         * @return
         */
    @RequestMapping(value = "/hap/om/order/lines/add")
    @ResponseBody
    public ResponseData insert(HttpServletRequest request, @RequestBody OmOrderLines line) {
        IRequest iRequest = createRequestContext(request);
        List<OmOrderLines> linesList = new ArrayList<>();
        ResponseData responseData = new ResponseData();
        line.setOrderQuantityUom(line.getItemUom());
        try {
            //获取前台传来的参数进行完整性判断
            if (line.getItemUom().equals("") || line.getDescription().equals("") || line.getOrderdQuantity() == 0 || line.getUnitSellingPrice() == 0) {
                responseData.setSuccess(false);
                responseData.setMessage("存在必输字段未填写！");
                responseData.setRows(null);
                return responseData;
            }
            linesList.add(service.insertSelective(iRequest, line));
            responseData.setRows(linesList);
            responseData.setMessage("新增成功");
        } catch (Exception e) {
            responseData.setSuccess(false);
            responseData.setMessage("新增失败");
            return responseData;
        }
        return responseData;
    }

        /**
         * 获取订单行行号
         * @param headerId
         * @return
         */
        @RequestMapping(value = "/OrderLine/getSeqMum", method = RequestMethod.GET)
        @ResponseBody
        public Long findMaxSeqNum(@RequestParam Long headerId) {
            return service.findMaxSeqNum(headerId);
        }

    }