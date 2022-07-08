CREATE SEQUENCE  IF NOT EXISTS hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE event (
  id UUID NOT NULL,
   created_at TIMESTAMP WITHOUT TIME ZONE,
   last_updated_at TIMESTAMP WITHOUT TIME ZONE,
   title VARCHAR(255) NOT NULL,
   description VARCHAR(255),
   date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
   start_sales TIMESTAMP WITHOUT TIME ZONE NOT NULL,
   end_sales TIMESTAMP WITHOUT TIME ZONE NOT NULL,
   institution_id UUID NOT NULL,
   sales_strategy VARCHAR(255) NOT NULL,
   CONSTRAINT pk_event PRIMARY KEY (id)
);

CREATE TABLE institution (
  id UUID NOT NULL,
   created_at TIMESTAMP WITHOUT TIME ZONE,
   last_updated_at TIMESTAMP WITHOUT TIME ZONE,
   name VARCHAR(255) NOT NULL,
   CONSTRAINT pk_institution PRIMARY KEY (id)
);

CREATE TABLE item (
  id UUID NOT NULL,
   created_at TIMESTAMP WITHOUT TIME ZONE,
   last_updated_at TIMESTAMP WITHOUT TIME ZONE,
   name VARCHAR(255) NOT NULL,
   value DECIMAL NOT NULL,
   event_id UUID NOT NULL,
   CONSTRAINT pk_item PRIMARY KEY (id)
);

CREATE TABLE role (
  id UUID NOT NULL,
   created_at TIMESTAMP WITHOUT TIME ZONE,
   last_updated_at TIMESTAMP WITHOUT TIME ZONE,
   description VARCHAR(255) NOT NULL,
   CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE sale (
  id UUID NOT NULL,
   created_at TIMESTAMP WITHOUT TIME ZONE,
   last_updated_at TIMESTAMP WITHOUT TIME ZONE,
   buyer VARCHAR(255) NOT NULL,
   seller_id UUID NOT NULL,
   event_id UUID NOT NULL,
   donation BOOLEAN NOT NULL,
   status VARCHAR(255) NOT NULL,
   active BOOLEAN NOT NULL,
   CONSTRAINT pk_sale PRIMARY KEY (id)
);

CREATE TABLE sale_item (
  id UUID NOT NULL,
   created_at TIMESTAMP WITHOUT TIME ZONE,
   last_updated_at TIMESTAMP WITHOUT TIME ZONE,
   amount DECIMAL NOT NULL,
   sale_id UUID NOT NULL,
   item_id UUID NOT NULL,
   CONSTRAINT pk_saleitem PRIMARY KEY (id)
);

CREATE TABLE "user" (
  id UUID NOT NULL,
   created_at TIMESTAMP WITHOUT TIME ZONE,
   last_updated_at TIMESTAMP WITHOUT TIME ZONE,
   username VARCHAR(255),
   password VARCHAR(255),
   full_name VARCHAR(255),
   institution_id UUID NOT NULL,
   CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE user_role (
  id UUID NOT NULL,
   created_at TIMESTAMP WITHOUT TIME ZONE,
   last_updated_at TIMESTAMP WITHOUT TIME ZONE,
   user_id UUID NOT NULL,
   role_id UUID NOT NULL,
   CONSTRAINT pk_userrole PRIMARY KEY (id)
);

ALTER TABLE event ADD CONSTRAINT FK_EVENT_ON_INSTITUTION FOREIGN KEY (institution_id) REFERENCES institution (id);

ALTER TABLE item ADD CONSTRAINT FK_ITEM_ON_EVENT FOREIGN KEY (event_id) REFERENCES event (id);

ALTER TABLE sale_item ADD CONSTRAINT FK_SALEITEM_ON_ITEM FOREIGN KEY (item_id) REFERENCES item (id);

ALTER TABLE sale_item ADD CONSTRAINT FK_SALEITEM_ON_SALE FOREIGN KEY (sale_id) REFERENCES sale (id);

ALTER TABLE sale ADD CONSTRAINT FK_SALE_ON_EVENT FOREIGN KEY (event_id) REFERENCES event (id);

ALTER TABLE sale ADD CONSTRAINT FK_SALE_ON_SELLER FOREIGN KEY (seller_id) REFERENCES "user" (id);

ALTER TABLE user_role ADD CONSTRAINT FK_USERROLE_ON_ROLE FOREIGN KEY (role_id) REFERENCES role (id);

ALTER TABLE user_role ADD CONSTRAINT FK_USERROLE_ON_USER FOREIGN KEY (user_id) REFERENCES "user" (id);

ALTER TABLE "user" ADD CONSTRAINT FK_USER_ON_INSTITUTION FOREIGN KEY (institution_id) REFERENCES institution (id);

DO $$
DECLARE master_role_id uuid;
DECLARE rotaract_institution_id uuid;
DECLARE user_id uuid;
BEGIN
    INSERT INTO role VALUES (uuid_generate_v4(), now(), now(), 'MASTER') RETURNING id INTO master_role_id;
    INSERT INTO institution VALUES (uuid_generate_v4(), now(), now(), 'Rotaract Club Sorocaba-Leste') RETURNING id INTO rotaract_institution_id;
    INSERT INTO "user" VALUES (uuid_generate_v4(), now(), now(), 'master_user', '$2y$12$LdveL/nBTmBTftYfYExaM.bB5hW0hbp0SPkCxy4uqpH9hOoNZOO8S', 'Administrator', rotaract_institution_id) RETURNING id INTO user_id;
    INSERT INTO user_role VALUES (uuid_generate_v4(), now(), now(), user_id, master_role_id);
END $$