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
                            <td class="tb-center ${appointment.status === 'APPOINTMENT' ? '' : 'visited'}">${appointment.note}</td>
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

        // 모든 행에 이벤트 리스너 추가
        document.querySelectorAll('.appointment-record').forEach(rowElement => {
            rowElement.addEventListener('click', function(event) {
                // 폼 제출 버튼 클릭 시 모달 표시 방지
                if (!event.target.matches('button')) {
                    let appointmentIndex = Array.from(rowElement.parentNode.children).indexOf(rowElement);
                    showModal(appointments[appointmentIndex]);
                }
            });
        });
    }
}
function formatTime(hourMinute) {
    if (hourMinute && hourMinute.length === 4) {
        let hours = hourMinute.substring(0, 2);
        let minutes = hourMinute.substring(2);
        return `${hours}:${minutes}`;
    } else {
        return '유효하지 않은 시간';
    }
}
function showModal(appointment) {
    // 모달 요소에 데이터를 채웁니다.
    document.getElementById('modalChildrenName').innerHTML = '<strong>이름</strong>: ' + appointment.childrenName;
    document.getElementById('modalGuardianName').innerHTML = '<strong>보호자 이름</strong>: ' + appointment.guardianName;
    document.getElementById('modalPhoneNumber').innerHTML = '<strong>보호자 전화번호</strong>: ' + appointment.phoneNumber;
    document.getElementById('modalProfileImage').src = appointment.profileImage || '/img/favicon-96x96.png';
    document.getElementById('modalGender').innerHTML = '<strong>성별</strong>: ' + formatGender(appointment.gender);
    document.getElementById('modalBirthdate').innerHTML = '<strong>생년월일</strong>: ' + formatBirthdate(appointment.age);
    document.getElementById('modalTime').innerHTML = '<strong>시간</strong>: ' + formatTime(appointment.appointmentHourMinute);
    document.getElementById('modalDoctor').innerHTML = '<strong>담당의</strong>: ' + appointment.doctor;
    document.getElementById('modalNote').innerHTML = '<strong>증상</strong>: ' + appointment.note;

    // 모달을 표시합니다.
    document.getElementById('appointmentDetailsModal').style.display = 'block';
}

// 모달 닫기 버튼에 대한 이벤트 리스너 추가
document.querySelector('.close').addEventListener('click', function() {
    document.getElementById('appointmentDetailsModal').style.display = 'none';
});
document.addEventListener('DOMContentLoaded', function () {
    loadAppointments();
    document.getElementById('date').addEventListener('change', loadAppointments);

});