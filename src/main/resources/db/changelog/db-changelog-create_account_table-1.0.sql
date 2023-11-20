CREATE TABLE account
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    client_id     BIGINT         NOT NULL DEFAULT 0,
    balance       DECIMAL(19, 2) NOT NULL DEFAULT 0,
    currency_code VARCHAR(3)     NOT NULL DEFAULT '',
    INDEX idx_client_id (client_id)
);
