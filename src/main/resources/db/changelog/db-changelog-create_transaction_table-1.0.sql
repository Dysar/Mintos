CREATE TABLE transaction
(
    id                     BIGINT AUTO_INCREMENT PRIMARY KEY,
    source_account_id      BIGINT         NOT NULL DEFAULT 0,
    destination_account_id BIGINT         NOT NULL DEFAULT 0,
    timestamp              DATETIME       NOT NULL DEFAULT '1000-01-01',
    currency_code          VARCHAR(3)     NOT NULL DEFAULT '',
    amount                 DECIMAL(19, 2) NOT NULL DEFAULT 0,
/*CONSTRAINT fk_transaction_source FOREIGN KEY (source_account_id) REFERENCES account (id),
CONSTRAINT fk_transaction_destination FOREIGN KEY (destination_account_id) REFERENCES account (id),*/
    INDEX                  idx_source_destination_accounts (source_account_id, destination_account_id)
);


