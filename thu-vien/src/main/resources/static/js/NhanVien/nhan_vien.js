$(function () {

    var url = "http://localhost:8080/auth/";



    $("#click_them").click(function () {
        $("#active_them").addClass("show_active")
        $("#active_sua").removeClass("show_active")
    });



    $(".click_sua").click(function () {
        $("#active_sua").addClass("show_active")
        $("#active_them").removeClass("show_active")

        var t1 = $(this).parent().parent().siblings(".t1").html();
        var t2 = $(this).parent().parent().siblings(".t2").html();
        var t3 = $(this).parent().parent().siblings(".t3").html();
        var t4 = $(this).parent().parent().siblings(".t4").html();
        var t5 = $(this).parent().parent().siblings(".t5").html();
        var t6 = $(this).parent().parent().siblings(".t6").html();
        var t7 = $(this).parent().parent().siblings(".t7").html();
        var t8 = $(this).parent().parent().siblings(".t8").html();
        var t9 = $(this).parent().parent().siblings(".t9").html();

        $("#t1_edit").val(t1);
        $("#t2_edit").val(t2);
        $("#t3_edit").val(t3);
        $("#t4_edit").val(t4);
        $("#t5_edit").val(t5);
        $("#t6_edit").val(t6);
        $("#t7_edit").val(t7);
        $("#t8_edit").val(t8);
        $("#t9_edit").val(t9);

    });

    $(".button_huy").click(function () {
        $("#active_them").removeClass("show_active")
        $("#active_sua").removeClass("show_active")
    });

    $("#button_sua").click(function () {
        $("#active_them").removeClass("show_active")
        $("#active_sua").removeClass("show_active")

        var id = $("#t1_edit").val();
        var hoTen = $("#t2_edit").val();
        var ngaySinh = $("#t3_edit").val();
        var gioiTinh = $("#t4_edit").val();
        var diaChi = $("#t5_edit").val();
        var chucVu = $("#t6_edit").val();
        var heSoLuong = $("#t7_edit").val();
        var sdt = $("#t8_edit").val();
        var email = $("#t9_edit").val();



        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'type': 'POST',
            'url': url + "nhan-vien/edit/" + id,
            'data': JSON.stringify({
                hoTen: hoTen,
                ngaySinh: ngaySinh,
                gioiTinh: gioiTinh,
                diaChi: diaChi,
                chucVu: chucVu,
                heSoLuong: heSoLuong,
                sdt: sdt,
                email: email,
            }),
            'dataType': 'json',
            success: function (data) {
                alert('Sửa thành công');
                window.location.href = 'nhan-vien';
            },
            error: function () {
                console.log("error");
            }
        });

    });


    $("#button_them_moi").click(function () {
        $("#active_them").removeClass("show_active")
        $("#active_sua").removeClass("show_active")



        var hoTen = $("#t1_add").val();
        var ngaySinh = $("#t2_add").val();
        var gioiTinh = $("#t3_add").val();
        var diaChi = $("#t4_add").val();
        var chucVu = $("#t5_add").val();
        var heSoLuong = $("#t6_add").val();
        var sdt = $("#t7_add").val();
        var email = $("#t8_add").val();

        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'type': 'POST',
            'url': "http://localhost:8080/nhan-vien",
            'data': JSON.stringify({
                hoTen: hoTen,
                ngaySinh: ngaySinh,
                gioiTinh: gioiTinh,
                diaChi: diaChi,
                chucVu: chucVu,
                heSoLuong: heSoLuong,
                sdt: sdt,
                email: email,
            }),
            'dataType': 'json',
            success: function (data) {
                window.location.href = 'nhan-vien';
            },
            error: function () {
                console.log("error");
            }
        });
    });

});
