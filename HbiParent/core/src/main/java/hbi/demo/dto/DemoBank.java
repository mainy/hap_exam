package hbi.demo.dto;

/**Auto Generated By Hap Code Generator**/
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.hand.hap.core.annotation.Children;
import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import org.hibernate.validator.constraints.Length;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.hand.hap.system.dto.BaseDTO;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@ExtensionAttribute(disable=true)
@Table(name = "hbi_demo_bank")
public class DemoBank extends BaseDTO {

     public static final String FIELD_BANK_ID = "bankId";
     public static final String FIELD_BANK_CODE = "bankCode";
     public static final String FIELD_BANK_NAME = "bankName";
     public static final String FIELD_BANK_TYPE = "bankType";
     public static final String FIELD_DESCRIPTION = "description";
     public static final String FIELD_EXPIRATION_DATE = "expirationDate";
     public static final String FIELD_COMMENTS = "comments";


     @Id
     @GeneratedValue
     private Long bankId; //表ID，主键，供其他表做外键

     @NotEmpty
     @Length(max = 30)
     private String bankCode; //银行简码

     @NotEmpty
     @Length(max = 240)
     private String bankName; //银行名称

     @NotEmpty
     @Length(max = 30)
     private String bankType; //银行类型

     @Length(max = 240)
     private String description; //说明

     @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
     private Date expirationDate; //截止日期

     @Length(max = 240)
     private String comments; //备注



    @Children
    @Transient//该属性不是数据库的映射，是额外添加的，hap框架会自己忽略
    private List<DemoBankBranch> demoBankBranchList;

    public List<DemoBankBranch> getDemoBankBranchList() {
        return demoBankBranchList;
    }

    public void setDemoBankBranchList(List<DemoBankBranch> demoBankBranchList) {
        this.demoBankBranchList = demoBankBranchList;
    }

     public void setBankId(Long bankId){
         this.bankId = bankId;
     }

     public Long getBankId(){
         return bankId;
     }

     public void setBankCode(String bankCode){
         this.bankCode = bankCode;
     }

     public String getBankCode(){
         return bankCode;
     }

     public void setBankName(String bankName){
         this.bankName = bankName;
     }

     public String getBankName(){
         return bankName;
     }

     public void setBankType(String bankType){
         this.bankType = bankType;
     }

     public String getBankType(){
         return bankType;
     }

     public void setDescription(String description){
         this.description = description;
     }

     public String getDescription(){
         return description;
     }

     public void setExpirationDate(Date expirationDate){
         this.expirationDate = expirationDate;
     }

     public Date getExpirationDate(){
         return expirationDate;
     }

     public void setComments(String comments){
         this.comments = comments;
     }

     public String getComments(){
         return comments;
     }

     }
