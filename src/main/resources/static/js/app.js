var tbodyy = $('#tbody');

$('#sendFile').click(function () {
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
            // $('#mainBody').attr("data-file-name", fileName);
            $('#mainBody').data("data-file-name", fileName);
            // console.log($('#mainBody').attr("data-file-name"));
            // reqFileName(fileName);
            // location.href = "/";
            // alert("hello");
            init();
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
}

function deleteData() {
    $.ajax({
        url: '/api/deleteData',
        type: "POST",
        success: function(response) {
            alert("데이터가 삭제 되었습니다.");
            deleteTbody();
            // window.location.reload();
        },
        error: function(response) {
            console.log('response', response);

            alert(response.responseJSON.message);
        }

    });
}

$('#testBt').click(function () {
    $.ajax({
        url: '/deleteData',
        type: "POST",
        success: function(response) {
            alert("데이터가 삭제 되었습니다.");
            // window.location.reload();
            init();
        },
        error: function(response) {
            console.log('response', response);

            alert(response.responseJSON.message);
        }

    })
});


var init = function() {
    $.ajax({
        url: '/api',
        method: 'GET',
        contentType: 'application/json; charset=utf-8',
        success: function(response) {
            console.log(response);
            renderResultBox(response);
            // renderSelectBoxOption(response);
        },
        error: function(response) {
            alert(response.responseJSON.message);
        }
    });
};

let renderResultBox = function(data) {
    console.log('renderresultbox =  ');
    console.log(data);
    let source = $('#result-template').html();
    let template = Handlebars.compile(source);
    let html = template(data);
    // $('#tbody2').html(html);
    console.log("-------------------");
    console.log(data);
    console.log("-------------------");
    console.log(html);
    // tbodyy.html('');
    tbodyy.append(html);
    console.log("renderResultBox 완료로그 ")
};


var renderSelectBoxOption = function(data) {
    console.log("SelectBoxOption  : " + data);
    var source = $('#result-template').html();
    var template = Handlebars.compile(source);
    var html = template(data);
    tbodyy.html('');
    // $('#tbody2').html(html);
    $('#tbody').html(html);
};

// $(document).ready(function () {
//     init();
// });

