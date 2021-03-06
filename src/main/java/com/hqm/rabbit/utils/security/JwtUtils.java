package com.hqm.rabbit.utils.security;

import com.hqm.rabbit.domain.vo.SysUserVo;
import com.hqm.rabbit.service.LoginService;
import com.hqm.rabbit.service.UserService;
import com.hqm.rabbit.utils.error.MsgException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * jwt工具类封装
 *
 * @作者 胡勤明
 * @时间 2022-01-10 14:16
 * @版本 1.0
 */
@Component
@PropertySource({"classpath:application.properties"})
public class JwtUtils {
    @Value("${token.header}")
    private String header;

    /**
     * 设置token的过期时间
     */
    @Value("${token.expireTime}")
    private long expireTime;

    /**
     * 秘钥，不同的环境应该配置不同的秘钥，注意保存好，不要泄露
     */
    @Value("${token.secret}")
    private String APPSECRET_KEY;

    /**
     * 创建人 胡勤明
     * 创建时间 2022/1/13 10:33
     * 作用 令牌有效期是否小于20分钟
     * 版本 1.0
     */
    private static final Long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;

    /**
     * 创建人 胡勤明
     * 创建时间 2022/1/13 10:32
     * 作用 秒转化毫秒
     * 版本 1.0
     */
    protected static final long MILLIS_SECOND = 1000;

    /**
     * 创建人 胡勤明
     * 创建时间 2022/1/13 10:29
     * 作用 分钟转化秒
     */
    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;
    @Autowired
    public RedisTemplate redisTemplate;

    @Autowired
    public static UserService userservice;

    /**
     * 加密生成token
     *
     * @param uservo
     * @return
     */
    public String createToken(SysUserVo uservo) {
        if (uservo.getUsername() == null || uservo.getPassword() == null) {
            return null;
        }
        String token = Jwts.builder().setSubject(header)
                .claim("username", uservo.getUsername())
                .claim("password", uservo.getPassword())
                .setIssuedAt(new Date())
                //.setExpiration(new Date(System.currentTimeMillis() + expireTime * MILLIS_MINUTE))
                .signWith(SignatureAlgorithm.HS256, APPSECRET_KEY).compact();
        refreshToken(uservo);
        return token;
    }

    /**
     * 刷新令牌有效期
     *
     * @param uservo 登录信息
     */
    public void refreshToken(SysUserVo uservo) {
        uservo.setLoginTime(System.currentTimeMillis());
        uservo.setExpireTime(uservo.getLoginTime() + expireTime * MILLIS_MINUTE);
        uservo.setOnline(true);

        Boolean onlineusername = redisTemplate.hasKey("onlineusername");
        HashMap<String, SysUserVo> userMap = null;
        if (!onlineusername) {
            userMap = new HashMap<String, SysUserVo>();
        }else{
            userMap = (HashMap<String, SysUserVo>) redisTemplate.opsForValue().get("onlineusername");
        }
        userMap.put("id" + uservo.getUsername(), uservo);
        redisTemplate.opsForValue().set("onlineusername", userMap, expireTime, TimeUnit.MINUTES);
        //redisTemplate.opsForValue().set("id" + uservo.getUsername(), uservo, expireTime, TimeUnit.MINUTES);
        System.out.println("redis已经刷新");
    }

    /**
     * 验证令牌有效期，相差不足20分钟，自动刷新缓存
     *
     * @param uservo
     * @return 令牌
     */
    public void verifyToken(SysUserVo uservo) {
        long expireTime = uservo.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN) {
            refreshToken(uservo);
        }
    }

    /**
     * 解密token获取用户信息
     *
     * @param token
     * @return
     */
    public Claims gettoekntoclaims(String token) {
        try {
            final Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
            return claims;
        } catch (Exception e) {
            throw new RuntimeException("token不合法");
        }

    }


    /**
     * 创建人 胡勤明
     * 创建时间 2022/1/13 10:39
     * 作用 通过请求获取token
     * 版本 1.0
     */
    public SysUserVo getToken(HttpServletRequest request){
        String token = request.getHeader(header);
        if (token != null && !"".equals(token)) {
            return getTokentoUserVO(token);
        }
        return null;

    }

    /**
     * 创建人 胡勤明
     * 创建时间 2022/1/13 10:39
     * 作用 通过token获取对象
     * 版本 1.0
     */
    public SysUserVo getTokentoUserVO(String token) {
            Claims claims = gettoekntoclaims(token);
            HashMap<String, SysUserVo> onlineusername = (HashMap<String, SysUserVo>) redisTemplate.opsForValue().get("onlineusername");
            if (onlineusername == null) {
                throw new MsgException("未获取到登录信息,请重新登录");
            }
            SysUserVo username = (SysUserVo)onlineusername.get("id" + claims.get("username"));
            if(username==null){
                throw new MsgException("未获取到登录信息,请重新登录");
            }
            return  username;
    }
    /**
     * 创建人 胡勤明
     * 创建时间 2022/3/13 12:35
     * 作用 获取当前在线对象集合
     * 版本 1.0
     */
    public List<SysUserVo> getUserVoonlineList() {
        HashMap<String, SysUserVo> onlineusername = (HashMap<String, SysUserVo>) redisTemplate.opsForValue().get("onlineusername");
        ArrayList<SysUserVo> sysUserVos = new ArrayList<>();
        for(String key:onlineusername.keySet()){
            sysUserVos.add(onlineusername.get(key));
        }
        return  sysUserVos;
    }




}