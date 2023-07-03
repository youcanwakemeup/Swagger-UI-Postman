CREATE TABLE People (
    id SERIAL PRIMARY KEY,
    name TEXT,
    age INTEGER,
    hasLicense BOOLEAN
    carId INT,
    FOREIGN KEY (carId) REFERENCES Cars(id)
);

CREATE TABLE Cars (
    id INT PRIMARY KEY,
    brand TEXT,
    model TEXT,
    price INTEGER
);

