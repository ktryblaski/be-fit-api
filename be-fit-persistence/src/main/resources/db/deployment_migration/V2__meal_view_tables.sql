CREATE VIEW v_meal AS
SELECT
    m.id,
    m.name,
    SUM(i.weight) AS weight,
    SUM(i.weight / 100.0 * ma.carbohydrates) AS carbohydrates,
    SUM(i.weight / 100.0 * ma.proteins) AS proteins,
    SUM(i.weight / 100.0 * ma.fats) AS fats
FROM meal m
LEFT JOIN meal_ingredient mi on m.id = mi.meal_id
LEFT JOIN ingredient i on mi.ingredient_id = i.id
LEFT JOIN product p ON i.product_id = p.id
LEFT JOIN macronutrients ma ON p.macronutrients_id = ma.id
GROUP BY 1;

CREATE VIEW v_diet AS
SELECT
    d.id,
    d.name,
    d.start_date,
    d.end_date,
    d.type,
    ma.carbohydrates AS carbohydrates,
    ma.proteins AS proteins,
    ma.fats AS fats,
    COUNT(DISTINCT dm.meal_id) AS meals
FROM diet d
LEFT JOIN macronutrients ma ON d.macronutrients_id = ma.id
LEFT JOIN diet_meal dm ON dm.diet_id = d.id
GROUP BY d.id, ma.id;