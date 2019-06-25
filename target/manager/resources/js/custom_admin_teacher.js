$(document).ready(function () {

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
    var name = $('#search-teacher').val();
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
    $('#teachers').empty();
    var th = "<tr class=\"row\" style=\"font-weight: bold\">\n" +
        "         <td>Id</td>\n" +
        "         <td>Họ tên</td>\n" +
        "         <td>Các khóa học đang dạy</td>\n" +
        "     </tr>";
    $('#teachers').append(th);
    $.each(data , function (index, teacher) {


        var td3 = "<td>";
        $.each(teacher['courses'], function (index, course) {
            td3 += "<a href=\"${pageContext.request.contextPath}/course_detail?id="+course['courseId']+"\">"+course['courseName']+"</a>,";
        })
        td3 += "</td>";
        var td4 = "";
        $('#data-teachers').append(td3);
        if(teacher['status'] == 1){
            td4 = "<td><button onclick=\"block(this, "+teacher['userId']+")\" class=\"btn btn-danger\">block</button></td>";
        }
        else{
            td4 = "<td><button onclick=\"block(this, "+teacher['userId']+")\" class=\"btn btn-primary\">unblock</button></td>";
        }
        var tr = "<tr class='row'>" +
            "           <td>"+teacher['userId']+"</td>" +
            "           <td>"+teacher['fullname']+"</td>" +
                        td3 + td4 +
            "      </tr>";
        $('#teachers').append(tr);
    })
}




