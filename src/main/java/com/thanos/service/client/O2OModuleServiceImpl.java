package com.thanos.service.client;


import com.thanos.common.masterslave.ZlSlaveDB;
import com.thanos.model.o2o.O2OModule;
import com.thanos.service.ZlTemplateServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Thanos Yu on 2017/8/3.
 */
@Service
@Transactional(readOnly = true,rollbackFor = Exception.class)
public class O2OModuleServiceImpl extends ZlTemplateServiceImpl<O2OModule> implements O2OModuleService {

    @ZlSlaveDB
    @Override
    public O2OModule get(Integer id) {
        System.out.println("----------------------module");
        O2OModule model = (O2OModule) getSession().get(O2OModule.class, id);
        return model;
    }
}
