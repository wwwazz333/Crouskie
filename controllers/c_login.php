<?php
if (isset($_POST['action'])) {
    require_once(PATH_MODELS . 'UtilisateurDAO.php');
    try {
        $DAO = new UtilisateurDAO(DEBUG);
        switch ($_POST['action']) {
            case 'check':
                if (isset($_POST['email'])) {
                    $res = $DAO->isEmailExist(htmlspecialchars($_POST['email']));
                    sendJson($res);
                }
                break;
            default:
                sendJson("Unknow action",false);
                break;
        }
    } catch (Exception $ex) {
        sendJson($ex->getMessage(),false);
    }
    exit;
} else {
    require_once(PATH_VIEWS . $page . '.php');
}

function sendJson(mixed $result,bool $success = true)
{
    header('Content-Type: application/json; charset=utf-8');
    $res['success'] = $success;
    $res['result'] = $result;
    echo json_encode($res);
}
