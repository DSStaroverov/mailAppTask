const folderUrl = "ajax/folder/";

function choose(id) {
    $.ajax({
        type: "GET",
        url: context.ajaxUrl+id
    }).done(function () {
        window.location = 'letter'
    })
}

function renderChooseBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='choose(" + row.id + ");' ><span class='fa fa-folder-open'></span></a>";
    }
}

$(function () {
    makeEditable({
        ajaxUrl: folderUrl,
        datatableOpts: {
            "columns": [
                {
                    "data": "name"
                },
                {
                    "orderable": false,
                    "defaultContent": "",
                    "render": renderChooseBtn
                },
                {
                    "orderable": false,
                    "defaultContent": "",
                    "render": renderEditBtn
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
                url: folderUrl
            }).done(updateTableByData);
        }
    });
});
