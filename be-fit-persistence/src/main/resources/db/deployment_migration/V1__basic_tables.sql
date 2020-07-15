CREATE SEQUENCE macronutrients_seq START 1;
CREATE TABLE macronutrients (
    id INTEGER PRIMARY KEY DEFAULT nextval('macronutrients_seq'),
    carbohydrates INTEGER NOT NULL,
    proteins INTEGER NOT NULL,
    fats INTEGER NOT NULL
);

CREATE SEQUENCE product_seq START 1;
CREATE TABLE product (
    id INTEGER PRIMARY KEY DEFAULT nextval('product_seq'),
    name VARCHAR(128) NOT NULL UNIQUE,
    macronutrients_id INTEGER NOT NULL REFERENCES macronutrients(id)
);

CREATE SEQUENCE ingredient_seq START 1;
CREATE TABLE ingredient (
    id INTEGER PRIMARY KEY DEFAULT nextval('ingredient_seq'),
    product_id INTEGER NOT NULL REFERENCES product(id),
    weight INTEGER NOT NULL
);