$(document).ready(function () {
    $('#assignment').hide();
    $('#text').show();
});

function radioChanged(pageType) {
    if (pageType === "assignment") {
        $('#assignment').show();
        $('#text').hide();
    } else if (pageType === "text") {
        $('#assignment').hide();
        $('#text').show();
    }
}

function sendPut() {
    var values = {};
    var ser = $('#form').serializeArray();
    $.each(ser, function (i, field) {
        values[field.name] = field.value;
    });
    console.log(values);
    $.ajax({
        method: "PUT",
        url: "curriculum",
        data: JSON.stringify(values),
        dataType: "json"
    }).done(function() {
        window.location.href = 'curriculum';
    }).fail(function() {
        alert( "error" );
    });
}

/*
 var values = {};
 $('#form :input').each(function () {
 values[this.name] = $(this).val();
 });
 console.log(values);
 */

