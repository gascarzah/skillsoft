
    function soloLetras(e){
        tecla = (document.all) ? e.keyCode : e.which; // 2

        if (tecla==8) return true; // 3

        patron =/[A-Za-z�������\s]/;

        te = String.fromCharCode(tecla); // 5

        return patron.test(te); // 6
    }

    function soloNumeros(e){
       key = e.keyCode || e.which;
       tecla = String.fromCharCode(key).toLowerCase();
       letras = "0123456789";
       especiales = [8,37,39,46];

       tecla_especial = false
       for(var i in especiales){
            if(key == especiales[i]){
                tecla_especial = true;
                break;
            }
        }

        if(letras.indexOf(tecla)==-1 && !tecla_especial){
            return false;
        }

    }
	
	function registro_ant(formulario) {
		//alert("Mensaje"+formulario.tipoIngreso[1].checked);
	if( !formulario.tipoIngreso[1].checked  ){
		
		if (formulario.DFICDID.value   == '') { 
			alert ('El DNI esta vacio'); 
			formulario.DFICDID.focus(); 
			return false; 
			} 
		
		if (formulario.DAPEPAT.value   == '') { 
			alert ('El Apellido Paterno esta vacio'); 
			formulario.DAPEPAT.focus(); 
			return false; 
			} 
		
		if (formulario.DAPEMAT.value   == '') { 
			alert ('La Apellido Materno esta vacio'); 
			formulario.DAPEMAT.focus(); 
			return false; 
			} 
		
		if (formulario.DDICSEX.value   == '') { 
			alert ('Seleccione el Sexo'); 
			formulario.DDICSEX.focus(); 
			return false; 
			} 
		
		if (formulario.NFICEDA.value   == '') { 
			alert ('Ingresa su Edad'); 
			formulario.NFICEDA.focus(); 
			return false; 
			} 
		/*
		if (formulario.DFICTEL.value   == '') { 
			alert ('El Telefono esta vacio'); 
			formulario.DFICTEL.focus(); 
			return false; 
			}
		*/
		//correo del solicitante		
		if(formulario.MAILRECLA.value!=''){			
			if(!validarEmail(formulario.MAILRECLA.value)){
				formulario.MAILRECLA.focus();
				return false;	
				}
		}
		//
		if (formulario.DFICDIR.value   == '') { 
			alert ('Ingresa su Direccion'); 
			formulario.DFICDIR.focus(); 
			return false; 
			} 
		
		if (formulario.CTPR.value   == '') { 
			alert ('Selecciona su tipo de Prestaciones'); 
			formulario.CTPR.focus(); 
			return false; 
			}
		
		//alert("Mensaje"+formulario.tipoIngreso.value);
		if(formulario.CTIN.value != '06' && formulario.CTIN.value != '07' ){
			if (formulario.CTIN.value   == '') { 
				alert ('Selecciona su tipo de Ingreso'); 
				formulario.CTIN.focus(); 
				return false; 
				}
		}
		
		//validacion de email del involucrado		
		if(formulario.DFICMAIL.value!=''){			
		if(!validarEmail(formulario.DFICMAIL.value)){
			formulario.DFICMAIL.focus();
			return false;
			}
	    }
		

		if (formulario.DFICHEC.value   == '') { 
			alert ('Digite la descripcion de los hechos'); 
			formulario.DFICHEC.focus(); 
			return false; 
			}  
		
	}
	
		if (formulario.CRED.value   == '') { 
			alert ('Selecciona la Unidad Organica Nivel 01'); 
			formulario.CRED.focus(); 
			return false; 
			}
		
		if (formulario.CCAS.value   == '') { 
			alert ('Selecciona la Unidad Organica Nivel 02'); 
			formulario.CCAS.focus(); 
			return false; 
			}
		
    
    return true;           //cambiar por return true para ejecutar la accion del formulario

}

	function validacion2(formulario) {
		
		//if (formulario.CMSO.value   == '') { alert ('Seleccione tipo de solicitud'); 
		//formulario.CMSO.focus(); return false; } 
		
		//if (formulario.CTEM.value   == '') { alert ('Seleccione tematica'); 
		//formulario.CTEM.focus(); return false; } 
		
		//if (formulario.CMATERIA.value   == '') { alert ('Seleccione motivo principal'); 
		//formulario.CMATERIA.focus(); return false; } 
		
		//if (formulario.CASU.value   == '') { alert ('Seleccione el Caso'); 
		//formulario.CASU.focus(); return false; } 
		
		if (document.f.CRED.options[document.f.CRED.selectedIndex].text=='') { alert ('Selecciona la Unidad Organica Nivel 01'); 
		formulario.CRED.focus(); return false; } 
		
		if (formulario.CCAS.selectedIndex==0) { alert ('Selecciona la Unidad Organica Nivel 02'); 
		formulario.CCAS.focus(); return false; } 
		
		if (formulario.ENLAREHOSCOD.selectedIndex==0) { alert ('Seleccione la Area'); 
		formulario.ENLAREHOSCOD.focus(); return false; } 
		
		if (formulario.ENLSERVHOSCOD.selectedIndex==0) { alert ('Selecciona el Servicio'); 
		formulario.ENLSERVHOSCOD.focus(); return false; }

	    alert('Los campos han sido ingresados corectamente.')
	    return true;           //cambiar por return true para ejecutar la accion del formulario

	}
