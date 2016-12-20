var params = JSON.stringify({"id": "0123", "fields": "id,fullname,organisation"});

var http = new XMLHttpRequest();
http.open("POST", "https://wso2.local.dev:8243/identity/1.0.0/details");
http.setRequestHeader("Content-type", "application/json; charset=utf-8");
http.setRequestHeader("Content-length", params.length);
http.setRequestHeader("Accept", "application/json");
http.setRequestHeader("Authorization", "Bearer <token>");
http.setRequestHeader("User-Agent", "WSO2 sample javascript caller");

http.onreadystatechange = function() {
    if(http.readyState == 4 && http.status == 200) {
        console.log(http.responseText);
    }
}

http.send(params);

