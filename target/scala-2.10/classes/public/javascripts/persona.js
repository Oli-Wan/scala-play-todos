$(document).on('click', '#signin', function() {
    navigator.id.request();
    var waiter = $(".waiter");
    waiter.addClass("show").addClass("login");
});
$(document).on('click', '#signout', function() {
    navigator.id.logout();
});

navigator.id.watch({
    loggedInUser: personaUser,
    onlogin: function(assertion) {

        $.ajax({
            type: 'POST',
            url: '/login',
            data: {assertion: assertion},
            success: function(res, status, xhr) {
                console.log(res);
                window.location = "/tasks/index";
            },
            error: function(xhr, status, err) {
                alert(err);
                navigator.id.logout();
                console.log(err);
            }
        });
    },
    onlogout: function() {
        var waiter = $(".waiter");
        waiter.addClass("show").addClass("logout");
        $.ajax({
            type: 'POST',
            url: '/logout',
            success: function(res, status, xhr) {
                window.location = "/";
            },
            error: function(xhr, status, err) {
                alert("Logout failure: " + err);
                console.log("Logout failure: " + err);
            }
        });
    }
});
