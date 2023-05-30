function name_load() {
  const request = new XMLHttpRequest();
  request.open('GET', "https://geniustodo.shop/user/name", false);
  request.setRequestHeader('Content-type', 'application/json');
  request.setRequestHeader('Authorization', sessionStorage.getItem('Authorization'));
  request.send();

  if (request.status === 200) {
    var response = request.responseText;
    var json = JSON.parse(response);
    document.getElementById('account_name').innerText = json.name;
  }else{
    window.location.replace("/");
  }

}
name_load();

function init_todo(){
  var target = document.getElementsByClassName('todo-list')[0];
  target.innerHTML = "";
}


function log_out(){
  sessionStorage.removeItem('Authorization');
  alert("로그아웃 되었습니다.");
  window.location.replace("/");
}



function load_todo() {
  const request = new XMLHttpRequest();
  request.open('GET', "https://geniustodo.shop/todo", false);
  request.setRequestHeader('Content-type', 'application/json');
  request.setRequestHeader('Authorization', sessionStorage.getItem('Authorization'));
  request.send();

  if (request.status === 200) {
    var response = request.responseText;
    var data = JSON.parse(response);
    console.log(JSON.parse(response));

    var target = document.getElementsByClassName('todo-list')[0];

    for (var i = 0; i < data.length; i++) {
      console.log(i);
      console.log("create todo");
      var obj = data[i];
      if (obj.complete) {
        target.innerHTML += `
        <div class="todo complete">
          <div class="todo-header">
            <div class="todo_info">
              <span class="todo-name">${obj.name}</span>
              <span class="todo-exp">${obj.expiration}</span>
            </div>
            <div class="tool_box">
              <button class="edit-button fa-solid fa-pencil" ></button>
              <button class="fa-regular fa-circle-check complete_btn" onclick="complete_todo(this)"></button>
              <button class="delete-button fa-solid fa-trash-can" onclick="delete_todo(this)"></button>
              <buttton class="fa-solid fa-plus add-button" onlcick="add_todo(this)"></button>
            </div>
          </div>
        `;
      } else {
        target.innerHTML += `
        <div class="todo">
          <div class="todo-header">
            <div class="todo_info">
              <span class="todo-name">${obj.name}</span>
              <span class="todo-exp">${obj.expiration}</span>
            </div>
            <div class="tool_box">
              <button class="edit-button fa-solid fa-pencil" onclick = "edit_todo(this)"></button>
              <button class="fa-regular fa-circle-check complete-button" onclick="complete_todo(this)"></button>
              <button class="delete-button fa-solid fa-trash-can" onclick="delete_todo(this)"></button>
              <buttton class="fa-solid fa-plus add-button" onlcick="add_todo(this)"></button>
            </div>
          </div>
        `;
      }
      var worklist = obj.workArrayList;
      console.log(worklist);
      if (worklist != null) {
        for (var j = 0; j < worklist.length; j++) {
          console.log("create work")
          if(worklist[j].complete){
            target.innerHTML += `
            <div class="work complete">
              <div class="work-details-header">
                <span>${worklist[j].name}</span>
                <div class="tool_box" >
                  <button class="memo-button"></button>
                  <button class="complete-button"><span class="fa-regular fa-circle-check" style="color: #000000;"></span></button>
                  <button class="delete-button"></button>
                </div>
              </div>
            </div >
            <div class="work-memo">
              <div class="work-memo-header">
                <span>${worklist[j].memo}</span>
                <div class = "tool_box">
                  <button class="delete-button complete_btn"></button> 
                </div>
              </div >
            </div >
            
              `;
          } else{
            target.innerHTML += `
            <div class="work">
              <div class="work-details-header">
                <span>${worklist[j].name}</span>
                <div class="tool_box" >
                  <button class="memo-button"></button>
                  <button class="complete-button"><span class="fa-regular fa-circle-check" style="color: #000000;"></span></button>
                  <button class="delete-button"></button>
                </div>
              </div>
            </div >
            <div class="work-memo">
              <div class="work-memo-header">
                <span>${worklist[j].memo}</span>
                <div class = "tool_box">
                  <button class="delete-button"></button> 
                </div>
              </div >
            </div >
            
              `;
          }
        }
      }

    }

  } else {
    alert("불러오기 실패");
    window.location.replace("/");
  }
}

