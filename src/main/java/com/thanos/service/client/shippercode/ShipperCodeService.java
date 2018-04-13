package com.thanos.service.client.shippercode;


import com.thanos.common.ZLException;
import com.thanos.model.kdniao.ShipperCode;
import com.thanos.service.ZlTemplateService;

import java.io.IOException;

/**
 * @author kevinli
 * @date 2018/1/12
 */
public interface ShipperCodeService extends ZlTemplateService<ShipperCode> {


    /**
     * 存储价格表数据到数据库
     *
     * @param path 文件路径
     * @throws ZLException
     * @throws IOException
     */
    public void saveData(String path) throws ZLException, IOException;
}
