-- DROP SCHEMA public;

CREATE SCHEMA public AUTHORIZATION postgres;

-- DROP TYPE public."CLIENTE";

CREATE TYPE public."CLIENTE" AS (
	"ID" int4,
	"CODIGO" varchar(10),
	"NOMBRES" varchar(50),
	"APELLIDOS" varchar(70),
	"DIRECCION" varchar(120),
	"TELEFONO" varchar(50),
	"NOMBRE_COMERCIAL" varchar(100),
	"DNI" varchar(8),
	"RUC" varchar(11),
	"CORREO" varchar(50));

-- DROP TYPE public."CONTROL_REPARTO";

CREATE TYPE public."CONTROL_REPARTO" AS (
	"ID" int4,
	"CODIGO" varchar(10),
	"FECHA" date,
	"CODIGO_CLIENTE" varchar(10),
	"CODIGO_ENVIO" varchar(10),
	"CANTIDAD" numeric(6,2),
	"ESTADO" varchar(15),
	"FECHA_CIERRE" date,
	"VALOR_PEDIDO" numeric(7,2),
	"VALOR_PAGADO" numeric(7,2),
	"VALOR_DEUDA" numeric(7,2));

-- DROP TYPE public."DETALLE_ENVIO";

CREATE TYPE public."DETALLE_ENVIO" AS (
	"ID" int4,
	"CODIGO" varchar(10),
	"CODIGO_ENVIO" varchar(10),
	"CODIGO_PRODUCTO" varchar(10),
	"CODIGO_SEMANA" varchar(10),
	"CANTIDAD" numeric(6,2),
	"VALOR_MIN" numeric(7,2),
	"VALOR_MAX" numeric(7,2));

-- DROP TYPE public."DETALLE_PRODUCTO";

CREATE TYPE public."DETALLE_PRODUCTO" AS (
	"ID" int4,
	"CODIGO" varchar(10),
	"FECHA" date,
	"CODIGO_PRODUCTO" varchar(10),
	"CODIGO_SEMANA" varchar(10),
	"PRECIOVENTA_MIN" float8,
	"PRECIOVENTA_MAX" float8,
	"PRECIODEVOLUCION_MIN" float8,
	"PRECIODEVOLUCION_MAX" float8,
	"CANTIDAD" int4,
	"ESTADO" varchar(15));

-- DROP TYPE public."DETALLE_REPARTO";

CREATE TYPE public."DETALLE_REPARTO" AS (
	"ID" int4,
	"CODIGO" varchar(10),
	"FECHA_DESPACHO" date,
	"FECHA_RECIBIDO" date,
	"ESTADO_REPARTO" varchar,
	"GUIA_DESPACHO" varchar(20),
	"VENDEDOR" varchar(100),
	"CODIGO_REPARTO" varchar(10),
	"CODIGO_DETALLEPRODUCTO" varchar(10),
	"CANTIDAD" numeric(6,2),
	"PRECIO" numeric(7,2));

-- DROP TYPE public."ENVIO";

CREATE TYPE public."ENVIO" AS (
	"ID" int4,
	"CODIGO" varchar(10),
	"CODIGO_VENDEDORT" varchar(10),
	"FECHA" date,
	"PLACA" varchar(10),
	"ESTADO" varchar(15));

-- DROP TYPE public."PERFIL_USUARIO";

CREATE TYPE public."PERFIL_USUARIO" AS (
	"ID" int4,
	"CODIGO" varchar(10),
	"ROL" varchar(50));

-- DROP TYPE public."PRODUCTO";

CREATE TYPE public."PRODUCTO" AS (
	"ID" int4,
	"CODIGO" varchar(10),
	"UNIDAD" varchar(20),
	"DESCRIPCION" varchar(100),
	"PRESENTACION" varchar(50),
	"CODIGO_TIPOPRODUCTO" varchar(10),
	"ESTADO" varchar(15));

-- DROP TYPE public."SEMANA";

