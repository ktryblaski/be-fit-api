ALTER SEQUENCE macronutrients_seq RESTART WITH 1000;
ALTER SEQUENCE product_seq RESTART WITH 1000;
ALTER SEQUENCE ingredient_seq RESTART WITH 1000;
ALTER SEQUENCE meal_seq RESTART WITH 1000;

INSERT INTO macronutrients(id, carbohydrates, proteins, fats) VALUES
(1, 30, 10, 10),
(2, 60, 20, 1),
(3, 15, 60, 1),
(4, 15, 17, 30),
(5, 64, 18, 2),
(6, 20, 50, 3),
(7, 32, 20, 20),
(8, 11, 50, 10),
(9, 41, 10, 10);

INSERT INTO product(id, name, macronutrients_id) VALUES
(1, 'Banana', 1),
(2, 'Oat flakes', 2),
(3, 'Peanut butter', 3),
(4, 'Egg', 4);

INSERT INTO ingredient(id, product_id, weight) VALUES
(1, 1, 120),
(2, 2, 70),
(3, 3, 30),
(4, 1, 90),
(5, 4, 100),
(6, 3, 20);

INSERT INTO meal(id, name, description) VALUES
(1, 'Pancakes', 'Tasty <3'),
(2, 'Porridge', 'Too much :O');

INSERT INTO meal_ingredient(meal_id, ingredient_id) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 5),
(2, 4),
(2, 6),
(2, 2);