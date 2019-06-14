$(document).ready(function () {

    loadSchedule(0);

    $('input[name="time"]').change(function () {
        console.log($(this).val());
        loadSchedule($(this).val());
    });
});



function loadSchedule(type) {
    $.ajax({
        type: "get",
        contentType: "application/json",
        url: "../api/load_schedule",
        data: {
            type: type
        },
        dataType: 'json',
        timeout: 10000,
        success: function (data) {
            console.log(data);
            var days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
            $('#schedule').empty();
            var tr = "<tr><th>No.</th><th>Course's name</th><th>Lesson's name</th><th>Date of the week</th><th>Date</th></tr>";
            $('#schedule').append(tr);
            $.each(data, function (index, lesson) {
                var currentTime = new Date(lesson['date']);
                var dateOfWeek = days[currentTime.getDay()];
                var tr = '<tr class="rows">'
                    + '<td>' + (index + 1) + '</td>'
                    + '<td>' + lesson['course']['courseName'] + '</td>'
                    + '<td>' + lesson['lessonName'] + '</td>'
                    + '<td>' + dateOfWeek + '</td>'
                    + '<td>' + getDateFormat(lesson['date']) + '</td>';

                $('#schedule').append(tr);
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