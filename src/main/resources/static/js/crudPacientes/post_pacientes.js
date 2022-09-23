window.addEventListener("load",function(){

    const form = document.querySelector("#add_new_paciente");
    const urlApi = "http://localhost:8080/pacientes"
    const nombre = document.querySelector('#inputNombre');
    const apellido = document.querySelector('#inputApellido');
    const email = document.querySelector('#inputEmail');
    const dni = document.querySelector('#inputDNI');
    const fecha = document.querySelector('#fechaAlta');
    const calle = document.querySelector('#inputCalle');
    const numero = document.querySelector('#inputAltura');
    const localidad = document.querySelector('#inputLocalidad');
    const provincia = document.querySelector('#inputProvincia');

    form.addEventListener('submit', (e) => {
        e.preventDefault();

        const infoPaciente={
            nombre:nombre.value,
            apellido:apellido.value,
            email:email.value,
            dni:parseInt(dni.value),
            fecha:fecha.value,
                domicilio:{
                    calle: calle.value,
                    numero:parseInt(numero.value),
                    localidad:localidad.value,
                    provincia:provincia.value
            }
        }
        // llamo a la funcion de registrar pacientes
        postPaciente(infoPaciente)
        form.reset()
    })

     function postPaciente (infoPaciente) {
        const configuraciones = {
            method: 'POST',
            body: JSON.stringify(infoPaciente),
            headers: {
                'Content-Type': 'application/json'
            }
        };
          fetch(urlApi,configuraciones)
          .then(response => response.json())
          .then(data =>{
            let successAlert = '<div class="alert alert-success alert-dismissible">' +
                               '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                               '<strong></strong> Paciente agregado </div>'

            document.querySelector('#response').innerHTML = successAlert;
            document.querySelector('#response').style.display = "block";
          })
            .catch(error => {
            let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                     '<strong> Error intente nuevamente</strong> </div>'

             document.querySelector('#response').innerHTML = errorAlert;
             document.querySelector('#response').style.display = "block";
             resetUploadForm();})

    }
})