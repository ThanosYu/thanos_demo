package com.thanos.job.toutiao;

import com.thanos.api.toutiao.ToutiaoApi;
import com.thanos.common.ZLException;
import com.thanos.model.toutiao.ToutiaoToken;
import com.thanos.cache.RedisCachePlugin;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Random;

/**
 * @author Thanos Yu
 * @date 2017/11/24
 */
@Component
public class UpdateToutiao {

    private static Logger logger = Logger.getLogger(UpdateToutiao.class);

    private static final String RDKEY = "thanos.totiao.token";


    @Autowired
    private RedisCachePlugin redisCachePlugin;


    private String saveRedisToken(String timestamp, String nonce) throws ZLException {
        synchronized (this) {
            redisCachePlugin.deleteKey(RDKEY);
            try {
                logger.info("ZlToutiao: load");
                ToutiaoToken model = ToutiaoApi.getToken(timestamp, nonce);
                String token = model.getData().getAccessToken();
                int expireTime = model.getData().getExpiresIn();
                redisCachePlugin.set(RDKEY, token, expireTime);
                System.out.println("------------------------new token");
                return token;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String getRedisToken(String timestamp, String nonce) throws Exception {
        if (StringUtils.isEmpty(redisCachePlugin.get(RDKEY))) {
            return saveRedisToken(timestamp, nonce);
        }
        String token = redisCachePlugin.get(RDKEY);
        System.out.println("-----------------------print old token" + token);
        logger.info("-----------------------log old token" + token);
        return token;
    }

    public static void main(String[] args) {
        try {
            UpdateToutiao toutiao = new UpdateToutiao();
            String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
            String nounce = String.valueOf(new Random().nextLong());
            String token = toutiao.getRedisToken(timestamp, nounce);
            System.out.println(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
