function selectImage() {
    document.getElementById('file-input').click();
}

function previewImage(event) {
        var fileInput = event.target;
        var file = fileInput.files[0];

        if (file) {
            var reader = new FileReader();

            reader.onload = function (e) {
                var profileImageContainer = document.getElementById('profilePictureContainer');
                var imgElement = profileImageContainer.querySelector('img');

                // 이미지 변경
                imgElement.src = e.target.result;
            };

            // 파일을 읽어오기
            reader.readAsDataURL(file);
        }
    }