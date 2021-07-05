function deleteUser(id) {
    $.get("/api/users/" + id, function (data) {
        $('#deleteId').val(data.id);
        $('#deleteName').val(data.name);
        $('#deleteLastName').val(data.lastName);
        $('#deleteEmail').val(data.email);
        $('#deletePassword').val(data.password);
        $('#deleteAge').val(data.age);
        $('#deleteRole').val(data.role);
    });
    $('#deleteModal').modal('show');
    $('#deleteModal #delete').on('click', function (event) {
        event.preventDefault();
        $.ajax({
            url: '/api/users/' + id,
            method: 'DELETE',
            success:
                function () {
                    getAllUsers();
                }
            ,
            error: function (error) {

            },
            complete: $('#deleteModal').modal('hide')
        })
    }) }

