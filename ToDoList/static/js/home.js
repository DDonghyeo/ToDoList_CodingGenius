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
  }

}
name_load();




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
      var obj = data[i];
      var is_complete = obj.complete;
      var complete = true;
      if (is_complete) {
        complete = true;
      } else {
        complete = false;
      }


      target.innerHTML += `
      <div class="task">
        <div class="task-header">
          <span class="task-name">${obj.name}</span>
          <span class="task-exp">${obj.expiration}</span>
          <button class="edit-button"></button>
          <button class="complete-button"><span class="fa-regular fa-circle-check" style="color: #000000;"></span></button>
          <button class="delete-button"></button>
          <button class="task-toggle-button"></button>
        </div>
      `;

      var worklist = obj.workArrayList;
      console.log(worklist);
      if (worklist != null) {
        for (var j = 0; j < worklist.length; j++) {
          target.innerHTML += `
          <div class="task-details">
            <div class="task-details-header">
              <span>${worklist.name}</span>
              <button class="memo-button"></button>
              <button class="complete-button"><span class="fa-regular fa-circle-check"
                  style="color: #000000;"></span></button>
              <button class="delete-button"></button>
              <button class="memo-toggle-button"></button>
            </div>

            <div class="task-memo">
              <div class="task-memo-header">
                <span>${worklist.memo}</span >
              </div >
            </div >
          </div >
            `;
        }
      }

    }

  } else {
    alert("불러오기 실패");
    window.location.replace("/");
  }
}

load_todo();




document.addEventListener('DOMContentLoaded', function () {
  const taskToggleButtons = document.querySelectorAll('.task-toggle-button');
  const memoToggleButtons = document.querySelectorAll('.memo-toggle-button');

  taskToggleButtons.forEach(function (button) {
    button.addEventListener('click', function () {
      const details = this.parentNode.parentNode.querySelector('.task-details');
      details.classList.toggle('active');
    });
  });

  memoToggleButtons.forEach(function (button) {
    button.addEventListener('click', function () {
      const memo = this.parentNode.parentNode.parentNode.parentNode.querySelector('.task-memo');
      memo.classList.toggle('active');
    });
  });
});
