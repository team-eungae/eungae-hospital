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
        var formattedTime = appointment.appointmentHourMinute.substring(0, 2) + ":" + appointment.appointmentHourMinute.substring(2);

        var row = `<tr>
                    <td class="tb-center">${appointment.appointmentSeq}</td>
                    <td class="tb-center">${appointment.childrenName}</td>
                    <td class="tb-center">${appointment.age}</td>
                    <td class="tb-center">${formattedTime}</td>
                    <td class="tb-center">${appointment.doctor}</td>
                    <td class="tb-center"></td>
                    <td class="tb-center">${appointment.note}</td>
                    <td class="tb-center">
                        <select class="visit-status-select" onchange="updateVisitStatus(this, ${appointment.id})">
                            <option value="방문" ${appointment.status === '방문' ? 'selected' : ''}>방문</option>
                            <option value="취소" ${appointment.status === '취소' ? 'selected' : ''}>취소</option>
                            <option value="완료" ${appointment.status === '완료' ? 'selected' : ''}>완료</option>
                        </select>
                    </td>

                   </tr>`;
        tableBody.innerHTML += row;
    });
}

document.addEventListener('DOMContentLoaded', function () {
    loadAppointments();
    document.getElementById('date').addEventListener('change', loadAppointments);
});