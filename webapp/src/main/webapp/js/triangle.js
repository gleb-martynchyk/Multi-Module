$(document).on("click", "#calculate", function () {
    $.ajax({
        url: 'GetTriangle',
        data: {
            x0: $('#x0').val(),
            y0: $('#y0').val(),
            x1: $('#x1').val(),
            y1: $('#y1').val(),
            x2: $('#x2').val(),
            y2: $('#y2').val()
        },
        success: function (responseText) {
            $('#ajaxGetUserServletResponse').text(responseText);
        }
    });
});