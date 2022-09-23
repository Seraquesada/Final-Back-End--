window.addEventListener('load', function () {
    (function(){

      const url = '/pacientes';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {

        for(paciente of data)  {
        

            const table = document.getElementById("pacientesTableBody");
            const pacienteRow =table.insertRow();
            let tr_id = 'tr_' + paciente.id;
            pacienteRow.id = tr_id;


            let deleteButton = '<button type="button"  onclick="deletePaciente('+paciente.id+')"' +
                                  ' id=' + '\"'+ paciente.id +
                                   '\"' +'class="btn-danger" >' + "Borrar" +
                                '</button>';
            let updateButton = '<button' +
                                  ' id=' + '\"' + paciente.id + '\"' +
                                  ' type="button" onclick="findBy('+paciente.id+')" class="btn btn_update btn-info btn_id">' + "Actualizar" +
                                '</button>';



            pacienteRow.innerHTML =
                    '<td class=\"td_id\">' + paciente.id + '</td>' +
                    '<td class=\"td_apellido\">' + paciente.apellido.toUpperCase() + '</td>' +
                    '<td class=\"td_nombre\">' + paciente.nombre.toUpperCase() + '</td>'+
                    '<td class=\"td_nombre\">' + paciente.email.toUpperCase() + '</td>'+
                    '<td class=\"td_matricula\">' + paciente.dni + '</td>'+
                    '<td class=\"td_fecha\">' + paciente.fecha.toString() + '</td>'+
                    '<td class=\"td_domicilio\">' + paciente.domicilio.id+ '</td>' +
                    '<td class=\"updateButton\">' + updateButton+ '</td>' +
                    '<td class=\"deleteButton\">' + deleteButton+ '</td>';
          }
      })
  })

    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/pacientesList.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })
})