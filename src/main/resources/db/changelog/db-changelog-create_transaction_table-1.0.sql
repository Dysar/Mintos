CREATE TABLE transaction
(
    transaction_id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    source_account_id      BIGINT         NOT NULL,
    destination_account_id BIGINT         NOT NULL,
    timestamp              DATETIME       NOT NULL,
    currency_code          VARCHAR(3)     NOT NULL,
    amount                 DECIMAL(19, 2) NOT NULL,
    CONSTRAINT fk_transaction_source FOREIGN KEY (source_account_id) REFERENCES account (id),
    CONSTRAINT fk_transaction_destination FOREIGN KEY (destination_account_id) REFERENCES account (id)
);
