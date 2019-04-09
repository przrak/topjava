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
    $(":checkbox").change(function () {
        let checkbox = $(this);
        let id = checkbox.closest("tr").prop('id');
        let checked = checkbox.prop('checked');
        $.ajax({
            url: context.ajaxUrl + id + "?enabled=" + checked,
            type: "GET"
        }).done(function () {
            $.get(context.ajaxUrl, function (data) {
                context.datatableApi.clear().rows.add(data).draw();
            });
            if (checked) {
                checkbox.closest("tr").removeClass("disabled").addClass("enabled");
            } else {
                checkbox.closest("tr").removeClass("enabled").addClass("disabled");
            }
            successNoty(checked ? "Enabled" : "Disabled");
        });
    });

});
