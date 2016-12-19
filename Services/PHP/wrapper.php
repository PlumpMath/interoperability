<?php
/**
 * Get params.
 * operation == queryID || echo 
 */
$params = [
    'username' => ($username = filter_input(INPUT_GET, 'username')) ? $username : 'user',
    'password' => ($password = filter_input(INPUT_GET, 'password')) ? $password : 'pass',
    'identity' => ($identity = filter_input(INPUT_GET, 'identity')) ? $identity : '-1',
    'operation' => (in_array($operation = filter_input(INPUT_GET, 'operation'), [
        'testServiceStatus',
        'queryIDnoCD', 'queryID',
        'echo',
    ])) ? $operation : 'queryID'
];

/**
 * Call remote ws 
 */
function wscall($params)
{
    /**
     * Prep auth 
     */
    $pass_md5 = md5($params['password']);
    $auth = "Basic " . base64_encode("{$params['username']}:{$pass_md5}");

    /**
     * Do the call 
     */
    $ch = curl_init();

    $payload = json_encode(array("SubmissionCode" => $params['identity']));

    curl_setopt($ch, CURLOPT_URL, "https://academicidapp.grnet.gr/admin/web/ws/users/inspectAcademicID");
    curl_setopt($ch, CURLOPT_POST, 1);
    curl_setopt($ch, CURLOPT_POSTFIELDS, $payload);
    curl_setopt($ch, CURLOPT_HTTPHEADER, [
        "Authorization: {$auth}",
        'Content-Type: application/json',
        'Accept: */*',
        'User-Agent: AcademicIDClientTestPHP/v1.0 osteam'
        ]
    );
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

    $result = curl_exec($ch);
    curl_close($ch);
    return $result;
}
/**
 * 
 */
switch ($params['operation']) {
    case 'queryID':
        header("Content-Type: application/json");
        $result = wscall($params);
        break;
    case 'queryIDnoCD':
        header("Content-Type: text/plain");
        $result = json_decode(wscall($params), true);
        $IDis = $result !== null &&
            isset($result['response']) && $result['response'] == 'SUCCESS' &&
            isset($result['inspectionResult']['webServiceSuccess']) && 
            $result['inspectionResult']['webServiceSuccess'] == true;
        $result = "isStudent:" . ($IDis ? 'true' : 'false');
        break;
    case 'testServiceStatus':
        header("Content-Type: text/plain");
        $result = "StudentID sent was:" . trim(filter_input(INPUT_GET, 'id'));
        break;
    case 'echo':
    default:
        header("Content-Type: text/plain");
//        $result = filter_input(INPUT_SERVER, 'QUERY_STRING');
        unset($_GET['operation']);
        $result = http_build_query($_GET);
        break;
}

echo $result;
exit(0);
