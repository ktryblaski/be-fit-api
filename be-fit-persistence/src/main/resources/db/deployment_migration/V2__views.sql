CREATE VIEW v_day_of_eating AS
SELECT
    dof.*,
    COUNT(DISTINCT dofm.meal_id) AS meals_number
FROM day_of_eating dof
LEFT JOIN day_of_eating_meal dofm ON dof.id = dofm.day_of_eating_id
GROUP BY dof.id;

CREATE VIEW v_product AS
SELECT
    p.id,
    p.name,
    p.favourite,
    m.proteins,
    m.fats,
    m.carbohydrates,
    (m.proteins*4 + m.fats*9 + m.carbohydrates*4) AS calories
FROM product p
LEFT JOIN macronutrients m on p.macronutrients_id = m.id;

CREATE VIEW v_recipe AS
SELECT
    r.id,
    r.name,
    r.description,
    r.active,
    COALESCE(SUM(i.weight), 0) AS weight,
    COALESCE(SUM(i.weight / 100.0 * m.proteins), 0) AS proteins,
    COALESCE(SUM(i.weight / 100.0 * m.fats), 0) AS fats,
    COALESCE(SUM(i.weight / 100.0 * m.carbohydrates), 0) AS carbohydrates
FROM recipe r
LEFT JOIN recipe_ingredient ri ON ri.recipe_id = r.id
LEFT JOIN ingredient i ON i.id = ri.ingredient_id
LEFT JOIN product p ON p.id = i.product_id
LEFT JOIN macronutrients m ON m.id = p.macronutrients_id
GROUP BY 1;
