$(document).ready(function() {
    const token = $("meta[name='_csrf']").attr("content");
    const header = $("meta[name='_csrf_header']").attr("content");

    $("#datatable").DataTable({
        dom: "<'row py-0'<'col-sm-12 py-0'B>><'row py-0'<'col-sm-8 py-1 _add_filter'><'col-sm-4 py-1'f>><'row py-0'<'col-sm-12 py-0'tr>><'row pt-1'<'col-sm-12 col-md-3'l><'col-sm-12 col-md-6'p><'col-sm-12 col-md-3'i>>",
        stateSave: true,
        destroy: true,
        processing: true,
        serverSide: true,
        scrollX: true,
        pageLength: 25,
        lengthMenu: [ 10, 25, 50, 75, 100 ],
        ajax: {
            contentType: 'application/json',
            url: '/document/acceptance_document/ajax',
            type: 'POST',
            data: function(d) {
                return JSON.stringify(d);
            },
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            }
        },
        bProcessing: true,
        columns:
            [
                {
                    data: "id",
                    width: '70px'
                },
                {
                    data: "documentNumber",
                    render: function (data, type, row) {
                        return "<div href='" + row.id + "' class='m_c_e-btn-edit' style='text-decoration:underline;cursor:pointer' title='Редактировать'>" + row.documentNumber + "</div>";
                    },
                    width: '400px'
                },
                {
                    data: "documentDate",
                    width: '150px'
                },
                {
                    data: "contractor",
                    render: function (data, type, row) {
                        return ((row.contractor.surname) ? (row.contractor.surname + " ") : ("")) + row.contractor.name + " " + row.contractor.patronymic;
                    },
                    width: '200px'
                },
            ],
        language: {
            "processing": "Подождите...",
            "search": "Поиск:",
            "lengthMenu": "Показать: _MENU_",
            "info": "C _START_ до _END_ из _TOTAL_",
            "infoEmpty": "C 0 до 0 из 0",
            "infoFiltered": "(из _MAX_)",
            "infoPostFix": "",
            "loadingRecords": "Загрузка...",
            "zeroRecords": "Данные отсутствуют.",
            "emptyTable": "В таблице отсутствуют данные",
            "paginate": {
                "first": "<i class='angle-double-left'></i>",
                "previous": "<i class='fas fa-caret-left'></i>",
                "next": "<i class='fas fa-caret-right'></i>",
                "last": "<i class='angle-double-right'></i>"
            },
            "aria": {
                "sortAscending": ": активировать для сортировки столбца по возрастанию",
                "sortDescending": ": активировать для сортировки столбца по убыванию"
            }
        },
        'initComplete': function () {
            $("#wRC").css("max-width", $(this).width());
        },
    });
});

$(document).ready(function () {
    const token = $("meta[name='_csrf']").attr("content");
    const header = $("meta[name='_csrf_header']").attr("content");

    function create_s_datatable (id) {
        $("#s_datatable").DataTable({
            // dom: "<'row py-0'<'col-sm-12 py-0'B>><'row py-0'<'col-sm-8 py-1 _add_filter'><'col-sm-4 py-1'f>><'row py-0'<'col-sm-12 py-0'tr>><'row pt-1'<'col-sm-12 col-md-3'l><'col-sm-12 col-md-6'p><'col-sm-12 col-md-3'i>>",
            stateSave: true,
            destroy: true,
            processing: true,
            serverSide: true,
            pageLength: 25,
            lengthMenu: [ 10, 25, 50, 75, 100 ],
            // bAutoWidth: true,
            ajax: {
                contentType: 'application/json',
                url: '/document/acceptance_document_item/ajax/' + id,
                type: 'POST',
                data: function(d) {
                    return JSON.stringify(d);
                },
                beforeSend: function (xhr) {
                    xhr.setRequestHeader(header, token);
                }
            },
            bProcessing: true,
            columns:
                [
                    {
                        data: "id"
                    },
                    {
                        data: "product.name"
                    },
                    {
                        data: "quantity"
                    },
                ],
            language: {
                "processing": "Подождите...",
                "search": "Поиск:",
                "lengthMenu": "Показать: _MENU_",
                "info": "C _START_ до _END_ из _TOTAL_",
                "infoEmpty": "C 0 до 0 из 0",
                "infoFiltered": "(из _MAX_)",
                "infoPostFix": "",
                "loadingRecords": "Загрузка...",
                "zeroRecords": "Данные отсутствуют.",
                "emptyTable": "В таблице отсутствуют данные",
                "paginate": {
                    "first": "<i class='angle-double-left'></i>",
                    "previous": "<i class='fas fa-caret-left'></i>",
                    "next": "<i class='fas fa-caret-right'></i>",
                    "last": "<i class='angle-double-right'></i>"
                },
                "aria": {
                    "sortAscending": ": активировать для сортировки столбца по возрастанию",
                    "sortDescending": ": активировать для сортировки столбца по убыванию"
                }
            }
        });
    }

    $('.m_c_e-form').on('edit', function (e, res) {
        res = JSON.parse(res);

        create_s_datatable(res.id)

        $('#s_datatable').css('width', '100%');
    })
});

