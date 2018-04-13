package com.thanos.sign;

import com.thanos.common.ZLErrorMessage;
import com.thanos.common.ZLResult;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Thanos Yu on 2017/8/10.
 */
public class CheckSign {

    private static Logger logger = Logger.getLogger(CheckSign.class);

    private static final String SIGN = "A-Sign";
    private static final String COMMON_PARAM = "A-Common-Param";
    private static final String CHANNEL = "A-Channel";
    private static final String TIMESTAMP = "A-Timestamp";

//    @Context
//    protected HttpServletRequest req;

    public Object checkSign(ProceedingJoinPoint pjp) throws Throwable {
        String sign = null;
        String commonParam;
        String timestamp;
        String channel;

        String checkSign = null;
        Object[] args = pjp.getArgs();
        HttpServletRequest request = null;
        for (int i = 0; i < args.length; i++) {
//            String clazzName = args[i].getClass().getName();
            if (args[i] instanceof HttpServletRequest) {
                request = (HttpServletRequest) args[i];
                if (request != null) {
                    sign = request.getHeader(SIGN);
                    commonParam = request.getHeader(COMMON_PARAM);
                    channel = request.getHeader(CHANNEL);
                    timestamp = request.getHeader(TIMESTAMP);
                    if (StringUtils.isEmpty(sign) || StringUtils.isEmpty(commonParam) || StringUtils.isEmpty(timestamp) || StringUtils.isEmpty(channel)) {
                        return ZLResult.failed(ZLErrorMessage.ZL_SIGN_ERROR);
                    }
//                    logger.info("--------------------------sign: " + sign + "--------------------------commonParam:" + commonParam + "--------------------------timestamp:" + timestamp);
                    String param = COMMON_PARAM + commonParam + CHANNEL + channel + TIMESTAMP + timestamp;

                    checkSign = MD5.MD5Encode(param);
//                    logger.info("-----------------------checkSign: " + checkSign);
                }
            }
        }
        if (checkSign != null && !sign.equals(checkSign)) {
            return ZLResult.failed(ZLErrorMessage.ZL_SIGN_ERROR);
        }
        return pjp.proceed();
    }
}
