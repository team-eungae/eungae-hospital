function loadAppointments() {
    var selectDate = document.getElementById('date').value;
    fetch('/appointments?selectDate=' + selectDate)
        .then(response => response.json())
        .then(appointments => {
            updateAppointmentsTable(appointments);
        })
        .catch(error => console.error('Error:', error));
}

function updateAppointmentsTable(appointments) {
    var tableBody = document.getElementById('appointment-list-body');
    tableBody.innerHTML = '';
    appointments.forEach(appointment => {
        var row = `<tr>
                    <td class="tb-center">${appointment.appointmentSeq}</td>
                    <td class="tb-center">${appointment.childrenName}</td>
                    <td class="tb-center">${appointment.age}</td>
                    <td class="tb-center">${appointment.appointmentHourMinute}</td>
                    <td class="tb-center">${appointment.doctor}</td>
 
                   </tr>`;
        tableBody.innerHTML += row;
    });
}

document.addEventListener('DOMContentLoaded', function () {
    loadAppointments();
    document.getElementById('date').addEventListener('change', loadAppointments);
});