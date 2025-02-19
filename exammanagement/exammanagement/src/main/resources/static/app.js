class UserManager {
    constructor() {
        this.token = localStorage.getItem('token');
        this.init();
    }

    init() {
        if (!this.token) {
            window.location.href = 'login.html'; // هدایت به صفحه لاگین اگر توکن وجود نداشته باشد
        } else {
            this.loadUsers();
        }
    }

    async loadUsers() {
        try {
            const response = await fetch('http://localhost:8080/teacher/all', {
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${this.token}`
                }
            });

            if (!response.ok) {
                throw new Error('Network response was not ok');
            }

            const users = await response.json();
            this.renderUsers(users);
        } catch (error) {
            console.error('Error loading users:', error);
            alert('خطا در بارگذاری کاربران. لطفاً دوباره تلاش کنید.');
        }
    }

    renderUsers(users) {
        const userTableBody = document.getElementById('userTableBody');
        userTableBody.innerHTML = ''; // پاک کردن محتوای قبلی

        users.forEach(user => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.nationalCode}</td>
                <td>${user.phoneNumber}</td>
                <td>${user.status}</td>
                <td>
                    <button class="btn btn-success btn-sm" onclick="userManager.confirmUser(${user.id})">تایید</button>
                    <button class="btn btn-warning btn-sm" onclick="userManager.editUser(${user.id})">ویرایش</button>
                </td>
            `;
            userTableBody.appendChild(row);
        });
    }

    confirmUser(userId) {
        // منطق تایید کاربر
        console.log(`User ${userId} confirmed.`);
        alert(`کاربر با شناسه ${userId} تایید شد.`);
    }

    editUser(userId) {
        // منطق ویرایش کاربر
        console.log(`Editing user ${userId}.`);
        alert(`ویرایش کاربر با شناسه ${userId}.`);
    }
}

// ایجاد یک نمونه از کلاس UserManager
const userManager = new UserManager();