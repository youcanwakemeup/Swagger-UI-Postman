CREATE TABLE People (
    id SERIAL PRIMARY KEY,
    name TEXT,
    age INTEGER,
    hasLicense BOOLEAN
);

CREATE TABLE Cars (
    id SERIAL PRIMARY KEY,
    brand TEXT,
    model TEXT,
    price INTEGER
);

CREATE TABLE CarOwnership (
    person_id INT,
    car_id INT,
    FOREIGN KEY (person_id) REFERENCES People(id),
    FOREIGN KEY (car_id) REFERENCES Cars(id)
);