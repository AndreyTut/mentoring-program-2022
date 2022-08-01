drop trigger if exists student_update_time_trigger ON students;
drop function if exists update_time_my();

CREATE FUNCTION update_time_my() RETURNS TRIGGER
    LANGUAGE plpgsql
AS
$$
BEGIN
   NEW.updated := current_date;
    RETURN NEW;
END;
$$;



CREATE TRIGGER student_update_time_trigger
    BEFORE UPDATE
    ON
        students
    FOR EACH ROW
    EXECUTE PROCEDURE update_time_my();