load_todo();




// document.addEventListener('DOMContentLoaded', function () {
//   const taskToggleButtons = document.querySelectorAll('.task-toggle-button');
//   const memoToggleButtons = document.querySelectorAll('.memo-toggle-button');

//   taskToggleButtons.forEach(function (button) {
//     button.addEventListener('click', function () {
//       const details = this.parentNode.parentNode.querySelector('.task-details');
//       details.classList.toggle('active');
//     });
//   });

//   memoToggleButtons.forEach(function (button) {
//     button.addEventListener('click', function () {
//       const memo = this.parentNode.parentNode.parentNode.parentNode.querySelector('.task-memo');
//       memo.classList.toggle('active');
//     });
//   });
// });


function delete_todo(e){
  var name = e.parentNode.parentNode.getElementsByClassName('todo_info')[0].getElementsByClassName('todo-name')[0].innerText;
  const request = new XMLHttpRequest();
  var url = "https://geniustodo.shop/todo?name="+name;
  console.log(url);
  request.open('DELETE', url, false);
  request.setRequestHeader('Content-type', 'application/json');
  request.setRequestHeader('Authorization', sessionStorage.getItem('Authorization'));
  request.send();

  if (request.status === 200) {
    init_todo();
    load_todo();
  }else alert('삭제 실패');
}

function complete_todo(e){
  var name = e.parentNode.parentNode.getElementsByClassName('todo_info')[0].getElementsByClassName('todo-name')[0].innerText;
  const request = new XMLHttpRequest();
  var url = "https://geniustodo.shop/todo/complete?name="+name;
  console.log(url);
  request.open('POST', url, false);
  request.setRequestHeader('Content-type', 'application/json');
  request.setRequestHeader('Authorization', sessionStorage.getItem('Authorization'));
  request.send();

  if (request.status === 200) {
    init_todo();
    load_todo();
  }else alert('삭제 실패');
}

function edit_todo(e){
  parent = e.parentNode.parentNode.getElementsByClassName('todo_info')[0];
  var name = parent.getElementsByClassName('todo-name')[0];
  var exp = parent.getElementsByClassName('todo-exp')[0];

  console.log(name);
  console.log(exp);

  //change span -> input
  var inputElement = document.createElement("input"); 
  var inputElement1 = document.createElement("input"); 

  inputElement.value = name.innerHTML;
  inputElement.setAttribute("type", "text"); 
  name.parentNode.replaceChild(inputElement, name);

  inputElement1.value = exp.innerHTML;
  inputElement1.setAttribute("type", "text"); 
  exp.parentNode.replaceChild(inputElement1, exp);

  e.classList.remove("fa-pencil");
  e.classList.add("fa-check");
  e.removeAttribute("onclick");
  e.setAttribute("onclick", "edit_complete_todo(this)");
  e.setAttribute("oldname", name);
}

function edit_complete_todo(e){
  parent = e.parentNode.parentNode.getElementsByClassName('todo_info')[0].getElementsByTagName("input");
  var old_name = e.getAttribute("oldname").value;
  var new_name = parent[0].value;
  var new_exp = parent[1].value;
  
  const request = new XMLHttpRequest();
  request.open('PUT', "https://geniustodo.shop/todo", false);
  request.setRequestHeader('Content-type', 'application/json');
  request.setRequestHeader('Authorization', sessionStorage.getItem('Authorization'));
  const requestBody = { oldName: old_name, newName: new_name, expiration: new_exp };
  const requestBodyString = JSON.stringify(requestBody);
  request.send(requestBodyString);

  if (request.status === 200) {
    init_todo();
    load_todo();
  }else{
    alert("변경 실패");
    return
  }
}

function add_todo(e){

}