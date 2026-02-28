package com.My.Spring_Final_Project.Config;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Component
public class HibernateFilterConfig {

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    @Transactional
    public void enableSoftDeleteFilter() {
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("activeFilter")
                .setParameter("isActive", true);
    }
}
