$(document).ready(function () {
    $("form input[type=checkbox]").on("input", function () {
        $(this).val($(this).prop("checked"));
    });

    $(document).on('submit', '.m_c_e-form', function (e) {
        e.preventDefault();

        const token = $("meta[name='_csrf']").attr("content");
        const header = $("meta[name='_csrf_header']").attr("content");

        const f_dt = new FormData($(this)[0]);
        const jq_add = $(this).attr('mce_loc') ? '[mce_loc="'+ $(this).attr('mce_loc') +'"]' : '';

        $.each($("form input[type=checkbox]"), function (key, val) {
            if (!$(this).is(':checked')) {
                f_dt.append($(val).attr('name'), "false")
            }
        });

        $.ajax({
            url: $(this).attr('action'),
            data: f_dt,
            processData: false,
            contentType: false,
            type: $(this).attr('_method'),
            dataType: 'text',
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (res) {
                const m_c_e_form = $('.m_c_e-form' + jq_add);

                if (res.error) {
                    m_c_e_form.attr('action', m_c_e_form.attr('_save_action'));
                    m_c_e_form.attr('_method', m_c_e_form.attr('_save_method'));
                    $('.m_c_e-form' + jq_add + ':input[name!="_token"]').removeClass('is-invalid');
                    $('.m_c_e-form' + jq_add + '.invalid-feedback').remove();
                    $("<div class=\"invalid-feedback mt-0\">" + res.message + "</div>")
                        .appendTo($('form.m_c_e-form' + jq_add + '[name=' + res.name + ']').parent());
                    $('.m_c_e-form' + jq_add + '[name=' + res.name + ']').addClass('is-invalid').focus();
                    return;
                }

                $('.dataTable[id]').DataTable().ajax.reload(null, false);
                $('.m_c_e-modal' + jq_add).modal('hide');
                $('.m_c_e-block' + jq_add).addClass('d-none');

                m_c_e_form.trigger('stored', res);
            },
            error: function (jqxhr) {
                alert('Ошибка: ' + jqxhr.responseText);
                $('.m_c_e-modal' + jq_add).modal('hide');
                $('.m_c_e-block' + jq_add).addClass('d-none');
            }
        });
    });

    $(document).on('click', '.m_c_e-btn-create', function (e) {
        e.preventDefault();

        const jq_add = $(this).attr('mce_loc') ? '[mce_loc="'+ $(this).attr('mce_loc') +'"]' : '';
        const link = window.location.origin + ($(this).attr('mce_loc') === undefined ? window.location.pathname : $(this).attr('mce_loc'));
        $.ajax({
            url: link + '/create',
            type: 'GET',
            dataType: 'text',
            success: function (res) {
                const m_c_e_form = $('.m_c_e-form' + jq_add);

                m_c_e_form.trigger('create', res);

                _mce_store_fields(res, jq_add);

                m_c_e_form
                    .attr('action', link)
                    .attr('_method', 'POST')
                    .attr('_save_action', link)
                    .attr('_save_method', 'POST');

                $('.m_c_e-btn-save' + jq_add).text('Сохранить');
                $('.m_c_e-btn-save-new' + jq_add).addClass('disabled');

                const m_c_e_label = $('#m_c_eLabel' + jq_add)
                const label_add_index = m_c_e_label.text().indexOf(':');

                if (label_add_index === -1) {
                    m_c_e_label.text(m_c_e_label.text() + ': добавление');
                } else {
                    m_c_e_label.text(m_c_e_label.text().substring(0, label_add_index) + ': добавление');
                }

                m_c_e_form.trigger('creating', res);
                $('.m_c_e-modal' + jq_add).modal('show');
                $('.m_c_e-block' + jq_add).removeClass('d-none');
            },
            error: function (jqxhr) {
                alert('Ошибка: ' + jqxhr.responseText);
            }
        });
    });

    $(document).on('click', '.m_c_e-btn-edit', function (e) {
        e.preventDefault();
        const that = $(this);

        const jq_add = $(this).attr('mce_loc') ? '[mce_loc="'+ $(this).attr('mce_loc') +'"]' : '';
        const link = window.location.origin + ($(this).attr("mce_loc") === undefined ? window.location.pathname : $(this).attr("mce_loc")) + '/' + that.attr('href');

        $.ajax({
            url: link,
            type: 'GET',
            dataType: 'text',

            success: function (res) {
                const m_c_e_form = $('.m_c_e-form' + jq_add);

                m_c_e_form.trigger('edit', res);
                _mce_store_fields(res, jq_add);

                m_c_e_form
                    .attr('action', link)
                    .attr('_method', 'PUT')
                    .attr('_save_action', link)
                    .attr('_save_method', 'PUT');

                $('.m_c_e-btn-save' + jq_add).text('Сохранить');
                $('.m_c_e-btn-save-new' + jq_add).removeClass('disabled');

                const m_c_e_label = $('#m_c_eLabel' + jq_add);
                const label_add_index = m_c_e_label.text().indexOf(':');

                if (label_add_index === -1) {
                    m_c_e_label.text(m_c_e_label.text() + ': редактирование');
                } else {
                    m_c_e_label.text(m_c_e_label.text().substring(0, label_add_index) + ': редактирование');
                }

                m_c_e_form.trigger('editing', res);

                $('.m_c_e-modal' + jq_add).modal('show');
                $('.m_c_e-block' + jq_add).removeClass('d-none');
            },
            error: function (jqxhr) {
                alert('Ошибка: ' + jqxhr.responseText);
            }
        });
    });

    $(document).on('click', '.m_c_e-btn-delete', function (e) {
        e.preventDefault();

        const token = $("meta[name='_csrf']").attr("content");
        const header = $("meta[name='_csrf_header']").attr("content");

        const that = $(this);

        $('.m_c_e-acc-del').modal('show');

        $(document).on('click', '.m_c_e-acc-del-btn', function (e) {
            $.ajax({
                url: window.location.origin + ($(this).attr("mce_loc") === undefined ? window.location.pathname : $(this).attr("mce_loc")) + '/' + that.attr('href'),
                type: 'DELETE',
                dataType: 'text',
                beforeSend: function (xhr) {
                    xhr.setRequestHeader(header, token);
                },
                success: function (res) {
                    if (res.error) {
                        alert(res.message);
                        return;
                    }
                    $(".dataTable[id]").DataTable().ajax.reload(null, false);
                },
                error: function (jqxhr) {
                    alert('Ошибка: ' + jqxhr.responseText);
                }
            });
            $('.m_c_e-acc-del').modal('hide');
            $(document).off('click', '.m_c_e-acc-del-btn');
        });
    });

    $(document).on('click', '.m_c_e-btn-save-new', function (e) {
        if ($(this).hasClass('disabled')) return;

        $('.m_c_e-form')
            .attr('action', window.location.origin + window.location.pathname)
            .attr('_method', 'POST');
        $('.m_c_e-btn-save').click();
    });

    $(document).on('click', '.m_c_e-btn-group-add', function (e) {
        e.preventDefault();

        const new_element = $(this).parents('.m_c_e-group-element').clone().appendTo($(this).parents('.m_c_e-group-element').parent());
        $('.m_c_e-form').trigger('group-add', new_element);
        $('.m_c_e-form .m_c_e-group-element:gt(0) .m_c_e-btn-group-delete').prop('disabled', false);
    });

    $(document).on('click', '.m_c_e-btn-group-delete', function (e) {
        if ($(this).hasClass('disabled')) return;
        $(this).parents('.m_c_e-group-element').remove();
        $('.m_c_e-form').trigger('group-delete');
    });
});

