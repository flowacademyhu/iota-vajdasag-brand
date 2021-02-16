#!/bin/bash

set -euo pipefail

export KEYCLOAK_USER=admin
export KEYCLOAK_PASSWORD=admin

if [[ -f "/keycloak/realm.json" ]]; then
    /opt/jboss/tools/docker-entrypoint.sh -b 0.0.0.0 -Dkeycloak.profile.feature.upload_scripts=enabled -Dkeycloak.migration.action=import -Dkeycloak.migration.provider=singleFile -Dkeycloak.migration.realmName=VajdasagBrand -Dkeycloak.migration.file=/keycloak/realm.json
else
    /opt/jboss/tools/docker-entrypoint.sh -b 0.0.0.0 -Dkeycloak.profile.feature.upload_scripts=enabled
fi
