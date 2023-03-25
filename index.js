let user = JSON.parse(localStorage.getItem("user")) || [];
document.querySelector("#signinPage").addEventListener("click", function () {
    getUser();
})

function logoutFun(value) {
    if (value == "logout") {
        localStorage.removeItem("user");
        window.location.reload();
    }
}
function getUser() {
    if (user.length == 0) {
        window.location.href = "signup.html";
    }
}

function showUser(user) {
    if (user.length != 0) {
        let handler = document.querySelector("#signinPage");
        handler.innerHTML = "";
        let select = document.createElement("select");
        select.setAttribute("id","select");
        let option1 = document.createElement("option");
        let option2 = document.createElement("option");
        option1.innerHTML = `Hi, ${user.firstName}`
        option2.innerHTML = `Logout`;
        option1.value = "username";
        option2.value = "logout";
        select.addEventListener("change", function () {
            logoutFun(select.value);
        })
        select.append(option1, option2);
        handler.append(select);
    }
}
showUser(user);

function getSignupData() {
    event.preventDefault();
    let form = document.querySelector("#signupForm");
    let fname = form.fname.value;
    let lname = form.lname.value;
    let email = form.email.value;
    let passwd = form.password.value;
    let gender = form.gender.value;

    let signupData = {
        firstName: fname,
        lastName: lname,
        gender: gender,
        email: email,
        password: passwd
    }
    let data = JSON.stringify(signupData);
    fetch("https://task-planner-zvg7.onrender.com:443/api/v1/user/register", {
        method: "POST",
        body: data,
        headers: {
            "content-type": "application/json"
        }
    }).then((res) => {
        return res.json();
    }).then((res) => {
        if (res.message == null) {
            alert("Signup Successfull !");
        } else {
            alert(res.message);
        }
    }).catch((err) => {
        console.log(err);
    })
    form.reset();
}