function _mce_store_fields (res, jq_add) {
    $('.m_c_e-form' + jq_add + ' :input[name!="_token"]').val('').removeClass('is-invalid');
    $('.m_c_e-form' + jq_add + ' .invalid-feedback').remove();

    $('form' + jq_add + ' .m_c_e-group').each(function(index, element) {
        $(element).find('.m_c_e-group-element:gt(0)').remove();
    });

    if (res === '') return;

    let r;
    try {
        r = JSON.parse(res);
    }
    catch (e) {
        return;
    }

    Object.keys(r).forEach(function(key) {
        let n = 'form' + jq_add + ' [name="' + key + '"]';
        if (r[key] && typeof r[key] == 'object' && !$.isArray(r[key])) {
            $(n).val(r[key].id ? r[key].id : r[key].programName).trigger('change');
            $(n).find('option[value="' + r[key].id ? r[key].id : r[key].programName + '"]').prop('selected', 'true');
            return;
        }

        if (r[key] && $.isArray(r[key]) && typeof r[key][0] == 'object') {
            let key_array = []
            r[key].forEach(function (item) {
                key_array.push(item.id ? item.id : item.programName);
                $(n).find('option[value="' + item.id ? item.id : item.programName + '"]').prop('selected', 'true');
            });
            $(n).val(key_array).trigger('change');
            return;
        }

        $(n).val(r[key] ? r[key] : '').trigger('change');
        if ($(n).length && $(n)[0].tagName !== 'SELECT') {
            $(n).text(r[key]);
        }

        if ($(n).length && $(n)[0].tagName === 'SELECT') {
            $(n).find("option[value='"+r[key]+"']").prop('selected','true');
        }

        if ($(n).prop('type') === 'checkbox') {
            $(n).prop('checked', r[key]);
        }
    });
}

$(document).ready(function () {
    let time_out;
    let phone = $("form [type=tel]");

    function check_tel(tel) {
        tel = tel
            .trim()
            .replaceAll("(", "")
            .replaceAll(")", "")
            .replaceAll("+7", "8")
            .replaceAll("-", "")
            .replaceAll(" ", "");

        if (!tel) return true;
        if (isNaN(parseInt(tel))) return false;
        if ((tel.length !== 11 || tel[0] !== "8") && (tel.length !== 6) && (tel.length !== 3)) return false;

        tel = [tel.slice(0, 1), "(", tel.slice(1, 4), ") ", tel.slice(4, 7), "-",
            tel.slice(7, 9), "-", tel.slice(9)].join('');

        phone.val(tel);

        return true;
    }

    function validate_tel(e=null){
        if (!check_tel(phone.val()))
        {
            if (e) e.preventDefault();

            $("form :input[name!='_token']").removeClass("is-invalid");
            $("form .invalid-feedback").remove();
            $("<div class=\"invalid-feedback mt-0\" id=\"invalid_phoneNumber\">Неправильный формат</div>")
                .appendTo(phone.parent());
            phone.addClass("is-invalid");
            phone.focus();
        } else{
            $("form #invalid_phoneNumber").remove();
            phone.removeClass("is-invalid");
        }
    }

    function time_out_func() {
        validate_tel()
    }

    phone
        .on('keyup', function () {
            clearTimeout(time_out);
            time_out = setTimeout(time_out_func, 1000);
        })
        .on('keydown', function () {
            clearTimeout(time_out);
        });

    $(document).on("click",".m_c_e-modal [type=\"submit\"]", function (e) {
        if (!phone.length) return;
        validate_tel(e);
    });
});
