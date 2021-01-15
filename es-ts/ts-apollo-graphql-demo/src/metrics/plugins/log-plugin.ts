import { ApolloServerPlugin } from "apollo-server-plugin-base";
import { GraphQLRequestListener } from "apollo-server-plugin-base/src/index";
import { GraphQLRequestContext } from "apollo-server-types";

export const LogPlugin: ApolloServerPlugin = {
  requestDidStart<TContext>(
    requestContext: GraphQLRequestContext<TContext>,
  ): GraphQLRequestListener<TContext> {
    const start = Date.now();
    console.log(
      `Request started! OperationName: ${requestContext.request.operationName}`,
    );
    return {
      parsingDidStart(context) {
        console.log(`Hash: ${context.queryHash}, Parsing started!`);
      },
      validationDidStart(context) {
        console.log(`Hash: ${context.queryHash}, Validation started!`);
      },
      didResolveOperation(context) {
        console.log(`Hash: ${context.queryHash}, Operation resolved!`);
      },
      executionDidStart(context) {
        console.log(`Hash: ${context.queryHash}, Execution started!`);
      },
      didEncounterErrors(context) {
        console.log(
          `Hash: ${context.queryHash}, Encounter Errors: ${context.errors}`,
        );
      },
      willSendResponse(context) {
        const stop = Date.now();
        const elapsed = stop - start;
        const size = JSON.stringify(context.response).length * 2;

        console.log(
          `Hash: ${context.queryHash}, Extensions=${JSON.stringify(
            context.response.extensions,
          )}, Duration=${elapsed}ms Bytes=${size}`,
        );
      },
    };
  },
};
