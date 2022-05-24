package com.rei1997.vault.util.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class AuthorizationCheckFilter extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws ServletException, IOException {

                if(!req.getServletPath().equals("/api/v1/user/login")&&
                !req.getServletPath().equals("/api/v1/Qpay/query")){
                    String authorHeader =  req.getHeader(AUTHORIZATION);
                    String bearer ="Bearer ";
                    
                    if(authorHeader!= null && authorHeader.startsWith(bearer)){
                        try{
                        String token = authorHeader.substring(bearer.length());
                        Claims claims = Jwts.parser().setSigningKey("MySecret")
                        .parseClaimsJws(token).getBody();

                        System.out.println("JWT payload:"+claims.toString());

                        // String userEmail=claims.getSubject();
                        // UsernamePasswordAuthenticationToken authenticationToken=
                        // new UsernamePasswordAuthenticationToken(userEmail, null) ;

                        // SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                        chain.doFilter(req, res);
                        
                        }catch(Exception e){
                            System.err.println("Error : ");
                            e.printStackTrace();
                            //res.setHeader("err", e.getMessage());
                            res.setStatus(FORBIDDEN.value());
                            
                            Map<String, String> err = new HashMap<>();
                            err.put("jwt_err", e.getMessage());
                            res.setContentType(APPLICATION_JSON_VALUE);
                            new ObjectMapper().writeValue(res.getOutputStream(), err);
                        }
                    }else{
                        res.setStatus(UNAUTHORIZED.value());
                    }
                }else{
                    chain.doFilter(req, res);
                }
        
    }

}
