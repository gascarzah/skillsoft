stm_bm(["menu14d9",400,"","../images/blank.gif",0,"","",1,0,0,0,0,1,0,0,""],this);
stm_bp("p0",[0,4,0,0,3,4,0,7,100,"",-2,"",-2,90,0,0,"#000000","transparent","",3,0,0,"#000000"]);


stm_ai("p0i0",[0,"     EXPEDIENTE","","",-1,-1,0,"","_self","","","","",0,0,0,"../images/arrow_r.gif","../images/arrow_d.gif",7,7,0,1,1,"#3DB7E4",0,"#ffffff",0,"","",3,3,0,0,"#ffffff #8e9398 #8e9398 #ffffff","#76b2ed #406182 #406182 #76b2ed","#ffffff","#3DB7E4","bold 8pt 'Tahoma','Arial'","bold 8pt 'Tahoma','Arial'",0,0]);

stm_bpx("p3","p0",[1,4,0,0,1,4,0,0,100,"progid:DXImageTransform.Microsoft.Fade(overlap=.5,enabled=0,Duration=0.50)",-2,"progid:DXImageTransform.Microsoft.Fade(overlap=.5,enabled=0,Duration=0.50)",-2,60,0,0,"#000000","#ffffff"]);
stm_aix("p3i0","p0i0",[0,"INGRESO DE EXPEDIENTES","","",-1,-1,0,"CtrlFicha?opt=1"]);
//stm_aix("p3i1","p0i0",[0,"ANULACION DE EXPEDIENTE","","",-1,-1,0,"CtrlFicha?opt=16"]);
stm_aix("p3i2","p0i0",[0,"BUSQUEDA DE EXPEDIENTE","","",-1,-1,0,"CtrlFicha?opt=3"]);
stm_aix("p3i3","p0i0",[0,"BANDEJA DE EXPEDIENTES  ","","",-1,-1,0,"CtrlFicha?opt=34"]);
stm_aix("p3i4","p0i0",[0,"EXPEDIENTES DESDE PORTAL","","",-1,-1,0,"CtrlFicha?opt=36"]);
stm_aix("p3i5","p0i0",[0,"EXPEDIENTES ESSALUD EN LINEA  ","","",-1,-1,0,"CtrlFicha?opt=19"]);
//stm_aix("p3i6","p0i0",[0,"BANDEJA DE EXPEDIENTES DIARIAS ","","",-1,-1,0,"CtrlFicha?opt=5"]);
//stm_aix("p3i7","p0i0",[0,"EXPEDIENTES ASIGNADAS POR DELEGADOS  ","","",-1,-1,0,"CtrlFicha?opt=6"]);
stm_aix("p3i6","p0i0",[0,"HISTORIAL DE ASIGNACIONES","","",-1,-1,0,"CtrlFicha?opt=10"]);
stm_aix("p3i7","p0i0",[0,"EXPEDIENTES DEL LIBRO DE RECLAMACIONES  ","","",-1,-1,0,"CtrlFicha?opt=20"]);
stm_ep();

stm_aix("p0i2","p0i0",[0,"   CONSULTAS Y REPORTES"]); //,"","",-1,-1,0,"","_self","","","","",0,0,0,"../images/arrow_r.gif","../images/arrow_d.gif",7,7,0,1,1,"#3DB7E4",0,"#ffffff",0,"","",3,3,0,0,"#ffffff #8e9398 #8e9398 #ffffff","#eaeff5 #5d6647 #5d6647 #eaeff5"]);
stm_bpx("p4","p3",[]);
stm_aix("p4i0","p0i0",[0,"EXPORTACION A EXCEL","","",-1,-1,0,"CtrlFicha?opt=8"]);
stm_ep();


stm_aix("p0i3","p0i0",[0,"   AYUDA"]);
stm_bpx("p5","p3",[]);
//stm_aix("p5i0","p0i0",[0,"SOBRE EL SISTEMA","","",-1,-1,0,"javascript:alert(\'EN CONSTRUCCION ...\');","_self","","","","",0,0,0,"","",0,0,0,0,1,"#3DB7E4",0,"#ffffff",0,"","",3,3,1,2,"#007bbe","#ffffff","#ffffff","#007bbe"]);
//stm_aix("p5i1","p5i0",[0,"DESCRIPCION DE ASUNTOS","","",-1,-1,0,"../tabla_codigos.pdf","_blank"]);
stm_aix("p5i0","p0i0",[0,"TABLAS PARA LA CALIFICACI�N","","",-1,-1,0,"../TABLAS_DAE.pdf","_blank"]);
stm_aix("p5i1","p5i0",[0,"DIRECTIVA PARA LA GESTI�N DE INTERVENCIONES","","",-1,-1,0,"../DIRECTIVA_DEFENSORIALES_DAE.pdf","_blank"]);
stm_aix("p5i2","p5i0",[0,"MANUAL DEL USUARIO","","",-1,-1,0,"../Usuario_DDefensorial.pdf","_blank"]);
stm_ep();
stm_aix("p0i4","p0i0",[0,"   ADMINISTRACION"]);
stm_bpx("p6","p3",[]);

stm_aix("p6i0","p5i0",[0,"CAMBIO DE USUARIO","","",-1,-1,0,"../index.html","_parent","","","","",0,0,0,"","",0,0,0,0,1,"#3DB7E4",0,"#ffffff",0,"","",3,3,1,2,"#007bbe","#007bbe"]);
stm_aix("p6i1","p6i0",[0,"SALIR DEL SISTEMA","","",-1,-1,0,"../servlet/CtrlSalir?upd=Salir","_parent"]);
stm_ep();
stm_em();
