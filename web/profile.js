function postDelete() {
    var currpass = $('input[name=currpass]').val();
    $.post('profile', {deleteUser: '', currpass: currpass}).done(function() {
        window.location.href = 'logout?deleted';
    }).fail(function() {
        window.location.href = 'profile?wrongpass';
    });
}