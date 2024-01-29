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

function updateAppointmentsTable(appointments) {
    let tableBody = document.getElementById('appointment-list-body');
    tableBody.innerHTML = '';

    if (appointments.length === 0) {
        // 예약이 없을 경우의 메시지를 표시합니다.
        tableBody.innerHTML = '<tr><td colspan="8" class="tb-center">접수된 예약이 없습니다.</td></tr>';
    } else {
        let timeOrder = 1;
        appointments.forEach(appointment => {
            let formattedTime = appointment.appointmentHourMinute.substring(0, 2) + ":" + appointment.appointmentHourMinute.substring(2);
            let formattedBirthdate = formatBirthdate(appointment.age);

            if (appointment.status === 'APPOINTMENT') {
                let row = `<tr>
                            <td class="tb-center">${timeOrder++}</td>
                            <td class="tb-center">${appointment.childrenName}</td>
                            <td class="tb-center">${appointment.gender}</td>
                            <td class="tb-center">${formattedBirthdate}</td>
                            <td class="tb-center">${formattedTime}</td>
                            <td class="tb-center">${appointment.doctor}</td>
                            <td class="tb-center">${appointment.note}</td>
                            <td class="tb-center">
                                    <form method="get" action="/hospital/appointments/${appointment.appointmentSeq}/status/visit">
                                        <button type="submit" class="visited-check" id="visited-btn">
                                            방문
                                        </button>
                                    </form>
                                </td>
                               </tr>`;

                tableBody.innerHTML += row;
            } else {
                let row = `<tr>
                            <td class="tb-center visited">${timeOrder++}</td>
                            <td class="tb-center visited">${appointment.childrenName}</td>
                            <td class="tb-center visited">${appointment.gender}</td>
                            <td class="tb-center visited">${formattedBirthdate}</td>
                            <td class="tb-center visited">${formattedTime}</td>
                            <td class="tb-center visited">${appointment.doctor}</td>
                            <td class="tb-center visited">${appointment.note}</td>
                            <td class="tb-center visited">
                                <form method="get" action="/hospital/appointments/${appointment.appointmentSeq}/status/restore">
                                    <button type="submit" class="visited-check" id="visited-rollback-btn">
                                        취소
                                    </button>
                                </form>
                            </td>
                           </tr>`;

                tableBody.innerHTML += row;
            }
        });
    }
}

document.addEventListener('DOMContentLoaded', function () {
    loadAppointments();
    document.getElementById('date').addEventListener('change', loadAppointments);
});