package com.nlitvins.social_media.outbound.config;

import com.nlitvins.social_media.inbound.utils.PostsByAuthorDataLoader;
import org.dataloader.DataLoaderFactory;
import org.dataloader.DataLoaderRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.BatchLoaderRegistry;
import org.springframework.graphql.execution.DefaultBatchLoaderRegistry;


@Configuration
public class DataLoaderConfig {

    @Bean
    public DefaultBatchLoaderRegistry dataLoaderRegistry(
            DefaultBatchLoaderRegistry batchLoaderRegistry,
            PostsByAuthorDataLoader loader
    ) {
        DataLoaderRegistry registry = new DataLoaderRegistry();

//        registry.register(
//                "postsByAuthor",

//        );

        batchLoaderRegistry.registerDataLoaders(
                DataLoaderFactory.newDataLoader("postsByAuthor", loader.batchLoader()),
                registry
        );

        return batchLoaderRegistry;
    }
}
