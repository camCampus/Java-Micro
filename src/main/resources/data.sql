CREATE TABLE user
(
    license_number BIGINT PRIMARY KEY ,
    first_name VARCHAR(255) NOT NULL ,
    last_name VARCHAR(255) NOT NULL ,
    birth_date DATE NOT NULL,
    email VARCHAR(255) NOT NULL
);

INSERT INTO user VALUES (987546521589, 'nico','flemme', '2009-12-31' , 'nico@gmail.com');
INSERT INTO user VALUES (458796548514, 'plouf', 'plouf', '2009-12-31' , 'plouf@gmail.com');
INSERT INTO user VALUES (632587985421, 'bim', 'bam', '2009-12-31' , 'boom@gmail.com');