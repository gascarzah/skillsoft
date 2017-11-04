/*
devuelve valores:
0: son iguales.
<0: la primera fecha es menor.
>0: la primera fecha es mayor.
*/
function validarFecha(date){
var today = formattedDate(new Date());

return compare_dates(today, date)
}


function formattedDate(date) {
    var d = new Date(date || Date.now()),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [day, month, year].join('/');
}


function compare_dates(fecha, fecha2)  
{  
  var xDay=fecha.substring(0, 2);
  var xMonth=fecha.substring(3, 5);
  var xYear=fecha.substring(6,10);  
  
  var yMonth=fecha2.substring(3, 5);  
  var yDay=fecha2.substring(0, 2);  
  var yYear=fecha2.substring(6,10);  
  if (xYear> yYear)  
  {  
      return(true);  
  }else{  
    if (xYear == yYear)  
    {   
      if (xMonth> yMonth)  
      {  
          return(true) ; 
      }else{   
        if (xMonth == yMonth)  
        {  
          if (parseInt(xDay) >= parseInt(yDay))  
            return(true);  
          else  
            return(false);  
        }  
        else  
          return(false);  
      }  
    }  
    else  
      return(false);  
  }  
}  

// Date Validation and Format Javascript
// copyright 11th June 2007 by Stephen Chapman
// permission to use this Javascript on your web page is granted
// provided that all of the code in this script (including these
// comments) is used without any alteration
// you may swap the 12 and 31 around if you want mm/dd instead of dd/mm

function dtval(d,e) {
	var pK = e ? e.which : window.event.keyCode;
	if (pK == 8) {d.value = substr(0,d.value.length-1); return;}
	var dt = d.value;
	var da = dt.split('/');
	for (var a = 0; a < da.length; a++) {if (da[a] != +da[a]) da[a] = da[a].substr(0,da[a].length-1);}
	if (da[0] > 31) {da[1] = da[0].substr(da[0].length-1,1);da[0] = '0'+da[0].substr(0,da[0].length-1);}
	if (da[1] > 12) {da[2] = da[1].substr(da[1].length-1,1);da[1] = '0'+da[1].substr(0,da[1].length-1);}
	if (da[2] > 2100) da[1] = da[2].substr(0,da[2].length-1);
	dt = da.join('/');
	if (dt.length == 2 || dt.length == 5) dt += '/';
	d.value = dt;
}
