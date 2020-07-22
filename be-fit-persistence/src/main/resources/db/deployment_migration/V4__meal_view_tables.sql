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