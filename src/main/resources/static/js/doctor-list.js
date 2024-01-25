function alertDelete() {
    if (alert("예약이 남아있는 상태라 삭제가 되지 않습니다.")) {
        // 확인 버튼을 눌렀을 때 서버로 요청 전송
        document.getElementById("deleteForm");
    } else {
        // 취소 버튼을 눌렀을 때 아무 동작 없음
    }
}