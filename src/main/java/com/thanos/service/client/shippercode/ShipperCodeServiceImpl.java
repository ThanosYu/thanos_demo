package com.thanos.service.client.shippercode;


import com.thanos.common.ZLException;
import com.thanos.model.kdniao.ShipperCode;
import com.thanos.service.ZlTemplateServiceImpl;
import com.thanos.tools.ShipperCodeExcelReader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

/**
 * @author kevinli
 * @date 2018/1/12
 */
@Service
@Transactional(readOnly = true, rollbackFor = RuntimeException.class)
public class ShipperCodeServiceImpl extends ZlTemplateServiceImpl<ShipperCode> implements ShipperCodeService {

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public void saveData(String path) throws ZLException, IOException {
        ShipperCodeExcelReader reader = new ShipperCodeExcelReader();
        List<ShipperCode> list = reader.readExcel(path);
        if (null != list && !list.isEmpty()) {
            for (ShipperCode model : list) {
                super.save(model);
            }
        }
    }
}
