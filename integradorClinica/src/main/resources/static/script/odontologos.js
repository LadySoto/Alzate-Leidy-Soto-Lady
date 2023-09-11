function enviarDatos() {
  
    const datosProfesional = {
      matricula: document.getElementById('matricula').value,
      nombre: document.getElementById('nombre').value,
      apellido: document.getElementById('apellido').value
    };
  
    const urlServicio = 'http://localhost:8081/odontologos/registrar'
  
    const opciones = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(datosProfesional)
    };
  
    fetch(urlServicio, opciones)
      .then(response => {
        if (!response.ok) {
          throw new Error('Error en la solicitud al servicio');
        }
        return response.json();
      })
      .then(data => {
        const id_response = "El ID del odontólogo " + data.nombre + " " + data.apellido + " es " + data.id;
        alert(id_response);
        // Puedes hacer más cosas con la respuesta aquí si es necesario
      })
      .catch(error => {
        console.error('Error:', error);
      });
  }