<?php
require_once('./config/configuration.php');

function sendJson(mixed $result,bool $success = true)
{
    header('Content-Type: application/json; charset=utf-8');
    $res['success'] = $success;
    $res['result'] = $result;
    echo json_encode($res);
}

// get json data from POST Request
$data = json_decode(file_get_contents('php://input'), true);

if (isset($data['action'])) {
    require_once(PATH_MODELS . 'UtilisateurDAO.php');
    require_once(PATH_MODELS . 'ProductDAO.php');
    try {
        switch ($data['action']) {
            case 'check':
                if (isset($data['email'])) {
                    $DAO = new UtilisateurDAO(DEBUG);
                    $res = $DAO->isEmailExist(htmlspecialchars($data['email']));
                    sendJson($res);
                }
                break;
            case 'search':
                if (isset($_POST['name'])) {
                    $DAO = new ProductDAO(DEBUG);
                    $res = $DAO->getProductsByName(htmlspecialchars($data['name']));
                    sendJson($res);
                }
                break;
            case 'products':
                $DAO = new ProductDAO(DEBUG);
                $res = $DAO->getProducts();
                sendJson($res);
                break;
            case 'prod_id':
                $DAO = new ProductDAO(DEBUG);
                $res = $DAO->getProductByID($data['id']);
                sendJson($res);
                break;
            default:
                sendJson("Unknow action",false);
                break;
        }
    } catch (Exception $ex) {
        sendJson($ex->getMessage(),false);
    }
    exit;
}else{
    sendJson("Action not specified !",false);
}