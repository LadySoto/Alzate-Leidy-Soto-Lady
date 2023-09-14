function enviarDatos() {
  
    const datosTurno = {
      pacienteId: document.getElementById('idpaciente').value,
      odontologoId: document.getElementById('idodontologo').value,
      fechaYHora: document.getElementById('fechahora').value
    };
  
    const urlServicio = 'http://localhost:8081/turnos/registrar'
  
    const opciones = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(datosTurno)
    };
  
    fetch(urlServicio, opciones)
      .then(response => {
        if (!response.ok) {
          alert ('Error en la solicitud');
          throw new Error('Error en la solicitud al servicio');
        }
        return response.json();
      })
      .then(data => {
        const id_response = "El turno fue registrado con ID: " + data.id;
        alert(id_response);
        
      })
      .catch(error => {
        console.error('Error:', error);
      });
  }