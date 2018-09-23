function changeStatusFinished(id)
{
  $.getJSON("/api/tasks/" + id)
    .done(function(task) {
      task.finished = !task.finished
      $.ajax("/api/tasks", {
        data: JSON.stringify(task),
        contentType: 'application/json',
        type: 'PATCH'
      });
    });
}
