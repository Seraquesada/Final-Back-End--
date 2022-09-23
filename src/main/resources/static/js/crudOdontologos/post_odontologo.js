window.addEventListener("load",function(){

    const form = document.querySelector("#add_new_odontologo");
    const urlApi = "http://localhost:8080/odontologos"

    const nombre = document.querySelector('#inputNombre');
    const apellido = document.querySelector('#inputApellido');
    const matricula = document.querySelector('#inputMatricula');

    form.addEventListener('submit', (e) => {
        e.preventDefault();

        const infoOdontologo={
            nombre:nombre.value,
            apellido:apellido.value,
            matricula:matricula.value
        }
        postOdontologos(infoOdontologo)
        form.reset()
        })

        const postOdontologos = infoOdontologo => {

        const configuraciones = {
            method: 'POST',
            body: JSON.stringify(infoOdontologo),
            headers: {
                    "Content-Type": 'application/json'
                    }
                };
        fetch(urlApi,configuraciones)
        .then(res => res.json())
        .then(data =>
            {
          let successAlert = '<div class="alert alert-success alert-dismissible">' +
                                '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                             '<strong></strong> Odontologo agregado </div>'
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
