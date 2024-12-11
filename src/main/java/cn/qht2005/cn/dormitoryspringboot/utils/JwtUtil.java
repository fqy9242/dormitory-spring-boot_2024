package cn.qht2005.cn.dormitoryspringboot.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
public class JwtUtil {
	/**
	 *  生成jwt令牌
	 * @param secretKey jwt秘钥
	 * @param ttlMillis 过期时间(毫秒)
	 * @param claims 加密的信息
	 * @return jwt令牌
	 */
	public static String createJWT(String secretKey, Long ttlMillis, Map<String, Object> claims) {
		// 指定签名的时候使用的签名算法，也就是header那部分
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		// 设置jwt的过期时间
		long expMillis = System.currentTimeMillis() + ttlMillis;
		Date exp = new Date(expMillis);
		// 生成jwt
		JwtBuilder jwtBuilder = Jwts.builder()
				.setClaims(claims)
				.signWith(signatureAlgorithm, secretKey.getBytes(StandardCharsets.UTF_8))
				.setExpiration(exp);
		// 返回令牌
		return  jwtBuilder.compact();
	}

	/**
	 *  jwt解密
	 * @param secretKey 秘钥
	 * @param token jwt
	 * @return 加密的信息
	 */
	public  static Claims parseJWT(String secretKey, String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
				.parseClaimsJws(token)
				.getBody();
		return  claims;
	}
}
