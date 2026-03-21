CREATE TABLE IF NOT EXISTS perfil_usuario (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS usuario (
    id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(150) NOT NULL,
    username VARCHAR(100) NOT NULL UNIQUE,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN NOT NULL,
    deleted BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS user_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user_roles_user FOREIGN KEY (user_id) REFERENCES usuario(id),
    CONSTRAINT fk_user_roles_role FOREIGN KEY (role_id) REFERENCES perfil_usuario(id)
);

CREATE TABLE IF NOT EXISTS refresh_tokens (
    id BIGSERIAL PRIMARY KEY,
    token VARCHAR(500) NOT NULL UNIQUE,
    expiry_date TIMESTAMP NOT NULL,
    revoked BOOLEAN NOT NULL DEFAULT FALSE,
    user_id BIGINT NOT NULL,
    deleted BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    CONSTRAINT fk_refresh_token_user FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS cliente (
    id BIGSERIAL PRIMARY KEY,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    telefono VARCHAR(20),
    direccion VARCHAR(255),
    activo BOOLEAN NOT NULL,
    deleted BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS envio (
    id BIGSERIAL PRIMARY KEY,
    codigo_seguimiento VARCHAR(100) NOT NULL UNIQUE,
    direccion_origen VARCHAR(255) NOT NULL,
    direccion_destino VARCHAR(255) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    fecha_envio DATE NOT NULL,
    deleted BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS vendedor (
    id BIGSERIAL PRIMARY KEY,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    telefono VARCHAR(20),
    activo BOOLEAN NOT NULL,
    deleted BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS reparto (
    id BIGSERIAL PRIMARY KEY,
    fecha_reparto DATE NOT NULL,
    estado VARCHAR(50) NOT NULL,
    observacion VARCHAR(255),
    cliente_id BIGINT NOT NULL,
    envio_id BIGINT NOT NULL,
    deleted BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    CONSTRAINT fk_reparto_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id),
    CONSTRAINT fk_reparto_envio FOREIGN KEY (envio_id) REFERENCES envio(id)
);

CREATE TABLE IF NOT EXISTS liquidacion (
    id BIGSERIAL PRIMARY KEY,
    fecha_liquidacion DATE NOT NULL,
    monto_total NUMERIC(12,2) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    vendedor_id BIGINT NOT NULL,
    deleted BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    CONSTRAINT fk_liquidacion_vendedor FOREIGN KEY (vendedor_id) REFERENCES vendedor(id)
);

INSERT INTO perfil_usuario(rol) VALUES ('ROLE_ADMIN') ON CONFLICT (rol) DO NOTHING;
INSERT INTO perfil_usuario(rol) VALUES ('ROLE_OPERADOR') ON CONFLICT (rol) DO NOTHING;
INSERT INTO perfil_usuario(rol) VALUES ('ROLE_VENDEDOR') ON CONFLICT (rol) DO NOTHING;
INSERT INTO perfil_usuario(rol) VALUES ('ROLE_REPARTIDOR') ON CONFLICT (rol) DO NOTHING;