CREATE TABLE todo (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      title VARCHAR(20) NOT NULL,
                      contents VARCHAR(100) NOT NULL,
                      weather VARCHAR(255),
                      created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                      user_id BIGINT,
                      CONSTRAINT fk_todo_user FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE comment (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         contents VARCHAR(50) NOT NULL,
                         created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         user_id BIGINT,
                         todo_id BIGINT NOT NULL,
                         CONSTRAINT fk_comment_user FOREIGN KEY (user_id) REFERENCES user(id),
                         CONSTRAINT fk_comment_todo FOREIGN KEY (todo_id) REFERENCES todo(id)
);

CREATE TABLE user (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(6) NOT NULL UNIQUE,
                      password VARCHAR(255) NOT NULL,
                      email VARCHAR(255) NOT NULL UNIQUE,
                      role VARCHAR(255) NOT NULL,
                      created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE writer (
                        todo_id BIGINT,
                        user_id BIGINT,
                        PRIMARY KEY (todo_id, user_id),
                        CONSTRAINT fk_writer_todo FOREIGN KEY (todo_id) REFERENCES todo(id),
                        CONSTRAINT fk_writer_user FOREIGN KEY (user_id) REFERENCES user(id)
);


