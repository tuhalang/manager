$(document).ready(function () {

    loadCourse(1);

    $('input[name="type"]').change(function () {
        console.log($(this).val());
        loadCourse($(this).val());
    });


});

function loadCourse(type) {
    $.ajax({
        type: "get",
        contentType: "application/json",
        url: "api/load_course_of_student",
        data: {
            type: type
        },
        dataType: 'json',
        timeout: 10000,
        success: function (data) {
            console.log(data);
            $('#courses').empty();
            var tr = '<tr><th>ID</th><th>Tên khóa học</th><th>Bắt đầu</th><th>Kết thúc</th><th>Học phí</th><th>Giáo viên</th></tr>';
            $('#courses').append(tr);
            $.each(data, function (index, course) {
                var start = getDateFormat(course['startDate']);
                var end = getDateFormat(course['endDate']);
                var tr = '<tr class="rows">'
                    + '<td>' + course['courseId'] + '</td>'
                    + '<td>' + course['courseName'] + '</td>'
                    + '<td>' + start + '</td>'
                    + '<td>' + end + '</td>'
                    + '<td>' + course['fee'] + '</td>'
                    + '<td>' + course['teacher'] + '</td>';
                $('#courses').append(tr);
            });
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
}


function getDateFormat(date) {
    var d = new Date(date), month = '' + (d.getMonth() + 1), day = ''
        + d.getDate(), year = d.getFullYear();

    if (month.length < 2)
        month = '0' + month;
    if (day.length < 2)
        day = '0' + day;
    var date = new Date();
    date.toLocaleDateString();

    return [day, month, year].join('-');
}