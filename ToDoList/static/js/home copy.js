function toggleTaskDetails(element) {
  element.classList.toggle('active');
  var details = element.querySelector('.task-details');
  details.classList.toggle('active');
}

function toggleTaskMemo(element) {
  element.nextElementSibling.classList.toggle('active');
}
