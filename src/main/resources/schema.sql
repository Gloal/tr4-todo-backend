CREATE TABLE todo (
    task_id SERIAL PRIMARY KEY,
    task VARCHAR(250) NOT NULL,
    is_completed BOOLEAN NOT NULL DEFAULT FALSE
);