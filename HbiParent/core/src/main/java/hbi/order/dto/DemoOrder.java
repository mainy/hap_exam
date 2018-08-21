package hbi.order.dto;

/**Auto Generated By Hap Code Generator**/
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import org.hibernate.validator.constraints.Length;
import javax.persistence.Table;
import com.hand.hap.system.dto.BaseDTO;
import java.util.Date;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@ExtensionAttribute(disable=true)
@Table(name = "hbi_demo_order")
public class DemoOrder extends BaseDTO {

     public static final String FIELD_ORDER_ID = "orderId";
     public static final String FIELD_ORDER_CODE = "orderCode";
     public static final String FIELD_ORDER_NAME = "orderName";
     public static final String FIELD_ORDER_DATE = "orderDate";
     public static final String FIELD_ORDER_COMPANY = "orderCompany";


     @Id
     @GeneratedValue
     private Long orderId; //表ID，主键，供其他表做外键

     @NotEmpty
     @Length(max = 250)
     private String orderCode; //订单编号

     @NotEmpty
     @Length(max = 250)
     private String orderName; //订单名称
    @DateTimeFormat(pattern = "yyyy-MM-dd")
     private Date orderDate; //订单日期

     @NotEmpty
     @Length(max = 250)
     private String orderCompany; //订单公司


     public void setOrderId(Long orderId){
         this.orderId = orderId;
     }

     public Long getOrderId(){
         return orderId;
     }

     public void setOrderCode(String orderCode){
         this.orderCode = orderCode;
     }

     public String getOrderCode(){
         return orderCode;
     }

     public void setOrderName(String orderName){
         this.orderName = orderName;
     }

     public String getOrderName(){
         return orderName;
     }

     public void setOrderDate(Date orderDate){
         this.orderDate = orderDate;
     }

     public Date getOrderDate(){
         return orderDate;
     }

     public void setOrderCompany(String orderCompany){
         this.orderCompany = orderCompany;
     }

     public String getOrderCompany(){
         return orderCompany;
     }

     }