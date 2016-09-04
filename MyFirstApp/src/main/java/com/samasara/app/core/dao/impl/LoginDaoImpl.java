package com.samasara.app.core.dao.impl;

import com.samasara.app.core.dao.ILoginDao;
import com.samasara.app.core.models.dbModels.LoginCredentials;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by waghmode.tayappa on 04/09/16.
 */
public class LoginDaoImpl extends AbstractDAO<LoginCredentials> implements ILoginDao {

    private static final Logger logger = LoggerFactory.getLogger(LoginDaoImpl.class);

    public LoginDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public LoginCredentials saveOrUpdate(LoginCredentials credentials) {
        logger.info("updating user details of user : {}",credentials.getUserId());
        LoginCredentials credentials1 = persist(credentials);
        return credentials1;
    }

    @Override
    public LoginCredentials getUserByUserId(String userId) {
        logger.info("Getting details of user : {}",userId);
        Criteria criteria = criteria();
        criteria.add(Restrictions.eq("userId",userId));
        return uniqueResult(criteria);
    }
}
