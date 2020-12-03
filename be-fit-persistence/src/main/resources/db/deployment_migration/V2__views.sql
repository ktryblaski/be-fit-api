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
