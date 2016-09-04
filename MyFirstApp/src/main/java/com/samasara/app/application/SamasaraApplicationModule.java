package com.samasara.app.application;

import com.google.inject.AbstractModule;
import com.samasara.app.configuration.SamasaraConfig;
import com.samasara.app.core.dao.ILoginDao;
import com.samasara.app.core.dao.impl.LoginDaoImpl;
import com.samasara.app.core.services.LoginService;
import org.hibernate.SessionFactory;

/**
 * Created by waghmode.tayappa on 04/09/16.
 */
public class SamasaraApplicationModule extends AbstractModule {

    private final SamasaraConfig config;
    SessionFactory factory;

    public  SamasaraApplicationModule(SamasaraConfig config, SessionFactory factory){
        this.config = config;
        this.factory =factory;
    }

    @Override
    protected void configure() {
        bind(SamasaraConfig.class).toInstance(config);
        bind(LoginService.class).toInstance(new LoginService());
        bind(ILoginDao.class).toInstance(new LoginDaoImpl(factory));
    }
}
