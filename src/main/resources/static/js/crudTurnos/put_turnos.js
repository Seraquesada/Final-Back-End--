window.addEventListener("load",function(){
       const form = document.querySelector("#update_turno");

       form.addEventListener('submit', e=>{
            e.preventDefault();

            const turno_id=document.querySelector("#turno_id");
            const inputPaciente = document.querySelector('#inputPaciente');
            const inputOdontologo = document.querySelector('#inputOdontologo');
            const inputFecha = document.querySelector('#inputFecha');

            const infoTurno= {
                id: turno_id.value,
                odontologo_id: inputOdontologo.value,
                paciente_id:inputPaciente.value,
                fecha: inputFecha.value
            }

            console.log(infoTurno)

            const url = 'http://localhost:8080/turnos';
            const settings = {
                            method: 'PUT',
                            headers: {
                                'Content-Type': 'application/json',
                            },
                            body: JSON.stringify(infoTurno)
                        }
            fetch(url,settings)
            .then(response => response.json())
            .then(data=>data)
       })
})
      function findBy(id){

              const urlApi = "http://localhost:8080/turnos/" + id;
              const configuraciones = {method: 'GET'}
             fetch(urlApi,configuraciones)
             .then(res=> res.json())
             .then(data => {
             let turno = data;

             document.querySelector("#turno_id").value = turno.id;
             document.querySelector('#inputPaciente').value = turno.paciente_id ;
             document.querySelector('#inputOdontologo').value = turno.odontologo_id;
             document.querySelector('#inputFecha').value = turno.fecha;
             document.querySelector('#div_turno_updating').style.display = "block";
             console.log(turno)
             })
             .catch(error => {
                           console.log(error);
                       })

     }