function validacion3(formulario) {
		
		if (formulario.CACCREAL.value   == '') { alert ('Seleccione Acciones Realizadas'); 
		formulario.CACCREAL.focus(); return false; } 
		
		if (formulario.CQUIEN.value   == '') { alert ('Digite Remitente'); 
		formulario.CQUIEN.focus(); return false; } 
		
		if (formulario.FECHAQ.value   == '') { alert ('Seleccione Fecha'); 
		formulario.FECHAQ.focus(); return false; } 
		
		if (formulario.DCARGO.value   == '') { alert ('Digite Cargo'); 
		formulario.DCARGO.focus(); return false; } 
		
		if (formulario.DFICOBS.value   == '') { alert ('Digite la Gestion'); 
		formulario.DFICOBS.focus(); return false; } 
		
	    alert('Los campos han sido ingresados corectamente.')
	    return true;           //cambiar por return true para ejecutar la accion del formulario

	}

	function enLinea(formulario) {
	
	if (formulario.DFICDID.value   == '') { alert ('El DNI esta vacio'); 
	formulario.DFICDID.focus(); return false; } 
	
	if (formulario.DAPEPAT.value   == '') { alert ('El Apellido Paterno esta vacio'); 
	formulario.DAPEPAT.focus(); return false; } 
	
	if (formulario.DAPEMAT.value   == '') { alert ('La Apellido Materno esta vacio'); 
	formulario.DAPEMAT.focus(); return false; } 
	
	if (formulario.DDICSEX.value   == '') { alert ('Seleccione el Sexo'); 
	formulario.DDICSEX.focus(); return false; } 
	
	if (formulario.NFICEDA.value   == '') { alert ('Ingresa su Edad'); 
	formulario.NFICEDA.focus(); return false; } 
	
	if (formulario.DFICTEL.value   == '') { alert ('El Tel�fono esta vacio'); 
	formulario.DFICTEL.focus(); return false; } 
	
	if (formulario.DFICDIR.value   == '') { alert ('Ingresa su Direccion'); 
	formulario.DFICDIR.focus(); return false; } 
	
	if (formulario.CTPR.value   == '') { alert ('Selecciona su tipo de Prestaciones'); 
	formulario.CTPR.focus(); return false; }

	if (formulario.CRED.value   == '') { alert ('Selecciona la Unidad Organica Nivel 01'); 
	formulario.CRED.focus(); return false; }
	

	if (formulario.DFICHEC.value   == '') { alert ('Digite la descripcion de los hechos'); 
	formulario.DFICHEC.focus(); return false; }  
	
    
    return true;           //cambiar por return true para ejecutar la accion del formulario

}

	function registro2(formulario) {
		/*
		if (formulario.TELRECLA.disabled=="" && formulario.TELRECLA.value   == '') { alert ('El telefono del solicitante esta vacio'); 
		formulario.TELRECLA.focus(); return false; } 
		*/
		if (formulario.DFICDID.value   == '') { alert ('El DNI esta vacio'); 
		formulario.DFICDID.focus(); return false; } 
		
		if (formulario.DAPEPAT.value   == '') { alert ('El Apellido Paterno esta vacio'); 
		formulario.DAPEPAT.focus(); return false; } 
		
		if (formulario.DAPEMAT.value   == '') { alert ('La Apellido Materno esta vacio'); 
		formulario.DAPEMAT.focus(); return false; }
		
		if (formulario.DNOM.value   == '') { alert ('El Nombre esta vacio'); 
		formulario.DNOM.focus(); return false; } 
		
		if (formulario.DDICSEX.value   == '') { alert ('Seleccione el Sexo'); 
		formulario.DDICSEX.focus(); return false; } 
		
		if (formulario.NFICEDA.value   == '') { alert ('Ingresa su Edad'); 
		formulario.NFICEDA.focus(); return false; } 
		/*
		if (formulario.DFICTEL.value   == '') { alert ('El Tel�fono esta vacio'); 
		formulario.DFICTEL.focus(); return false; }
		*/ 
		//correo del solicitante		
		if(formulario.MAILRECLA.value!=''){			
		if(!validarEmail(formulario.MAILRECLA.value)){
			formulario.MAILRECLA.focus();
			return false;	
			}
		}
		//
		if (formulario.PROVINCIA.value   == '') { alert ('Ingresa su Provincia'); 
		formulario.PROVINCIA.focus(); return false; }
		
		if (formulario.CFICUBIGEO.value   == '') { alert ('Ingresa su Distrito'); 
		formulario.CFICUBIGEO.focus(); return false; }
		
		if (formulario.DEPARTAMENTO.value   == '') { alert ('Ingresa su Departamento'); 
		formulario.DEPARTAMENTO.focus(); return false; }
		
		if (formulario.DFICDIR.value   == '') { alert ('Ingresa su Direccion'); 
		formulario.DFICDIR.focus(); return false; } 
		//validacion de email del involucrado		
		if(formulario.DFICMAIL.value!=''){			
		if(!validarEmail(formulario.DFICMAIL.value)){
			formulario.DFICMAIL.focus();
			return false;
			}
	    }
		//
		if (formulario.DFICHEC.value   == '') { alert ('Digite la descripcion de los hechos'); 
		formulario.DFICHEC.focus(); return false; }  
		
	    
	    return true;           //cambiar por return true para ejecutar la accion del formulario

	    
	}
