insert into ROLE (NAME) values('ROLE_ADMIN');
insert into ROLE (NAME) values('ROLE_USER');
insert into USER(username,password,name) values('jhiguera','$2a$10$FMQOTEUiRN1L2MV2gfYas.MEDnLcEffuenRme5WdFgkwcuWA2jyhG','Jaime Higuera');
insert into USER(username,password,name) values('admin','$2a$10$FMQOTEUiRN1L2MV2gfYas.MEDnLcEffuenRme5WdFgkwcuWA2jyhG','Admin');
insert into USER(username,password,name) values('gplombardi','$2a$10$FMQOTEUiRN1L2MV2gfYas.MEDnLcEffuenRme5WdFgkwcuWA2jyhG','Gian Paolo');

insert into USER_ROLES(user_id,role_id) values(1,1);
insert into USER_ROLES(user_id,role_id) values(2,1);
insert into USER_ROLES(user_id,role_id) values(3,2);




insert into SECTOR(sector_productivo,parcela,nombre_cuartelero,superficie_real_plantada,especie_plantada,variedad_copa,
                   variedad_portainjerto,plantas_reales)
      values('1','MANDROGNE',null,3.0,'TOMATE','ATTIYA','KARDIA',29980);
 
 
insert into SECTOR(sector_productivo,parcela,nombre_cuartelero,superficie_real_plantada,especie_plantada,variedad_copa,
                   variedad_portainjerto,plantas_reales)
      values('2','MANDROGNE',null,3.0,'TOMATE','ATTIYA','KARDIA',29980);
 
 
insert into SECTOR(sector_productivo,parcela,nombre_cuartelero,superficie_real_plantada,especie_plantada,variedad_copa,
                   variedad_portainjerto,plantas_reales)
      values('3','MANDROGNE',null,3.0,'TOMATE','ATTIYA','KARDIA',29980);
 
 
 
insert into SECTOR(sector_productivo,parcela,nombre_cuartelero,superficie_real_plantada,especie_plantada,variedad_copa,
                   variedad_portainjerto,plantas_reales)
      values('4','MANDROGNE',null,3.0,'TOMATE','ATTIYA','KARDIA',29980);
      
      
insert into SECTOR(sector_productivo,parcela,nombre_cuartelero,superficie_real_plantada,especie_plantada,variedad_copa,
                   variedad_portainjerto,plantas_reales)
      values('5','MANDROGNE',null,3.0,'TOMATE','ATTIYA','KARDIA',29980);
 
insert into PRODUCTO(producto,caracteristicas,ingrediente_activo,reingreso,umedida,color_etiqueta,mda_irac,mda_frac,vigente)
    values('TAC-I/BETA','FUNGICIDA','RICHODERMA VIRIDE - TRICHODERMA HARZIANUM - TRICHODERMA LONGIBRACHIATUM',2,'CC','VERDE','NO APLICA','NO APLICA',true);

      
insert into PRODUCTO(producto,caracteristicas,ingrediente_activo,reingreso,umedida,color_etiqueta,mda_irac,mda_frac,vigente)
    values('ABAMAX 1,8% EC','INSECTICIDA','ABAMECTINA',12,'CC','AMARILLO','NO APLICA','NO APLICA',true);
 
       
insert into PRODUCTO(producto,caracteristicas,ingrediente_activo,reingreso,umedida,color_etiqueta,mda_irac,mda_frac,vigente)
    values('TERCEL 50 WP','INSECTICIDA','ABAMECTINA',12,'CC','AMARILLO','NO APLICA','NO APLICA',true);
 
       
