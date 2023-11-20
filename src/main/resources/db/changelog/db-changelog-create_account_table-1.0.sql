CREATE TABLE account
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    client_id     BIGINT         NOT NULL,
    balance       DECIMAL(19, 2) NOT NULL,
    currency_code VARCHAR(3)     NOT NULL
);