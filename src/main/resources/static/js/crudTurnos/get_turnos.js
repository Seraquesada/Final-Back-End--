window.addEventListener('load', function () {
    (function(){
      const url = '/turnos';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {

          for(turno of data) {

            const table = document.getElementById("turnosTableBody");
            const turnoRow =table.insertRow();
            let tr_id = 'tr_' + turno.id;
            turnoRow.id = tr_id;

            let deleteButton = '<button class="btn-danger" onclick="deleteTurno('+turno.id+')"' +' id=' + '\"' + 'btn_delete_' + turno.id + '\">' + "Borrar" + '</button>';

            let updateButton = '<button' +
                                  ' id=' + '\"' + 'btn_id_' + turno.id + '\"' +
                                  ' type="button" onclick="findBy('+turno.id+')" class="btn btn-info btn_id">' +"Actualizar" +
                                '</button>';

            turnoRow.innerHTML =
            '<td class=\"td_id\">' + turno.id + '</td>' +
            '<td class=\"td_nombre\">' + turno.odontologo_id+ '</td>'+
            '<td class=\"td_nombre\">' + turno.paciente_id + '</td>'+
            '<td class=\"td_fecha\">' + turno.fecha.toString() + '</td>'+
            '<td class=\"updateButton\">' + updateButton + '</td>'+
            '<td class=\"deleteButton\">' + deleteButton + '</td>';
            }
        })
   })

    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/turnosList.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })
})