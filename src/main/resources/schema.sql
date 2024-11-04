CREATE TABLE task_model (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title varchar (250) NOT NULL,
    description varchar (255) NOT NULL,
    cost DECIMAL (10,2) NOT NULL,
    deadline DATE NOT NULL,
    orderPresentation INT NOT NULL UNIQUE
);