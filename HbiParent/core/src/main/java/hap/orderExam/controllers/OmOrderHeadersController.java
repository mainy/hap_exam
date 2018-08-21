package hap.orderExam.controllers;

import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import hap.orderExam.dto.OmOrderHeaders;
import hap.orderExam.service.IOmOrderHeadersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

    @Controller
    public class OmOrderHeadersController extends BaseController{

    @Autowired
    private IOmOrderHeadersService service;

    @RequestMapping(value = "/hap/om/order/headers/queryById")
    @ResponseBody
    public ResponseData queryById(OmOrderHeaders dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        List<OmOrderHeaders> headerList = new ArrayList<>();
        headerList.add(service.selectByPrimaryKey(requestContext,dto));
        return new ResponseData(headerList);
    }


    @RequestMapping(value = "/hap/om/order/headers/query")
    @ResponseBody
    public ResponseData query(OmOrderHeaders dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.selectAll1(requestContext,dto,page,pageSize));
    }

        /**
         * 新增头信息
         * @param request
         * @param header
         * @return
         */
    @RequestMapping(value = "/hap/om/order/headers/add")
    @ResponseBody
    public ResponseData insert(HttpServletRequest request, @RequestBody OmOrderHeaders header) {
        IRequest iRequest = createRequestContext(request);
        List<OmOrderHeaders> headerList = new ArrayList<>();
        ResponseData responseData = new ResponseData();
        try {
            //非空验证
            if (header.getOrderNumber().equals("") || header.getCompanyId() == 0 || header.getCustomerId() == 0 || header.getOrderStatus().equals("") || header.getOrderDate().equals("")) {
                responseData.setSuccess(false);
                responseData.setMessage("存在必输字段未填写！");
                responseData.setRows(null);
                return responseData;
            }
            //获取订单编号判断是否重复
            int count = service.selectByOrderNumber(header.getOrderNumber());

            if (count < 1) {
                //不重复
                headerList.add(service.insertSelective(iRequest, header));
                responseData.setRows(headerList);
                responseData.setMessage("成功保存！");
            } else {
                responseData.setSuccess(false);
                responseData.setMessage("订单编号重复！");
                responseData.setRows(null);
                return responseData;
            }
        } catch (Exception e) {
            responseData.setSuccess(false);
            responseData.setMessage("新增订单头记录失败！");
            return responseData;
        }
        return responseData;
    }

        /**
         * 更新订单状态
         * @param request
         * @param header
         * @return
         */
    @RequestMapping(value = "/hap/om/order/headers/submit")
    @ResponseBody
    public ResponseData myupdate(HttpServletRequest request, @RequestBody OmOrderHeaders header) {
        IRequest iRequest = createRequestContext(request);
        List<OmOrderHeaders> headerList = new ArrayList<>();
        ResponseData responseData = new ResponseData();
        try {
            //根据主键来更新订单的状态
            headerList.add(service.updateByPrimaryKey(iRequest, header));
            responseData.setRows(headerList);
            if ("SUBMITED".equals(header.getOrderStatus())) {
                responseData.setMessage("提交成功");
            }
            if ("APPROVED".equals(header.getOrderStatus())) {
                responseData.setMessage("审批成功");
            }
            if ("REJECTED".equals(header.getOrderStatus())) {
                responseData.setMessage("您已拒绝");
            }
        } catch (Exception e) {
            responseData.setSuccess(false);
            responseData.setMessage("操作失败");
            return responseData;
        }
        return responseData;
    }

    @RequestMapping(value = "/hap/om/order/headers/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<OmOrderHeaders> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }

        /**
         * 更新订单头信息
         * @param dto
         * @param request
         * @return
         */
    @RequestMapping(value = "/hap/om/order/headers/update")
    @ResponseBody
    public ResponseData updateHead(@RequestBody OmOrderHeaders dto, HttpServletRequest request) {
        ResponseData responseData = new ResponseData();
        try {
            long companyId = dto.getCompanyId();
           // String headerId = request.getParameter("headerId");
           // dto.setHeaderId(Long.parseLong(headerId));
            List<Long> list = new ArrayList<>();
            list.add(dto.getHeaderId());
            list.add(companyId);
            if (service.updateHeader(dto)) {
                responseData.setSuccess(true);
                responseData.setRows(list);
                responseData.setMessage("更新订单头信息成功！");
            }
        } catch (Exception e) {
            responseData.setSuccess(false);
            responseData.setMessage("更新订单头信息失败！");
        }
        return responseData;
    }

        /**
         * 整单删除
         * @param request
         * @param dto
         * @return
         */
    @RequestMapping(value = "/hap/om/order/headers/delete")
    @ResponseBody
    public ResponseData mydelete(HttpServletRequest request, @RequestBody OmOrderHeaders dto) {
        IRequest iRequest = createRequestContext(request);
        ResponseData responseData = new ResponseData();
        try {
            Long headerId = dto.getHeaderId();
            if (service.deleteAll(headerId)) {
                responseData.setMessage("您已删除");
            }
        } catch (Exception e) {
            responseData.setSuccess(false);
            responseData.setMessage("操作失败");
            return responseData;
        }
        return responseData;
    }


    }