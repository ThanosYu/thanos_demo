package com.thanos.common.masterslave;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;

/**
 * Created by Kevin5_Li on 2017/5/26.
 */
public class ReadOnlyAspect {
    private static Logger logger = Logger.getLogger(ReadOnlyAspect.class);

    public void read(JoinPoint joinPoint) {
//        logger.info("READ ONLY! joinPoint class method: " + joinPoint.getSignature().toShortString());
        DynamicDataSourceHolder.markSlave();
//        System.out.println("mark slave");
    }

    public void write() {
        DynamicDataSourceHolder.markMaster();
//        System.out.println("mark master");
    }

    public void clear() {
//        logger.info("joinPoint: " + joinPoint.getSignature().getName());
        DynamicDataSourceHolder.clearDataSource();
//        System.out.println("mark slave");
    }
}
