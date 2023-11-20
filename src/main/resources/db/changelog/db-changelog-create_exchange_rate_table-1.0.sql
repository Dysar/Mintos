CREATE TABLE exchange_rate
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    rate          DECIMAL(19, 2) NOT NULL DEFAULT 0,
    currency_code VARCHAR(3)     NOT NULL DEFAULT '',
    INDEX         idx_currency_code (currency_code)
);
