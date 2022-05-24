package com.rei1997.vault.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.rei1997.vault.model.entity.User;
import com.rei1997.vault.model.repository.UserRepo;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@Service //標記這是一個Service層的class，Spring會將其建立並納入Spring Container內
@RequiredArgsConstructor
public class UserService  {
    
    private final UserRepo userRepo;
    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public Optional<User> getOneUser(String email){
        return userRepo.findUserByEmail(email);
    }

    public Map<String,String> verifyUser(String email,String userAcct , String userPasswd){
        /*
         0000 login success
         0001 wrong email
         0002 wrong useracct
         0003 wrong passwd
         0004 acct locked
         */
        Map<String,String> token =new HashMap<String,String>();
        token.put("status", "9999");
        Optional<User> user=userRepo.findUserByEmail(email);
        if(user.isPresent()){
            if(user.get().getUserAccount().equals(userAcct)){
                if(user.get().getUserPassword().equals(userPasswd)){

                    Date expireDate = new Date(System.currentTimeMillis()+ 30 * 60 * 1000);//30min                    

                    String jwtToken = Jwts.builder()
                    .setSubject(email)
                    .setExpiration(expireDate)
                    .signWith(SignatureAlgorithm.HS512,"MySecret")
                    .compact();

                    token.put("status", "0000");
                    token.put("token",jwtToken);

                }else{
                    token.put("status", "0003");
                }
            }else{
                token.put("status", "0002");
            }
        }else{
            token.put("status", "0001");
        }
        return token;        
    }    

    public void addUser(User user){
        user.setDepositAccount(0);
        user.setStatus(1);

        userRepo.insert(user);
    }

    // @Override
    // public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    //     System.out.println(email);
    //     Optional<User> user =userRepo.findUserByEmail(email);
    //     if (!user.isPresent()){
    //         System.out.println(email+" not found!13");
    //     }

    //     Collection<SimpleGrantedAuthority> auth = new ArrayList<>();
    //     // Collection<GrantedAuthority> auth =  AuthorityUtils
    //     // .createAuthorityList("ROLE_USER");
        
    //     return new org.springframework.security.core.userdetails.User(user.get().getUserAccount(),user.get().getUserPassword(),auth);
    // }

}
