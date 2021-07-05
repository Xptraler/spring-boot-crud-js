$('document').ready(function () {
    $('#nav-profile #new').on('click', function (event) {
        event.preventDefault();
        let user = {
            name: $("#newName").val(),
            lastName: $("#newLastName").val(),
            age: $("#newAge").val(),
            email: $("#newEmail").val(),
            password: $("#newPassword").val(),
        }
        let newRole = $("#newRole").val()
        $.ajax({
            url: '/api/users/' + newRole,
            method: 'PUT',
            dataType: 'json',
            processData: false,
            contentType: 'application/json',
            data: JSON.stringify(user),
            success:
                console.log("!!!")
            ,
            error: function (error) {

            },
            complete: function () {
                getAllUsers()

            }

        })
        document.getElementById('nav-home-tab').click()
    })
})
