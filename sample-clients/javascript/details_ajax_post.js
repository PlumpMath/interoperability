
$.ajax({
    type: 'POST',
    url: 'https://wso2.local.dev:8243/identity/1.0.0/details',
    dataType: "json",
    data: {"id" : "0123", "fields" : "id,fullname,organisation"},
    beforeSend: function(req) {
        req.setRequestHeader("Authorization", "Bearer <token>");
    },
    success: function(data){
        console.log(data);
    }
});

