package com.rei1997.vault.api.Qpay.OrderCreate;


import lombok.Data;
@Data
public class OrderCreateRes {
    public enum PayType { A, C };
    public enum Status {S,F};

    
    @Data
    public class ATMParam{
        private String AtmPayNo;
        private String WebAtmURL;
        private String OtpUrl;
    }
    @Data
    public class CardParam{
        private String CardPayURL;
    }
    
    private String OrderNo;
    private String ShopNo;
    private String TSNo;
    private Integer Amount;
    private Status Status;
    private String Description;
    private String Param1;
    private String Param2;
    private String Param3;
    private PayType PayType;
    private ATMParam ATMParam;
    private CardParam CardParam;

}
