from django.db import models

# Create your models here.
class ToDoList(models.Model):
    site_name = models.CharField(max_length=100)
    url = models.URLField('Site URL')