CREATE TYPE public."SEMANA" AS (
	"ID" int4,
	"CODIGO" varchar(10),
	"FECHA_INICIO" date,
	"FECHA_FIN" date,
	"MES" varchar(12),
	"TRIMESTRE" varchar(12),
	"ANIO_ISO" varchar(10));

-- DROP TYPE public."TIPO_PRODUCTO";

CREATE TYPE public."TIPO_PRODUCTO" AS (
	"ID" int4,
	"CODIGO" varchar(10),
	"DESCRIPCION" varchar(100));

-- DROP TYPE public."USUARIO";

CREATE TYPE public."USUARIO" AS (
	"ID" int4,
	"CODIGO" varchar(10),
	"CODIGO_PERFILUSAURIO" varchar(10),
	"USUARIO" varchar(30),
	"PASS" varchar(30),
	"FECHA" date,
	"ESTADO" bit);

-- DROP TYPE public."VENDEDOR";

CREATE TYPE public."VENDEDOR" AS (
	"ID" int4,
	"CODIGO" varchar(10),
	"DNI" varchar(8),
	"NOMBRES" varchar(50),
	"LICENCIA" varchar(15),
	"CELULAR" varchar(15),
	"CORREO" varchar(20),
	"DIRECCION" varchar(100),
	"USUARIO" varchar(30),
	"FECHA" date);

-- DROP TYPE public."_CLIENTE";

CREATE TYPE public."_CLIENTE" (
	INPUT = array_in,
	OUTPUT = array_out,
	RECEIVE = array_recv,
	SEND = array_send,
	ANALYZE = array_typanalyze,
	ALIGNMENT = 8,
	STORAGE = any,
	CATEGORY = A,
	ELEMENT = public."CLIENTE",
	DELIMITER = ',');

-- DROP TYPE public."_CONTROL_REPARTO";

CREATE TYPE public."_CONTROL_REPARTO" (
	INPUT = array_in,
	OUTPUT = array_out,
	RECEIVE = array_recv,
	SEND = array_send,
	ANALYZE = array_typanalyze,
	ALIGNMENT = 8,
	STORAGE = any,
	CATEGORY = A,
	ELEMENT = public."CONTROL_REPARTO",
	DELIMITER = ',');

-- DROP TYPE public."_DETALLE_ENVIO";

CREATE TYPE public."_DETALLE_ENVIO" (
	INPUT = array_in,
	OUTPUT = array_out,
	RECEIVE = array_recv,
	SEND = array_send,
	ANALYZE = array_typanalyze,
	ALIGNMENT = 8,
	STORAGE = any,
	CATEGORY = A,
	ELEMENT = public."DETALLE_ENVIO",
	DELIMITER = ',');

-- DROP TYPE public."_DETALLE_PRODUCTO";

CREATE TYPE public."_DETALLE_PRODUCTO" (
	INPUT = array_in,
	OUTPUT = array_out,
	RECEIVE = array_recv,
	SEND = array_send,
	ANALYZE = array_typanalyze,
	ALIGNMENT = 8,
	STORAGE = any,
	CATEGORY = A,
	ELEMENT = public."DETALLE_PRODUCTO",
	DELIMITER = ',');

-- DROP TYPE public."_DETALLE_REPARTO";

CREATE TYPE public."_DETALLE_REPARTO" (
	INPUT = array_in,
	OUTPUT = array_out,
	RECEIVE = array_recv,
	SEND = array_send,
	ANALYZE = array_typanalyze,
	ALIGNMENT = 8,
	STORAGE = any,
	CATEGORY = A,
	ELEMENT = public."DETALLE_REPARTO",
	DELIMITER = ',');

-- DROP TYPE public."_ENVIO";

CREATE TYPE public."_ENVIO" (
	INPUT = array_in,
	OUTPUT = array_out,
	RECEIVE = array_recv,
	SEND = array_send,
	ANALYZE = array_typanalyze,
	ALIGNMENT = 8,
	STORAGE = any,
	CATEGORY = A,
	ELEMENT = public."ENVIO",
	DELIMITER = ',');

