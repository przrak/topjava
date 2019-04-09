// $(document).ready(function () {
$(function () {
    makeEditable({
            ajaxUrl: "ajax/admin/users/",
            datatableApi: $("#datatable").DataTable({
                "paging": false,
                "info": true,
                "columns": [
                    {
                        "data": "name"
                    },
                    {
                        "data": "email"
                    },
                    {
                        "data": "roles"
                    },
                    {
                        "data": "enabled"
                    },
                    {
                        "data": "registered"
                    },
                    {
                        "defaultContent": "Edit",
                        "orderable": false
                    },
                    {
                        "defaultContent": "Delete",
                        "orderable": false
                    }
                ],
                "order": [
                    [
                        0,
                        "asc"
                    ]
                ]
            })
        }
    );
});

function setActive() {
    let checkbox = $('input[type="checkbox"]');
    let id = checkbox.attr("id");
    let checked;
    alert("checkbox" + id);
    if (checkbox.is(":checked")) {
        checked = true;
    } else if (checkbox.is(":not(:checked)")) {
        checked = false;
    }
    var data = {};
    data['checked'] = checked;
    $.ajax({
        url: context.ajaxUrl + id,
        type: "PUT",
        data: data.serialize()
    }).done(function () {
        updateTable();
        successNoty("Updated");
    });
}
