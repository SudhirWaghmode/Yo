package com.samasara.app.configuration;

import com.bazaarvoice.dropwizard.assets.AssetsBundleConfiguration;
import com.bazaarvoice.dropwizard.assets.AssetsConfiguration;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableMap;
import com.google.inject.Singleton;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * Created by waghmode.tayappa on 04/09/16.
 */

@Getter
@Setter
@Singleton
public class SamasaraConfig extends Configuration implements AssetsBundleConfiguration {

    private DataSourceFactory db;

    @Valid
    @NotNull
    @JsonProperty
    private final AssetsConfiguration assetsConfiguration = new AssetsConfiguration();

    @NotNull
    private ImmutableMap<String, ImmutableMap<String, String>> viewRendererConfiguration = ImmutableMap.of();

    @JsonProperty("viewRendererConfiguration")
    public ImmutableMap<String, ImmutableMap<String, String>> getViewRendererConfiguration() {
        return viewRendererConfiguration;
    }

    @JsonProperty("viewRendererConfiguration")
    public void setViewRendererConfiguration(Map<String, Map<String, String>> viewRendererConfiguration) {
        ImmutableMap.Builder<String, ImmutableMap<String, String>> builder = ImmutableMap.builder();
        for (Map.Entry<String, Map<String, String>> entry : viewRendererConfiguration.entrySet()) {
            builder.put(entry.getKey(), ImmutableMap.copyOf(entry.getValue()));
        }
        this.viewRendererConfiguration = builder.build();
    }
}
