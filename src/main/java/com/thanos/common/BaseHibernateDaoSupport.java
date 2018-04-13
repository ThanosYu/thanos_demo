package com.thanos.common;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

import java.util.Map;


public class BaseHibernateDaoSupport {
    protected static final Integer pageSize = ConstInfo.PAGE_SIZE;
    @Autowired
    private SessionFactory sessionFactory;

    public BaseHibernateDaoSupport() {

    }

    public static int getPageCount(Integer totalCount) {
        return getPageCount(totalCount, pageSize);
    }

    public static int getPageCount(Long totalCount) {
        return getPageCount(totalCount.intValue(), pageSize);
    }

    public static int getPageCount(Long totalCount, int pageSize) {
        return getPageCount(totalCount.intValue(), pageSize);
    }

    public static int getFirstResult(int page) {
        return getFirstResult(page, pageSize);
    }

    public static int getFirstResult(int page, int pageSize) {
        return (page - 1) * pageSize;
    }

    public static int getPageCount(int totalCount, int pageSize) {
        int pageCount = 0;
        if (totalCount == 0) {
            pageCount = 0;
        } else {
            if (totalCount < pageSize) {
                pageCount = 1;
            } else {
                pageCount = totalCount / pageSize;
                if (totalCount % pageSize != 0) {
                    pageCount++;
                }
            }
        }
        return pageCount;
    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session openSession() {
        return this.sessionFactory.openSession();
    }

    public Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    protected void releaseSession(Session session) {
        SessionFactoryUtils.releaseSession(session, getSessionFactory());
    }

    protected void dealSorts(Criteria cri, Map<String, String> con) {
        String dir = StrUtil.nullToString((String) con.get("dir"), "desc").toLowerCase();
        String sort = StrUtil.nullToString((String) con.get("sort"), "id");
        if (dir.equals("asc")) {
            cri.addOrder(Order.asc(sort));
        } else {
            cri.addOrder(Order.desc(sort));
        }
    }

    protected void dealPcSorts(Criteria cri, Map<String, String> con) {
        String dir = StrUtil.nullToString((String) con.get("dir"), "desc").toLowerCase();
        String sort = StrUtil.nullToString((String) con.get("sort"), "appTptId");
        if (dir.equals("asc")) {
            cri.addOrder(Order.asc(sort));
        } else {
            cri.addOrder(Order.desc(sort));
        }
    }

    protected void Sorts(Query query, Map<String, String> con) {
        String dir = StrUtil.nullToString((String) con.get("dir"), "desc").toLowerCase();
        String sort = StrUtil.nullToString((String) con.get("sort"), "id");
        if (dir.equals("asc")) {
            query.setParameter("sortAsc","a.zlApp."+sort);

        } else {
            query.setParameter("sortDesc","a.zlApp."+sort);

        }
    }

    protected void clearCache() {
        getSessionFactory().getCache().evictEntityRegions();
        getSessionFactory().getCache().evictCollectionRegions();
        getSessionFactory().getCache().evictQueryRegions();
        getSessionFactory().getCache().evictAllRegions();
    }
}
