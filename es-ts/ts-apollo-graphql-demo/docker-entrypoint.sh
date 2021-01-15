#!/bin/sh

set -e

if [ "$JAEGER_ENABLED" = "true" ]; then

  # Get the EC2 Host IP to send logs to the jaeger agent deploy in the same host.
  if [ "$JAEGER_AGENT_HOST" = "{ECS_INSTANCE_HOST_IP}" ]; then
    JAEGER_AGENT_HOST=$(curl -s 127.0.0.1/latest/meta-data/local-ipv4)
  fi

  echo >&2 "JAEGER_AGENT_HOST is ($JAEGER_AGENT_HOST)"
  echo >&2 "JAEGER_AGENT_PORT is ($JAEGER_AGENT_PORT)"
  echo >&2 "JAEGER_LOG_SPANS_ENABLED is ($JAEGER_LOG_SPANS_ENABLED)"
fi

echo >&2 "Preparation complete. Ready to start application."

exec "$@"
