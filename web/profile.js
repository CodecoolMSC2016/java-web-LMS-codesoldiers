function postDelete() {
    var currpass = $('input[name=currpass]').val();
    $.post('profile', {deleteUser: '', currpass: currpass});
}