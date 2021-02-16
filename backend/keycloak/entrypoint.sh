#!/bin/bash

set -euo pipefail

if [[ -f "/keycloak/realm.json" ]]; then
    /opt/jboss/tools/docker-entrypoint.sh -b 0.0.0.0 -Dkeycloak.migration.action=import -Dkeycloak.migration.provider=singleFile -Dkeycloak.migration.realmName=dev -Dkeycloak.migration.file=/keycloak/realm.json
else
    /opt/jboss/tools/docker-entrypoint.sh -b 0.0.0.0
fi
