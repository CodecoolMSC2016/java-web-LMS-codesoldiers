var posts = [];

$(document).ready(function () {
    $.get('loginstatus', function (data) {
        console.log(data);
        if (data === "mentor") {
            $('#sortable').sortable({
                update: function (event, ui) {
                    $('#sortable').children().each(function (i) {
                        var id = $(this).attr('data-post-id');
                        var order = $(this).index();
                        // Instead of echoing this, build a real array
                        posts[parseInt(id)] = order;
                    });
                    var postsJson = JSON.stringify(posts);
                    $.post("/curriculum", {method: "reorder", json: postsJson});
                }
            });
        }
    });
});
