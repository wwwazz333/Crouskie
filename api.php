<?php
require_once('./config/configuration.php');

/**
 * Generate a random string, using a cryptographically secure 
 * pseudorandom number generator (random_int)
 * 
 * For PHP 7, random_int is a PHP core function
 * For PHP 5.x, depends on https://github.com/paragonie/random_compat
 * 
 * @param int $length      How many characters do we want?
 * @param string $keyspace A string of all possible characters
 *                         to select from
 * @return string
 */
function random_str(
    int $length = 64,
    string $keyspace = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ'
): string {
    if ($length < 1) {
        throw new \RangeException("Length must be a positive integer");
    }
    $pieces = [];
    $max = mb_strlen($keyspace, '8bit') - 1;
    for ($i = 0; $i < $length; ++$i) {
        $pieces []= $keyspace[random_int(0, $max)];
    }
    return implode('', $pieces);
}


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
    try {
        switch ($data['action']) {
            case 'check':
                if (isset($data['email'])) {
                    require_once(PATH_MODELS . 'UtilisateurDAO.php');
                    $DAO = new UtilisateurDAO(DEBUG);
                    $res = $DAO->isEmailExist(htmlspecialchars($data['email']));
                    sendJson($res);
                }else{
                    throw new Exception("Email argument is required");
                }
                break;
            case 'search':
                if (isset($data['name'])) {
                    require_once(PATH_MODELS . 'ProductDAO.php');
                    $DAO = new ProductDAO(DEBUG);
                    $res = $DAO->getProductsByName(htmlspecialchars($data['name']));
                    sendJson($res);
                }else{
                    throw new Exception("Name argument is required");
                }
                break;
            case 'upload':
                if (isset($data['image'])) {
                    $file_name = PATH_IMAGES . 'uploads/' . random_str(16) . '.png';
                    file_put_contents($file_name, base64_decode($data['image']));
                    sendJson($file_name);
                }else{
                    throw new Exception("Image argument is required");
                }
                break;
            case 'stock':
                if (isset($data['id'])) {
                    require_once(PATH_MODELS . 'StockDAO.php');
                    $DAO = new StockDAO(DEBUG);
                    if (isset($data['color'])) {
                        $res = $DAO->getSizesWithColor($data['id'],$data['color']);
                    }elseif (isset($data['size'])) {
                        $res = $DAO->getColorsWithSize($data['id'],$data['size']);
                    }else{
                        throw new Exception("Color or size argument is required");
                    }
                    sendJson($res);
                    break;
                }
                throw new Exception("Id argument is required");
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