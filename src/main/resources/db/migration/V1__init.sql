CREATE TABLE categories
(
    created_at       datetime NULL,
    id               BIGINT AUTO_INCREMENT NOT NULL,
    last_modified_at datetime NULL,
    title            VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE product
(
    id               BIGINT NOT NULL,
    created_at       datetime NULL,
    last_modified_at datetime NULL,
    `description`    VARCHAR(255) NULL,
    image_url        VARCHAR(255) NULL,
    price DOUBLE NULL,
    title            VARCHAR(255) NULL,
    category_id      BIGINT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

ALTER TABLE product
    ADD CONSTRAINT FKowomku74u72o6h8q0khj7id8q FOREIGN KEY (category_id) REFERENCES categories (id) ON DELETE NO ACTION;

CREATE INDEX FKowomku74u72o6h8q0khj7id8q ON product (category_id);