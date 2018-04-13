package com.thanos.service.client;


import com.thanos.model.o2o.O2OBanner;
import com.thanos.model.o2o.O2OCategory;
import com.thanos.service.ZlTemplateServiceImpl;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Thanos Yu on 2017/8/3.
 */
@Service
@Transactional(readOnly = true,rollbackFor = Exception.class)
public class O2OBannerServiceImpl extends ZlTemplateServiceImpl<O2OBanner> implements O2OBannerService {

    @Override
    public O2OBanner get(Integer id){
        O2OBanner model = (O2OBanner) getSession().get(O2OBanner.class,id);
        return model;
    }

    @Override
    public O2OCategory getUser(Integer id){
        O2OBanner banner = this.get(id);
        O2OCategory category = banner.getCategory();
        if (category!=null){
            Hibernate.initialize(category.getModules());
        }
        return category;
    }

    @Override
    public List<O2OBanner> select(){
        Query query = getSession().getNamedQuery("O2OBanner.select");
        List<O2OBanner> list = query.list();
        for (O2OBanner banner:list){
            O2OCategory category = banner.getCategory();
            if (category!=null){
                Hibernate.initialize(category.getModules());
            }
        }
        return query.list();
    }
}
