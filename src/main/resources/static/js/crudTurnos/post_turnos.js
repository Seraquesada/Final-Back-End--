window.addEventListener("load",function(){

    const form = document.querySelector("#add_new_turno");
    const urlApi = "http://localhost:8080/turnos"
    const inputPaciente = document.querySelector('#inputPaciente');
    const inputOdontologo = document.querySelector('#inputOdontologo');
    const inputFecha = document.querySelector('#inputFecha');

    form.addEventListener('submit', (e) => {
        e.preventDefault();

        const infoTurno={
            paciente_id:inputPaciente.value,
            odontologo_id:inputOdontologo.value,
            fecha:inputFecha.value
        }
        // llamo a la funcion de registrar pacientes

        postTurno(infoTurno)
        form.reset()
    })

     function postTurno (infoTurno) {
        const configuraciones = {
            method: 'POST',
            body: JSON.stringify(infoTurno),
            headers: {
                'Content-Type': 'application/json'
            }
        };
        fetch(urlApi,configuraciones)
        .then(res=> res.json())
        .then(data=>
        {
         let successAlert = '<div class="alert alert-success alert-dismissible">' +
                                '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                             '<strong></strong> Turno agregado </div>'

         document.querySelector('#response').innerHTML = successAlert;
         document.querySelector('#response').style.display = "block";
                  })
          .catch(error => {
             let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                   '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                              '<strong> Error intente nuevamente</strong> </div>'

             document.querySelector('#response').innerHTML = errorAlert;
             document.querySelector('#response').style.display = "block";

             })

     }
})