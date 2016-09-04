package com.samasara.app.configuration;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by waghmode.tayappa on 04/09/16.
 */

@Getter
@Setter
public class SamasaraConfig extends Configuration {

    private DataSourceFactory db;

    private String name = "ninja";
}
