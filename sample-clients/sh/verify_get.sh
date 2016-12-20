#!/bin/sh

curl -X GET \
    --header "Accept: application/json" \
    --header "Authorization: Bearer <token>" \
    --header "User-Agent: WSO2 sample command line caller" \
    'https://wso2.local.dev:8243/identity/1.0.0/verify?id=0123'

