CREATE TABLE producto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(100),
    precio DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL,
    oferta BOOLEAN DEFAULT FALSE,
    id_categoria INT
);