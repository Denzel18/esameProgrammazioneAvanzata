package it.univpm.advancedcode.cri.model.dao;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class DefaultDao {

    private SessionFactory sessionFactory;
    private Session session;

    
    /** 
     * @return SessionFactory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    
    /** 
     * @param sessionFactory
     */
    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    
    /** 
     * @param session
     */
    public void setSession(Session session) {
        this.session = session;
    }

    
    /** 
     * @return Session
     */
    public Session getSession() {
        // shared session exists
        Session session = this.session;
        if (session == null) {
            // if the session does not exist, create it
            try {
                session = this.sessionFactory.getCurrentSession();
            } catch (HibernateException ex) {
                session = this.sessionFactory.openSession();
            }
        }
        return session;
    }

}

