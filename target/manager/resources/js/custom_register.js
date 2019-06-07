$(document).ready(function () {
    $('#pass').keyup(function () {
        validateForm();
    })

    $('#re-pass').keyup(function () {
        validateForm();
    })
    validateForm();
})


function validateForm() {
    var pass = $('#pass').val();
    var re_pass = $('#re-pass').val();
    if (pass == re_pass && pass.length > 5) {
        console.log(true);
    } else {
        console.log(false);
    }
}