package com.rei1997.vault.api.Qpay.OrderQuery;

import com.rei1997.vault.api.Qpay.QpayReq;

import lombok.Data;

@Data
public class OrderQueryReq implements QpayReq{

    public enum PayType { A, C };
    public enum PayFlag { Y, N, O };

    private String shopNo;
    private String orderNo;
    private PayType payType;
    private String orderDateTimeS;
    private String orderDateTimeE;
    private String payDateTimeS;
    private String payDateTimeE;
    private PayFlag payFlag;


}
