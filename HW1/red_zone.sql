CREATE OR REPLACE FUNCTION students_red_zone() RETURNS SETOF students
AS
$$
    BEGIN
  return query ( SELECT *
            FROM students
            WHERE id IN (
                SELECT student_id
                FROM (
                         SELECT student_id, count(*) c
                         FROM marks
                         WHERE mark <= 3
                           AND student_id IS NOT NULL
                         GROUP BY student_id) AS foo
                WHERE c >= 2
                GROUP BY student_id
            ));
    END;
$$ LANGUAGE plpgsql;
