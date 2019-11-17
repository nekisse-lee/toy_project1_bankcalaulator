var tbodyy = $('#tbody');

$('#sendFile').click(function () {
    deleteData();
    $('.nekisse').removeClass("fade").addClass("show");
    let form = $('#fileForm')[0];
    let formData = new FormData(form);

    $.ajax({
        url: '/uploadFile',
        type: "POST",
        data: formData,
        processData: false,
        contentType: false,
        // cache: false,
        success: function (response) {
            // let fileName = response.responseJSON;
            // console.log(fileName);
            // alert(response.fileName);

            // let elementById = document.getElementById("mainBody");
            // let mainBody = $('mainBody');
            // elementById.dataset.code(fileName);
            let fileName = response.fileName.toString();
            console.log(fileName);

            // $('#mainBody').attr("data-filename", fileName);
            $('#mainBody').data("filename", fileName);

            console.log("데이터 저장 " +  $('#mainBody').data("filename").toString());
            // console.log($('#mainBody').attr("data-file-name"));
            // reqFileName(fileName);
            // location.href = "/";
            // alert("hello");
            renderTemplate();
        },
        error: function (response) {
            console.log('response', response);
            alert(response.responseJSON.message);
        }

    });
});

function reqFileName(fileName) {

    $.ajax({
        url: '/test',
        method: "GET",
        data: {'fileName': JSON.stringify(fileName)},
        // cache: false,
        success: function() {
            // let fileName = response.responseJSON;
            // console.log(fileName);
            // alert(response.fileName);

            // let elementById = document.getElementById("mainBody");
            // let mainBody = $('mainBody');
            // elementById.dataset.code(fileName);
            alert("잘 보냄 파일이름 : " + fileName);
            console.log("잘 보냄 파일이름 : " + fileName);
            window.location.reload();
            // location.href = "/";
            // alert("hello");

        },
        error: function(response) {
            console.log('response', response);
            alert(response.responseJSON.message);
        }
    });
}


function deleteTbody() {
    $('#tbody').empty();
    $('#calValue').empty();
    $('#depositor').val('');
}

function deleteData() {
    let fileName = $('#mainBody').data("filename");
    console.log("fileName =      " + fileName);
    $.ajax({
        url: '/api/deleteData',
        type: "delete",
        data: {'fileName': fileName},
        success: function(response) {
            alert("기존 데이터가 삭제 되었습니다.");
            deleteTbody();
            // window.location.reload();
        },
        error: function(response) {
            console.log('response', response);

            alert(response.responseJSON.message);
        }

    });
}

$('#btDeleteData').click(function () {
    $('.nekisse').removeClass("show").addClass("fade");
    deleteData();
});


var renderTemplate = function() {
    $.ajax({
        url: '/api',
        method: 'GET',
        contentType: 'application/json; charset=utf-8',
        success: function(response) {
            console.log(response);
            renderResultListBox(response);

            console.log("=================" + response.calValue);
            // renderSelectBoxOption(response);
        },
        error: function(response) {
            alert(response.responseJSON.message);
        }
    });
};



let renderCalculateResultBox2 = function (calValue) {
    let source = $('#result-string').html();
    let template = Handlebars.compile(source);
    let html = template(calValue);
    // $('#tbody2').html(html);
    console.log(calValue.calValue);
    console.log(html);
    // tbodyy.html('');
    $('#calValue').append(html);
    console.log("renderCalculateResultBox2 완료로그 ")
};

let renderResultListBox = function(data) {
    let source = $('#result-template').html();
    let template = Handlebars.compile(source);
    let html = template(data);
    // $('#tbody2').html(html);
    console.log('renderResultListBox =  ');
    console.log(data);
    console.log("-------------------");
    // console.log(html);
    // tbodyy.html('');
    tbodyy.append(html);
    console.log("renderResultListBox 완료로그 ")
};


$('#btCalFindUser').click(function () {
    let depositor = $('#depositor').val();
    let startDate = $('#startDate').val();
    let endDate = $('#endDate').val();
    console.log(depositor + "+" + startDate + "+" + endDate);

    $.ajax({
        url: '/api/calculateOfOneDepositor',
        type: "GET",
        data: {'startDate': startDate,'endDate': endDate,'depositor': depositor},
        success: function (response) {
            deleteTbody();
            renderResultListBox(response);
            renderCalculateResultBox2(response);
        },
        error: function (response) {
            console.log('response', response);
            alert(response.responseJSON.message);
        }
    })
});

$('#btFindUser').click(function () {
    let depositor = $('#depositor').val();

    $.ajax({
        url: '/api/findDepositor',
        type: "GET",
        data: {'depositor': depositor},
        success: function (response) {
            deleteTbody();
            renderResultListBox(response);
        },
        error: function (response) {
            console.log('response', response);
            alert(response.responseJSON.message);
        }
    })
});


$('#btFindAll').click(function () {
    let depositor = $('#depositor').val();
    let startDate = $('#startDate').val();
    let endDate = $('#endDate').val();
    console.log(depositor + "+" + startDate + "+" + endDate);

    $.ajax({
        url: '/api',
        type: "GET",
        data: {'startDate': startDate,'endDate': endDate,'depositor': depositor},
        success: function (response) {
            deleteTbody();
            renderResultListBox(response);
        },
        error: function (response) {
            console.log('response', response);
            alert(response.responseJSON.message);
        }
    })
});


$('#btSetMoney').click(function () {
    let userSetMoney = $('#setMoney').val();
    console.log(userSetMoney);

    $.ajax({
        url : 'api/setMoney',
        type :"GET",
        data: {'setMoney': userSetMoney},
        success: function (res) {
            alert(res);
        },
        error: function (res) {
            console.log('response', res);
            alert(res.responseJSON.message);
        }

    });

});

// $(document).ready(function () {
//     init();
// });

