CREATE
MATERIALIZED VIEW snapshot_02 AS
SELECT st.name, st.surname, sj.name as subject, m.mark
FROM students st
         LEFT JOIN marks m on st.id = m.student_id
         LEFT JOIN subjects sj ON m.subject_id = sj.id
