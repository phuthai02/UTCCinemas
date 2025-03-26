function logout() {
    httpRequest('/api/auth/logout', {method: 'POST'})
        .finally(() => {
            localStorage.removeItem('token');
            const redirectUrl = window.location.href.includes('/utc-cinemas/access-denied')
                ? '/utc-cinemas/home'
                : window.location.href;
            localStorage.setItem('url_redirect', redirectUrl);
            document.cookie = "token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
            window.location.href = '/utc-cinemas/login';
        });
}

function httpRequest(url, options = {}) {
    const token = localStorage.getItem('token');
    if (!token) {
        logout();
        return Promise.reject('Không có token, cần đăng nhập lại.');
    }
    options.headers = {
        ...options.headers, 'Content-Type': 'application/json', 'Authorization': `Bearer ${token}`
    };
    return fetch(url, options)
        .then(response => {
            if (response.status === 401) {
                logout();
                return Promise.reject('Token hết hạn hoặc không hợp lệ.');
            }
            if (!response.ok) {
                return Promise.reject(`Lỗi HTTP ${response.status}`);
            }
            return response.json();
        })
        .catch(error => {
            console.error('Lỗi request:', error);
            return Promise.reject(error);
        });
}

function navigateTo(url) {
    window.location.href = url;
}

function checkToken() {
    httpRequest('/api/auth/check-token', {method: 'POST'})
    .then(data => {
        if (data && data.valid) {
            if (data.name) {
                const welcomeUser = document.getElementById('welcomeUser');
                const userName = document.getElementById('userName');
                if (welcomeUser) welcomeUser.textContent = `Chào mừng, ${data.name}`;
                if (userName) userName.textContent = data.name;
            }
        }
    })
    .catch(error => {
        console.error(error);
    });
}


function showModal(type, title, message, redirectUrl = null) {
    const icons = {
        success: '<svg width="64" height="64" viewBox="0 0 64 64" fill="none" xmlns="http://www.w3.org/2000/svg"><circle cx="32" cy="32" r="28" fill="#4CAF50" fill-opacity="0.2"/><path d="M32 56C45.2548 56 56 45.2548 56 32C56 18.7452 45.2548 8 32 8C18.7452 8 8 18.7452 8 32C8 45.2548 18.7452 56 32 56Z" stroke="#4CAF50" stroke-width="3"/><path d="M22 32L29 39L42 26" stroke="#4CAF50" stroke-width="3" stroke-linecap="round" stroke-linejoin="round"/></svg>',
        error: '<svg width="64" height="64" viewBox="0 0 64 64" fill="none" xmlns="http://www.w3.org/2000/svg"><circle cx="32" cy="32" r="28" fill="#F44336" fill-opacity="0.2"/><path d="M32 56C45.2548 56 56 45.2548 56 32C56 18.7452 45.2548 8 32 8C18.7452 8 8 18.7452 8 32C8 45.2548 18.7452 56 32 56Z" stroke="#F44336" stroke-width="3"/><path d="M32 24V36" stroke="#F44336" stroke-width="3" stroke-linecap="round"/><circle cx="32" cy="42" r="2" fill="#F44336"/></svg>'
    };
    const modalOverlay = document.createElement('div');
    modalOverlay.className = 'modal-overlay';
    const modalContent = document.createElement('div');
    modalContent.className = 'modal-content';
    const iconDiv = document.createElement('div');
    iconDiv.className = 'modal-icon';
    iconDiv.innerHTML = icons[type] || icons.success;
    const titleElement = document.createElement('h2');
    titleElement.className = 'modal-title';
    titleElement.textContent = title;
    const messageElement = document.createElement('p');
    messageElement.className = 'modal-message';
    messageElement.textContent = message;
    const buttonContainer = document.createElement('div');
    buttonContainer.className = 'modal-buttons';
    const confirmButton = document.createElement('button');
    confirmButton.className = `modal-button modal-button-primary ${type}`;
    if (type === 'success' && redirectUrl) {
        confirmButton.textContent = 'Quay lại danh sách';
    } else if (type === 'success' && !redirectUrl) {
        confirmButton.textContent = 'Đồng ý';
    } else if (type === 'error') {
        confirmButton.textContent = 'Đã hiểu';
    }
    confirmButton.addEventListener('click', () => {
        document.body.removeChild(modalOverlay);
        if (redirectUrl && type === 'success') {
            navigateTo(redirectUrl);
        }
    });
    if (type === 'success' && redirectUrl) {
        const cancelButton = document.createElement('button');
        cancelButton.className = 'modal-button modal-button-secondary';
        cancelButton.textContent = 'Tiếp tục thêm';

        cancelButton.addEventListener('click', () => {
            document.body.removeChild(modalOverlay);
        });

        buttonContainer.appendChild(cancelButton);
    }
    const handleEscapeKey = function(e) {
        if (e.key === 'Escape' && document.body.contains(modalOverlay)) {
            document.body.removeChild(modalOverlay);
            document.removeEventListener('keydown', handleEscapeKey);
        }
    };
    document.addEventListener('keydown', handleEscapeKey);
    buttonContainer.insertBefore(confirmButton, buttonContainer.firstChild);
    modalContent.appendChild(iconDiv);
    modalContent.appendChild(titleElement);
    modalContent.appendChild(messageElement);
    modalContent.appendChild(buttonContainer);

    modalOverlay.appendChild(modalContent);
    document.body.appendChild(modalOverlay);
}
