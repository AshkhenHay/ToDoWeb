$(document).ready(function () {
    let registerBtn = document.querySelector("#register-btn");
    registerBtn.addEventListener("click", function openHideBox() {
        let registerBox = document.querySelector("#register");
        if (registerBox.style.display === '' || registerBox.style.display === 'none') {
            registerBox.style.display = 'block';
        } else {
            registerBox.style.display = 'none';
        }

    });
});
let addUser = document.querySelector("#addUser-btn");
addUser.addEventListener("click", function openUserBox() {
    let addUserBox = document.querySelector("#addUser");
    if (addUserBox.style.display === '' || addUserBox.style.display === 'none') {
        addUserBox.style.display = 'block';
    } else {
        addUserBox.style.display = 'none';
    }
});

let addTask = document.querySelector("#addTask-btn");
addTask.addEventListener("click",function openTaskBox() {
    let addTaskBox = document.querySelector("#addTask");
    if (addTaskBox.style.display === '' || addTaskBox.style.display === 'none') {
        addTaskBox.style.display = 'block';
    } else {
        addTaskBox.style.display = 'none';
    }

});
let comment = document.querySelector("#comment-btn");
comment.addEventListener("click",function openCommentBox() {
    let commentBox = document.querySelector("#comment");
    if (commentBox.style.display === '' || commentBox.style.display === 'none') {
        commentBox.style.display = 'block';
    } else {
        commentBox.style.display = 'none';
    }

});

$("#addTaskForm").submit(function (e) {
    e.preventDefault();
    let title = $("#title").val();
    let description = $("#description").val();
    let deadline = $("#deadline").val();
    let status = $("#status").val();
    let userId = $("#userId").val();
    $.ajax({
        url: "/addTask?title=" + title + "&description=" + description + "&deadline=" +
            deadline + "&status=" + status + "&userId=" + userId,
        method: "Post",
        success: function (result) {
            $("#info").html(result);
            $("#title").val("");
            $("#description").val("");
            $("#deadline").val("");
            $("#status").val("");
            $("#userId").val("");
        },
        error: function () {
            $("#info").html("There is problem with task data!");
        }
    });

})

let getTaskList = function () {
    $.ajax({
        url: "/taskList",
        method: "GET",
        success: function (result) {
            $("#allTasks").html(result);

        }
    });
};
getTaskList();
setInterval(getTaskList, 4000);
