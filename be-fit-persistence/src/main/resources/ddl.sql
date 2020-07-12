CREATE SEQUENCE macronutrients_seq START 1;
CREATE TABLE macronutrients (
    id INTEGER PRIMARY KEY DEFAULT nextval('macronutrients_seq'),
    carbohydrates INTEGER NOT NULL,
    proteins INTEGER NOT NULL,
    fats INTEGER NOT NULL
);

CREATE SEQUENCE component_seq START 1;
CREATE TABLE component (
    id INTEGER PRIMARY KEY DEFAULT nextval('component_seq'),
    name VARCHAR(128) NOT NULL UNIQUE,
    macronutrients_id INTEGER NOT NULL REFERENCES macronutrients(id)
);

CREATE SEQUENCE ingredient_seq START 1;
CREATE TABLE ingredient (
    id INTEGER PRIMARY KEY DEFAULT nextval('ingredient_seq'),
    component_id INTEGER NOT NULL REFERENCES component(id),
    weight INTEGER NOT NULL
);