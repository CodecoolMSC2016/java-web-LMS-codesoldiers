var posts = [];

$(document).ready(function () {
    $.get('loginstatus', function (data) {
        console.log(data);
        if (data === "mentor") {
            $('#sortable').sortable({
                update: function (event, ui) {
                    var posts = {};
                    $('#sortable').children().each(function (i) {
                        var id = $(this).attr('data-post-id');
                        var order = $(this).index();
                        // Instead of echoing this, build a real array
                        posts[parseInt(id)] = order;
                    });
                    var postsJson = JSON.stringify(posts);
                    $.post("curriculum", {method: "reorder", json: postsJson});
                }
            });
        }
    });
});

function sendDelete(id) {
    var values = {};
    var ser = $('#form').serializeArray();
    $.each(ser, function (i, field) {
        values[field.name] = field.value;
    });
    console.log(values);
    $.ajax({
        method: "DELETE",
        url: "curriculum",
        data: JSON.stringify(values),
        dataType: "json"
    }).done(function() {
        window.location.href = 'curriculum';
    }).fail(function() {
        alert( "error" );
    });
}
