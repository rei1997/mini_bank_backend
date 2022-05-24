package com.rei1997.vault.api.Qpay.OrderCreate;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.rei1997.vault.api.Qpay.QpayReq;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@JsonPropertyOrder(alphabetic=true)
public class OrderCreateReq implements QpayReq{

    public enum PayType { A, C };
    public enum AutoBilling { Y, N };
    
    @Data
    @Component
    public class ATMParam{
        private Integer expireDate;
    }
    @Data
    @Component
    public class CardParam{
        private AutoBilling autoBilling;
        private Integer expBillingDays;
        private Integer expMinutes;
        private String payTypeSub;
    }
    
    private String shopNo;
    private String orderNo;
    private Integer amount;
    private String currencyID;
    private String prdtName;
    private String memo;
    private String param1;
    private String param2;
    private String param3;
    private String returnURL;
    private String backendURL;
    private PayType payType;
    private ATMParam ATMParam;
    private CardParam cardParam;

}
