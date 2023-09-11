function enviarDatos() {
  console.log("enviarDatos");
    const datosPaciente = {
      nombre: document.getElementById('nombre').value,
      apellido: document.getElementById('apellido').value,
      dni: document.getElementById('dni').value,
      fechaDeIngreso: document.getElementById('fechaDeIngreso').value,
      domicilio: {
        calle: document.getElementById('calle').value,
        numero: document.getElementById('numero').value,
        localidad: document.getElementById('localidad').value,
        provincia: document.getElementById('provincia').value,
      }
    }
  
    const urlServicio = 'http://localhost:8081/pacientes/registrar';
  
    const opciones = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(datosPaciente)
    };
  
    fetch(urlServicio, opciones)
      .then(response => {
        if (!response.ok) {
          throw new Error('Error en la solicitud al servicio');
        }
        return response.json();
      })
      .then(data => {
        const id_response = "El ID del paciente " + data.nombre + " " + data.apellido + " es " + data.id;
        alert(id_response);
        // Puedes hacer más cosas con la respuesta aquí si es necesario
      })
      .catch(error => {
        console.error('Error:', error);
      });
    }