function choose(id) {
    // $("#modalTitle").html('Edit');
    // $.get(context.ajaxUrl + id, function (data) {
    //     $.each(data, function (key, value) {
    //         form.find("input[name='" + key + "']").val(value);
    //     });
    //     $('#editRow').modal();
    // });
}

function renderChooseBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='choose(" + row.id + ");' ><span class='fa fa-arrow-circle-right'></span></a>";
    }
}

$.ajaxSetup({
    converters: {
        "text json": function (stringData) {
            const json = JSON.parse(stringData);
            $(json).each(function () {
                this.sendTime = this.sendTime.replace('T', ' ').substr(0, 16);
            });
            return json;
        }
    }
});

$(function () {
    makeEditable({
        ajaxUrl: letterUrl,
        datatableOpts: {
            "columns": [
                {
                    "data": "sender"
                },
                {
                    "data": "recipient"
                },
                {
                    "data": "title"
                },
                {
                    "data": "sendTime"
                },
                {
                    "orderable": false,
                    "defaultContent": "",
                    "render": renderOpenLetterBtn
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
                url: letterUrl
            }).done(updateTableByData);
        }
    });
});
