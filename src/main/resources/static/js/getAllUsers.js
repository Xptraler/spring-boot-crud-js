$(document).ready(function () {
    getAllUsers();
});
$(' #deleteButton').on('click', function (event) {
    event.preventDefault();
    const href = $(this).attr('href');
    $.get(href, function (data) {
        $('#deleteId').val(data.id);
        $('#deleteName').val(data.name);
        $('#deleteLastName').val(data.lastName);
        $('#deleteEmail').val(data.email);
        $('#deletePassword').val(data.password);
        $('#deleteAge').val(data.age);
        $('#deleteRole').val(data.role);
    });
    $('#deleteModal').modal('show');

});

function getAllUsers() {
    $.ajax({
        url: "/api/users",
        method: "GET",
        dataType: "json",
        success: function (data) {
            let tableBody = $('#getAll tbody');
            let stringRole
            tableBody.empty()
            $(data).each(function (i, user) {
                if (user.roles.length === 2) {
                    stringRole = "ADMIN USER"
                } else {
                    stringRole = JSON.stringify(user.roles).substr(22, 5).replaceAll("\"", "")
                }
                tableBody.append(`<tr>
                                       <td><span>${user.id}</span></td>
                                                <td><p>${user.name}</p></td>
                                                <td><p>${user.lastName}</p></td>
                                                <td><p>${user.age}</p></td>
                                                <td><p>${user.email}</p></td>
                                                <td><p>${stringRole}</p></td>
                                                <td><button id="updateButton" class="btn btn-info btn-sm" role="button" data-toggle="modal" 
                    data-target="#exampleModal" onclick = "editUser(${user.id})"> 
                    Edit 
                    </button></td>
                                                <td><button  id="deleteButton" class="btn btn-danger btn-sm" 
                                                role="button" onclick = "deleteUser(${user.id})">Delete </button></td>
                                            </tr>`);
            })
        },
        error: function (error) {
            alert(error);
        }
    })
}


