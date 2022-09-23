
  window.addEventListener('load', function () {

      (function(){
      const url = '/odontologos';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {

       for(odontologo of data) {

            const table = document.getElementById("odontologoTableBody");
            const odontologRow =table.insertRow();
            let tr_id = 'tr_' + odontologo.id;

            odontologRow.id = tr_id;

            let deleteButton = '<button type="button"  onclick="deleteOdontologo('+odontologo.id+')"' +
                                  ' id=' + '\"'+ odontologo.id +
                                   '\"' +'class="btn-danger" >' + "Borrar" +
                                '</button>';


            let updateButton = '<button' +
                                  ' id=' + '\"' + odontologo.id + '\"' +
                                  ' type="button" onclick="findBy('+odontologo.id+')" class="btn btn_update btn-info btn_id">' + "Actualizar" +
                                '</button>';

            odontologRow.innerHTML =
                    '<td class=\"td_id\">' + odontologo.id + '</td>' +
                    '<td class=\"td_apellido\">' + odontologo.apellido.toUpperCase() + '</td>' +
                    '<td class=\"td_nombre\">' + odontologo.nombre.toUpperCase() + '</td>'+
                    '<td class=\"td_matricula\">' + odontologo.matricula.toUpperCase() + '</td>'+
                    '<td class=\"updateButton\">' + updateButton+ '</td>' +
                    '<td class=\"deleteButton\">' + deleteButton+ '</td>';
            }
        })
})
    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/odontologList.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })
})