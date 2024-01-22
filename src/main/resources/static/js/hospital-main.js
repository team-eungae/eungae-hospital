function toggleStatus(button) {
    // 해당 버튼 내의 .badge 요소들을 찾습니다.
    var badgeElements = button.querySelectorAll('.badge');

    // 각각의 .badge 요소의 텍스트와 클래스를 토글합니다.
    badgeElements.forEach(function (badgeElement) {
        var currentText = badgeElement.innerText;
        badgeElement.innerText = currentText === 'ON' ? 'OFF' : 'ON';
        badgeElement.classList.toggle('bg-dark');
        badgeElement.classList.toggle('bg-green');
    });
}

window.onload = function () {

};

const sysdate = new Date();
document.getElementById('date').value = sysdate.toISOString().substring(0, 10);
document.getElementById('date').min = sysdate.toISOString().substring(0, 10);

//선택가능한 최대날짜 설정 다음달 말일까지
var year = sysdate.getFullYear() + 1;
var month = sysdate.getMonth() % 11 + 1;

if (month < 10) {
    month = "0" + month;
}

const nextmonthdate = new Date(year, month, 1);
document.getElementById('date').max = nextmonthdate.toISOString().substring(0, 10);

$('.doctor-on-off').click(function () {
    let doctorSeq = $(this).find('.doctorSeq').val();

    $.ajax({
        type: 'PATCH',
        url: '/api/hospital/doctor/status?doctorSeq=' + doctorSeq,
        contentType: 'application/json',
        success: function (status) {
            let statusContainer = $('#doctor-status-' + doctorSeq);
            statusContainer.empty();
            if (status) {
                // 진료가능 상태일 때 아이콘과 텍스트 변경
                statusContainer.append('<span class="badge bg-green text-light">ON</span>');
            } else {
                // 자리비움 상태일 때 아이콘과 텍스트 변경
                statusContainer.append('<span class="badge bg-dark text-light">OFF</span>');
            }
        },
        error: function () {
            alert("의사선생님 상태 변경에 실패했습니다.");
        }
    })
})