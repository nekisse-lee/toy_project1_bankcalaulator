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
            reqFileName(fileName);
            // location.href = "/";
            // alert("hello");
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


function deleteData() {
    $.ajax({
        url: '/deleteData',
        type: "POST",
        success: function(response) {
            alert("데이터가 삭제 되었습니다.");
            window.location.reload();
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
            window.location.reload();
        },
        error: function(response) {
            console.log('response', response);

            alert(response.responseJSON.message);
        }

    })
});
