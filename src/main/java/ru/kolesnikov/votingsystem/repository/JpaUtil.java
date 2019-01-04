package ru.kolesnikov.votingsystem.repository;


import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JpaUtil {

    @PersistenceContext
    private EntityManager em;

    public void clear2ndLevelHibernateCache() {
        Session s = (Session) em.getDelegate();
        SessionFactory sf = s.getSessionFactory();
//        sf.evict(Restaurant.class);
//        sf.getCache().evictEntity(Restaurant.class, BaseEntity.START_SEQ);
//        sf.getCache().evictEntityRegion(Restaurant.class);
        sf.getCache().evictAllRegions();
    }
}

