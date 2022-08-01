drop function avarage_mark_for_student;
CREATE OR REPLACE FUNCTION avarage_mark_for_student(stud_id integer)
    RETURNS float
    LANGUAGE plpgsql
AS
$$
begin
    return (select avg(mark)
            from marks
            where marks.student_id = stud_id);
end;
$$