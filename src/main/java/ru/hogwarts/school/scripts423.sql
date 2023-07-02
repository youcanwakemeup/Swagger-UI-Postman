SELECT Student.Name, Student.Age
    FROM Student
         JOIN Avatar ON Student.Id = Avatar.student_id;

SELECT Student.Name, Student.Age, Faculty.Name AS FacultyName
    FROM student
        JOIN faculty ON Student.faculty_name = Faculty.Id;


