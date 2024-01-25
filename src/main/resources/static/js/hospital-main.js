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
        url: '/api/hospital/doctors/status?doctorSeq=' + doctorSeq,
        contentType: 'application/json',
        success: function (status) {
            let statusContainer = $('#doctor-status-' + doctorSeq);
            statusContainer.empty();
            if (status === 'ON') {
                // 진료가능 상태일 때 아이콘과 텍스트 변경
                statusContainer.append('<span class="badge bg-green text-light">ON</span>');
            } else {
                // 자리비움 상태일 때 아이콘과 텍스트 변경
                statusContainer.append('<span class="badge bg-dark text-light">OFF</span>');
            }
        },
        error: function() {
            alert("의사선생님 상태 변경에 실패했습니다.");
        }
    })
})

