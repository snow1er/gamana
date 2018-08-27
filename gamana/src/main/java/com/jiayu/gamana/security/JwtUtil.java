package com.jiayu.gamana.security;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import com.jiayu.gamana.common.Constants;
import com.jiayu.gamana.exception.JwtExpiredTokenException;
import com.jiayu.gamana.exception.JwtTokenMalformedException;
import com.jiayu.gamana.security.dto.AuthContext;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * JWT util
 * @author Neo.Li
 */
@Component
public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    /**
     * token secret
     */
    @Value("${jwt.secret}")
    private String secret;

    /**
     * token expire time
     */
    @Value("${jwt.token.expireTime}")
    private long tokenExpireTime;
    
    /**
     * refresh token expire time
     */
    @Value("${jwt.refreshToken.expireTime}")
    private long refreshTokenExpireTime;

    /**
     * Create token then add claims.
     * @param authContext
     * @return
     */
    public String generateToken(AuthContext authContext) {
        Claims claims = Jwts.claims().setSubject(authContext.getUserId().toString());
        claims.put(Constants.USER_ID, authContext.getUserId());
        claims.put(Constants.USER_NAME, authContext.getUserName());
        claims.put(Constants.ROLE_ID, authContext.getRoleId());
        try {
            LocalDateTime currentTime = LocalDateTime.now();
            return Jwts.builder().setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secret.getBytes("UTF-8"))
                .setExpiration(Date.from(currentTime
                        .plusSeconds(tokenExpireTime)
                        .atZone(ZoneId.systemDefault()).toInstant()))
                .compact();
            
        } catch (UnsupportedEncodingException encodingEx) {
            logger.error("Exception in building token. " + encodingEx);
            throw new JwtTokenMalformedException("Invalid JWT token: " + encodingEx.getMessage());
        }
    }
    
    /**
     * Create refresh token then add claims.
     * @param authContext
     * @return
     */
    public String generateRefreshToken(AuthContext authContext) {
    	Claims claims = Jwts.claims().setSubject(authContext.getUserId().toString());
        claims.put(Constants.USER_ID, authContext.getUserId());
        claims.put(Constants.USER_NAME, authContext.getUserName());
        claims.put(Constants.ROLE_ID, authContext.getRoleId());
        try {
            LocalDateTime currentTime = LocalDateTime.now();
            
            return Jwts.builder()
                    .setClaims(claims)
                    .signWith(SignatureAlgorithm.HS256, secret.getBytes("UTF-8"))
                    .setExpiration(Date.from(currentTime
                            .plusSeconds(refreshTokenExpireTime)
                            .atZone(ZoneId.systemDefault()).toInstant()))
                    .compact();
        } catch (UnsupportedEncodingException encodingEx) {
            logger.error("Exception in building token. " + encodingEx);
            throw new JwtTokenMalformedException("Invalid JWT token: " + encodingEx.getMessage());
        }
    }


    /**
     * Parse token using secret key
     * @param authToken
     * @return
     */
    public Jws<Claims> parseClaims(String authToken) {
        try {
            return Jwts.parser().setSigningKey(secret.getBytes("UTF-8")).parseClaimsJws(authToken);
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException ex) {
            logger.error("Exception in parsing token. " + ex);
            throw new BadCredentialsException("Invalid token: ", ex);
        } catch (ExpiredJwtException expiredEx) {
            logger.error("Exception in parsing token. " + expiredEx);
            throw new JwtExpiredTokenException("Token expired", expiredEx);
        } catch (UnsupportedEncodingException encodingEx) {
            logger.error("Exception in parsing token. " + encodingEx);
            throw new JwtTokenMalformedException("Invalid token: " + encodingEx.getMessage());
        }
    }
    
    
}
