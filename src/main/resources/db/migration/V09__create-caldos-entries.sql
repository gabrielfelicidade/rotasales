INSERT INTO "event" values (uuid_generate_v4(), 'Noite dos caldos', '2021-07-31T13:00:00.000Z', now());

INSERT INTO "item" values (uuid_generate_v4(), 'Caldo Verde Vegetariano', 10, (SELECT "event_id" FROM "event" e WHERE e.description = 'Noite dos caldos'));
INSERT INTO "item" values (uuid_generate_v4(), 'Caldo Verde', 10, (SELECT "event_id" FROM "event" e WHERE e.description = 'Noite dos caldos'));
INSERT INTO "item" values (uuid_generate_v4(), 'Caldo Mandioquinha', 10, (SELECT "event_id" FROM "event" e WHERE e.description = 'Noite dos caldos'));
INSERT INTO "item" values (uuid_generate_v4(), 'Caldo Feijão', 10, (SELECT "event_id" FROM "event" e WHERE e.description = 'Noite dos caldos'));
