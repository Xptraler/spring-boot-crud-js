function fillUpdateModalForm(id) {
    $.ajax({
        url: '/users/' + id,
        method: "GET",
        dataType: "json",
        success: function (data) {
            $('#Id').val(data.id);
            $('#Name').val(data.name);
            $('#lastName').val(data.lastName);
            $('#modalPassword').val(data.password);
        },
        error: function (error) {
            alert(error);
        }
    })
}