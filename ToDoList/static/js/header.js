const header = document.querySelector('header');

fetch('./templates/ToDoList/header.html')
    .then(res => res.text())
    .then(data => header.innerHTML = data);