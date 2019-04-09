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

function setEnabled() {
    var checkbox = $('input[name="enableCheckbox"]');
    let id = checkbox.attr("id");
    $.ajax({
        url: context.ajaxUrl + id + "?enabled=" + checkbox.prop('checked'),
        type: "GET"
    }).done(function () {
        $.get(context.ajaxUrl, function (data) {
            context.datatableApi.clear().rows.add(data).draw();
        });
        successNoty("Updated");
    });
}
