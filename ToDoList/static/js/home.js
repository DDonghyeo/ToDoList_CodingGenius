//function toggleTaskDetails(button) {
//  var task = button.parentNode.parentNode;
//  var details = task.querySelector('.task-details');
//  details.classList.toggle('active');
//}
//
//function toggleTaskMemo(button) {
//  var memo = button.nextElementSibling;
//  memo.classList.toggle('active');
//}

document.addEventListener('DOMContentLoaded', function() {
  const taskToggleButtons = document.querySelectorAll('.task-toggle-button');
  const memoToggleButtons = document.querySelectorAll('.memo-toggle-button');

  taskToggleButtons.forEach(function(button) {
    button.addEventListener('click', function() {
      const details = this.parentNode.parentNode.querySelector('.task-details');
      details.classList.toggle('active');
    });
  });

  memoToggleButtons.forEach(function(button) {
    button.addEventListener('click', function() {
      const memo = this.parentNode.parentNode.parentNode.parentNode.querySelector('.task-memo');
      memo.classList.toggle('active');
    });
  });
});