function validarEmail(email){
	
	var expr = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
     if ( !expr.test(email) ) {
    	 alert("Error: La direcci�n de correo " + email + " es incorrecta.");
    	 return false;
    	 } 
     return true;
}

function registro(formulario) {

	if(formulario.CTIN.value == '10'){
		//alert("1")
			return validacionComun(formulario);
	}else{
		if(formulario.tipoIngreso[0].checked == false && formulario.tipoIngreso[1].checked == false){
			alert ('Debe seleccionar (en la parte superior de la pantalla) un tipo de ingreso'); 
			return false;
	
		}else{
	//ssi es tipo de ingreso DE OFICIO = 1
			if(formulario.tipoIngreso[0].checked ) {
				//alert("2")
				if( !formulario.tipoIngreso[1].checked  ){//de parte
					if(formulario.tipoPersona[0].checked == false && formulario.tipoPersona[1].checked == false){
						//alert("5")
						alert ('Debe seleccionar (en la parte superior de la pantalla) un Tipo de persona'); 
						return false;
				
					} else{
						//alert("4")
						if(formulario.tipoPersona[0].checked  ){ 
							//persona juridica
								//alert("6")
							if(formulario.tipoSector[0].checked == false && formulario.tipoSector[1].checked == false){
								alert ('Debe seleccionar (en la parte superior de la pantalla) un sector'); 
								return false;			
							}
						}
					}
						return validacionComun(formulario);
				}
			}else{
				return validacionOficioComun(formulario);
			}
	
		}
		return true;           //cambiar por return true para ejecutar la accion del formulario
	}
}


	function registroMod(formulario) {
		
		if(formulario.CTIN.value == '10'){
			//alert("1")
				return validacionComunComplementaria(formulario);
				
		}else{
			if(formulario.tipoIngreso[0].checked == false && formulario.tipoIngreso[1].checked == false){
				alert ('Debe seleccionar (en la parte superior de la pantalla) un tipo de ingreso'); 
				return false;
		
			}else{
		//ssi es tipo de ingreso DE OFICIO != 0
				if(formulario.tipoIngreso[0].checked ) {
					//alert("2")
					if( !formulario.tipoIngreso[1].checked  ){//de parte
						if(formulario.tipoPersona[0].checked == false && formulario.tipoPersona[1].checked == false){
							//alert("5")
							alert ('Debe seleccionar (en la parte superior de la pantalla) Persona Natural o  Persona Jurídica'); 
							return false;
					
						} else{
							//alert("4")
							if(formulario.tipoPersona[0].checked  ){ 
								//persona juridica
									//alert("6")
								if(formulario.tipoSector[0].checked == false && formulario.tipoSector[1].checked == false){
									alert ('Debe seleccionar (en la parte superior de la pantalla) un sector'); 
									return false;			
								}
							}
						}
					
							return validacionComunComplementaria(formulario);
							
						
					}
				}else{
					return validacionOficioComunComplementariaConDelegado(formulario);
				}
		
			}
			return true;           //cambiar por return true para ejecutar la accion del formulario
		}
	}
	
	
	function validacionOficioComun(formulario){
		if (formulario.CRED.value   == '') { alert ('Selecciona la Unidad Organica Nivel 01'); 
		formulario.CRED.focus(); return false; }
		
		if (formulario.CCAS.value   == '') { alert ('Selecciona la Unidad Organica Nivel 02'); 
		formulario.CCAS.focus(); return false; }
		
		if (formulario.DFICHEC.value   == '') { alert ('Digite la descripcion de los hechos'); 
		formulario.DFICHEC.focus(); return false; }
		
		return true;
	}
	
	function validacionOficioComunComplementariaConDelegado(formulario){
		if (formulario.CRED.value   == '') { alert ('Selecciona la Unidad Organica Nivel 01'); 
		formulario.CRED.focus(); return false; }
		
		if (formulario.CCAS.value   == '') { alert ('Selecciona la Unidad Organica Nivel 02'); 
		formulario.CCAS.focus(); return false; }
		
		if (formulario.DFICHEC.value   == '') { alert ('Digite la descripcion de los hechos'); 
		formulario.DFICHEC.focus(); return false; }
		
	
		if(formulario.perfiles.value != '11' ){//si tipo de ingreso es essalud en linea
			if(formulario.asignar[0].checked){
				
				if(formulario.CRED1.value == '' || formulario.CCAS1.value == '' || formulario.CDELEGADO.value==''){
					alert("Datos del Delegado Asignado no debe de estar vacio");
					return false;
				}
			}
		}
		
		return true;
	}
	
	function validacionComunComplementaria(formulario){
		if (formulario.DFICDID.value   == '') { alert ('El DNI esta vacio'); 
		formulario.DFICDID.focus(); return false; } 
		
		if (formulario.DAPEPAT.value   == '') { alert ('El Apellido Paterno esta vacio'); 
		formulario.DAPEPAT.focus(); return false; } 
		
		if (formulario.DAPEMAT.value   == '') { alert ('La Apellido Materno esta vacio'); 
		formulario.DAPEMAT.focus(); return false; } 
		
		if(formulario.ORIRECLA.value != '1'){
		
			if (formulario.DDICSEX.value   == '') { alert ('Seleccione el Sexo'); 
			formulario.DDICSEX.focus(); return false; } 
			
			if (formulario.NFICEDA.value   == '') { alert ('Ingresa su Edad'); 
			formulario.NFICEDA.focus(); return false; }
		}
		/*
		if (formulario.DFICTEL.value   == '') { alert ('El Telefono esta vacio'); 
		formulario.DFICTEL.focus(); return false; } 
		*/
		//correo del solicitante		
		if(formulario.MAILRECLA.value!=''){			
		if(!validarEmail(formulario.MAILRECLA.value)){
			formulario.MAILRECLA.focus();
			return false;	
			}
		}
		//
		if (formulario.DFICDIR.value   == '') { alert ('Ingresa su Direccion'); 
		formulario.DFICDIR.focus(); return false; } 
		
		if (formulario.CTPR.value   == '') { alert ('Selecciona su tipo de Prestaciones'); 
		formulario.CTPR.focus(); return false; }
		
		
		//if(formulario.tipoIngreso.value != null){
		//	if(formulario.tipoIngreso.value != '06' && formulario.tipoIngreso.value != '07' ){
				if (formulario.CTIN.value   == '') { alert ('Selecciona su tipo de Ingreso'); 
				formulario.CTIN.focus(); return false; }
		//	}
		//}
		
		//validacion de email del involucrado		
		if(formulario.DFICMAIL.value!=''){			
		if(!validarEmail(formulario.DFICMAIL.value)){
			formulario.DFICMAIL.focus();
			return false;
			}
	    }
		
		if (formulario.CRED.value   == '') { alert ('Selecciona la Unidad Organica Nivel 01'); 
		formulario.CRED.focus(); return false; }
		
		if (formulario.CCAS.value   == '') { alert ('Selecciona la Unidad Organica Nivel 02'); 
		formulario.CCAS.focus(); return false; }
		
		if (formulario.DFICHEC.value   == '') { alert ('Digite la descripcion de los hechos'); 
		formulario.DFICHEC.focus(); return false; }
		
	
		if(formulario.perfiles.value != '11' && formulario.perfiles.value != '08'){//si tipo de ingreso es essalud en linea
				if(formulario.asignar[0].checked){
					if(formulario.CRED1.value == '' || formulario.CCAS1.value == '' || formulario.CDELEGADO.value==''){
						alert("Datos del Delegado Asignado no debe de estar vacio");
						return false;
					}
				}
			 
		}
		
		return true;
	}
		

	function validacionComun(formulario){
		//alert("1111")
		if (formulario.DFICDID.value   == '') { alert ('El DNI esta vacio'); 
		formulario.DFICDID.focus(); return false; } 
		//alert("11113")
		if (formulario.DAPEPAT.value   == '') { alert ('El Apellido Paterno esta vacio'); 
		formulario.DAPEPAT.focus(); return false; } 
		//alert("11114")
		if (formulario.DAPEMAT.value   == '') { alert ('La Apellido Materno esta vacio'); 
		formulario.DAPEMAT.focus(); return false; } 
		//alert("11115")
		
		//if(formulario.ORIRECLA.value != '1'){
			//alert("11116")
		if (formulario.DDICSEX.value   == '') { alert ('Seleccione el Sexo'); 
		formulario.DDICSEX.focus(); return false; } 
		//	alert("11117")
		if (formulario.NFICEDA.value   == '') { alert ('Ingresa su Edad'); 
		formulario.NFICEDA.focus(); return false; }
		
		//}
		//alert("11118")
		/*
		if (formulario.DFICTEL.value   == '') { alert ('El Telefono esta vacio'); 
		formulario.DFICTEL.focus(); return false; } 
		*/
		//correo del solicitante		
		if(formulario.MAILRECLA.value!=''){			
		if(!validarEmail(formulario.MAILRECLA.value)){
			formulario.MAILRECLA.focus();
			return false;	
			}
		}

		//alert("11119")
		if (formulario.DFICDIR.value   == '') { alert ('Ingresa su Direccion'); 
		formulario.DFICDIR.focus(); return false; } 
		
		//alert("11110")
		if (formulario.CTPR.value   == '') { alert ('Selecciona su tipo de Prestaciones'); 
		formulario.CTPR.focus(); return false; }
		
		//alert("Mensaje"+formulario.tipoIngreso.value);
		//if(formulario.tipoIngreso.value != null){
		//	if(formulario.tipoIngreso.value != '06' && formulario.tipoIngreso.value != '07' ){
		
				if (formulario.CTIN.value   == '') { alert ('Selecciona su tipo de Ingreso'); 
				formulario.CTIN.focus(); return false; }
		//	}
		//}
		//alert("11112")
		//validacion de email del involucrado		
		if(formulario.DFICMAIL.value!=''){			
		if(!validarEmail(formulario.DFICMAIL.value)){
			formulario.DFICMAIL.focus();
			return false;
			}
	    }
		
		if (formulario.CRED.value   == '') { alert ('Selecciona la Unidad Organica Nivel 01'); 
		formulario.CRED.focus(); return false; }
		
		if (formulario.CCAS.value   == '') { alert ('Selecciona la Unidad Organica Nivel 02'); 
		formulario.CCAS.focus(); return false; }
		
		if (formulario.DFICHEC.value   == '') { alert ('Digite la descripcion de los hechos'); 
		formulario.DFICHEC.focus(); return false; }
		
		return true;
	}

	
	

