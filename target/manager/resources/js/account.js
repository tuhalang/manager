$(document).ready(function () {
    $('#update').click(function (e) {

        //Prevent default submission of form
        e.preventDefault();

        //Remove all errors
        $('input').next().remove();
        $.post({
            url: 'api/update_user',
            data: $('form[name=form-account]').serialize(),
            success: function (res) {
                alert("Đã thay đổi thông tin!");
            },
            error: function (e) {
                console.log(e)
            }
        })

    });

    $('#change').click(function (e) {

        var old_pass = $('#old-pass').val();
        var new_pass = $('#new-pass').val();
        console.log(old_pass);
        console.log(new_pass);
        //Prevent default submission of form
        e.preventDefault();

        //Remove all errors
        $('input').next().remove();
        if (validate()) {
            $.post({
                url: 'api/change_pass_user',
                data: {
                    old_pass: old_pass,
                    new_pass: new_pass
                },
                success: function (res) {
                    alert(res);
                },
                error: function (e) {
                    console.log(e)
                }
            })
        }
    });

    function validate() {
        var new_pass = $('#new-pass').val();
        var re_new_pass = $('#re-new-pass').val();
        if(new_pass != re_new_pass){
            alert("Mật khẩu xác nhận không đúng!");
            return false;
        }
        return true;
    }
})