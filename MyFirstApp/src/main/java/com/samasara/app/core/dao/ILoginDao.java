package com.samasara.app.core.dao;

import com.samasara.app.core.models.dbModels.LoginCredentials;

/**
 * Created by waghmode.tayappa on 04/09/16.
 */
public interface ILoginDao {
    public LoginCredentials saveOrUpdate(LoginCredentials credentials);
    public LoginCredentials getUserByUserId(String userId);
}
