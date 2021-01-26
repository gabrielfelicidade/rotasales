CREATE TABLE "event" (
    "event_id" uuid PRIMARY KEY,
    "description" varchar(255) NOT NULL,
    "end_date" timestamp NOT NULL,
    "start_date" timestamp NOT NULL
);

CREATE TABLE "item" (
     "item_id" uuid PRIMARY KEY,
     "description" varchar(255) NOT NULL,
     "value" numeric(19,2) NOT NULL
);

CREATE TABLE "user" (
     "user_id" uuid PRIMARY KEY,
     "username" varchar(255) UNIQUE NOT NULL,
     "password" varchar(255) NOT NULL,
     "full_name" varchar(255) NOT NULL
);

CREATE TABLE "role" (
    "role_id" uuid PRIMARY KEY,
    "description" varchar(255) NOT NULL
);

CREATE TABLE "user_role" (
    "user_role_id" uuid PRIMARY KEY,
    "user_id" uuid REFERENCES "user"("user_id") NOT NULL,
    "role_id" uuid REFERENCES "role"("role_id") NOT NULL
);

CREATE TABLE "sale" (
     "sale_id" uuid PRIMARY KEY,
     "seller_id" uuid REFERENCES "user"("user_id") NOT NULL,
     "customer" varchar(255) NOT NULL,
     "event_id" uuid REFERENCES "event"("event_id") NOT NULL,
     "active" boolean NOT NULL
);

CREATE TABLE "sale_item" (
    "sale_item_id" uuid PRIMARY KEY,
    "sale_id" uuid REFERENCES "sale"("sale_id") NOT NULL,
    "item_id" uuid REFERENCES "item"("item_id") NOT NULL,
    "amount" numeric(19,2) NOT NULL,
    "unitary_value" numeric(19,2) NOT NULL
);

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";