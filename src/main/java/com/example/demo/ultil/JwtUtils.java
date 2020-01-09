package com.example.demo.ultil;

import com.example.demo.entity.User;
import com.example.demo.sercurity.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JwtUtils {
    public static Claims verifyToken(HttpServletRequest request){
        String token = request.getHeader(SecurityConstants.HEADER);
        if(token==null||!token.startsWith(SecurityConstants.PREFIX)){
            return null;
        }
        return Jwts.parser()
                .setSigningKey(SecurityConstants.SECRET)
                .parseClaimsJws(token.replace(SecurityConstants.PREFIX,""))
                .getBody();
    }

    public static String generateToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getEmail());
        List<String> roles = new ArrayList<>();
        roles.add(user.getRoles());
        // Thông tin lưu trữ trong JWT dạng json key value
        // Muốn lưu thêm thông tin khác thì cứ put vào
        claims.put("roles", roles);
        claims.put("id",user.getId());
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + SecurityConstants.EXPIRATION);
        // Encode
        String token = Jwts.builder()
                .setClaims(claims)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
                .compact();
        return SecurityConstants.PREFIX + token;
    }
}
