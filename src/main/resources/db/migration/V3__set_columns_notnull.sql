ALTER TABLE kindofproduct RENAME TO kind_of_product;

UPDATE product SET name='' WHERE name IS NULL;
UPDATE product SET type='' WHERE type IS NULL;
UPDATE product SET price=0 WHERE price IS NULL;
UPDATE kind_of_product SET name='' WHERE name IS NULL;
UPDATE kind_of_product SET type='' WHERE type IS NULL;

ALTER TABLE product ALTER COLUMN name SET NOT NULL,
                    ALTER COLUMN type SET NOT NULL,
                    ALTER COLUMN price SET NOT NULL;
ALTER TABLE kind_of_product ALTER COLUMN name SET NOT NULL,
                             ALTER COLUMN type SET NOT NULL;
