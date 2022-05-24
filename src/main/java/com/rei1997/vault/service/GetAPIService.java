package com.rei1997.vault.service;

import java.io.IOException;
import com.rei1997.vault.util.HttpUtil;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetAPIService {
    
    private final  HttpUtil util;

    public String getMmaRate() throws IOException{
        String res="";
        res=util.post("https://mma.sinopac.com/ws/share/rate/ws_exchange.ashx", "");
        return res;
    }
}
