package com.samasara.app.views;

import io.dropwizard.views.View;

import java.nio.charset.Charset;
/**
 * Created by waghmode.tayappa on 04/09/16.
 */
public class DashboardView extends View{

    public DashboardView(String templateName, Charset charset) {
        super(templateName,charset);
    }
}
