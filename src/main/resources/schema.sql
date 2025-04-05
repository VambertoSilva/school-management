CREATE TABLE users (
    id UUID PRIMARY KEY ,
    username VARCHAR(255) UNIQUE ,
    password VARCHAR(255),
    email VARCHAR(100),
    name VARCHAR(255),
    role VARCHAR(20)
);

CREATE TABLE classification (
    id UUID PRIMARY KEY NOT NULL,
    name VARCHAR(40) NOT NULL UNIQUE
);

CREATE TABLE book (
    id UUID PRIMARY KEY NOT NULL,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255),
    publish_date DATE,
    classification_id UUID REFERENCES classification(id) ON DELETE SET NULL,
    status VARCHAR(50)
);

CREATE TABLE reservation (
    id UUID PRIMARY KEY NOT NULL,
    user_id UUID REFERENCES users(id),
    book_id UUID REFERENCES book(id),
    reservation_date TIMESTAMP,
    reservation_due_date TIMESTAMP,
    status VARCHAR(50)
);

CREATE TABLE loan (
    id UUID PRIMARY KEY NOT NULL,
    user_id UUID REFERENCES users(id),
    book_id UUID REFERENCES book(id),
    name VARCHAR(255),
    title VARCHAR(255),
    loan_date TIMESTAMP,
    due_date TIMESTAMP,
    return_date TIMESTAMP,
    fine_amount DECIMAL(10, 2),
    fine_date TIMESTAMP,
    paid BOOLEAN,
    renewal_balance INTEGER,
    status VARCHAR(50)
);

CREATE TABLE config (
    id VARCHAR(100) PRIMARY KEY,
    valor VARCHAR(100)  NOT NULL
);


--CREATE TABLE book_copy (
--    id UUID PRIMARY KEY NOT NULL,
--    book_id UUID REFERENCES book(id),
--    status VARCHAR(50) DEFAULT 'available',
--    created_at TIMESTAMP,
--    updated_at TIMESTAMP ,
--    created_by UUID REFERENCES users(id),
--    updated_by UUID REFERENCES users(id)
--);

