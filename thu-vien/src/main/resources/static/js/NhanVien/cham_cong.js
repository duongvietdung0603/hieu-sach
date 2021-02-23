$(document).ready(function () {

    var url = "http://localhost:8088/";



    $("#click_luu").click(function () {

        var Action_dungGio = document.getElementsByClassName('Action_dungGio');
        var Action_cham = document.getElementsByClassName('Action_cham');
        var Action_vang = document.getElementsByClassName('Action_vang');

        var Cham_cong = [{
            id: "",
            status: "",
        }];

        // Lặp qua từng checkbox để lấy giá trị
        for (var i = 0; i < Action_dungGio.length; i++) {
            if (Action_dungGio[i].checked) {
                // get value, set checked flag or do whatever you need to
                Cham_cong.push({ id: Action_dungGio[i].value, status: 0 });
            }
        }

        // Lặp qua từng checkbox để lấy giá trị
        for (var i = 0; i < Action_cham.length; i++) {
            if (Action_cham[i].checked) {
                // get value, set checked flag or do whatever you need to
                Cham_cong.push({ id: Action_cham[i].value, status: 1 });
            }
        }

        // Lặp qua từng checkbox để lấy giá trị
        for (var i = 0; i < Action_vang.length; i++) {
            if (Action_vang[i].checked) {
                // get value, set checked flag or do whatever you need to
                Cham_cong.push({ id: Action_cham[i].value, status: 2 });
            }
        }
        Cham_cong.shift()


        var diemDanh = '';
        Cham_cong.forEach(function (item) {
            diemDanh += item.id + "_" + item.status + "-";
            console.log(item);
        });
        console.log(diemDanh);

        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'type': 'POST',
            'url': url + "nhan-vien/api/diem-danh/" + diemDanh,
            'dataType': 'json',
            success: function (data) {
                alert('Điểm danh thành công');
                window.location.href = 'diem-danh';
            },
            error: function () {
                console.log("error");
            }
        });

    });

});