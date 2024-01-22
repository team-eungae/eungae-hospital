function selectImage() {
    document.getElementById('file-input').click();
}

function previewImage(event) {
    var img = document.getElementById('preview');
    img.src = URL.createObjectURL(event.target.files[0]);
}