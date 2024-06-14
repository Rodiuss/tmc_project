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
        lengthMenu: [10, 25, 50, 75, 100],
        ajax: {
            contentType: 'application/json',
            url: 'contractors/ajax',
            type: 'POST',
            data: function (d) {
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
                    data: "name",
                    render: function (data, type, row) {
                        return "<div href='" + row.id + "' class='m_c_e-btn-edit' style='text-decoration:underline;cursor:pointer' title='Редактировать'>" + row.name + "</div>";
                    },
                    width: '200px'
                },
                {
                    data: "legalAddress",
                    width: '200px'
                },
                {
                    data: "actualAddress",
                    width: '200px'
                },
                {
                    data: "tin",
                    width: '70px'
                },
                {
                    data: "kpp",
                    width: '70px'
                },
                {
                    data: "ogrn",
                    width: '70px'
                },
                {
                    data: "phoneNumber",
                    width: '70px'
                },
                {
                    data: "email",
                    width: '150px'
                },
                {
                    data: "website",
                    width: '150px'
                },
                {
                    data: "accountNumber",
                    width: '70px'
                },
                {
                    data: "bank.name",
                    width: '200px'
                },
                {
                    data: "providerType.name",
                    width: '100px'
                },
                {
                    data: "contractorStatus.name",
                    width: '110px'
                },
                {
                    data: "delete",
                    render: function (data, type, row) {
                        return "<a href='" + row.id + "' role='button' class='btn-sm btn-danger m_c_e-btn-delete' style='text-decoration: none' title='Удалить'>&#215</a>"
                    },
                    searchable: false,
                    orderable: false,
                    className: "text-center",
                    width: '50px'
                }
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