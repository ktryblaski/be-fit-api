CREATE SEQUENCE meal_seq START 1;
CREATE TABLE meal (
    id INTEGER PRIMARY KEY DEFAULT nextval('meal_seq'),
    name VARCHAR(128) NOT NULL,
    description VARCHAR(1024),
    type VARCHAR(32)
);

CREATE SEQUENCE diet_seq START 1;
CREATE TABLE diet (
    id INTEGER PRIMARY KEY DEFAULT nextval('diet_seq'),
    diet_start DATE,
    diet_end DATE,
    type VARCHAR(32),
    macronutrients_id INTEGER NOT NULL REFERENCES macronutrients(id)
);

CREATE TABLE meal_ingredient (
    meal_id INTEGER NOT NULL REFERENCES meal(id),
    ingredient_id INTEGER NOT NULL REFERENCES ingredient(id)
);

CREATE TABLE diet_meal (
    diet_id INTEGER NOT NULL REFERENCES diet(id),
    meal_id INTEGER NOT NULL REFERENCES meal(id)
);