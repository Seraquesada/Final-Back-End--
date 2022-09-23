window.addEventListener("load",function(){
       const form = document.querySelector("#update_paciente");

       form.addEventListener('submit', e=>{
                e.preventDefault();
                const id=document.querySelector("#odontologo_id");
                const urlApi = "/pacientes"
                const nombre = document.querySelector('#inputNombre');
                const apellido = document.querySelector('#inputApellido');
                const email = document.querySelector('#inputEmail');
                const dni = document.querySelector('#inputDNI');
                const fecha = document.querySelector('#fechaAlta');
                const calle = document.querySelector('#inputCalle');
                const numero = document.querySelector('#inputAltura');
                const localidad = document.querySelector('#inputLocalidad');
                const provincia = document.querySelector('#inputProvincia');

                const infoPaciente={
                            id:document.querySelector("#paciente_id").value,
                            nombre:nombre.value,
                            apellido:apellido.value,
                            email:email.value,
                            dni:parseInt(dni.value),
                            fecha:fecha.value,
                                domicilio:{
                                    id:document.querySelector('#paciente_domicilio_id').value,
                                    calle: calle.value,
                                    numero:parseInt(numero.value),
                                    localidad:localidad.value,
                                    provincia:provincia.value
                            }
                        }
            const url = '/pacientes';
            const settings = {
                            method: 'PUT',
                            headers: {
                                'Content-Type': 'application/json',
                            },
                            body: JSON.stringify(infoPaciente)
                        }
            fetch(url,settings)
            .then(response => response.json())
            .then(data=>data)
       })
})
      function findBy(id){

              const urlApi = "/pacientes/" + id;
              const configuraciones = {method: 'GET'}
             fetch(urlApi,configuraciones)
             .then(res=> res.json())
             .then(data => {
             let odontologo = data;
             document.querySelector("#paciente_id").value = paciente.id;
             document.querySelector('#inputNombre').value= odontologo.nombre ;
             document.querySelector('#inputApellido').value = odontologo.apellido ;
             document.querySelector('#inputEmail').value = odontologo.email;
             document.querySelector('#inputDNI').value = odontologo.dni;
             document.querySelector('#fechaAlta').value = odontologo.fechaAlta;

             document.querySelector('#paciente_domicilio_id').value = odontologo.domicilio.id;
             document.querySelector('#inputCalle').value = odontologo.domicilio.calle;
             document.querySelector('#inputAltura').value = odontologo.domicilio.numero;
             document.querySelector('#inputLocalidad').value = odontologo.domicilio.localidad;
             document.querySelector('#inputProvincia').value = odontologo.domicilio.provincia;
             document.querySelector('#div_odontologo_updating').style.display = "block";
             })
             .catch(error => {
                           alert("Error: " + error);
                       })

     }