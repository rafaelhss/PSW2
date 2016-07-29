function formatar(mascara, documento) {
  var i = documento.value.length;
  var saida = mascara.substring(0,1);
  var texto = mascara.substring(i)
  
  if (texto.substring(0,1) != saida) {
            documento.value += texto.substring(0,1);
  }
  
}

function validaCampos() {
    if (document.forms[0].sNomeForm.value == "") {
        alert("Informe o nome !");
        document.forms[0].sNomeForm.focus();
        return false;
    } 

    if (document.forms[0].sDtNascForm.value == "") {
        alert("Informe a data de nascimento !");
        document.forms[0].sDtNascForm.focus();
        return false;
    }                
}

function validaCamposPrevisao(servlet, fr ) {
    if (document.forms[0].sDtPrevForm.value == "") {
        alert("Informe a data referência da previsão !");
        document.forms[0].sDtPrevForm.focus();
        return false;
    } 

    if (document.forms[0].signoForm.value == "") {
        alert("Informe o signo referência da previsão !");
        document.forms[0].signoForm.focus();
        return false;
    }
    
    if (document.forms[0].sPrevisaoForm.value == "") {
        alert("Informe o conteúdo da previsão !");
        document.forms[0].sPrevisaoForm.focus();
        return false;
    }    
    
    fr.action=servlet;
    fr.submit();
}

function redireciona(param){
  location.href=param;
}

function call( servlet, fr ){
    fr.action=servlet;
    fr.submit();
}