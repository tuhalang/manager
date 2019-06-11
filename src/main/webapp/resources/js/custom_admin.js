$(document).ready(function () {

    loadCourseType();
    loadStudent();
    getTotalCourse(0);

    $('#course_type').change(function (event) {
        var type = $(this).val();
        console.log("type course: " + type);
        getTotalCourse(type);
    });

    $('#multiple-search').select2({
        placeholder: "Select a student",
        allowClear: true
    }).on("select2:select", function (e) {
        var selected_element = $(e.currentTarget);
        var select_val = selected_element.val();
        getTotalCourseByStudent(select_val.join());
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

function loadPagination(totalPages) {
    window.pagObj = $('#pagination').twbsPagination({
        totalPages: totalPages,
        visiblePages: 1,
        onPageClick: function (event, page) {
            var type = $('#course_type').val();
            var list_id = $('#multiple-search').val().join();
            if(list_id == ""){
                loadCourse(type,page);
            }else{
                loadCoursesByStudent(list_id, page)
            }
        }
    }).on('page', function (event, page) {
        // console.info(page + ' (from event listening)');
    });
}

// function getStudents(listId) {
//     $.ajax({
//         type: "GET",
//         contentType: "application/json",
//         url: "api/get_students",
//         data: {
//             list_id: listId
//         },
//         dataType: 'json',
//         timeout: 10000,
//         success: function (data) {
//             updateTableStudent(data);
//         },
//         error: function (e) {
//             console.log("ERROR: ", e);
//         }
//     });
// }
//
// function updateTableStudent(data) {
//     $('#student').empty();
//     var th = '<tr><th>Id</th><th>Full Name</th><th>Total Time</th></tr>';
//     $('#student').append(th);
//     $.each(data, function (index, student) {
//         var tr = '<tr class="rows">'
//             + '<td>' + student['id'] + '</td>'
//             + '<td>' + student['fullname'] + '</td>'
//             + '<td>' + student['totalTime'] + '</td>';
//         $('#student').append(tr);
//     });
// }

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

function loadCoursesByStudent(listId, page) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "api/get_list_course_by_students",
        data: {
            list_id: listId,
            page: page
        },
        dataType: 'json',
        timeout: 10000,
        success: function (data) {
            console.log(data);
            updateTableCourse(data);
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
}

function loadCourse(type, page) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "api/load_course",
        data: {
            type_id: type,
            page: page
        },
        dataType: 'json',
        timeout: 10000,
        success: function (data) {
            console.log(data)
            updateTableCourse(data);
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
}

function getTotalCourse(type){
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "api/get_total_course",//TODO ...
        data: {
            type_id: type
        },
        dataType: 'text',
        timeout: 10000,
        success: function (data) {
            var total = parseInt(data);
            console.log(total)
            loadPagination(total/8 +1);
            loadCourse(type, 1);
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
}

function getTotalCourseByStudent(listId){
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "api/get_total_course_by_students",//TODO ...
        data: {
            list_id: listId
        },
        dataType: 'text',
        timeout: 10000,
        success: function (data) {
            var total = parseInt(data);
            console.log("total course: " + total);
            loadPagination(total/8 + 1);
            loadCoursesByStudent(listId,1);
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

// function searchCourseByStudent(key) {
//     $.ajax({
//         type: "GET",
//         contentType: "application/json",
//         url: "api/search_course_by_student",
//         data: {
//             key: key
//         },
//         dataType: 'json',
//         timeout: 10000,
//         success: function (data) {
//             updateTableCourse(data);
//         },
//         error: function (e) {
//             console.log("ERROR: ", e);
//         }
//     });
// }


function updateTableCourse(data) {
    $('#list_course').empty();
    var th = '<tr><th>Id</th><th>Tên khóa học</th><th>Bắt đầu</th><th>Kết thúc</th><th>Số lượng bài học</th><th>Học phí</th><th>Khuyến mại</th><th>Loại</th></tr>'
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
            + '">chi tiết</a></td>' + '</tr>';
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