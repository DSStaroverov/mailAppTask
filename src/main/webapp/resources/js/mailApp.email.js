const emailUrl = "ajax/email/";

function choose(id) {
    $.ajax({
        type: "GET",
        url: context.ajaxUrl+id
    }).done(function () {
        window.location = 'folder'
    })
}

function renderChooseBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='choose(" + row.id + ");' ><span class='fa fa-sign-in'></span></a>";
    }
}

$(function () {
    makeEditable({
        ajaxUrl: emailUrl,
        datatableOpts: {
            "columns": [
                {
                    "data": "address"
                },
                {
                    "orderable": false,
                    "defaultContent": "",
                    "render": renderChooseBtn
                },
                {
                    "orderable": false,
                    "defaultContent": "",
                    "render": renderDeleteBtn
                }

            ]
        },
        updateTable: function () {
            $.ajax({
                type: "GET",
                url: emailUrl
            }).done(updateTableByData);
        }
    });
});
