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