-- DROP TYPE public."_PERFIL_USUARIO";

CREATE TYPE public."_PERFIL_USUARIO" (
	INPUT = array_in,
	OUTPUT = array_out,
	RECEIVE = array_recv,
	SEND = array_send,
	ANALYZE = array_typanalyze,
	ALIGNMENT = 8,
	STORAGE = any,
	CATEGORY = A,
	ELEMENT = public."PERFIL_USUARIO",
	DELIMITER = ',');

-- DROP TYPE public."_PRODUCTO";

CREATE TYPE public."_PRODUCTO" (
	INPUT = array_in,
	OUTPUT = array_out,
	RECEIVE = array_recv,
	SEND = array_send,
	ANALYZE = array_typanalyze,
	ALIGNMENT = 8,
	STORAGE = any,
	CATEGORY = A,
	ELEMENT = public."PRODUCTO",
	DELIMITER = ',');

-- DROP TYPE public."_SEMANA";

CREATE TYPE public."_SEMANA" (
	INPUT = array_in,
	OUTPUT = array_out,
	RECEIVE = array_recv,
	SEND = array_send,
	ANALYZE = array_typanalyze,
	ALIGNMENT = 8,
	STORAGE = any,
	CATEGORY = A,
	ELEMENT = public."SEMANA",
	DELIMITER = ',');

-- DROP TYPE public."_TIPO_PRODUCTO";

CREATE TYPE public."_TIPO_PRODUCTO" (
	INPUT = array_in,
	OUTPUT = array_out,
	RECEIVE = array_recv,
	SEND = array_send,
	ANALYZE = array_typanalyze,
	ALIGNMENT = 8,
	STORAGE = any,
	CATEGORY = A,
	ELEMENT = public."TIPO_PRODUCTO",
	DELIMITER = ',');

-- DROP TYPE public."_USUARIO";

CREATE TYPE public."_USUARIO" (
	INPUT = array_in,
	OUTPUT = array_out,
	RECEIVE = array_recv,
	SEND = array_send,
	ANALYZE = array_typanalyze,
	ALIGNMENT = 8,
	STORAGE = any,
	CATEGORY = A,
	ELEMENT = public."USUARIO",
	DELIMITER = ',');

-- DROP TYPE public."_VENDEDOR";

CREATE TYPE public."_VENDEDOR" (
	INPUT = array_in,
	OUTPUT = array_out,
	RECEIVE = array_recv,
	SEND = array_send,
	ANALYZE = array_typanalyze,
	ALIGNMENT = 8,
	STORAGE = any,
	CATEGORY = A,
	ELEMENT = public."VENDEDOR",
	DELIMITER = ',');

-- DROP SEQUENCE public.seq_01_tipo_pro;

CREATE SEQUENCE public.seq_01_tipo_pro
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 100000
	START 1
	CACHE 1
	NO CYCLE;-- public."CLIENTE" definition

-- Drop table

-- DROP TABLE public."CLIENTE";

CREATE TABLE public."CLIENTE" (
	"ID" int4 NOT NULL,
	"CODIGO" varchar(10) NOT NULL,
	"NOMBRES" varchar(50) NULL,
	"APELLIDOS" varchar(70) NULL,
	"DIRECCION" varchar(120) NULL,
	"TELEFONO" varchar(50) NULL,
	"NOMBRE_COMERCIAL" varchar(100) NULL,
	"DNI" varchar(8) NULL,
	"RUC" varchar(11) NULL,
	"CORREO" varchar(50) NULL,
	CONSTRAINT "CLIENTE_pkey" PRIMARY KEY ("ID")
);


-- public."CONTROL_REPARTO" definition

-- Drop table

-- DROP TABLE public."CONTROL_REPARTO";

