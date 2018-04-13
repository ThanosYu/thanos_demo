package com.thanos.service.client;



import com.thanos.common.ZLData;
import com.thanos.common.ZLException;
import com.thanos.model.o2o.O2OCategory;
import com.thanos.service.ZlTemplateServiceImpl;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @author Thanos Yu on 2017/3/28.
 */
@Service
@Transactional(readOnly = true,rollbackFor = Exception.class)
public class O2OCategoryServiceImpl extends ZlTemplateServiceImpl<O2OCategory> implements O2OCategoryService {

    @Override
    public O2OCategory get(Integer id){
        O2OCategory model = (O2OCategory) getSession().get(O2OCategory.class,id);
        if (model!=null){
            Hibernate.initialize(model.getModules());
        }
        return model;
    }

    @Override
//    @Transactional(readOnly = false)
    @Transactional(readOnly = false, noRollbackFor = RuntimeException.class)
//    @Transactional(readOnly = false, noRollbackFor = Exception.class)
    public Serializable save(O2OCategory model) throws ZLException{
        getSession().save(model);
        String s = null;
//        if (s.equals("")) {
//            int i = 0;
//        }
        return null;
    }

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public Object update(O2OCategory model){
        O2OCategory user = (O2OCategory) getSession().load(O2OCategory.class,model.getId());
        user.setTitle(model.getTitle());
        user.setContent(model.getContent());
        user.setVideo(model.getVideo());
        return ZLData.OK;
    }

    @Override
    public List<O2OCategory> getAll(){
        Query query = getSession().getNamedQuery("O2OCategory.all");
        List<O2OCategory> list = query.list();
        if (list!=null&&!list.isEmpty()){
            for (O2OCategory model:list){
                Hibernate.initialize(model.getModules());
            }
        }
        return query.list();
    }

}
