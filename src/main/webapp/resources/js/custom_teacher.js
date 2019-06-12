$(document).ready(function () {
    var courseId = getUrlParameter('id');
    loadLesson(courseId);

    $('#lesson').change(function (event) {
        var lessonId = $(this).val();
        if (lessonId != 'NONE') {
            loadInfoCourse(lessonId);
            loadStudentsOfCourse(lessonId);
        } else {
            alert("You haven't choose lesson!")
        }
    });
});

function loadInfoCourse(lessonId) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "api/load_info_lessons",
        data: {
            lessonId: lessonId
        },
        dataType: 'json',
        timeout: 10000,
        success: function (data) {
            $('#name_lesson').text(data['lessonName']);
            $('#length').text(data['length']);
            $('#content').text(data['content']);
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
}

function loadLesson(courseId) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "api/load_lessons",
        data: {
            courseId: courseId
        },
        dataType: 'json',
        timeout: 10000,
        success: function (data) {
            var myOptions = data;
            $.each(myOptions, function (val, text) {
                $('#lesson').append(
                    new Option(text['lessonName'], text['lessonId']));
            });
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
}

function handleClick(cb) {
    if (cb.checked) {
        var userId = $(cb).val();
        var lessonId = $('#lesson').val();
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "api/roll_up",
            data: {
                userId: userId,
                lessonId: lessonId
            },
            dataType: 'text',
            timeout: 10000,
            success: function (data) {
                if (data == "success") {
                    console.log("ok");
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
            }
        });
    }
}

function loadStudentsOfLesson(lessonId) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "api/load_students_of_lesson",
        data: {
            lessonId: lessonId
        },
        dataType: 'json',
        timeout: 10000,
        success: function (data) {
            $.each(data, function (index, student) {
                var id = '#student_' + student['userId'];
                $(id).prop('checked', true);
            })
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
}

function loadStudentsOfCourse(lessonId) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "api/load_students_of_course",
        data: {
            lessonId: lessonId
        },
        dataType: 'json',
        timeout: 10000,
        success: function (data) {
            $('#students').empty();
            var th = '<tr><th>Id</th><th>Họ tên</th><th>Điểm danh</th></tr>';
            $('#students').append(th);
            $.each(data, function (index, student) {
                var tr = '<tr class="rows">'
                    + '<td>' + student['userId'] + '</td>'
                    + '<td>' + student['fullname'] + '</td>'
                    + '<td><input onclick="handleClick(this)" id="' + 'student_' + student['userId'] + '" type="checkbox" value="' + student['userId'] + '"></td>';
                $('#students').append(tr);
            });
            loadStudentsOfLesson(lessonId);
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
}


function getUrlParameter(sParam) {
    var sPageURL = window.location.search.substring(1),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
        }
    }
}

