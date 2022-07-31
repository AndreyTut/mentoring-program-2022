DROP TABLE IF EXISTS marks;
DROP TABLE IF EXISTS subjects;
DROP TABLE IF EXISTS students;
CREATE TABLE students (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(50),
                          surname VARCHAR(50),
                          dob DATE,
                          phones VARCHAR(50),
                          skill VARCHAR(50),
                          created DATE,
                          updated DATE
);
CREATE INDEX name_index ON students(name);

CREATE TABLE subjects (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(50),
                          tutor VARCHAR(50)
);

CREATE TABLE marks (
                       id SERIAL PRIMARY KEY,
                       mark INTEGER,
                       student_id INTEGER,
                       subject_id INTEGER,
                       FOREIGN KEY(student_id)
                           REFERENCES students(id),
                       FOREIGN KEY(subject_id)
                           REFERENCES subjects(id)
);
