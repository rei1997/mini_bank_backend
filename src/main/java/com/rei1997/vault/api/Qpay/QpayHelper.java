package com.rei1997.vault.api.Qpay;

import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rei1997.vault.util.EncryptUtil;
import com.rei1997.vault.util.HttpUtil;
import com.rei1997.vault.util.SystemConfigUtil;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class QpayHelper {
    
    private final  HttpUtil util;
    private final EncryptUtil encryptUtil;
    private String getNonce() throws JsonProcessingException,IOException{

        Map<String,String> map = new HashMap<String,String>();
        map.put("ShopNo", SystemConfigUtil.systemConfMap.get("ShopNo"));
        
        String nonceurl=SystemConfigUtil.systemConfMap.get("Qpay_API_Url_Nonce");
        String res="";
        String json = new ObjectMapper().writeValueAsString(map);
        res=util.post(nonceurl, json);

        
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> resMap =new HashMap<String,String>();
        resMap = mapper.readValue(res, new TypeReference<HashMap<String, String>>() {});

        String nonce = resMap.get("Nonce");
        return nonce;
    }

    private String getIV(String nonce){
        String sha256hex = DigestUtils.sha256Hex(nonce); 
		String iv = sha256hex.substring(sha256hex.length()-16, sha256hex.length()).toUpperCase();
        return iv;
    }

    private String getHashID(){
        BigInteger hashA1 = new BigInteger(SystemConfigUtil.systemConfMap.get("HASH_ID_A1"), 16);
        BigInteger hashA2 = new BigInteger(SystemConfigUtil.systemConfMap.get("HASH_ID_A2"), 16);
        BigInteger hashB1 = new BigInteger(SystemConfigUtil.systemConfMap.get("HASH_ID_B1"), 16);
        BigInteger hashB2 = new BigInteger(SystemConfigUtil.systemConfMap.get("HASH_ID_B2"), 16);
        BigInteger hashA = hashA1.xor(hashA2);
        BigInteger hashB = hashB1.xor(hashB2);

        String hashID =hashA.toString(16)+hashB.toString(16);
        return hashID.toUpperCase();
    }

    private String getSign(Map<String, Object> map,String nonce,String hashid){
        //remove sub object
        map.values().removeIf(entries->entries.getClass().equals(java.util.LinkedHashMap.class)||entries.toString().isBlank());

        map.forEach((k,v)->System.out.println(k+":"+v));
        //order
        String param=map.entrySet().stream()
          .map(p -> p.getKey() + "=" + p.getValue())
          .reduce((p1, p2) -> p1 + "&" + p2)
          .orElse("");

        //convert to string
        StringBuilder content = new StringBuilder();
        content.append(param).
        append(nonce).
        append(hashid);
        String sign= DigestUtils.sha256Hex(content.toString()); 
        return sign.toUpperCase();
    }

    public String qpayHelper(QpayReq qReq,String apiName)throws IOException, GeneralSecurityException, DecoderException{
        //1.nonce
        String nonce=getNonce();
        //2.hashID
        String hashID=getHashID();
        //3.iv
        String iv = getIV(nonce);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(new QpayPropNamingStrategy());
        mapper.setSerializationInclusion(Include.NON_NULL);

        //4.message
        String jsonContent =mapper.writeValueAsString(qReq);

        String message = "";
        message= encryptUtil.encrypt(jsonContent, hashID, iv);


        //5.sign
        Map<String, Object> map = 
        mapper.convertValue(qReq, new TypeReference<TreeMap<String, Object>>() {});
        String sign = getSign(map,nonce,hashID);
        
        //send to api
        Map<String,String> request = new HashMap<String,String>();
        request.put("Version", "1.0.0");
        request.put("ShopNo", SystemConfigUtil.systemConfMap.get("ShopNo"));
        request.put("APIService", apiName);
        request.put("Sign", sign);        
        request.put("Nonce", nonce);
        request.put("Message", message);
        
        String reqJson="";
        reqJson = new ObjectMapper().writeValueAsString(request);

        String res="";
        HttpUtil util = new HttpUtil();
        res=util.post(SystemConfigUtil.systemConfMap.get("Qpay_API_Url"), reqJson);


        Map<String, String> resMap =new HashMap<String,String>();
        resMap = mapper.readValue(res, new TypeReference<HashMap<String, String>>() {});

        String resSign =resMap.get("Sign");
        String resNonce =resMap.get("Nonce");
        String resMessage =resMap.get("Message");

        String resiv= getIV(resNonce);

        resMessage=encryptUtil.decrypt(resMessage, hashID, resiv);
 
        Map<String, Object> resMessageMap =new TreeMap<String,Object>();
        resMessageMap = mapper.readValue(resMessage, new TypeReference<TreeMap<String, Object>>() {});

        resMessageMap.forEach((k, v) ->System.out.println(k+":"+v));
        if(apiName!="OrderQuery"){
            String signCheck = getSign(resMessageMap, resNonce, hashID);

            if(signCheck.equals(resSign)){
                System.out.println("Sign Check OK："+signCheck);
            }else{
                throw new IllegalArgumentException("永豐回傳簽章驗證失敗");
            }
        }
        return resMessage;
    }   

}
