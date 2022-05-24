package com.rei1997.vault.service;

import java.util.Optional;
import com.rei1997.vault.model.entity.NextNum;
import com.rei1997.vault.model.repository.NextNumRepo;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NextNumService {
    
    private final NextNumRepo nextNumRepo;
    public long getNum(){
        Optional<NextNum> num=nextNumRepo.findById("no");
        long nextNum=0;
        if(num.isPresent()){
            nextNum=num.get().getNo()+1;
            num.get().setNo(nextNum);
            nextNumRepo.save(num.get());;
        }else{
            NextNum n=new NextNum();
            n.setId("no");
            n.setNo(1);
            nextNum=n.getNo();
            nextNumRepo.save(n);
        }
        return nextNum;
    }

}
