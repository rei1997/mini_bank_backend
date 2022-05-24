package com.rei1997.vault.api.Qpay.OrderPayQuery;

import com.rei1997.vault.api.Qpay.QpayReq;

import lombok.Data;

@Data
public class OrderPayQueryReq implements QpayReq{
    private String ShopNo;
    private String PayToken;
}