function verifyUser() {
    event.preventDefault();
    let form = document.querySelector("#loginForm");
    let loginData = {
        email: form.lemail.value,
        password: form.lpassword.value
    }
    let data = JSON.stringify(loginData);
    fetch("https://task-planner-zvg7.onrender.com:443/api/v1/user/validate", {
        method: "POST",
        body: data,
        headers: {
            "content-type": "application/json"
        }
    }).then((res) => {
        return res.json();
    }).then((res) => {
        if (res.message == null) {
            alert("Login Successful !");
            localStorage.setItem("user", JSON.stringify(res));
            window.location.href = "index.html";
        } else {
            alert(res.message);
        }
    }).catch((err) => {
        console.log(err);
    })
    form.reset();
}
if (user.length != 0) {
    getAllTask(user);
}
document.querySelector("#allTask").addEventListener("click", function () {
    getAllTask(user);
})
function getAllTask(user) {
    if (user.length == 0) {
        alert("Please login first !");
    } else {
        let url = `https://task-planner-zvg7.onrender.com:443/api/v1/task/all?userId=${user.userId}`;
        fetch(url).then((res) => {
            return res.json();
        }).then((res) => {
            showTasks(res);
        }).catch((err) => {
            console.log(err);
        })
    }
}
document.querySelector("#pendingTask").addEventListener("click", function () {
    getPendingTask(user);
})
function getPendingTask(user) {
    if (user.length == 0) {
        alert("Please login first !");
    } else {
        let url = `https://task-planner-zvg7.onrender.com:443/api/v1/task/pending?userId=${user.userId}`;
        fetch(url).then((res) => {
            return res.json();
        }).then((res) => {
            showTasks(res);
        }).catch((err) => {
            console.log(err);
        })
    }
}
document.querySelector("#completedTask").addEventListener("click", function () {
    getCompletedTask(user);
})
function getCompletedTask(user) {
    if (user.length == 0) {
        alert("Please login first !");
    } else {
        let url = `https://task-planner-zvg7.onrender.com:443/api/v1/task/completed?userId=${user.userId}`;
        fetch(url).then((res) => {
            return res.json();
        }).then((res) => {
            showTasks(res);
        }).catch((err) => {
            console.log(err);
        })
    }
}
function showTasks(data) {
    let container = document.querySelector("#container");
    container.innerHTML = "";
    data.forEach(element => {
        let div = document.createElement("div");
        let title = document.createElement("h2");
        let desc = document.createElement("p");
        let status = document.createElement("select");
        let date = document.createElement("p");
        let option1 = document.createElement("option");
        let option2 = document.createElement("option");
        let deleteItem=document.createElement("p");
        deleteItem.innerHTML="Delete";
        let cdiv=document.createElement("div");
        cdiv.setAttribute("class","cdiv");
        if (element.taskStatus == "PENDING") {
            option1.innerHTML = "Pending";
            option2.innerHTML = "Completed";
            option1.value = "PENDING";
            option2.value = "COMPLETED"
        } else {
            option1.innerHTML = "Completed";
            option2.innerHTML = "Pending";
            option1.value = "COMPLETED";
            option2.value = "PENDING"
        }
        status.append(option1, option2);
        status.addEventListener("change", function () {
            updateStatus(status.value, element.taskId);
        })
        title.innerHTML = element.title;
        desc.innerHTML = element.description;
        let dateTime = element.dateTime;
        let dateObj = new Date(dateTime);
        date.innerHTML = `Date : ${dateObj.toDateString()}`;
        deleteItem.addEventListener("click",function(){
            deleteTask(element.taskId);
        })
        status.style.backgroundColor="#FFFF33";
        deleteItem.style.cursor="pointer";
        deleteItem.style.color="red";
        cdiv.append(status,deleteItem);
        div.append(title, desc, date, cdiv);

        container.append(div);
    });
}
function deleteTask(taskId){
    fetch(`https://task-planner-zvg7.onrender.com:443/api/v1/task/delete?taskId=${taskId}`,{
        method:"DELETE",
    }).then((res)=>{
        return res.json();
    }).then((res)=>{
        if(res.message==null){
            alert("Task deleted..");
            window.location.reload();
        }else{
            alert(res.message);
        }
    }).catch((err)=>{
        console.log(err);
    })
}
function updateStatus(value, id) {
    let updateValue = {
        taskId: id,
        taskStatus: value
    }
    let data = JSON.stringify(updateValue);
    fetch("https://task-planner-zvg7.onrender.com:443/api/v1/task/update", {
        method: "PUT",
        body: data,
        headers: {
            "content-type": "application/json"
        }
    }).then((res) => {
        return res.json();
    }).then((res) => {
        if (res.message == null) {
            alert("Status Updated !");
            window.location.reload();
        } else {
            alert(res.message);
        }
    }).catch((err) => {
        console.log(err);
    })
}
document.querySelector("#addTask").addEventListener("click", function () {
    createTask(user);
})
function createTask(user) {
    if (user.length == 0) {
        alert("Please login first !");
    } else {
        let title = prompt("Title", "Test");
        let desc = prompt("Description", "This is a demo");
        if (title.length < 3 || desc.length < 3) {
            alert("Please enter valid data..");
        } else {
            let taskData = {
                title: title,
                description: desc,
                userId: user.userId
            }
            let data = JSON.stringify(taskData);
            addTask(data);
        }
    }
}
function addTask(data) {
    let url = `https://task-planner-zvg7.onrender.com:443/api/v1/task/add`;
    fetch(url, {
        method: "POST",
        body: data,
        headers: {
            "content-type": "application/json"
        }
    }).then((res) => {
        return res.json();
    }).then((res) => {
        if (res.message == null) {
            alert("Task added successfully !");
            window.location.reload();
        } else {
            alert(res.message);
        }
    }).catch((err) => {
        console.log(err);
    })
}