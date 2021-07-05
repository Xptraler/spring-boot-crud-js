function editUser(id) {
    $.get("/api/users/" + id, function (data) {
        $('#id').val(data.id);
        $('#firstName').val(data.name);
        $('#lastName').val(data.lastName);
        $('#email').val(data.email);
        $('#password').val(data.password);
        $('#age').val(data.age);
        $('#role').val(data.role);
    });
    $('#exampleModal').modal('show');
    $('#exampleModal #edit').on('click', function (event) {
            event.preventDefault();
            let user = {
                id: $("#id").val(),
                name: $("#firstName").val(),
                lastName: $("#lastName").val(),
                age: $("#age").val(),
                email: $("#email").val(),
                password: $("#password").val(),
            }
            let role = $("#roleEdit").val()
            console.log(role)
            console.log(user)
            $.ajax({
                url: '/api/users/' + role,
                method: 'PUT',
                dataType: 'json',
                processData: false,
                contentType: 'application/json',
                data: JSON.stringify(user),
                success:
                    $('#exampleModal').modal('hide')
                ,
                error: function (error) {

                },
                complete:    function () {
                    getAllUsers();
                }

            })
        }
    );
}