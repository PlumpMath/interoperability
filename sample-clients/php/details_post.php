<?php

$auth = "Bearer <token>";
$payload = json_encode([
    "id" => "0123",
    "fields" => "id,fullname,organisation" 
]);

$ch = curl_init();

curl_setopt($ch, CURLOPT_URL, "https://wso2.local.dev:8243/identity/1.0.0/details");
curl_setopt($ch, CURLOPT_POST, true);
curl_setopt($ch, CURLOPT_POSTFIELDS, $payload);
curl_setopt($ch, CURLOPT_HTTPHEADER, [
    "Authorization: {$auth}",
    "Content-Type: application/json",
    "Accept: application/json",
    "User-Agent: WSO2 sample php caller" 
]);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

$result = curl_exec($ch);

if (curl_errno($ch)) {
    echo "Error: ", curl_error($ch), 
        "Curl info: ", var_export(curl_getinfo($ch), true);
    $result = false;
} else {
    // possibly parse result...
    $result = json_decode($result, true);
} 
curl_close($ch);
return $result;

