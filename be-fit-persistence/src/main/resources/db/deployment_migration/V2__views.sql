CREATE VIEW v_day_of_eating AS
SELECT
    dof.*,
    COUNT(DISTINCT dofm.meal_id) AS meals_number
FROM day_of_eating dof
LEFT JOIN day_of_eating_meal dofm ON dof.id = dofm.day_of_eating_id
GROUP BY dof.id;


-- CREATE VIEW v_diet AS
-- SELECT
--     d.id,
--     d.name,
--     d.start_date,
--     d.end_date,
--     d.type,
--     ma.carbohydrates AS carbohydrates,
--     ma.proteins AS proteins,
--     ma.fats AS fats,
--     COUNT(DISTINCT dm.meal_id) AS meals
-- FROM diet d
--          LEFT JOIN macronutrients ma ON d.macronutrients_id = ma.id
--          LEFT JOIN diet_meal dm ON dm.diet_id = d.id
-- GROUP BY d.id, ma.id;

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
