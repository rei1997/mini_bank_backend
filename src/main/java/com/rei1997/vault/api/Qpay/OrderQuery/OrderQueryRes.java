package com.rei1997.vault.api.Qpay.OrderQuery;

import lombok.Data;

@Data
public class OrderQueryRes {

    public enum Status { S, F };
    public enum PayType { A, C };
    public enum RefundFlag { Y, N, R };

    @Data
    public class OrderList{
        private String OrderNo;
        private String TSNo;
        private String TSDate;
        private String ApprovedDate;
        private String PayDate;
        private Integer Amount;
        private PayType PayType;
        private String PayStatus;
        private String ExpireDate;
        private RefundFlag RefundFlag;
        private String RefundStatus;
        private String RefundDate;
        private String PrdtName;
        private String Memo;
        private String Param1;
        private String Param2;
        private String Param3;
        

    }    
    @Data
    public class ATMParam{
        private String OrderList;
        private String WebAtmURL;
        private String OtpURL;
        private String BankNo;
        private String AcctNo;
    }

    @Data
    public class CardParam{
        private String CardPayURL;
        private Integer StagingFirstAmount;
        private Integer StagingEachAmount;
        private Integer BonusCount;
        private Integer BonusAmount;
        private Integer BonusPayAmount;
        private Integer BonusLastCount;
        private String LeftCCNo;
        private String RightCCNo;
        private String AuthCode;
        private String CCExpDate;
        private String CCToken;
    }
    
    private String ShopNo;
    private String Date;
    private Status Status;
    private String Description;
    private OrderList OrderList;
    private ATMParam ATMParam;
    private CardParam CardParam;
    
}
