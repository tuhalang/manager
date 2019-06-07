$(document).ready(function () {
    var courseId = getUrlParameter('id');
    loadLesson(courseId);

    $('#lesson').change(function (event) {
        var lessonId = $(this).val();
        if (lessonId != 'NONE') {
            loadStudentsOfCourse(lessonId);
        } else {
            alert("You haven't choose lesson!")
        }
    });
});


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
                    alert("New lesson is created!");
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
                alert("Can't create new lesson");
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
            var th = '<tr><th>Id</th><th>Name</th><th>Roll up</th></tr>';
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

function newLesson() {
    var lessonName = $('#lessonName').val();
    var courseId = getUrlParameter("id");
    var length = $('#length').val();
    if (lessonName !== "") {
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "api/new_lesson",
            data: {
                courseId: courseId,
                lessonName: lessonName,
                length: length
            },
            dataType: 'text',
            timeout: 10000,
            success: function (data) {
                if (data == "success") {
                    alert("A new lesson is added!")
                    $.each(data, function (val, text) {
                        $('#lesson').append(
                            new Option(text['lessonName'], text['lessonId']));
                    });
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
            }
        });
    } else {
        alert("Please enter name of lesson!")
    }
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

