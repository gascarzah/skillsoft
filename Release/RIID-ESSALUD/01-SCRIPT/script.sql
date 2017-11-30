-- generado por oracle sql developer data modeler 4.0.3.853
--   en:        2015-09-20 13:27:43 cot
--   sitio:      oracle database 11g
--   tipo:      oracle database 11g

-- CREATE SCHEMA sc AUTHORIZATION postgres;


drop table sc.cara cascade  ;

drop table sc.clase_producto cascade  ;

drop table sc.cliente cascade  ;

drop table sc.comprobante cascade  ;

-- drop table sc.comprobante_historial cascade  ;

drop table sc.despacho_evolution cascade  ;

drop table sc.isla cascade  ;

drop table sc.manguera cascade  ;

drop table sc.maquina_registradora cascade  ;

drop table sc.producto cascade  ;

drop table sc.tipo_comprobante cascade  ;

drop table sc.tipo_documento cascade  ;

drop table sc.tipo_medio_pago cascade  ;

drop table sc.turno cascade  ;

drop table sc.usuario cascade  ;

create table sc.cara
  (
    co_cara     bigint not null ,
    nu_cara     integer ,
    de_cara     varchar (100) ,
    fe_registra timestamp without time zone ,
    co_usuario  bigint ,
    co_estado   char (2) ,
    ip_registra varchar (20) ,
    co_isla     integer not null
  ) ;
alter table sc.cara add constraint cara_pk primary key ( co_cara ) ;

create table sc.clase_producto
  (
    co_clase  bigint not null ,
    no_clase  varchar (100) ,
    co_estado char (2)
  ) ;
alter table sc.clase_producto add constraint clase_producto_pk primary key ( co_clase ) ;

create table sc.cliente
  (
    co_cliente                  bigint not null ,
    de_nombres                  varchar (100) ,
    de_paterno                  varchar (100) ,
    de_materno                  varchar (100) ,
    de_direccion                varchar (100) ,
    fe_registra                 timestamp without time zone ,
    co_usuario                  bigint ,
    co_estado                   char (2) ,
    ip_registra                 varchar (20) ,
	nu_documento_identidad 		character varying(11),
    co_tipo_documento_identidad integer not null
  ) ;
alter table sc.cliente add constraint cliente_pk primary key ( co_cliente ) ;

create table sc.comprobante
  (
    co_comprobante           bigint not null ,
	co_despacho		         bigint not null ,
    nu_comprobante           bigint ,
    fe_comprobante           timestamp without time zone ,
    sub_total                numeric (10,2) ,
    igv                      numeric (10,2) ,
    total                    numeric (10,2) ,
	descuento                numeric (10,2) ,
    co_estado                char (2) ,
    fe_registra              timestamp without time zone ,
    ip_registra              varchar (20) ,
    co_usuario               bigint ,
    co_tipo_comprobante      bigint not null ,
	co_isla				     bigint ,
	co_cara				     bigint ,	
    co_manguera              bigint ,
    co_producto              bigint not null ,
    co_maquina_registradora  bigint not null ,
    co_cliente               bigint ,
    co_turno                 bigint not null ,
    co_correlativo_evolution bigint ,
	co_empresa				 bigint ,
	co_tipo_medio_pago   	 bigint ,
    de_serie                 varchar (10) ,
    nu_placa                 varchar (15),
	nu_pre_unitario          numeric (10,2),
	nu_cantidad_galones      numeric (10,2),
	no_usuario_evolution     varchar (100),
	de_detalle     			 varchar (400),
	in_puntos      bigint DEFAULT 0	
  ) ;
alter table sc.comprobante add constraint comprobante_pk primary key ( co_comprobante ) ;

create table sc.despacho_evolution
  (
    co_despacho           		bigint not null ,
    co_consecutivo bigint,
	fe_venta timestamp without time zone ,
	fe_registra timestamp without time zone ,
	cantidad_despachada numeric (10,2),
	total_venta numeric (10,2),
	precio_unitario numeric (10,2),
	co_usuario_venta numeric (10,2),
	no_usuario_venta varchar (100),
	co_turno bigint,
	co_isla bigint,
	co_cuenta bigint,
	co_posicion bigint,
	co_manguera bigint,
	co_producto bigint,
	id_empresa bigint,
	nu_placa varchar (15),
	fe_actualiza_comprobante timestamp without time zone ,
	co_comprobante bigint,
	in_facturado      bigint DEFAULT 0,
	precio_nivel1 numeric (10,2),	
	precio_nivel2 numeric (10,2)
  ) ;
