$(document).ready(function () {
    $("#search").click(function () {
        var userId = $("#txt-search").val();
        if (userId != "") {
            $.ajax({
                type: "get",
                contentType: "application/json",
                url: "../api/payment/list",
                data: {
                    userId: userId
                },
                dataType: 'json',
                timeout: 10000,
                success: function (data) {
                    updateInfo(data);
                    loadHistory(userId);
                },
                error: function (e) {
                    console.log("ERROR: ", e);
                }
            });
        }
    });

    $('#push').click(function () {
        var userId = $("#txt-search").val();
        var money = $('#money').val();
        if (money != "" && userId != "") {
            $.ajax({
                type: "get",
                contentType: "application/json",
                url: "../api/payment",
                data: {
                    paid: money,
                    userId: userId
                },
                dataType: 'text',
                timeout: 10000,
                success: function (data) {
                    loadHistory(userId);
                    alert(data);
                },
                error: function (e) {
                    console.log("ERROR: ", e);
                }
            });
        }
    });
})

function loadHistory(userId) {
    $.ajax({
        type: "get",
        contentType: "application/json",
        url: "../api/payment/history",
        data: {
            userId: userId
        },
        dataType: 'json',
        timeout: 10000,
        success: function (data) {
            updateHistory(data);
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    })
}

function updateInfo(data) {
    $('#info-course').empty();
    if (data != null) {
        var trh = "<tr>\n" +
            "           <th>STT</th>\n" +
            "           <th>Ten khoa hoc</th>\n" +
            "           <th>Hoc phi</th>\n" +
            "      </tr>";
        $('#info-course').append(trh);
        var total = 0;
        $.each(data, function (index, course) {
            var tr = "<tr>\n" +
                "           <th>" + index + 1 + "</th>\n" +
                "           <th>" + course['courseName'] + "</th>\n" +
                "           <th>" + course['fee'] + "</th>\n" +
                "      </tr>";
            $('#info-course').append(tr);
            total += course['fee'];
        })
        $('#btn-payment').show();
        $('#nodata').hide();
        $('#total').text(total);
    }
}

function updateHistory(data) {
    $('#history').empty();
    if (data != null) {
        var trh = "<tr>\n" +
            "         <th>STT</th>\n" +
            "         <th>Ngay nop</th>\n" +
            "         <th>So tien</th>\n" +
            "      </tr>";
        $('#history').append(trh);
        var paid = 0;
        $.each(data, function (index, bill) {
            var date = getDateFormat(bill['paid']);
            var tr = "<tr>\n" +
                "           <th>" + index + 1 + "</th>\n" +
                "           <th>" + bill['date'] + "</th>\n" +
                "           <th>" + bill['paid'] + "</th>\n" +
                "      </tr>";
            $('#history').append(tr);
            paid += bill['paid'];
        })
        $('#paid').text(paid);
        var total = $('#total').text();
        console.log(total);
        console.log(paid);
        var lack = total - paid;
        $('#lack').text(lack);
    }
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