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

CREATE SEQUENCE recipe_seq START 1;
CREATE TABLE recipe
(
    id INTEGER PRIMARY KEY DEFAULT nextval('recipe_seq'),
    name VARCHAR(128) NOT NULL UNIQUE,
    description VARCHAR(1024),
    active BOOLEAN NOT NULL DEFAULT true
);

CREATE TABLE recipe_ingredient
(
    recipe_id INTEGER NOT NULL REFERENCES recipe(id),
    ingredient_id INTEGER NOT NULL REFERENCES ingredient(id),
    UNIQUE (recipe_id, ingredient_id)
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

CREATE SEQUENCE users_seq START 1;
CREATE TABLE users
(
    id INTEGER PRIMARY KEY DEFAULT nextval('users_seq'),
    name VARCHAR(64) NOT NULL,
    surname VARCHAR(64) NOT NULL,
    email VARCHAR(128) NOT NULL UNIQUE,
    password VARCHAR(512) NOT NULL,
    register_at TIMESTAMP NOT NULL DEFAULT now(),
    locked BOOLEAN NOT NULL DEFAULT false
);

CREATE SEQUENCE permission_seq START 1;
CREATE TABLE permission
(
    id INTEGER PRIMARY KEY DEFAULT nextval('permission_seq'),
    code VARCHAR(64) NOT NULL
);

CREATE TABLE users_permission
(
    user_id INTEGER NOT NULL REFERENCES users(id),
    permission_id INTEGER NOT NULL REFERENCES permission(id),
    UNIQUE (user_id, permission_id)
);
