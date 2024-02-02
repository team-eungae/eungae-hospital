function loadAppointments() {
    let selectDate = document.getElementById('date').value;
    fetch('/appointments?selectDate=' + selectDate)
        .then(response => response.json())
        .then(appointments => {
            updateAppointmentsTable(appointments);
        })
        .catch(error => console.error('Error:', error));
}

function formatBirthdate(dateStr) {
    if (dateStr && dateStr.length === 8) {
        let year = dateStr.substring(0, 4);
        let month = dateStr.substring(4, 6);
        let day = dateStr.substring(6, 8);

        return `${year}년 ${month}월 ${day}일`;
    } else {
        return '유효하지 않은 데이터';
    }
}
function formatGender(gender) {
    switch(gender) {
        case 'Male':
            return '남자';
        case 'Female':
            return '여자';
        default:
            return '정보 없음';
    }
}
function updateAppointmentsTable(appointments) {
    let tableBody = document.getElementById('appointment-list-body');
    tableBody.innerHTML = '';

    if (appointments.length === 0) {
        tableBody.innerHTML = '<tr><td colspan="8" class="tb-center">접수된 예약이 없습니다.</td></tr>';
    } else {
        let timeOrder = 1;
        appointments.forEach(appointment => {
            let formattedTime = appointment.appointmentHourMinute.substring(0, 2) + ":" + appointment.appointmentHourMinute.substring(2);
            let formattedBirthdate = formatBirthdate(appointment.age);
            let formattedGender = formatGender(appointment.gender);
            let row = `<tr class="appointment-record">
                            <td class="tb-center ${appointment.status === 'APPOINTMENT' ? '' : 'visited'}">${timeOrder++}</td>
                            <td class="tb-center ${appointment.status === 'APPOINTMENT' ? '' : 'visited'}">${appointment.childrenName}</td>
                            <td class="tb-center ${appointment.status === 'APPOINTMENT' ? '' : 'visited'}">${formattedGender}</td>
                            <td class="tb-center ${appointment.status === 'APPOINTMENT' ? '' : 'visited'}">${formattedBirthdate}</td>
                            <td class="tb-center ${appointment.status === 'APPOINTMENT' ? '' : 'visited'}">${formattedTime}</td>
                            <td class="tb-center ${appointment.status === 'APPOINTMENT' ? '' : 'visited'}">${appointment.doctor}</td>
                            <td class="tb-center ${appointment.status === 'APPOINTMENT' ? '' : 'visited'}">${appointment.guardianName}</td>
                            <td class="tb-center">
                                <form method="get" action="/hospital/appointments/${appointment.appointmentSeq}/status/${appointment.status === 'APPOINTMENT' ? 'visit' : 'restore'}">
                                    <button type="submit" class="visited-check" id="${appointment.status === 'APPOINTMENT' ? 'visited-btn' : 'visited-rollback-btn'}">
                                        ${appointment.status === 'APPOINTMENT' ? '방문' : '취소'}
                                    </button>
                                </form>
                            </td>
                        </tr>`;

            tableBody.innerHTML += row;
        });

    }
}

document.addEventListener('DOMContentLoaded', function () {
    loadAppointments();
    document.getElementById('date').addEventListener('change', loadAppointments);

});