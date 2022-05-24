package com.rei1997.vault;




import java.io.IOException;
import java.lang.invoke.StringConcatException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.rei1997.vault.api.Qpay.QpayHelper;
import com.rei1997.vault.api.Qpay.OrderCreate.OrderCreateReq;
import com.rei1997.vault.api.Qpay.OrderCreate.OrderCreateReq.AutoBilling;
import com.rei1997.vault.api.Qpay.OrderCreate.OrderCreateReq.CardParam;
import com.rei1997.vault.api.Qpay.OrderCreate.OrderCreateReq.PayType;
import com.rei1997.vault.api.Qpay.OrderPayQuery.OrderPayQueryReq;
import com.rei1997.vault.model.entity.SysConf;
import com.rei1997.vault.model.entity.User;
import com.rei1997.vault.model.repository.SysConfRepo;
import com.rei1997.vault.model.repository.UserRepo;
import com.rei1997.vault.util.EncryptUtil;
import com.rei1997.vault.util.HttpUtil;
import com.rei1997.vault.util.SystemConfigUtil;

import org.apache.commons.codec.digest.DigestUtils;
import org.bson.json.JsonObject;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


@SpringBootApplication
public class VaultApplication {

	public static void main(String[] args) {
			SpringApplication.run(VaultApplication.class, args);
	}

	@Bean
//	CommandLineRunner runner(UserRepo userDao){
	CommandLineRunner runner(QpayHelper qpayHelper){	
		return args -> {

			// HttpUtil util = new HttpUtil();
			// Map<String,String> map = new HashMap<String,String>();
			// map.put("ShopNo", "BA0026_001");
			// String json =new ObjectMapper().writeValueAsString(map);


			// String res=util.post("https://sandbox.sinopac.com/QPay.WebAPI/api/Nonce", json);
			// System.out.println(res);


// 			BigInteger hashA1 = new BigInteger("4D9709D699CA40EE", 16);
// 			BigInteger hashA2 = new BigInteger("5A4FEF83140C4E9E", 16);
// 			BigInteger hashB1 = new BigInteger("BC74301945134CB4", 16);
// 			BigInteger hashB2 = new BigInteger("961F67F8FCA44AB9", 16);				
	
// 			BigInteger hashA = hashA1.xor(hashA2);
// 			BigInteger hashB = hashB1.xor(hashB2);
	
// 			String hashID =(hashA.toString(16)+hashB.toString(16)).toUpperCase();

// 			String sha256hex = DigestUtils.sha256Hex("Amount=50000&BackendURL=http://10.11.22.113:8803/QPay.ApiClient/AutoPush/PushSuccess&CurrencyID=TWD&OrderNo=C201804300001&PayType=C&PrdtName=信用卡訂單&ReturnURL=http://10.11.22.113:8803/QPay.ApiClient/Store/Return&ShopNo=BA0026_001NjM2NjY5MDQ3OTQwMzIuMTphZmJjODBhOTM5NzQ1NjMyNDFhZTczMjVjYzg0Mjg5ZjQxYTk2MWI2ZjNkYTA0NDdmOTRhZjU3ZTIzOWJlNDgz4DA70F5E2D800D50B43ED3B537480C64"); 
// System.out.println(sha256hex);
// 			String iv = sha256hex.substring(sha256hex.length()-16, sha256hex.length());
// System.out.println(iv);



// 			OrderCreateReq orderCreateReq = new OrderCreateReq();
// 			orderCreateReq.setShopNo("NA0249_001");
// 			orderCreateReq.setOrderNo("C201804300002246");
// 			orderCreateReq.setAmount(55000);
// 			orderCreateReq.setCurrencyID("TWD");
// 			orderCreateReq.setPayType(PayType.C);

// 			CardParam cardParam = orderCreateReq.new CardParam();
// 			cardParam.setAutoBilling(AutoBilling.Y);
// 			orderCreateReq.setCardParam(cardParam);

// 			orderCreateReq.setPrdtName("信用卡訂單123");
// 			orderCreateReq.setReturnURL("http://google.com");
// 			orderCreateReq.setBackendURL("http://yahoo.com.tw");

// System.err.println(qpayHelper.qpayHelper(orderCreateReq,"OrderCreate"));

// // qpayHelper.orderCreate(orderCreateReq);

// OrderPayQueryReq orderPayQueryReq = new OrderPayQueryReq();
// orderPayQueryReq.setShopNo("NA0249_001");
// orderPayQueryReq.setPayToken("927ab2cd731fd30c845ab9e621306167ceb9a88d465903042e40fff93035fbd9");
// System.out.println(qpayHelper.qpayHelper(orderPayQueryReq,"OrderPayQuery"));
			


// EncryptUtil encryptUtil = new EncryptUtil();
// String data="{\"ShopNo\":\"BA0026_001\",\"OrderNo\":\"A201804270001\",\"Amount\":50000,\"CurrencyID\":\"TWD\",\"PayType\":\"A\",\"ATMParam\":{\"ExpireDate\":\"20180502\"},\"CardParam\":{},\"PrdtName\":\"虛擬帳號訂單\",\"ReturnURL\":\"http://10.11.22.113:8803/QPay.ApiClient/Store/Return\",\"BackendURL\":\"http://10.11.22.113:8803/QPay.ApiClient/AutoPush/PushSuccess\"}";
// String message=encryptUtil.encrypt(data, "17D8E6558DC60E702A6B57E1B9B7060D", "CB6FA68E42B655AB");
// System.out.println(message);

			// SysConf sysConf1 = new SysConf("HASH_ID_A1", "86D50DEF3EB7400E", "永豐豐收款api_hash id 1");
			// SysConf sysConf2 = new SysConf("HASH_ID_A2", "01FD27C09E5549E5", "永豐豐收款api_hash id 2");
			// SysConf sysConf3 = new SysConf("HASH_ID_B1", "9E004965F4244953", "永豐豐收款api_hash id 3");
			// SysConf sysConf4 = new SysConf("HASH_ID_B2", "7FB3385F414E4F91", "永豐豐收款api_hash id 4");
			// SysConf sysConf5 = new SysConf("ShopNo", "NA0249_001", "永豐豐收款api_Shop_no");
			// SysConf sysConf6 = new SysConf("Qpay_API_Url_Nonce", "https://sandbox.sinopac.com/QPay.WebAPI/api/Nonce", "永豐豐收款api_Nonce_url");
			// sysConfRepo.insert(sysConf5);
			// sysConfRepo.insert(sysConf6);
			// sysConfRepo.insert(sysConf2);
			// sysConfRepo.insert(sysConf3);
			// sysConfRepo.insert(sysConf4);

			// String email ="test456@456.ccc";
			// //建立一個user
			// User user = new User(email,"test1","test1",0123456,1);
			// //查這個email在db有沒有重複
			// userDao.findUserByEmail(email)
			// 	.ifPresentOrElse(users ->{
			// 		//有的話就印出來
			// 		System.out.println("already exists");
			// 		System.out.println(users);
		
			// 	}, ()->{
			// 		//沒有的話就新增
			// 		userDao.insert(user);	
			//	});
		};
	}
	
}