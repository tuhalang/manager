$(document).ready(function () {
    $('.popupCloseButton').click(function () {
        $('.hover_bkgr_fricc').hide();
    });
    $('#create_new').click(function () {
        $('.hover_bkgr_fricc').show();
    });
})

function block(e, userId) {
    console.log(userId);
    var action = $(e).text();
    if(action === "block"){
        blockUser(userId,0);
        $(e).removeClass('btn-danger');
        $(e).text("unblock");
        $(e).addClass("btn-primary")
        alert("blocked!")
    }

    if(action === "unblock"){
        blockUser(userId,1);
        $(e).removeClass('btn-primary');
        $(e).text("block");
        $(e).addClass("btn-danger");
        alert("unblocked!")
    }
}

function blockUser(userId, status){
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "../api/block_user",
        data: {
            userId : userId,
            status : status
        },
        dataType: 'text',
        timeout: 10000,
        success: function (data) {
            console.log(data);
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
}

function search(userTypeId) {
    var name = $('#search-student').val();
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "../api/search_user",
        data: {
            userTypeId : userTypeId,
            name : name
        },
        dataType: 'json',
        timeout: 10000,
        success: function (data) {
            updateTable(data);
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
}


function updateTable(data) {
    $('#students').empty();
    var th = "<tr class=\"row\" style=\"font-weight: bold\">\n" +
        "         <td>Id</td>\n" +
        "         <td>Họ tên</td>\n" +
        "         <td>Tổng số giờ học</td>\n" +
        "         <td>Các khóa học đang học</td>\n" +
        "     </tr>";
    $('#students').append(th);
    $.each(data , function (index, student) {


        var td4 = "<td>";
        $.each(student['courses'], function (index, course) {
            td4 += "<a href=\"${pageContext.request.contextPath}/course_detail?id="+course['courseId']+"\">"+course['courseName']+"</a>,";
        })
        td4 += "</td>";
        var td5 = "";
        $('#data-teachers').append(td4);
        if(student['status'] == 1){
            td5 = "<td><button onclick=\"block(this, "+student['userId']+")\" class=\"btn btn-danger\">block</button></td>";
        }
        else{
            td5 = "<td><button onclick=\"block(this, "+student['userId']+")\" class=\"btn btn-primary\">unblock</button></td>";
        }
        var tr = "<tr class='row'>" +
            "           <td>"+student['userId']+"</td>" +
            "           <td>"+student['fullname']+"</td>" +
            "           <td>"+student['totalTime']+"</td>" +
            td4 + td5 +
            "      </tr>";
        $('#students').append(tr);
    })
}




