$(document).ready(function () {
    loadPage();


    $('.hover_bkgr_fricc').click(function () {
        $('.hover_bkgr_fricc').hide();
    });
    $('.popupCloseButton').click(function () {
        $('.hover_bkgr_fricc').hide();
    });
})


function loadCourseShort(page) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "api/load_course_short",
        data: {
            page: page
        },
        dataType: 'json',
        timeout: 10000,
        success: function (data) {
            console.log(data);
            updateData(data);
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    })
}

function searchCourse(){
    var type = $("input[name='type']:checked"). val();
    if(type == "1"){
        type = "courseName";
    }
    if(type == "2"){
        type = "startDate";
    }
    var key = $('#search_course').val();
    $.ajax({
        type : 'get',
        contentType : "application/json; charset=utf-8",
        url: "api/search_course",
        data: {
            type: type,
            key : key
        },
        dataType: 'json',
        timeout: 10000,
        success : function (data) {
            console.log(data);
            updateData(data);
        },
        error : function (e) {
            console.log(e);
        }
    })
}

function showPopUp(courseId) {

    $.ajax({
        type: "GET",
        contentType: "application/json; charset=utf-8",
        url: "api/show_course_detail",
        data: {
            courseId: courseId
        },
        dataType: 'json',
        timeout: 10000,
        success: function (data) {
            console.log(data);
            $('.hover_bkgr_fricc').show();
            $('#content-popup').empty();

            var table_detail = "     <table id='table-detail' class=\"table table-bordered\">" +
                "                        <tr>\n" +
                "                            <td><strong>Ten khoa hoc</strong></td>\n" +
                "                            <td>"+data['courseName']+"</td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td><strong>Giao vien</strong></td>\n" +
                "                            <td>"+data['teacher']+"</td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td><strong>Bat dau</strong></td>\n" +
                "                            <td>"+data['startDate']+"</td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td><strong>Ket thuc</strong></td>\n" +
                "                            <td>"+data['endDate']+"</td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td><strong>Hoc phi</strong></td>\n" +
                "                            <td>"+data['fee']+"</td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                           <td><strong>Noi dung chi tiet</strong></td>\n" +
                "                        </tr>\n" +
                "                    </table>\n";

            $('#content-popup').append(table_detail);

            $.each(data['lessons'], function (index, val) {
                var row = "<tr>\n" +
                    "          <td><strong>"+val['lessonName']+"</strong></td>\n" +
                    "          <td>"+val['content']+"</td>\n" +
                    "       </tr>\n";
                $('#table-detail').append(row);
            })

            var button = "<button class='btn btn-primary' onclick='enroll("+data['courseId']+")'>Đăng kí</button>";
            $('#content-popup').append(button);
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
}

function updateData(data){
    $("#courses").empty();
    $.each(data, function (index, val) {
        var div = "<div class=\"col-xs-4 col-sm-4 col-md-4 col-lg-4\">\n" +
            "                <div class=\"card\" style=\"margin-top: 10px\">\n" +
            "                    <div class=\"card-body\">\n" +
            "                        <h5 class=\"card-title\">" + val['courseName'] + "</h5>\n" +
            "                        <p class=\"card-text\"> Giáo viên: " + val['teacher'] + "</p>\n" +
            "                        <p class=\"card-text\"> Học phí: " + val['fee'] + "</p>\n" +
            "                        <button onclick=\"showPopUp(" + val['courseId'] + ")\" class=\"btn btn-primary\">Chi tiết</button>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "            </div>";
        $("#courses").append(div);
    })
}


function enroll(courseId) {
    $.ajax({
        type: "GET",
        contentType: "application/json; charset=utf-8",
        url: "api/enroll",
        data: {
            courseId: courseId
        },
        dataType: 'text',
        timeout: 10000,
        success: function (data) {
            alert(data);
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
}

function loadPage() {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "api/get_total_course",
        data: {
            type_id: 0
        },
        dataType: 'text',
        timeout: 10000,
        success: function (data) {
            var total = parseInt(data);
            console.log(total)
            loadPagination(total / 8 + 1);
            loadCourseShort(1);
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
}

function loadPagination(totalPages) {
    window.pagObj = $('#pagination').twbsPagination({
        totalPages: totalPages,
        visiblePages: 1,
        onPageClick: function (event, page) {
            loadCourseShort(page);
        }
    }).on('page', function (event, page) {
        // console.info(page + ' (from event listening)');
    });
}