$(document).ready(function () {

    $('#btn_newAccount').click(function () {
        $('#newAccount').show();
        $('#newCourse').hide();
        $('#btn_newCourse').removeClass("btn-primary");
        $('#btn_newCourse').addClass("btn-info")
        $('#btn_newAccount').removeClass("btn-info")
        $('#btn_newAccount').addClass("btn-primary")
    });

    $('#btn_newCourse').click(function () {
        $('#newCourse').show();
        $('#newAccount').hide();
        $('#btn_newAccount').removeClass("btn-primary");
        $('#btn_newAccount').addClass("btn-info")
        $('#btn_newCourse').removeClass("btn-info")
        $('#btn_newCourse').addClass("btn-primary")
    })

    var courseId = -1;

    $('#id-teacher').change(function (event) {
        var teacher_id = $(this).val();
        if (courseId == -1) {
            alert("Bạn chưa nhập khóa học!");
        } else {
            if (teacher_id != 0) {
                add_teacher(teacher_id, courseId);
            }
        }

    });

    $('#new_lesson').prop('disabled', true);
    /*  Submit form using Ajax */
    $('#new_course').click(function (e) {

        //Prevent default submission of form
        e.preventDefault();

        //Remove all errors
        $('input').next().remove();
        if(validate()){
            $.post({
                url: '../api/create_new_course',
                data: $('form[name=course_form]').serialize(),
                success: function (res) {
                    alert("Đã thêm khóa học thành công! Hãy chọn giáo viên và nhập nội dung cho các bài học!")
                    console.log(res);
                    courseId = res['courseId'];
                    var num = res['numOfLesson'];
                    $('#new_course').prop('disabled', true);
                    $('#total_lesson').text(num);
                    $('#num_lesson').text(1);
                    $('#new_lesson').prop('disabled', false);
                },
                error: function (e) {
                    console.log(e)
                }
            })
        }

    });

    $('#signup').click(function (e) {
        //Prevent default submission of form
        e.preventDefault();

        //Remove all errors
        $('input').next().remove();
        if(validateUser()){
            $.post({
                url: '../api/create_new_account',
                data: $('form[name=register-form]').serialize(),
                success: function (res) {
                    alert("Tạo tài khoản thành công!")
                    console.log(res);
                },
                error: function (e) {
                    console.log(e)
                }
            })
        }
    });

    $('#new_lesson').click(function (e) {

        var nameLesson = $('#name_lesson').val();
        var contentLesson = $('#content_lesson').val();
        var dateLesson = $('#date_lesson').val();
        var lengthLesson = $('#length_lesson').val();

        $.ajax({
            type: "get",
            url: '../api/save_lesson',
            data: {
                courseId: courseId,
                nameLesson: nameLesson,
                contentLesson: contentLesson,
                dateLesson: dateLesson,
                lengthLesson: lengthLesson
            },
            success: function (res) {
                console.log(res);
                $('#name_lesson').val("");
                $('#content_lesson').val("");
                $('#date_lesson').val("");
                $('#length_lesson').val("");
                var num = parseInt($('#num_lesson').text()) + 1;
                var max = parseInt($('#total_lesson').text())
                console.log(num);
                console.log(max);
                if (num <= max) {
                    $('#num_lesson').text(num);
                } else {
                    $('#new_lesson').prop('disabled', true);
                    alert("Bạn đã nhập đủ số bài học!")
                }
            }
        })
    });
})

function add_teacher(teacher_id, course_id) {
    $.ajax({
        type: "get",
        url: "../api/add_teacher",
        data: {
            teacher_id: teacher_id,
            course_id: course_id
        },
        success: function (res) {
            console.log(res);
        },
        error: function (e) {
            console.log(e);
        }
    })
}

function validate() {
    var courseName = $('#courseName').val();
    var startDate = $('#startDate').val();
    var endDate = $('#endDate').val();
    var numOfLesson = $('#numOfLesson').val();
    var courseType = $('#courseType').val();

    if (courseName == "") {
        alert("Tên khóa học không được trống");
        return false;
    }
    if (startDate == "") {
        alert("Thời gian không được null")
        return false;
    }
    if (endDate == "") {
        alert("Thời gian không được null");
        return false;
    }
    if (numOfLesson == "0") {
        alert("Số lượng bài học phải lớn hơn 0")
        return false;
    }
    if (courseType == "NONE") {
        alert("Bạn chưa chọn loại khóa học")
        return false;
    }

    var start = new Date(startDate);
    var end = new Date(endDate);

    if (start.getTime() > end.getTime()) {
        alert("Ngày bắt đầu phải lớn hơn ngày kết thúc");
        return false;
    }

    return true;
}

function validateUser(){
    var name = $('#name').val();
    var username = $('#email').val();
    var pass = $('#pass').val();
    var re_pass = $('#re_pass').val();

    if(name == ""){
        alert("Tên không được để trống!");
        return false;
    }
    if(username == ""){
        alert("Email không được để trống!");
        return false;
    }
    if(pass.length < 6){
        alert("Pass tối thiểu 6 kí tự");
        return false;
    }
    if(pass != re_pass){
        alert("Mật khẩu xác nhận không khớp!");
        return false;
    }

    return true;
}