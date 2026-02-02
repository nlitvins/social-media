package com.nlitvins.social_media.domain.graphqlexception;

import com.nlitvins.social_media.domain.exception.BusinessException;
import com.nlitvins.social_media.domain.exception.ErrorCode;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GlobalExceptionResolver extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        log.error("Exception during GraphQL request: ", ex);

        if (ex instanceof BusinessException e) {
            return GraphqlErrorBuilder.newError(env)
                    .message(e.getErrorCode().getMessage())
                    .extensions(e.getDetails())
                    .build();
        }

        return GraphqlErrorBuilder.newError(env)
                .message(ErrorCode.INTERNAL_ERROR.getMessage())
                .build();
    }
}
