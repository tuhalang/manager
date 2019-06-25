$(document).ready(function () {

    var courseId = -1;

    $('#lessonInfo').hidden
    $('#new_lesson').prop('disabled', true);
    /*  Submit form using Ajax */
    $('#new_course').click(function (e) {

        //Prevent default submission of form
        e.preventDefault();

        //Remove all errors
        $('input').next().remove();

        $.post({
            url: 'api/save_course',
            data: $('form[name=course_form]').serialize(),
            success: function (res) {
                alert("Đã thêm khóa học thành công! Hãy nhập nội dung cho các bài học!")
                console.log(res);
                courseId = res['courseId'];
                var num = res['numOfLesson'];
                $('#new_course').prop('disabled', true);
                $('#total_lesson').text(num);
                $('#num_lesson').text(1);
                $('#new_lesson').prop('disabled', false);
            }
        })
    });

    $('#new_lesson').click(function (e) {

        // //Prevent default submission of form
        // e.preventDefault();
        //
        // //Remove all errors
        // $('input').next().remove();

        var nameLesson = $('#name_lesson').val();
        var contentLesson = $('#content_lesson').val();
        var dateLesson = $('#date_lesson').val();
        var lengthLesson = $('#length_lesson').val();

        $.ajax({
            type: "get",
            url: 'api/save_lesson',
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

});

function base_url() {
    var pathparts = location.pathname.split('/');
    if (location.host == 'localhost:8080') {
        var url = location.origin + '/' + pathparts[1].trim('/') + '/'; // http://localhost/myproject/
    } else {
        var url = location.origin; // http://stackoverflow.com
    }
    return url;
}