CREATE TABLE public."CONTROL_REPARTO" (
	"ID" int4 NOT NULL,
	"CODIGO" varchar(10) NOT NULL,
	"FECHA" date NULL,
	"CODIGO_CLIENTE" varchar(10) NULL,
	"CODIGO_ENVIO" varchar(10) NULL,
	"CANTIDAD" numeric(6, 2) NULL,
	"ESTADO" varchar(15) NULL,
	"FECHA_CIERRE" date NULL,
	"VALOR_PEDIDO" numeric(7, 2) NULL,
	"VALOR_PAGADO" numeric(7, 2) NULL,
	"VALOR_DEUDA" numeric(7, 2) NULL,
	CONSTRAINT "CONTROL_REPARTO_pkey" PRIMARY KEY ("ID"),
	CONSTRAINT "UNIQUE_CLIENTE_REPARTO" UNIQUE ("CODIGO")
);


-- public."DETALLE_ENVIO" definition

-- Drop table

-- DROP TABLE public."DETALLE_ENVIO";

CREATE TABLE public."DETALLE_ENVIO" (
	"ID" int4 NOT NULL,
	"CODIGO" varchar(10) NOT NULL,
	"CODIGO_ENVIO" varchar(10) NULL,
	"CODIGO_PRODUCTO" varchar(10) NULL,
	"CODIGO_SEMANA" varchar(10) NULL,
	"CANTIDAD" numeric(6, 2) NULL,
	"VALOR_MIN" numeric(7, 2) NULL,
	"VALOR_MAX" numeric(7, 2) NULL,
	CONSTRAINT "DETALLE_ENVIO_pkey" PRIMARY KEY ("ID")
);


-- public."DETALLE_PRODUCTO" definition

-- Drop table

-- DROP TABLE public."DETALLE_PRODUCTO";

CREATE TABLE public."DETALLE_PRODUCTO" (
	"ID" int4 NOT NULL,
	"CODIGO" varchar(10) NOT NULL,
	"FECHA" date NULL,
	"CODIGO_PRODUCTO" varchar(10) NULL,
	"CODIGO_SEMANA" varchar(10) NULL,
	"PRECIOVENTA_MIN" float8 NULL,
	"PRECIOVENTA_MAX" float8 NULL,
	"PRECIODEVOLUCION_MIN" float8 NULL,
	"PRECIODEVOLUCION_MAX" float8 NULL,
	"CANTIDAD" int4 NULL,
	"ESTADO" varchar(15) NULL,
	CONSTRAINT "DETALLE_PRODUCTO_pkey" PRIMARY KEY ("ID")
);


-- public."DETALLE_REPARTO" definition

-- Drop table

-- DROP TABLE public."DETALLE_REPARTO";

CREATE TABLE public."DETALLE_REPARTO" (
	"ID" int4 NOT NULL,
	"CODIGO" varchar(10) NOT NULL,
	"FECHA_DESPACHO" date NULL,
	"FECHA_RECIBIDO" date NULL,
	"ESTADO_REPARTO" varchar NULL,
	"GUIA_DESPACHO" varchar(20) NULL,
	"VENDEDOR" varchar(100) NULL,
	"CODIGO_REPARTO" varchar(10) NULL,
	"CODIGO_DETALLEPRODUCTO" varchar(10) NULL,
	"CANTIDAD" numeric(6, 2) NULL,
	"PRECIO" numeric(7, 2) NULL,
	CONSTRAINT "DETALLE_REPARTO_pkey" PRIMARY KEY ("ID")
);


-- public."ENVIO" definition

-- Drop table

-- DROP TABLE public."ENVIO";

CREATE TABLE public."ENVIO" (
	"ID" int4 NOT NULL,
	"CODIGO" varchar(10) NULL,
	"CODIGO_VENDEDORT" varchar(10) NULL,
	"FECHA" date NULL,
	"PLACA" varchar(10) NULL,
	"ESTADO" varchar(15) NULL,
	CONSTRAINT "ENVIO_pkey" PRIMARY KEY ("ID")
);


-- public."PERFIL_USUARIO" definition

-- Drop table

