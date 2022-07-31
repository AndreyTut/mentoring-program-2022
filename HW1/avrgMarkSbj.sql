drop function if exists avarage_mark_for_subject;
CREATE OR REPLACE FUNCTION avarage_mark_for_subject(subj varchar)
    RETURNS float
    LANGUAGE plpgsql
AS
$$
begin
    return (select  avg(mark) from marks m where m.subject_id in (
        select sj.id
        from subjects sj
        where sj.name = subj));
end
$$