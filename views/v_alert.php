<?php
if (isset($alert)) {
    $_type = $alert[0];
    $_title = $alert[1];
    $_message = $alert[2];

    $_color = '#03a9f4';
    $_icon = 'mdi:information-outline';
    switch ($_type) {
        case 1:
            $_color = '#82E105';
            $_icon = 'mdi:success-circle-outline';
            break;
        case 2:
            $_color = '#ffb74d';
            $_icon = 'material-symbols:warning-outline-rounded';
            break;
        case 3:
            $_color = '#E10512';
            $_icon = 'material-symbols:error-outline-rounded';
            break;
    }
}
?>

<?php if (isset($alert)){ ?>
    <div id="PopUp" class="flex center">
        <div class="rounded flex column shadow">
            <div class="fill-w flex center" style="background-color:<?=$_color?>;">
                <iconify-icon icon="<?=$_icon?>" style="color: white"></iconify-icon>
            </div>
            <h2><?=$_title?></h2>
            <p><?=$_message?></p>
            <a class="circle" style="background-color:<?=$_color?>;">Fermer</a>
        </div>
    </div>
<?php } ?>