/**
 * Created by thomas on 2017.04.10..
 */
var posts = {};
alert(posts);
$(document).ready(function() {
    $('#sortable').sortable({
        update: function(event, ui) {
            $('#console').html('<b>posts[id] = pos:</b><br>');
            $('#sortable').children().each(function(i) {
                var id = $(this).attr('data-post-id')
                    ,order = $(this).index() + 1;
                // Instead of echoing this, build a real array
                $('#console').html($('#console').html() + 'posts[' + id + '] = ' + order + '<br>');
                //alert('posts[' + id + '] = ' + order);
                posts[id] = order;
                // Then do an ajax request to send the array
                // Update order fields from posts in db
                /*
                 $.ajax('url', {
                 data: //posts array
                 });
                 */
            });

            var postsPosts = JSON.parse(posts);


        }
    });
});
