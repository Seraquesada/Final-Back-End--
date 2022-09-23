 function deletePaciente(id){
        const url = "/pacientes/" + id
        const configuraciones = {
                    method: 'DELETE'
                  };
        fetch(url,configuraciones)
        .then(res=> res.json())
        .then(data=> data);

        let row_id = "#tr_" + id;
        document.querySelector(row_id).remove();

}