CREATE TABLE IF NOT EXISTS perfil_usuario (
    id bigserial primary key,
    nombre varchar(50) not null unique
);

CREATE TABLE IF NOT EXISTS usuario (
    id bigserial primary key,
    username varchar(50) not null unique,
    password varchar(255) not null,
    nombres varchar(150),
    email varchar(120),
    enabled boolean not null default true,
    failed_login_attempts INTEGER NOT NULL DEFAULT 0,
    locked_until TIMESTAMP,
    deleted BOOLEAN NOT NULL DEFAULT FALSE,
    created_at timestamp not null default now(),
    updated_at timestamp not null default now()
);

CREATE TABLE IF NOT EXISTS user_roles (
    usuario_id bigint not null references usuarios(id) on delete cascade,
    rol_id bigint not null references roles(id) on delete cascade,
    primary key (usuario_id, rol_id)
);

CREATE TABLE IF NOT EXISTS refresh_tokens (
    id BIGSERIAL PRIMARY KEY,
    token_hash VARCHAR(128) NOT NULL UNIQUE,
    expiry_date TIMESTAMP NOT NULL,
    revoked BOOLEAN NOT NULL DEFAULT FALSE,
    user_id BIGINT NOT NULL,
    deleted BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    CONSTRAINT fk_refresh_token_user FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS cliente (
    id bigserial primary key,
    codigo varchar(30) not null unique,
    nombres varchar(100) not null,
    apellidos VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    documento_identidad varchar(20),
    telefono varchar(20),
    email varchar(120),
    direccion varchar(255),
    activo BOOLEAN NOT NULL,
    deleted BOOLEAN NOT NULL DEFAULT FALSE,
    created_at timestamp not null default now(),
    updated_at timestamp not null default now()
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

/*
create table clientes (
  id bigserial primary key,
  codigo varchar(30) not null unique,
  nombres varchar(150) not null,
  documento_identidad varchar(20),
  telefono varchar(20),
  email varchar(120),
  direccion varchar(255),
  activo boolean not null default true,
  created_at timestamp not null default now(),
  updated_at timestamp not null default now()
);
*/

create table documentos_cobranza (
  id bigserial primary key,
  cliente_id bigint not null references clientes(id),
  glosa varchar(255) not null,
  monto_original numeric(14,2) not null,
  fecha_emision date,
  fecha_vencimiento date not null,
  estado varchar(20) not null,
  saldo_pendiente numeric(14,2) not null,
  observacion varchar(255),
  activo boolean not null default true,
  created_at timestamp not null default now(),
  updated_at timestamp not null default now()
);

create table abonos (
  id bigserial primary key,
  documento_cobranza_id bigint not null references documentos_cobranza(id),
  monto numeric(14,2) not null,
  fecha_abono timestamp not null,
  medio_pago varchar(50),
  referencia varchar(100),
  observacion varchar(255),
  created_by varchar(100),
  created_at timestamp not null default now()
);

/*
create table roles (
  id bigserial primary key,
  nombre varchar(50) not null unique
);

create table usuarios (
  id bigserial primary key,
  username varchar(80) not null unique,
  password varchar(255) not null,
  nombres varchar(150),
  email varchar(120),
  enabled boolean not null default true,
  created_at timestamp not null default now(),
  updated_at timestamp
);

create table usuarios_roles (
  usuario_id bigint not null references usuarios(id),
  rol_id bigint not null references roles(id),
  primary key (usuario_id, rol_id)
);

*/