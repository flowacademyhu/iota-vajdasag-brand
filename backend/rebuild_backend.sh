#!/bin/bash

set -euo pipefail

docker-compose up -d --build --force-recreate --no-deps app

