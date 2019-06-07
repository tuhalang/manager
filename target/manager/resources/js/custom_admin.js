$(document).ready(function () {

    loadCourseType();
    loadCourse(0);
    loadStudent();

    $('#course_type').change(function (event) {
        var type = $(this).val();
        loadCourse(type);
    });

    $('#multiple-search').select2({
        placeholder: "Select a student",
        allowClear: true
    }).on("select2:select", function (e) {
        var selected_element = $(e.currentTarget);
        var select_val = selected_element.val();
        getCourses(select_val.join());
    });

    $('#student-search').select2({
        placeholder: "Select a student",
        allowClear: true
    }).on("select2:select", function (e) {
        var selected_element = $(e.currentTarget);
        var select_val = selected_element.val();
        getStudents(select_val.join());
    });

});

function getStudents(listId) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "api/get_students",
        data: {
            list_id: listId
        },
        dataType: 'json',
        timeout: 10000,
        success: function (data) {
            updateTableStudent(data);
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
}

function updateTableStudent(data) {
    $('#student').empty();
    var th = '<tr><th>Id</th><th>Full Name</th><th>Total Time</th></tr>';
    $('#student').append(th);
    $.each(data, function (index, student) {
        var tr = '<tr class="rows">'
            + '<td>' + student['id'] + '</td>'
            + '<td>' + student['fullname'] + '</td>'
            + '<td>' + student['totalTime'] + '</td>';
        $('#student').append(tr);
    });
}

function getCourses(listId) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "api/get_list_course_by_students",
        data: {
            list_id: listId
        },
        dataType: 'json',
        timeout: 10000,
        success: function (data) {

            updateTableCourse(data);
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
}

function updateTableCourse(data) {
    $('#list_course').empty();
    var th = '<tr><th>Id</th><th>Name</th><th>Start time</th><th>End time</th><th>Num Of Lesson</th><th>Fee</th><th>Promotion</th><th>Type</th></tr>'
    $('#list_course').append(th);
    $.each(data, function (index, course) {
        var startTime = getDateFormat(course['startDate']);
        var endTime = getDateFormat(course['endDate']);
        var tr = '<tr class="rows">'
            + '<td>' + course['courseId'] + '</td>'
            + '<td>' + course['courseName'] + '</td>'
            + '<td>' + startTime + '</td>'
            + '<td>' + endTime + '</td>'
            + '<td>' + course['numOfLesson'] + '</td>'
            + '<td>' + course['fee'] + '</td>'
            + '<td>' + course['promotion'] + '</td>'
            + '<td>' + course['courseType']['type'] + '</td>'
            + '<td><a href="course_detail?id=' + course['courseId']
            + '">detail</a></td>' + '</tr>';
        $('#list_course').append(tr);
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

function loadCourseType() {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "api/load_dropdown",
        dataType: 'json',
        timeout: 10000,
        success: function (data) {
            var myOptions = data;
            $.each(myOptions, function (val, text) {
                $('#course_type').append(
                    new Option(text['type'], text['courseTypeId']));
            });
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
}

function loadStudent() {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "api/load_student",
        dataType: 'json',
        timeout: 10000,
        success: function (data) {
            var myOptions = data;
            $.each(myOptions, function (val, text) {
                $('#multiple-search').append(
                    '<option value=' + text['userId'] + '>'
                    + text['fullname'] + '</option>');
                $('#student-search').append(
                    '<option value=' + text['userId'] + '>'
                    + text['fullname'] + '</option>');
            });
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
}

function loadCourse(type) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "api/load_course",
        data: {
            type_id: type
        },
        dataType: 'json',
        timeout: 10000,
        success: function (data) {
            updateTableCourse(data);
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
}

function searchCourseByStudent(key) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "api/search_course_by_student",
        data: {
            key: key
        },
        dataType: 'json',
        timeout: 10000,
        success: function (data) {
            updateTableCourse(data);
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
}
