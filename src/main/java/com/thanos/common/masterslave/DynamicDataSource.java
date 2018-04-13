package com.thanos.common.masterslave;

import org.apache.log4j.Logger;
import org.logicalcobwebs.proxool.ProxoolDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

/**
 * Created by Kevin5_Li on 2017/5/26.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    private static Logger logger = Logger.getLogger(DynamicDataSource.class);

    @Override
    protected Object determineCurrentLookupKey() {
        String object = DynamicDataSourceHolder.getDataSourceKey();
//        logger.info("-----------------------------------server: " + (!StringUtils.isEmpty(object) ? object : "master"));
        return object;
    }

    @Override
    protected DataSource determineTargetDataSource() {
        DataSource dataSource = super.determineTargetDataSource();
        if(dataSource != null && dataSource instanceof ProxoolDataSource) {
//            System.out.println("------------------------------------DataSource.getAlias: " + ((ProxoolDataSource) dataSource).getAlias());
//            System.out.println("------------------------------------DataSource.getDriverUrl: " + ((ProxoolDataSource) dataSource).getDriverUrl());
        }
        return dataSource;
    }
}
