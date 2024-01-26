function alertDelete(currentAppointmentSize,event) {
    if (currentAppointmentSize === 0) {
        var userConfirmed = confirm("해당 의사를 삭제하시겠습니까?");
        if (userConfirmed) {
            return true;
        } else {
            event.preventDefault();
        }
    } else if (currentAppointmentSize > 0) {
        alert("예약이 남아있는 상태라 삭제가 되지 않습니다.");
        return false;
    }
}
