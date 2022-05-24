package com.rei1997.vault.controller;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rei1997.vault.api.Qpay.QpayHelper;
import com.rei1997.vault.api.Qpay.OrderCreate.OrderCreateReq;
import com.rei1997.vault.api.Qpay.OrderCreate.OrderCreateReq.*;
import com.rei1997.vault.api.Qpay.OrderPayQuery.OrderPayQueryReq;
import com.rei1997.vault.api.Qpay.OrderQuery.OrderQueryReq;
import com.rei1997.vault.service.GetAPIService;
import com.rei1997.vault.service.NextNumService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping(value="api/v1/Qpay",produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class QpayController {
    // private final OrderCreateReq orderCreateReq;
    private final CardParam cardParam;
    private final QpayHelper qpayHelper;
    private final NextNumService service;
    private final GetAPIService getAPIService;
    
    @PostMapping("/")
    public String orderCreate(@RequestBody OrderCreateReq orderCreateReq){


        cardParam.setAutoBilling(AutoBilling.Y);
        orderCreateReq.setPayType(PayType.C);
        orderCreateReq.setPrdtName("鐵肝30全端挑戰大訂單");
        orderCreateReq.setShopNo("NA0249_001");
        orderCreateReq.setOrderNo(String.valueOf(service.getNum()));
        orderCreateReq.setCurrencyID("TWD");
        orderCreateReq.setCardParam(cardParam);
        orderCreateReq.setReturnURL("http://localhost:3000/list");
        String jsonRes="";
        try {
            jsonRes=qpayHelper.qpayHelper(orderCreateReq, "OrderCreate");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.err.println(e.toString());
        }
        return jsonRes;


    }

    @PostMapping("/query")
    public String orderPayQuery(@RequestParam Map<String,String>params){

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_NULL);
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        OrderPayQueryReq orderPayQueryReq=
        mapper.convertValue(params, OrderPayQueryReq.class);
        String jsonRes="";
        try {
            jsonRes=qpayHelper.qpayHelper(orderPayQueryReq, "OrderPayQuery");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.toString());
        }
        return jsonRes;
    }

    @GetMapping("/query/all")
    public String orderPayQueryAll(){
        OrderQueryReq orderQueryReq = new OrderQueryReq();
        orderQueryReq.setShopNo("NA0249_001");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -7);
        String timeStampe = new SimpleDateFormat("yyyyMMddHHmm").format(Calendar.getInstance().getTime());
        String timeStamps = new SimpleDateFormat("yyyyMMddHHmm").format(c.getTime());
        orderQueryReq.setOrderDateTimeS(timeStamps);
        orderQueryReq.setOrderDateTimeE(timeStampe);

        String jsonRes="";
        try {
            jsonRes=qpayHelper.qpayHelper(orderQueryReq, "OrderQuery");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.toString());
        }
        return jsonRes;
    }

    @GetMapping("/rate")
    public String getRate(){
        try {
            return  getAPIService.getMmaRate();
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }

    }   


}
