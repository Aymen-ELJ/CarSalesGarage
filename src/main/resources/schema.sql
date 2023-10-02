CREATE TABLE IF NOT EXISTS  car (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    make VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    registration_date TIMESTAMP NOT NULL,
    price DOUBLE NOT NULL,
    fuel_type VARCHAR(20) NOT NULL,
    mileage DOUBLE NOT NULL,
    transmission VARCHAR(20) NOT NULL,
    picture VARCHAR(255),

    CHECK (registration_date >= '2015-01-01'),
    CHECK (fuel_type IN ('DIESEL', 'ELECTRIC', 'HYBRID')),
    CHECK (transmission IN ('MANUAL', 'SEMI_AUTOMATIC', 'AUTOMATIC'))
);


CREATE TABLE IF NOT EXISTS request_tracking (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    method_name VARCHAR(255) NOT NULL,
    inputs TEXT,
    outputs TEXT,
    processing_time_millis BIGINT NOT NULL,
    date TIMESTAMP NOT NULL,
    success_status VARCHAR(2) NOT NULL,

    CHECK (success_status IN ('OK', 'KO'))
);