insert into PRODUCTO(producto,caracteristicas,ingrediente_activo,reingreso,umedida,color_etiqueta,mda_irac,mda_frac,vigente)
    values('ULBU','INSECTICIDA','ABAMECTINA',12,'CC','AMARILLO','NO APLICA','NO APLICA',true);



 
 insert into TAB_COD(codigo,valor) values ('metodo_aplicacion','PULVERIZACIÓN');
 insert into TAB_COD(codigo,valor) values ('metodo_aplicacion','NEBULIZACIÓN');
 insert into TAB_COD(codigo,valor) values ('metodo_aplicacion','DRENCHING');
 insert into TAB_COD(codigo,valor) values ('metodo_aplicacion','ESPOLVOREO');
 insert into TAB_COD(codigo,valor) values ('metodo_aplicacion','VÍA RIEGO');
  
 insert into TAB_COD(codigo,valor) values ('maquinas','FUMIMATIC TURBO');
 insert into TAB_COD(codigo,valor) values ('maquinas','FUMIMATIC SIMPLE');
 insert into TAB_COD(codigo,valor) values ('maquinas','BOMBA DE ESPALDA');
 insert into TAB_COD(codigo,valor) values ('maquinas','BOMBA LEVERA');  
 insert into TAB_COD(codigo,valor) values ('maquinas','TURBO JACTO');
 
 insert into TAB_COD(codigo,valor) values ('unidad_medida','CC/HL');
 insert into TAB_COD(codigo,valor) values ('unidad_medida','GR/HL');
 insert into TAB_COD(codigo,valor) values ('unidad_medida','LT/HA');
 insert into TAB_COD(codigo,valor) values ('unidad_medida','KG/HA');
 
 //insert into TAB_COD(codigo,valor) values ('unidad_medida_gasto','CC');
 //insert into TAB_COD(codigo,valor) values ('unidad_medida_gasto','GR');
 insert into TAB_COD(codigo,valor) values ('unidad_medida_gasto','LT');
 insert into TAB_COD(codigo,valor) values ('unidad_medida_gasto','KG');
 
 
 insert into TAB_COD(codigo,valor) values ('objetivo_aplicacion','CONTROL CURATIVO DE LEPIDOPTERO');
 insert into TAB_COD(codigo,valor) values ('objetivo_aplicacion','CONTROL CURATIVO DE PULGÓN');
  insert into TAB_COD(codigo,valor) values ('objetivo_aplicacion','CONTROL CURATIVO DE ACAROS');
  insert into TAB_COD(codigo,valor) values ('objetivo_aplicacion','CONTROL CURATIVO DE ACAROS');
  insert into TAB_COD(codigo,valor) values ('objetivo_aplicacion','CONTROL CURATIVO DE TRIPS');
  insert into TAB_COD(codigo,valor) values ('objetivo_aplicacion','CONTROL CURATIVO DE MOSCA BLANCA');
  insert into TAB_COD(codigo,valor) values ('objetivo_aplicacion','MEJORAR APLICACIÓN');
  insert into TAB_COD(codigo,valor) values ('objetivo_aplicacion','BAJAR PH');
  insert into TAB_COD(codigo,valor) values ('objetivo_aplicacion','CORREGIR CARENCIA NUTRICIONAL');
  insert into TAB_COD(codigo,valor) values ('objetivo_aplicacion','ESTIMULAR CUAJA');
  insert into TAB_COD(codigo,valor) values ('objetivo_aplicacion','ESTIMULAR CUAJA Y CALIBRE');
  insert into TAB_COD(codigo,valor) values ('objetivo_aplicacion','ESTIMULAR RESISTENCIA CONTRA ESTRÉS ABIOTICO');
  insert into TAB_COD(codigo,valor) values ('objetivo_aplicacion','ESTIMULAR DESARROLLO VEGETATIVO');
  insert into TAB_COD(codigo,valor) values ('objetivo_aplicacion','PREVENIR EL RAJADO DE FRUTOS');
  insert into TAB_COD(codigo,valor) values ('objetivo_aplicacion','INCREMENTAR CUAJA DE FRUTOS');
  insert into TAB_COD(codigo,valor) values ('objetivo_aplicacion','CONTROL PREVENTIVO DE OIDIO');
  insert into TAB_COD(codigo,valor) values ('objetivo_aplicacion','ESTIMULAR FLORACION');
  insert into TAB_COD(codigo,valor) values ('objetivo_aplicacion','APORTE DE MATERIA ORGANICA');
  insert into TAB_COD(codigo,valor) values ('objetivo_aplicacion','APORTE DE NUTRIENTES');
  insert into TAB_COD(codigo,valor) values ('objetivo_aplicacion','DESPLAZAR SALES');
  insert into TAB_COD(codigo,valor) values ('objetivo_aplicacion','LAVADOS');
  insert into TAB_COD(codigo,valor) values ('objetivo_aplicacion','CONTRTOL DE MALEZAS');
  insert into TAB_COD(codigo,valor) values ('objetivo_aplicacion','INCREMENTAR CUAJA DE FRUTOS Y DIVISION CELULAR');
  insert into TAB_COD(codigo,valor) values ('objetivo_aplicacion','CONTROL DE ORTHENZIA');
  insert into TAB_COD(codigo,valor) values ('objetivo_aplicacion','HONGO DE CUELLO');
  insert into TAB_COD(codigo,valor) values ('objetivo_aplicacion','POTENCIAR LLENADO DE FRUTOS');
  insert into TAB_COD(codigo,valor) values ('objetivo_aplicacion','CONTROL DE BOTRYTIS');
  insert into TAB_COD(codigo,valor) values ('objetivo_aplicacion','CRECIMIENTO Y DESARROLLO DE LAS PLANTAS');
 
  
  insert into CONVERSION(u_medida_1 , u_medida_2,expresion) values('CC/HL','LTS','/1000');
  insert into CONVERSION(u_medida_1,u_medida_2,expresion) values ('GR/HL','KG','/1000');
  insert into CONVERSION(u_medida_1,u_medida_2,expresion) values('LTS/HA','CC','*1000');
  insert into CONVERSION(u_medida_1,u_medida_2,expresion) values('KG/HA','GR','*1000');
  insert into CONVERSION(u_medida_1,u_medida_2,expresion) values('LTS/HA','LTS','*1');
  insert into CONVERSION(u_medida_1,u_medida_2,expresion) values('GR/HA','GR','*1');
  insert into CONVERSION(u_medida_1 , u_medida_2,expresion) values('CC/HL','CC','*1');
  insert into CONVERSION(u_medida_1,u_medida_2,expresion) values('KG/HA','KG','*1');
  
  insert into CONVERSION(u_medida_1 , u_medida_2,expresion) values('CC','LTS','/1000');
  insert into CONVERSION(u_medida_1,u_medida_2,expresion) values ('GR','KG','/1000');
  insert into CONVERSION(u_medida_1,u_medida_2,expresion) values('LTS','CC','*1000');
  insert into CONVERSION(u_medida_1,u_medida_2,expresion) values('KG','GR','*1000');
  insert into CONVERSION(u_medida_1,u_medida_2,expresion) values('LTS','LTS','*1');
  insert into CONVERSION(u_medida_1,u_medida_2,expresion) values('GR','GR','*1');
  insert into CONVERSION(u_medida_1 , u_medida_2,expresion) values('CC','CC','*1');
  insert into CONVERSION(u_medida_1,u_medida_2,expresion) values('KG','KG','*1');
  
  
  

  
 
  
  
 
    


  