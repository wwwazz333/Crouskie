 

DELIMITER $$
--
-- Functions
--
CREATE DEFINER=`p2100284`@`%` FUNCTION `customer_connection` (`mail` VARCHAR(255) CHARSET utf8, `password_hash` VARCHAR(255) CHARSET utf8) RETURNS INT(1) COMMENT '1: connected, 0: wrong pwd or mail, -1:to many try' BEGIN
	DECLARE pwd varchar(255) DEFAULT null;
    DECLARE counter int(11) DEFAULT null;
    DECLARE last_date TIMESTAMP;
    select `password`, counter_connection, last_connection_try into pwd, counter, last_date from CUSTOMER WHERE mail_address = mail;

    if pwd is null or counter is null THEN -- pas de correspondance avec le mail trouvé
    	return 0;
    end if;

    -- correspondance avec le mail trouvé
    if counter < 3 or (SELECT customer_delay_passed(mail)) = 1 THEN
        IF pwd = password_hash THEN
            UPDATE CUSTOMER SET counter_connection = 0, last_connection_try = CURRENT_TIMESTAMP() WHERE mail_address = mail;
            return 1;
        ELSE
            UPDATE CUSTOMER SET counter_connection = counter_connection + 1, last_connection_try = CURRENT_TIMESTAMP() WHERE mail_address = mail;
            return 0;
    	END IF;
    end if;
    return -1;
END$$

CREATE DEFINER=`p2100284`@`%` FUNCTION `customer_delay_passed` (`mail` VARCHAR(255) CHARSET utf8) RETURNS INT(1) UNSIGNED  BEGIN
    DECLARE last_date TIMESTAMP;
    select last_connection_try into last_date from CUSTOMER WHERE mail_address = mail;

    if CURRENT_TIMESTAMP() >= DATE_ADD(last_date,INTERVAL 1 HOUR) THEN -- reinitialise counter_connection si tps attente passé
        UPDATE CUSTOMER SET counter_connection = 0, last_connection_try = CURRENT_DATE() WHERE mail_address = mail;
        return 1;
    end if;
    return 0;
END$$

DELIMITER ;
