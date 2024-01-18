function toggleStatus(button) {
    // 해당 버튼 내의 .badge 요소들을 찾습니다.
    var badgeElements = button.querySelectorAll('.badge');

    // 각각의 .badge 요소의 텍스트와 클래스를 토글합니다.
    badgeElements.forEach(function(badgeElement) {
        var currentText = badgeElement.innerText;
        badgeElement.innerText = currentText === 'ON' ? 'OFF' : 'ON';
        badgeElement.classList.toggle('bg-dark');
        badgeElement.classList.toggle('bg-green');
    });
}