function registroPortal(formulario) {
		/*
		if (formulario.TELRECLA.disabled=="" && formulario.TELRECLA.value   == '') { alert ('El telefono del solicitante esta vacio'); 
		formulario.TELRECLA.focus(); return false; } 
		*/
		if (formulario.DFICDID.value   == '') { alert ('El DNI esta vacio'); 
		formulario.DFICDID.focus(); return false; } 
		
		if (formulario.DAPEPAT.value   == '') { alert ('El Apellido Paterno esta vacio'); 
		formulario.DAPEPAT.focus(); return false; } 
		
		if (formulario.DAPEMAT.value   == '') { alert ('La Apellido Materno esta vacio'); 
		formulario.DAPEMAT.focus(); return false; }
		
		if (formulario.DNOM.value   == '') { alert ('El Nombre esta vacio'); 
		formulario.DNOM.focus(); return false; } 
		
		if (formulario.DDICSEX.value   == '') { alert ('Seleccione el Sexo'); 
		formulario.DDICSEX.focus(); return false; } 
		
		if (formulario.NFICEDA.value   == '') { alert ('Ingresa su Edad'); 
		formulario.NFICEDA.focus(); return false; } 
		
		if (formulario.DFICTEL.value   == '') { alert ('El Tel�fono esta vacio'); 
		formulario.DFICTEL.focus(); return false; } 
		//correo del solicitante		
		if(formulario.MAILRECLA.value!=''){			
		if(!validarEmail(formulario.MAILRECLA.value)){
			formulario.MAILRECLA.focus();
			return false;	
			}
		}
				
		if (formulario.DFICDIR.value   == '') { alert ('Ingresa su Direccion'); 
		formulario.DFICDIR.focus(); return false; } 
		//validacion de email del involucrado		
		if(formulario.DFICMAIL.value!=''){			
		if(!validarEmail(formulario.DFICMAIL.value)){
			formulario.DFICMAIL.focus();
			return false;
			}
	    }
		//
		if (formulario.DFICHEC.value   == '') { alert ('Digite la descripcion de los hechos'); 
		formulario.DFICHEC.focus(); return false; }  
		
	    
	    return true;           //cambiar por return true para ejecutar la accion del formulario

	    
	}

