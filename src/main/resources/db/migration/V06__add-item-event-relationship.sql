ALTER TABLE "item" ADD COLUMN "event_id" uuid;
ALTER TABLE "item"
    ADD CONSTRAINT item_event_id_fkey
    FOREIGN KEY (event_id)
    REFERENCES event(event_id);
ALTER TABLE "sale_item" DROP COLUMN "unitary_value";
UPDATE "item" SET "event_id" = (SELECT "event_id" FROM "event" e WHERE e.description = 'Pizzaract');