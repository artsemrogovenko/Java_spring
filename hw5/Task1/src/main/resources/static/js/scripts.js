
  function togglePopup(button) {
    var popup = document.getElementById('popupForm');

           var taskId = button.getAttribute('data-taskId');
           var description = button.getAttribute('data-descr');
           var status = button.getAttribute('data-status');

         document.getElementById('status_task').value = status;
         document.getElementById('task_Id').value = taskId;
         document.getElementById('task_desc').value = description;

//         document.getElementById('task_name').value = name;


    if (popup.style.display === "none") {
        popup.style.display = "block";
    } else {
        popup.style.display = "none";
    }
}


