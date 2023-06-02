function email_validation_request() {
    var name = document.getElementById('signup-name').getElementsByClassName('form-style')[0].value;
    var email = document.getElementById('signup-email').getElementsByClassName('form-style')[0].value;
    var pw = document.getElementById('signup-pw').getElementsByClassName('form-style')[0].value;

    //validation
    if (name == ""){
        alert("이름을 입력해 주세요.");
        return
    }

    if (email == ""){
        alert("이메일을 입력해 주세요.");
        return
    }
    if (pw == ""){
        alert("비밀번호를 입력해 주세요.");
        return
    }

    document.getElementById('email_vali_check_btn').innerText = "Sending...";

    //정보 전송
    const email_request = new XMLHttpRequest();
    var url = 'https://geniustodo.shop/login/email?email=' + email;

    email_request.open('POST', url, true);
    email_request.setRequestHeader('Content-type', 'application/json');
    email_request.send();

    alert(email+ "로 메일을 전송했습니다. 메일을 확인해 주세요.");
    document.getElementById('signup-email-check').style.display = "block";
    document.getElementById('email_vali_check_btn').style.display = "none";
    document.getElementById('submit').style.display = "block";
    document.getElementById('signup-name').style.display = "none";
    document.getElementById('signup-email').style.display = "none";
    document.getElementById('signup-pw').style.display = "none";
}



function email_validation_check() {
    var email = document.getElementById('signup-email').getElementsByClassName('form-style')[0].value;
    var code = document.getElementById('signup-email-check').getElementsByClassName('form-style')[0].value;
    //작성했는지?
    if (code == "") {
        alert("이메일에서 받은 코드를 입력해 주세요. \n 전송한 이메일은 "+email+"입니다.");
        return
    }

    //정보 전송
    const email_check = new XMLHttpRequest();
    var url = 'https://geniustodo.shop/login/email?email=' + email + "&code=" + code;
    email_check.open('GET', url, false);
    //타입 JSON
    email_check.setRequestHeader('Content-type', 'application/json');
    email_check.send();

    if (email_check.status === 200) {
        submit_sign_up();
    } else {
        alert("코드가 다르거나 인증 시간이 지났습니다. 다시 시도해 주세요.");
    }

}


function submit_sign_up() {
    var name = document.getElementById('signup-name').getElementsByClassName('form-style')[0].value;
    var email = document.getElementById('signup-email').getElementsByClassName('form-style')[0].value;
    var pw = document.getElementById('signup-pw').getElementsByClassName('form-style')[0].value;

    // email validation

    //정보 전송
    const register_request = new XMLHttpRequest();
    register_request.open('POST', 'https://geniustodo.shop/login/register', false);
    register_request.setRequestHeader('Content-type', 'application/json');
    const requestBody = { name: name, email: email, password: pw };
    const requestBodyString = JSON.stringify(requestBody);
    register_request.send(requestBodyString);


    if (register_request.status === 200) {
        alert("회원가입이 완료 되었습니다.")
        window.location.reload();
    } else {
        alert('회원가입 실패');
    }
}

function log_in() {
    var email = document.getElementById('login-email').value;
    var pw = document.getElementById('login-pw').value;
    if((email=="")||(pw=="")){
        alert("이메일 또는 비밀번호를 입력해 주세요.")
        return
    }
    document.getElementById("log_in").innerText = "Logging in ...";
    //정보 전송
    const request = new XMLHttpRequest();
    request.open('POST', 'https://geniustodo.shop/login', false);
    request.setRequestHeader('Content-type', 'application/json');
    //타입 JSON
    const requestBody = { email: email, password: pw };
    const requestBodyString = JSON.stringify(requestBody);
    request.send(requestBodyString);

    if (request.status === 200) {
        sessionStorage.setItem('Authorization', request.responseText);
        window.location.replace('home');
    } else {
        alert('아이디 또는 비밀번호가 맞지 않습니다.');
        document.getElementById("log_in").innerText = "log in";
    }

}