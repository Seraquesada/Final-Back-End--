window.addEventListener("load",function(){
       const form = document.querySelector("#update_odontologo");

       form.addEventListener('submit', e=>{
            e.preventDefault();

            const id = document.querySelector("#odontologo_id");
            const inputNombre = document.querySelector('#inputNombre');
            const inputApellido = document.querySelector('#inputApellido');
            const inputMatricula = document.querySelector('#inputMatricula');
            const infoOdontologo= {
                id: id.value,
                nombre:inputNombre.value,
                apellido: inputApellido.value,
                matricula: inputMatricula.value
            }

            const url = '/odontologos';
            const settings = {
                            method: 'PUT',
                            headers: {
                                'Content-Type': 'application/json',
                            },
                            body: JSON.stringify(infoOdontologo)
                        }
            fetch(url,settings)
            .then(response => response.json())
            .then(data=>data)
       })
})
      function findBy(id){

              const urlApi = "/odontologos/" + id;
              const configuraciones = {method: 'GET'}
             fetch(urlApi,configuraciones)
             .then(res=> res.json())
             .then(data => {
             let odontologo = data;

             document.querySelector("#odontologo_id").value = odontologo.id;
             document.querySelector('#inputNombre').value= odontologo.nombre ;
             document.querySelector('#inputApellido').value = odontologo.apellido ;
             document.querySelector('#inputMatricula').value = odontologo.matricula;
             document.querySelector('#div_odontologo_updating').style.display = "block";
             })
             .catch(error => {
                           console.log("Error: " + error);
                       })

     }
