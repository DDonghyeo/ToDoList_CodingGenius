from django.shortcuts import render


# Create your views here.

def index(request):
    return render(request, "ToDoList/index.html")


def home(request):
    return render(request, "ToDoList/home.html")


def account(request):
    return render(request, "ToDoList/account.html")


def header(request):
    return render(request, "ToDoList/header.html")


def withdrawal(request):
    return render(request, "ToDoList/withdrawal.html")
