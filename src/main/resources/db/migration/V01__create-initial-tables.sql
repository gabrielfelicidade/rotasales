CREATE TABLE event (
    id uuid PRIMARY KEY,
    description varchar(255),
    end_date timestamp,
    start_date timestamp
);

CREATE TABLE item (
     id uuid PRIMARY KEY,
     description varchar(255),
     value numeric(19,2)
);

CREATE TABLE "user" (
     id uuid PRIMARY KEY,
     username varchar(255),
     "password" varchar(255),
     full_name varchar(255)
);

CREATE TABLE sale (
     id uuid PRIMARY KEY,
     seller_id uuid REFERENCES "user"(id),
     customer varchar(255),
     event_id uuid REFERENCES event(id),
     active boolean
);

CREATE TABLE sale_item (
    id uuid PRIMARY KEY,
    sale_id uuid REFERENCES sale(id),
    item_id uuid REFERENCES item(id),
    amount numeric(19,2),
    unitary_value numeric(19,2)
);

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";