alter table sc.despacho_evolution add constraint despacho_pk primary key ( co_despacho ) ;


create table sc.descuento
  (
	co_descuento    bigint not null ,
    co_empresa    		bigint not null ,
    co_producto 			bigint,	
	valor_descuento			numeric (10,2),
	fe_registra 			timestamp without time zone ,	
	co_estado 				char(2)
  ) ;
alter table sc.descuento add constraint descuento_pk primary key ( co_descuento) ;

create table sc.empresa(
	co_empresa bigint not null ,
	id_empresa_evolution bigint not null ,
    razon_social varchar (100) ,
    nit varchar (20) ,
    representante varchar (100) ,
    direccion varchar (100) ,
    telefono varchar (30) ,
    fax  varchar (30) ,
    email varchar (50) ,
    activa integer,
	co_estado char(2),
	fe_registra timestamp without time zone
	);
alter table sc.empresa add constraint empresa_pk primary key ( co_empresa ) ;

create table sc.isla
  (
    co_isla     bigint not null ,
    nu_isla     bigint ,
	nu_caras bigint,
    de_isla     varchar (50) ,
    de_serie    varchar (20) ,
    fe_registra timestamp without time zone ,
    co_usuario  bigint ,
    co_estado   char (2) ,
    ip_registra varchar (20)
  ) ;
alter table sc.isla add constraint isla_pk primary key ( co_isla ) ;

create table sc.manguera
  (
    co_manguera bigint not null ,
    nu_manguera bigint ,
    de_serie    varchar (100) ,
    de_manguera varchar (100) ,
    co_usuario  bigint ,
    co_estado   char (2) not null ,
    fe_registra timestamp without time zone ,
    ip_registra varchar (20) ,
    co_cara     bigint not null ,
    co_producto bigint not null
  ) ;
alter table sc.manguera add constraint manguera_pk primary key ( co_manguera ) ;

create table sc.maquina_registradora
  (
	co_maquina_registradora bigint not null ,
    de_maquina_registradora varchar (100) ,
    de_serie_sunat character varying(100),
	de_auto_sunat character varying(100),
	de_serie_comprobante character varying(10),
	de_ruta_impresora character varying(100),
	fe_registra timestamp without time zone,
	co_estado character(2),
	co_usuario bigint,
	ip_registra character varying(20),
	nu_comprobante bigint
  ) ;
alter table sc.maquina_registradora add constraint maquina_registradora_pk primary key ( co_maquina_registradora ) ;
create table sc.placa
  (
    co_placa 	bigint not null ,
	co_empresa 	bigint not null ,
    nu_placa   	varchar (20) ,
	de_observaciones   varchar (100) ,
    fe_registra timestamp without time zone ,
    co_usuario  bigint ,
    ip_registra varchar (20) ,
    co_estado   char (2) 
  ) ;
alter table sc.placa add constraint placa_pk primary key ( co_placa ) ;

create table sc.producto
  (
    co_producto bigint not null ,
    de_nombre   varchar (100) ,
    fe_registra timestamp without time zone ,
    co_usuario  bigint ,
    ip_registra varchar (20) ,
    co_estado   char (2) ,
    co_clase    bigint not null
  ) ;
alter table sc.producto add constraint producto_pk primary key ( co_producto ) ;

create table sc.parametro
  (
    co_parametro bigint not null ,
    de_nombre   varchar (100) ,
    nu_valor 	numeric (10,2),
    de_valor  	varchar (100)
  ) ;
alter table sc.parametro add constraint parametro_pk primary key ( co_parametro ) ;

create table sc.tipo_comprobante
  (
    co_tipo_comprobante bigint not null ,
    no_tipo_comprobante varchar (100) ,
    co_sunat            varchar (10) ,
    co_estado           char (2)
  ) ;
alter table sc.tipo_comprobante add constraint tipo_comprobante_pk primary key ( co_tipo_comprobante ) ;

