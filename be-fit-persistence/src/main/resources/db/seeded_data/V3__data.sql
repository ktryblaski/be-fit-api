ALTER SEQUENCE macronutrients_seq RESTART WITH 1000;
ALTER SEQUENCE product_seq RESTART WITH 1000;
ALTER SEQUENCE ingredient_seq RESTART WITH 1000;
ALTER SEQUENCE meal_seq RESTART WITH 1000;
ALTER SEQUENCE meal_template_seq RESTART WITH 1000;
ALTER SEQUENCE day_of_eating_seq RESTART WITH 1000;

INSERT INTO macronutrients (id, carbohydrates, proteins, fats) VALUES (1, 20, 1, 0);
INSERT INTO macronutrients (id, carbohydrates, proteins, fats) VALUES (2, 5, 3, 2);
INSERT INTO macronutrients (id, carbohydrates, proteins, fats) VALUES (3, 76, 7, 1);
INSERT INTO macronutrients (id, carbohydrates, proteins, fats) VALUES (4, 58, 13, 7);
INSERT INTO macronutrients (id, carbohydrates, proteins, fats) VALUES (5, 9, 79, 7);
INSERT INTO macronutrients (id, carbohydrates, proteins, fats) VALUES (6, 4, 26, 54);
INSERT INTO macronutrients (id, carbohydrates, proteins, fats) VALUES (7, 56, 8, 3);
INSERT INTO macronutrients (id, carbohydrates, proteins, fats) VALUES (8, 44, 7, 1);
INSERT INTO macronutrients (id, carbohydrates, proteins, fats) VALUES (9, 90, 0, 4);
INSERT INTO macronutrients (id, carbohydrates, proteins, fats) VALUES (10, 0, 22, 4);
INSERT INTO macronutrients (id, carbohydrates, proteins, fats) VALUES (11, 1, 19, 2);
INSERT INTO macronutrients (id, carbohydrates, proteins, fats) VALUES (12, 0, 12, 10);
INSERT INTO macronutrients (id, carbohydrates, proteins, fats) VALUES (13, 74, 10, 1);
INSERT INTO macronutrients (id, carbohydrates, proteins, fats) VALUES (14, 0, 0, 83);
INSERT INTO macronutrients (id, carbohydrates, proteins, fats) VALUES (15, 0, 0, 92);

INSERT INTO product (id, name, macronutrients_id, favourite) VALUES (1, 'Banan', 1, true);
INSERT INTO product (id, name, macronutrients_id, favourite) VALUES (2, 'Mleko 2%', 2, false);
INSERT INTO product (id, name, macronutrients_id, favourite) VALUES (3, 'Płatki ryżowe', 3, false);
INSERT INTO product (id, name, macronutrients_id, favourite) VALUES (4, 'Płatki owsiane', 4, false);
INSERT INTO product (id, name, macronutrients_id, favourite) VALUES (5, 'WPC 80%', 5, false);
INSERT INTO product (id, name, macronutrients_id, favourite) VALUES (6, 'Masło orzechowe', 6, true);
INSERT INTO product (id, name, macronutrients_id, favourite) VALUES (7, 'Bułka kajzerka', 7, false);
INSERT INTO product (id, name, macronutrients_id, favourite) VALUES (8, 'Chleb żytni razowy', 8, false);
INSERT INTO product (id, name, macronutrients_id, favourite) VALUES (9, 'Skittles', 9, false);
INSERT INTO product (id, name, macronutrients_id, favourite) VALUES (10, 'Pierś z kurczaka', 10, true);
INSERT INTO product (id, name, macronutrients_id, favourite) VALUES (11, 'Szynka z indyka', 11, false);
INSERT INTO product (id, name, macronutrients_id, favourite) VALUES (12, 'Jaja', 12, false);
INSERT INTO product (id, name, macronutrients_id, favourite) VALUES (13, 'Mąka pszenna 500', 13, true);
INSERT INTO product (id, name, macronutrients_id, favourite) VALUES (14, 'Masło 83%', 14, false);
INSERT INTO product (id, name, macronutrients_id, favourite) VALUES (15, 'Oliwa z oliwek', 15, false);

INSERT INTO ingredient (id, product_id, weight) VALUES (1, 1, 120);
INSERT INTO ingredient (id, product_id, weight) VALUES (2, 2, 200);
INSERT INTO ingredient (id, product_id, weight) VALUES (3, 3, 70);
INSERT INTO ingredient (id, product_id, weight) VALUES (4, 5, 30);
INSERT INTO ingredient (id, product_id, weight) VALUES (5, 6, 10);

INSERT INTO meal_template (id, name, description, active) VALUES (1, 'Ryżanka', 'Omniomniom', true);

INSERT INTO meal_template_ingredient (meal_template_id, ingredient_id) VALUES (1, 1);
INSERT INTO meal_template_ingredient (meal_template_id, ingredient_id) VALUES (1, 2);
INSERT INTO meal_template_ingredient (meal_template_id, ingredient_id) VALUES (1, 3);
INSERT INTO meal_template_ingredient (meal_template_id, ingredient_id) VALUES (1, 4);
INSERT INTO meal_template_ingredient (meal_template_id, ingredient_id) VALUES (1, 5);

INSERT INTO ingredient (id, product_id, weight) VALUES (11, 1, 120);
INSERT INTO ingredient (id, product_id, weight) VALUES (12, 2, 200);
INSERT INTO ingredient (id, product_id, weight) VALUES (13, 3, 70);
INSERT INTO ingredient (id, product_id, weight) VALUES (14, 5, 30);
INSERT INTO ingredient (id, product_id, weight) VALUES (15, 6, 1);

INSERT INTO meal (id, name, description) VALUES (1, 'Ryżanka', 'Omniomniom');

INSERT INTO meal_ingredient (meal_id, ingredient_id) VALUES (1, 11);
INSERT INTO meal_ingredient (meal_id, ingredient_id) VALUES (1, 12);
INSERT INTO meal_ingredient (meal_id, ingredient_id) VALUES (1, 13);
INSERT INTO meal_ingredient (meal_id, ingredient_id) VALUES (1, 14);
INSERT INTO meal_ingredient (meal_id, ingredient_id) VALUES (1, 15);

INSERT INTO day_of_eating (id, day_date) VALUES (1, '2020-10-07');

INSERT INTO day_of_eating_meal (day_of_eating_id, meal_id) VALUES (1, 1);

INSERT INTO ingredient (id, product_id, weight) VALUES (111, 4, 80);
INSERT INTO ingredient (id, product_id, weight) VALUES (112, 5, 20);
INSERT INTO ingredient (id, product_id, weight) VALUES (113, 6, 20);
INSERT INTO ingredient (id, product_id, weight) VALUES (114, 1, 120);
INSERT INTO ingredient (id, product_id, weight) VALUES (115, 9, 10);

INSERT INTO meal (id, name, description) VALUES (2, 'Owsianka', 'Posiłek na ciepło');

INSERT INTO meal_ingredient (meal_id, ingredient_id) VALUES (2, 111);
INSERT INTO meal_ingredient (meal_id, ingredient_id) VALUES (2, 112);
INSERT INTO meal_ingredient (meal_id, ingredient_id) VALUES (2, 113);
INSERT INTO meal_ingredient (meal_id, ingredient_id) VALUES (2, 114);
INSERT INTO meal_ingredient (meal_id, ingredient_id) VALUES (2, 115);

INSERT INTO day_of_eating_meal (day_of_eating_id, meal_id) VALUES (1, 2);
