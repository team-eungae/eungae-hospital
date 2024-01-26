function loadAppointments() {
    var selectDate = document.getElementById('date').value;
    fetch('/appointments?selectDate=' + selectDate)
        .then(response => response.json())
        .then(appointments => {
            updateAppointmentsTable(appointments);
        })
        .catch(error => console.error('Error:', error));
}
function formatBirthdate(dateStr) {
    if (dateStr && dateStr.length === 8) {
        var year = dateStr.substring(0, 4);
        var month = dateStr.substring(4, 6);
        var day = dateStr.substring(6, 8);

        return `${year}년 ${month}월 ${day}일`;
    } else {
        return '유효하지 않은 데이터';
    }
}
function updateAppointmentsTable(appointments) {
    var tableBody = document.getElementById('appointment-list-body');
    tableBody.innerHTML = '';

    // //시간 순으로 정렬.
    // appointments.sort((a,b) => {
    //     const timeComparison = a.appointmentHourMinute.localeCompare(b.appointmentHourMinute);
    //     if (timeComparison !==0) {
    //         return timeComparison;
    //     }
    //     return a.appointmentSeq - b.appointmentSeq;
    // })

    if (appointments.length === 0) {
        // 예약이 없을 경우의 메시지를 표시합니다.
        tableBody.innerHTML = '<tr><td colspan="8" class="tb-center">접수된 예약이 없습니다.</td></tr>';
    } else {
        let timeOrder = 1;
        appointments.forEach(appointment => {
            var formattedTime = appointment.appointmentHourMinute.substring(0, 2) + ":" + appointment.appointmentHourMinute.substring(2);
            var formattedBirthdate = formatBirthdate(appointment.age);
            var row = `<tr>
                    <td class="tb-center">${timeOrder++}</td>
                    <td class="tb-center">${appointment.childrenName}</td>
                    <td class="tb-center">${appointment.gender}</td>
                    <td class="tb-center">${formattedBirthdate}</td>
                    <td class="tb-center">${formattedTime}</td>
                    <td class="tb-center">${appointment.doctor}</td>
                    <td class="tb-center">${appointment.note}</td>
                    <td class="tb-center">
                        <button class="visited-check">
                            방문
                        </button>
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