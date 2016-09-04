package com.samasara.app.core.services;

import com.google.inject.Inject;
import com.samasara.app.core.dao.ILoginDao;
import com.samasara.app.core.models.dbModels.LoginCredentials;
import com.samasara.app.core.models.requestModels.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by waghmode.tayappa on 04/09/16.
 */
public class LoginService {

    Logger logger = LoggerFactory.getLogger(LoginService.class);
    @Inject
    private ILoginDao loginDao;

    public boolean isUserActive(String userId){

        LoginCredentials credentials = loginDao.getUserByUserId(userId);
        if (credentials == null)
            return false;

        return true;
    }

    public boolean isAuthenticateUser(UserDetails details){
        LoginCredentials credentials = loginDao.getUserByUserId(details.getUserId());

        if (credentials != null && credentials.getPassword().equals(details.getPassword()))
            return true;

        return false;
    }

    public boolean registerUser(UserDetails details){

        LoginCredentials credentials = new LoginCredentials();
        credentials.setUserId(details.getUserId());
        credentials.setPassword(details.getPassword());
        credentials.setActive(true);
        credentials.setAdditionalData("testData");
        loginDao.saveOrUpdate(credentials);
        return true;
    }
}
