ALTER TABLE student
    ADD CONSTRAINT NotNullName
        CHECK (name IS NOT NULL);

ALTER TABLE student
    ADD CONSTRAINT UniqueName
        UNIQUE (name);

ALTER TABLE student
    ADD CONSTRAINT AgeGreater
        CHECK (age >= 16);

ALTER TABLE faculty
    ADD CONSTRAINT UniqueNameAndColor
        UNIQUE (name, color);

ALTER TABLE student
    ALTER COLUMN age SET DEFAULT 20;