function validacionLibroRecla(formulario) {
	
	if (formulario.DNOM.value   == '') { alert ('El Nombre esta vacio'); 
	formulario.DNOM.focus(); return false; } 
	
	if (formulario.DAPEPAT.value   == '') { alert ('El Apellido Paterno esta vacio'); 
	formulario.DAPEPAT.focus(); return false; } 
	
	if (formulario.DAPEMAT.value   == '') { alert ('La Apellido Materno esta vacio'); 
	formulario.DAPEMAT.focus(); return false; }
	
	
	//direc
	if (formulario.DFICDIR.value   == '') { alert ('El Domicilio esta vacio'); 
	formulario.DFICDIR.focus(); return false; }	
	
	if (formulario.DFICDID.value   == '') { alert ('El DNI esta vacio'); 
	formulario.DFICDID.focus(); return false; } 
	
	
	//telefono
	if (formulario.DFICTEL.value   == '') { alert ('El Teléfono esta vacio'); 
	formulario.DFICTEL.focus(); return false; }	
	
	//correo del solicitante		
	if(formulario.DFICMAIL.value!=''){			
	if(!validarEmail(formulario.DFICMAIL.value)){
		formulario.DFICMAIL.focus();
		return false;	
		}
	}else{
		alert ('El Mail esta vacio'); 
		formulario.DFICMAIL.focus(); return false;
	}
			
	if (formulario.CCAS.value   == '') { alert ('Debe elegir un Centro Asistencial'); 
	formulario.CCAS.focus(); return false; }	

	//Descripcion
	if (formulario.DFICHEC.value   == '') { alert ('Digite la descripcion del reclamo'); 
	formulario.DFICHEC.focus(); return false; }  
	
	
	
	
    return true;

    
}

function cambioCtin(){
	if(document.f.CTIN.value == '10'){
		
		document.f.tipoIngreso[0].checked = false;
		document.f.tipoIngreso[0].disabled=true;
		document.f.tipoIngreso[1].checked = false;	
		document.f.tipoIngreso[1].disabled=true;
		
		document.f.tipoPersona[0].checked = false;
		document.f.tipoPersona[0].disabled=true;
		document.f.tipoPersona[1].checked = false;	
		document.f.tipoPersona[1].disabled=true;
		
		document.f.tipoSector[0].checked = false;
		document.f.tipoSector[0].disabled=true;
		document.f.tipoSector[1].checked = false;	
		document.f.tipoSector[1].disabled=true;
	}else{
		
		document.f.tipoIngreso[0].disabled=false;
		document.f.tipoIngreso[1].disabled=false;
		

	}
}