create table sc.permiso
  (
    co_permiso      bigint not null ,
    co_usuario      bigint ,
	co_modulo		varchar (20) ,
    fe_registra 	timestamp without time zone ,
    co_estado   	char (2) 
  ) ;
alter table sc.permiso add constraint permiso_pk primary key ( co_permiso ) ;

create table sc.tipo_documento
  (
    co_tipo_documento_identidad bigint not null ,
    no_tipo_documento_identidad varchar (20) ,
    co_sunat                    varchar (3) ,
    co_estado                   char (2)
  ) ;
alter table sc.tipo_documento add constraint tipo_documento_pk primary key ( co_tipo_documento_identidad ) ;

create table sc.tipo_medio_pago
  (
    co_tipo_medio_pago 			bigint not null ,
    no_tipo_medio_pago 			varchar (20) ,
    co_sunat                    varchar (3) ,
    co_estado                   char (2)
  ) ;
alter table sc.tipo_medio_pago add constraint tipo_medio_pago_pk primary key ( co_tipo_medio_pago ) ;

create table sc.turno
  (
    co_turno            bigint not null ,
    fe_turno            date ,
    co_turno_evolution  bigint ,
    fe_registra         timestamp without time zone ,
    co_estado           char (2) ,
    ip_registra         varchar (20) ,
    co_isla             bigint not null ,
    co_usuario          bigint not null ,
    co_usuario_registra bigint
  ) ;
alter table sc.turno add constraint turno_pk primary key ( co_turno ) ;

create table sc.usuario
  (
    co_usuario          bigint not null ,
    de_login            varchar (100) ,
    de_password         varchar (100) ,
    de_nombres          varchar (100) ,
    de_paterno          varchar (100) ,
    de_materno          varchar (100) ,
    de_nu_dni           varchar (8) ,
    fe_registra         timestamp without time zone ,
    fe_baja             date ,
    fe_actualiza        timestamp without time zone ,
    co_estado           char (2) ,
    co_usuario_registra bigint ,
    ip_registra         varchar (20)
  ) ;
alter table sc.usuario add constraint usuario_pk primary key ( co_usuario ) ;





alter table sc.cara add constraint cara_isla_fk foreign key ( co_isla ) references sc.isla ( co_isla ) ;

alter table sc.cliente add constraint cliente_tipo_documento_fk foreign key ( co_tipo_documento_identidad ) references sc.tipo_documento ( co_tipo_documento_identidad ) ;

alter table sc.comprobante add constraint comprobante_cliente_fk foreign key ( co_cliente ) references sc.cliente ( co_cliente ) ;

alter table sc.comprobante add constraint comprobante_manguera_fk foreign key ( co_manguera ) references sc.manguera ( co_manguera ) ;

alter table sc.comprobante add constraint comprobante_maquina_r_fk foreign key ( co_maquina_registradora ) references sc.maquina_registradora ( co_maquina_registradora ) ;

alter table sc.comprobante add constraint comprobante_producto_fk foreign key ( co_producto ) references sc.producto ( co_producto ) ;

alter table sc.comprobante add constraint comprobante_tipo_c_fk foreign key ( co_tipo_comprobante ) references sc.tipo_comprobante ( co_tipo_comprobante ) ;

-- alter table sc.comprobante add constraint comprobante_turno_fk foreign key ( co_turno ) references sc.turno ( co_turno ) ;

alter table sc.manguera add constraint manguera_cara_fk foreign key ( co_cara ) references sc.cara ( co_cara ) ;

alter table sc.manguera add constraint manguera_producto_fk foreign key ( co_producto ) references sc.producto ( co_producto ) ;

alter table sc.producto add constraint producto_clase_p_fk foreign key ( co_clase ) references sc.clase_producto ( co_clase ) ;

alter table sc.placa add constraint placa_empresa_fk foreign key ( co_empresa ) references sc.empresa ( co_empresa ) ;

alter table sc.turno add constraint turno_isla_fk foreign key ( co_isla ) references sc.isla ( co_isla ) ;

alter table sc.turno add constraint turno_usuario_fk foreign key ( co_usuario ) references sc.usuario ( co_usuario ) ;

alter table sc.descuento add constraint descuento_fk foreign key ( co_empresa ) references sc.empresa ( co_empresa ) ;
