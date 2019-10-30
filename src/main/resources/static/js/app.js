$('#sendFile').click(function () {

    let form = $('#fileForm')[0];
    let formData = new FormData(form);
    $.ajax({
        url: '/uploadFile2',
        type: "POST",
        data: formData,
        processData: false,
        contentType: false,
        // cache: false,
        success: function() {
            alert("hello");
        }
    });
});

function clickk() {
    let form = $('#fileForm')[0];
    let formData = new FormData(form);

    $.ajax({
        url: '/uploadFile',
        type: "POST",
        data: formData,
        processData: false,
        contentType: false,
        // cache: false,
        success: function(response) {
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
        error: function(response) {
            console.log('response', response);
            alert(response.responseJSON.message);
        }

    });

    function reqFileName(fileNamee) {

        $.ajax({
            url: '/test',
            method: "GET",
            data: {'fileName': JSON.stringify(fileNamee)},
            // cache: false,
            success: function() {
                // let fileName = response.responseJSON;
                // console.log(fileName);
                // alert(response.fileName);

                // let elementById = document.getElementById("mainBody");
                // let mainBody = $('mainBody');
                // elementById.dataset.code(fileName);
                alert("잘보냄 파일이름 : " + fileNamee);
                console.log("잘보냄 파일이름 : " + fileNamee);
                // location.href = "/";
                // alert("hello");
            },
            error: function(response) {
                console.log('response', response);
                alert(response.responseJSON.message);
            }
        });
    }


}