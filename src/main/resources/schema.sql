-- Creación de la tabla "users"
CREATE TABLE USERS (
                       user_id UUID NOT NULL,
                       created_at TIMESTAMP(6),
                       user_email VARCHAR(255),
                       is_active BOOLEAN,
                       modified_at TIMESTAMP(6),
                       last_login_at TIMESTAMP(6),
                       user_name VARCHAR(255),
                       user_password VARCHAR(255),
                       PRIMARY KEY (user_id)
);

-- Creación de la tabla "phones"
CREATE TABLE PHONES (
                        phone_id BIGINT GENERATED BY DEFAULT AS IDENTITY,
                        city_code VARCHAR(4),
                        country_code VARCHAR(4),
                        phone_number VARCHAR(20),
                        user_id UUID,
                        PRIMARY KEY (phone_id)
);

ALTER TABLE PHONES
    ADD CONSTRAINT FK_phones_users FOREIGN KEY (user_id) REFERENCES USERS (user_id);
