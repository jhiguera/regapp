insert into ROLE (NAME) values('ADMIN');
insert into ROLE (NAME) values('USER');
insert into USER(username,password,name) values('jhiguera','$2a$10$FMQOTEUiRN1L2MV2gfYas.MEDnLcEffuenRme5WdFgkwcuWA2jyhG','Jaime Higuera');
insert into USER_ROLES(user_id,role_id) values(1,1);

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
 
insert into PRODUCTO(producto,caracteristicas,ingrediente_activo,reingreso,umedida,color_etiqueta,mda_irac,mda_frac)
    values('TAC-I/BETA','FUNGICIDA','RICHODERMA VIRIDE - TRICHODERMA HARZIANUM - TRICHODERMA LONGIBRACHIATUM',2,'GR','VERDE','NO APLICA','NO APLICA');

      
insert into PRODUCTO(producto,caracteristicas,ingrediente_activo,reingreso,umedida,color_etiqueta,mda_irac,mda_frac)
    values('ABAMAX 1,8% EC','INSECTICIDA','ABAMECTINA',12,'CC','AMARILLO','NO APLICA','NO APLICA');


  