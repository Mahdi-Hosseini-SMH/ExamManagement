const loginSection = document.getElementById('login-section');
const pendingUsersSection = document.getElementById('pending-users-section');
const approveUserSection = document.getElementById('approve-user-section');
const updateUserSection = document.getElementById('update-user-section');
const loginMessage = document.getElementById('login-message');
const pendingUsersList = document.getElementById('pending-users-list');
const approveMessage = document.getElementById('approve-message');
const updateMessage = document.getElementById('update-message');

async function loginAdmin() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    const response = await fetch('http://localhost:8080/admin/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `username=${encodeURIComponent(username)}&password=${encodeURIComponent(password)}`
    });

    if (response.ok) {
        loginMessage.textContent = 'لاگین موفقیت‌آمیز بود';
        loginSection.style.display = 'none';
        pendingUsersSection.style.display = 'block';
        approveUserSection.style.display = 'block';
        updateUserSection.style.display = 'block';
        loadPendingUsers();
    } else {
        loginMessage.textContent = 'نام کاربری یا رمز عبور اشتباه است.';
    }
}

async function loadPendingUsers() {
    const response = await fetch('http://localhost:8080/admin/pending-users');
    const users = await response.json();
    pendingUsersList.innerHTML = '';
    users.forEach(user => {
        const li = document.createElement('li');
        li.textContent = `ID: ${user.id}, Name: ${user.name}`;
        pendingUsersList.appendChild(li);
    });
}

async function approveUser() {
    const userId = document.getElementById('user-id').value;
    const response = await fetch(`http://localhost:8080/admin/approve-user/${userId}`, {
        method: 'POST'
    });

    if (response.ok) {
        approveMessage.textContent = 'کاربر با موفقیت تایید شد.';
        loadPendingUsers();
    } else {
        approveMessage.textContent = 'کاربر پیدا نشد.';
    }
}

async function updateUser() {
    const userId = document.getElementById('update-user-id').value;
    const newName = document.getElementById('update-user-name').value;

    const userDTO = {
        name: newName
    };

    const response = await fetch(`http://localhost:8080/admin/update-user/${userId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(userDTO)
    });

    if (response.ok) {
        updateMessage.textContent = 'کاربر با موفقیت ویرایش شد.';
        loadPendingUsers();
    } else {
        updateMessage.textContent = 'کاربر پیدا نشد.';
    }
}