-- DROP TABLE public."PERFIL_USUARIO";

CREATE TABLE public."PERFIL_USUARIO" (
	"ID" int4 NOT NULL,
	"CODIGO" varchar(10) NULL,
	"ROL" varchar(50) NULL,
	CONSTRAINT "PERFIL_UNIQUE" UNIQUE ("CODIGO"),
	CONSTRAINT "PERFIL_USUARIO_pkey" PRIMARY KEY ("ID")
);


-- public."SEMANA" definition

-- Drop table

-- DROP TABLE public."SEMANA";

CREATE TABLE public."SEMANA" (
	"ID" int4 NOT NULL,
	"CODIGO" varchar(10) NOT NULL,
	"FECHA_INICIO" date NULL,
	"FECHA_FIN" date NULL,
	"MES" varchar(12) NULL,
	"TRIMESTRE" varchar(12) NULL,
	"ANIO_ISO" varchar(10) NULL,
	CONSTRAINT "SEMANA_pkey" PRIMARY KEY ("ID")
);


-- public."TIPO_PRODUCTO" definition

-- Drop table

-- DROP TABLE public."TIPO_PRODUCTO";

CREATE TABLE public."TIPO_PRODUCTO" (
	"ID" int4 NOT NULL,
	"CODIGO" varchar(10) NOT NULL,
	"DESCRIPCION" varchar(100) NULL,
	CONSTRAINT "CODIGO_UNIQUE" UNIQUE ("CODIGO"),
	CONSTRAINT "TIPO_PRODUCTO_pkey" PRIMARY KEY ("ID")
);


-- public."VENDEDOR" definition

-- Drop table

-- DROP TABLE public."VENDEDOR";

CREATE TABLE public."VENDEDOR" (
	"ID" int4 NOT NULL,
	"CODIGO" varchar(10) NOT NULL,
	"DNI" varchar(8) NULL,
	"NOMBRES" varchar(50) NULL,
	"LICENCIA" varchar(15) NULL,
	"CELULAR" varchar(15) NULL,
	"CORREO" varchar(20) NULL,
	"DIRECCION" varchar(100) NULL,
	"USUARIO" varchar(30) NULL,
	"FECHA" date NULL,
	CONSTRAINT "VENDEDOR_pkey" PRIMARY KEY ("ID")
);


-- public."PRODUCTO" definition

-- Drop table

-- DROP TABLE public."PRODUCTO";

CREATE TABLE public."PRODUCTO" (
	"ID" int4 NOT NULL,
	"CODIGO" varchar(10) NOT NULL,
	"UNIDAD" varchar(20) NULL,
	"DESCRIPCION" varchar(100) NULL,
	"PRESENTACION" varchar(50) NULL,
	"CODIGO_TIPOPRODUCTO" varchar(10) NULL,
	"ESTADO" varchar(15) NULL,
	CONSTRAINT "PRODUCTO_pkey" PRIMARY KEY ("ID"),
	CONSTRAINT "PRODUCTO_TIPO_PRODUCTO_FK" FOREIGN KEY ("CODIGO_TIPOPRODUCTO") REFERENCES public."TIPO_PRODUCTO"("CODIGO")
);


-- public."USUARIO" definition

-- Drop table

-- DROP TABLE public."USUARIO";

CREATE TABLE public."USUARIO" (
	"ID" int4 NOT NULL,
	"CODIGO" varchar(10) NULL,
	"CODIGO_PERFILUSAURIO" varchar(10) NULL,
	"USUARIO" varchar(30) NULL,
	"PASS" varchar(30) NULL,
	"FECHA" date NULL,
	"ESTADO" bit(1) NULL,
	CONSTRAINT "USUARIO_pkey" PRIMARY KEY ("ID"),
	CONSTRAINT "USUARIO_TIPOPEFIL_FK" FOREIGN KEY ("CODIGO_PERFILUSAURIO") REFERENCES public."PERFIL_USUARIO"("CODIGO")
);