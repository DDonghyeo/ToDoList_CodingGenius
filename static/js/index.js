function email_validation_request() {
    alert("email request 전송");
    var email = document.getElementById('signup-email').innerText;
    //정보 전송
    const email_request = new XMLHttpRequest();
    var url = 'https://geniustodo.shop/login/email?email=' + email;
    email_request.open('POST', url, true);
    //타입 JSON
    email_request.setRequestHeader('Content-type', 'application/json');
    email_request.send(JSON.stringify(order_final))

    email_request.onreadystatechange = function (event) {
        // 1) 데이터를 다 받았고, 2) 응답코드 200(성공)을 받았는지 체크
        if (email_reques.readyState == 4 && email_reques.status == 200) {
            alert("메일을 전송했습니다. 메일을 확인해 주세요.");
            document.getElementById('signup-email-check').style.display = "block";
            document.getElementById('email_vali_check_btn').style.display = "none";
            document.getElementById('submit').style.display = "block";
            document.getElementById('signup-name').style.display = "block";
            document.getElementById('signup-email').style.display = "block";
            document.getElementById('signup-pw').style.display = "block";
        } else {
            alert("메일 전송 요청 실패");
        }
    }


}



function email_validation_check() {
    var code = document.getElementById('signup-email-check').innerText;
    //작성했는지?
    if (code == "") {
        alert("이메일에서 받은 code를 입력해 주세요.");
        return
    }

    //정보 전송
    const email_check = new XMLHttpRequest();
    var url = 'https://geniustodo.shop/login/email?email=' + email + "&code=" + code;
    email_check.open('GET', url, true);
    //타입 JSON
    email_check.setRequestHeader('Content-type', 'application/json');
    email_check.send(JSON.stringify(order_final))

    email_check.onreadystatechange = function (event) {
        // 1) 데이터를 다 받았고, 2) 응답코드 200(성공)을 받았는지 체크
        if (email_check.readyState == 4 && email_check.status == 200) {
            alert("이메일 인증이 확인되었습니다.");
        } else {
            alert("코드가 다르거나 인증 시간이 지났습니다. 다시 시도해 주세요.");
        }
    }



}


function submit_sign_up() {
    var name = document.getElementById('signup-name').innerText;
    var email = document.getElementById('signup-email').innerText;
    var pw = document.getElementById('signup-pw').innerText;

    // email validation








    //정보 전송
    const request = new XMLHttpRequest();
    request.open('POST', 'localhost:3001/order/create', true);
    //타입 JSON
    request.setRequestHeader('Content-type', 'application/json');
    request.send(JSON.stringify(order_final))


    request.onreadystatechange = function (event) {
        // 1) 데이터를 다 받았고, 2) 응답코드 200(성공)을 받았는지 체크
        if (request.readyState == 4 && request.status == 200) {
            //local storage 초기화
            localStorage.clear();
            location.replace('/complete.html')
        } else {
            alert("주문 정보 전송 실패")
        }
    }
}

