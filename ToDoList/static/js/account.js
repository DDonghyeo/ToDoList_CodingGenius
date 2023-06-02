var modifybtn = document.getElementById('modify_btn');
var modifyCompleteLink = document.getElementById('modify_complete');

modifybtn.addEventListener('click', function () {
    var icon = document.getElementsByClassName('fa-lock')[0];
    icon.classList.remove("fa-lock");
    icon.classList.add("fa-lock-open");
    document.getElementById('logpass').disabled = false;
    document.getElementById('logpass').placeholder = "비밀번호 변경...";
    modifybtn.style.display = 'none'; // 버튼 숨기기
    modifyCompleteLink.style.display = 'inline-block'; // 링크 보이기
});



function load_account() {
    const request = new XMLHttpRequest();
    request.open('GET', "https://geniustodo.shop/user", false);
    request.setRequestHeader('Content-type', 'application/json');
    request.setRequestHeader('Authorization', sessionStorage.getItem('Authorization'));
    request.send();

    if (request.status === 200) {
        var response = request.responseText;
        var json = JSON.parse(response);
        document.getElementById('account-name').value = json.name;
        document.getElementById('account-email').value = json.email;

    }
}

load_account();


function withdrawal() {
    const request = new XMLHttpRequest();
    request.open('DELETE', "https://geniustodo.shop/user", false);
    request.setRequestHeader('Content-type', 'application/json');
    request.setRequestHeader('Authorization', sessionStorage.getItem('Authorization'));
    request.send();

    if (request.status === 200) {
        alert("회원 탈퇴가 완료 되었습니다.");
        window.location.replace("/");
    }else{
        alert("회원 탈퇴 실패");
    }
}

function pw_modify(){
    var icon = document.getElementsByClassName('fa-lock-open')[0];
    icon.classList.remove("fa-lock-open");
    icon.classList.add("fa-lock");
    document.getElementById('logpass').value = "";
    document.getElementById('logpass').disabled = true;
    document.getElementById('logpass').placeholder ="**********";
    modifybtn.style.display = "inline-flex";
    modifyCompleteLink.style.display = "none";


    const request = new XMLHttpRequest();
    request.open('POST', "https://geniustodo.shop/user", false);
    request.setRequestHeader('Content-type', 'application/json');
    request.setRequestHeader('Authorization', sessionStorage.getItem('Authorization'));
    var new_password = document.getElementById('logpass').value;
    request.send(new_password);
    
    if (request.status === 200) {
        alert("비밀번호 변경이 완료 되었습니다.")
    }
}