#!/bin/bash
set -ex
echo ${ENVIRONMENT}
export JVM_OPTS="-Xmx1g"

echo "INFO:: Initialization..."

java ${JVM_OPTS} -jar app.jar
