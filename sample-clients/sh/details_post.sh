#!/bin/sh

curl -X POST \
    --header "Content-Type: application/json" \
    --header "Accept: application/json" \
    --header "Authorization: Bearer <token>" \
    --header "User-Agent: WSO2 sample command line caller" \
    -d '{ "id": "0123", "fields": "id,fullname,organisation" }' \
    'https://wso2.local.dev:8243/identity/1.0.0/details'
