//현장등록 모달창
const modalOpenButton = document.getElementById("on-site-modal-btn");
const modalCloseButton = document.getElementById("modalCloseButton");
const modal = document.getElementById("on-site-registration");

modalOpenButton.addEventListener("click", () => {
    modal.classList.remove("hidden");
});

modalCloseButton.addEventListener("click", () => {
    modal.classList.add("hidden");
});

const sysdate = new Date();
document.getElementById('date').value = sysdate.toISOString().substring(0, 10);

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
        error: function () {
            alert("의사선생님 상태 변경에 실패했습니다.");
        }
    })
})
