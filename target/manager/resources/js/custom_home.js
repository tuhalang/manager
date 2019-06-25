$(document).ready(function () {
    loadCourseShort(1);
})

function loadCourseShort(page) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: window.location+"/api/load_course_short",
        data: {
            page: page
        },
        dataType: 'json',
        timeout: 10000,
        success: function (data) {
            console.log(data);
            $("#courses").empty();
            $.each(data, function (index, val) {
                var div = "<div class=\"col-xs-4 col-sm-4 col-md-4 col-lg-4\">\n" +
                    "                <div class=\"card\" style=\"margin-top: 10px\">\n" +
                    "                    <div class=\"card-body\">\n" +
                    "                        <h5 class=\"card-title\">" + val['courseName'] + "</h5>\n" +
                    "                        <p class=\"card-text\"> Giáo viên: " + val['teacher'] + "</p>\n" +
                    "                        <p class=\"card-text\"> Học phí: " + val['fee'] + "</p>\n" +
                    "                        <a href='login' class=\"btn btn-primary\">Đăng kí ngay</a>\n" +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "            </div>";
                $("#courses").append(div);
            })
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    })
}