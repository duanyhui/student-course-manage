package duan.server.utils;

import io.jsonwebtoken.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

/**
 * JWT工具类
 * @author duanyhui
 * @since 2022/08/23
 */
@Component
public class JwtUtil {


    private static String JWT_KEY;
    private  static  Long JWT_TTL;

    @Value("${token.JWT_TTL}")
    public void setJwtTtl(long JWT_TTL) {
        this.JWT_TTL = JWT_TTL;
    }

    @Value("${token.JWT_KEY}")
    public void setJwtKey(String JWT_KEY) {
        this.JWT_KEY = JWT_KEY;
    }


//    @Value("${token.secretKey}")
//    public void setSecretKey(String secretKey) {
//        this.JWT_KEY = secretKey;
//    }
//
//    @Value("${token.expireTime}")
//    public void setExpireTime(long expireTime) {
//        this.JWT_TTL = expireTime;
//    }

//    //有效期为
//    public static final Long JWT_TTL = 60 * 60 *1000L;// 60 * 60 *1000  一个小时
//    //设置秘钥明文
//    public static final String JWT_KEY = "";

    public static String getUUID(){
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        return token;
    }
    
    /**
     * 生成jwt
     * @param subject token中要存放的数据（json格式）
     * @return
     */
    public static String createJWT(String subject) {
        JwtBuilder builder = getJwtBuilder(subject, (Long) null, getUUID());// 设置过期时间
        return builder.compact();
    }

    /**
     * 生成jwt
     * @param subject token中要存放的数据（json格式）
     * @param ttlMillis token超时时间
     * @return
     */
    public static String createJWT(String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID());// 设置过期时间
        return builder.compact();
    }

    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if(ttlMillis==null){
            ttlMillis=JwtUtil.JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        return Jwts.builder()

                .setId(uuid)              //唯一的ID
                .setSubject(subject)   // 主题  可以是JSON数据
                .setIssuer("duanyhui")     // 签发者
                .setIssuedAt(now)      // 签发时间
                .signWith(signatureAlgorithm, secretKey) //使用HS256对称加密算法签名, 第二个参数为秘钥
                .setExpiration(expDate);
    }

    /**
     * 生成带角色权限的token
     *
     * @param subject
     * @param authorities
     * @return
     */
    public static String createJWT(String subject, Collection<? extends GrantedAuthority> authorities) {

        JwtBuilder builder = getJwtBuilder(subject, authorities, getUUID());// 设置过期时间
        return builder.compact();

    }

    private static JwtBuilder getJwtBuilder(String subject, Collection<? extends GrantedAuthority> authorities, String uuid) {
        String role = StringUtils.strip(authorities.toString(),"[]");
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        long expMillis = nowMillis + JWT_TTL;
        Date expDate = new Date(expMillis);
        return Jwts.builder()

                .setId(uuid)              //唯一的ID
                .setSubject(subject)   // 主题  可以是JSON数据
                .claim("authorities", role) //自定义属性 放入权限信息
                .setIssuer("duanyhui")     // 签发者
                .setIssuedAt(now)      // 签发时间
                .signWith(signatureAlgorithm, secretKey) //使用HS256对称加密算法签名, 第二个参数为秘钥
                .setExpiration(expDate);
    }


    /**
     * 创建token
     * @param id
     * @param subject
     * @param ttlMillis
     * @return
     */
    public static String createJWT(String id, String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id);// 设置过期时间
        return builder.compact();
    }

    public static void main(String[] args) throws Exception {

        System.out.println(JWT_KEY);
        System.out.println(JWT_TTL);
        Map<String,String> map=new HashMap<>();
        map.put("uid","2");
        map.put("ROLE","ROOT");

        System.out.println(map.toString());
        String jwt = createJWT(String.valueOf(map));
        System.out.println(jwt);
        Claims claims = parseJWT(jwt);
        System.out.println(claims.getSubject());


    }



    /**
     * 生成加密后的秘钥 secretKey
     * @return
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }


    /**
     * 解析
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        try {

            return Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(jwt)
                    .getBody();
        }
        catch (ExpiredJwtException e){
            throw new Exception("token已过期");
        }
        catch (Exception e){
            throw new Exception("token解析失败");
        }
    }


}