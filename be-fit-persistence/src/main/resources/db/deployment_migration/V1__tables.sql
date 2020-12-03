CREATE SEQUENCE macronutrients_seq START 1;
CREATE TABLE macronutrients
(
    id INTEGER PRIMARY KEY DEFAULT nextval('macronutrients_seq'),
    carbohydrates INTEGER NOT NULL,
    proteins INTEGER NOT NULL,
    fats INTEGER NOT NULL
);

CREATE SEQUENCE product_seq START 1;
CREATE TABLE product
(
    id INTEGER PRIMARY KEY DEFAULT nextval('product_seq'),
    name VARCHAR(128) NOT NULL UNIQUE,
    favourite BOOLEAN NOT NULL DEFAULT false,
    macronutrients_id INTEGER NOT NULL REFERENCES macronutrients(id)
);

CREATE SEQUENCE ingredient_seq START 1;
CREATE TABLE ingredient
(
    id INTEGER PRIMARY KEY DEFAULT nextval('ingredient_seq'),
    product_id INTEGER NOT NULL REFERENCES product(id),
    weight INTEGER NOT NULL
);

CREATE SEQUENCE meal_template_seq START 1;
CREATE TABLE meal_template
(
    id INTEGER PRIMARY KEY DEFAULT nextval('meal_template_seq'),
    name VARCHAR(128) NOT NULL UNIQUE,
    description VARCHAR(1024),
    active BOOLEAN NOT NULL DEFAULT true
);

CREATE TABLE meal_template_ingredient
(
    meal_template_id INTEGER NOT NULL REFERENCES meal_template(id),
    ingredient_id INTEGER NOT NULL REFERENCES ingredient(id),
    UNIQUE (meal_template_id, ingredient_id)
);

CREATE SEQUENCE meal_seq START 1;
CREATE TABLE meal
(
    id INTEGER PRIMARY KEY DEFAULT nextval('meal_seq'),
    name VARCHAR(128) NOT NULL,
    description VARCHAR(1024)
);

CREATE TABLE meal_ingredient
(
    meal_id INTEGER NOT NULL REFERENCES meal(id),
    ingredient_id INTEGER NOT NULL REFERENCES ingredient(id),
    UNIQUE (meal_id, ingredient_id)
);

CREATE SEQUENCE day_of_eating_seq START 1;
CREATE TABLE day_of_eating
(
    id INTEGER PRIMARY KEY DEFAULT nextval('day_of_eating_seq'),
    day_date DATE
);

CREATE TABLE day_of_eating_meal
(
    day_of_eating_id INTEGER NOT NULL REFERENCES day_of_eating(id),
    meal_id INTEGER NOT NULL REFERENCES meal(id),
    UNIQUE (day_of_eating_id, meal_id)
);
