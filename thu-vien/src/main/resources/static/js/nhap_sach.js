$(function () {

    var url = "http://localhost:8088/";


    
    $("#click_them").click(function () {
        $("#active_them").addClass("show_active")
        $("#active_sua").removeClass("show_active")
    });



    $(".click_sua").click(function () {
        $("#active_sua").addClass("show_active")
        $("#active_them").removeClass("show_active")

        var t1 = $(this).parent().parent().siblings(".t1").html();
        var t2 = $(this).parent().parent().siblings(".t2").html();
        var t5 = $(this).parent().parent().siblings(".t5").html();
        var t6 = $(this).parent().parent().siblings(".t6").html();
        var t7 = $(this).parent().parent().siblings(".t7").html();

        $("#t1_edit").val(t1);
        //truyền selected cho select
        $("#t2_edit").val(t2).change();
        $("#t3_edit").val(t5);
        $("#t4_edit").val(t6);
        $("#t5_edit").val(t7);
    });

    $(".button_huy").click(function () {
        $("#active_them").removeClass("show_active")
        $("#active_sua").removeClass("show_active")
    });

    $("#button_sua").click(function () {
        $("#active_them").removeClass("show_active")
        $("#active_sua").removeClass("show_active")

        var id = $("#t1_edit").val();
        var id_sach = $("#t2_edit").val();
        var soLuong = $("#t3_edit").val();
        var tongTien = $("#t4_edit").val();
        var nhaCC = $("#t5_edit").val();

        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'type': 'POST',
            'url':  url + "nhap-sach/edit/" + id,
            'data': JSON.stringify({ soLuong: soLuong, sach: { id: id_sach },nhaCungCap:  { id: nhaCC }, tongTien: tongTien }),
            'dataType': 'json',
            success: function (data) {
                window.location.href = 'nhap-sach';
            },
            error: function () {
                console.log("error");
            }
        });

    });


    $("#button_them_moi").click(function () {
        $("#active_them").removeClass("show_active")
        $("#active_sua").removeClass("show_active")

        var id_sach = $("#t1_add").val();
        var soLuong = $("#t2_add").val();
        var tongTien = $("#t3_add").val();
        var nhaCC = $("#t4_add").val();

        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'type': 'POST',
            'url':  url + "nhap-sach",
            'data': JSON.stringify({ soLuong: soLuong, sach: { id: id_sach }, nhaCungCap:  { id: nhaCC }, tongTien: tongTien }),
            'dataType': 'json',
            success: function (data) {
                window.location.href = 'nhap-sach';
            },
            error: function () {
                console.log("error");
            }
        });
    });

});
