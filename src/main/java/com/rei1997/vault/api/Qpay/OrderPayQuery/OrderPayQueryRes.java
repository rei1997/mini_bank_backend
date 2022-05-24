package com.rei1997.vault.api.Qpay.OrderPayQuery;

import com.rei1997.vault.api.Qpay.QpayRes;

import lombok.Data;

@Data
public class OrderPayQueryRes implements QpayRes{

    public enum Status { S, F };
    public enum APType { PayOut, CaptureOut, RegularOut, RegularEnd };
    public enum PayType { A, C };

    @Data
    public class TSResultContent{
        private APType APType;
        private String TSNo;
        private String OrderNo;
        private String ShopNo;
        private PayType PayType;
        private Integer Amount;
        private Status Status;
        private String Description;
        private String Param1;
        private String Param2;
        private String Param3;
        private String LeftCCNo;
        private String RightCCNo;
        private String CCExpDate;
        private String CCToken;
        private String PayDate;
        private String MasterOrderNo;
    }

    private String ShopNo;
    private String PayToken;
    private String Date;
    private String Status;
    private String Description;
    private TSResultContent TSResultContent;

}
