function toggleTaskDetails(button) {
  var task = button.parentNode.parentNode;
  var details = task.querySelector('.task-details');
  details.classList.toggle('active');
}

function toggleTaskMemo(button) {
  var memo = button.nextElementSibling;
  memo.classList.toggle('active');
}
