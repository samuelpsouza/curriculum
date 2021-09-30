export const userService = {
    login,
    logout
};

function login(username, password){
    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, password })
    };

    return fetch('http://localhost:8080/auth/login', requestOptions)
        .then(response => response.json())
        .then(response => {
            if (response.data) {
                localStorage.setItem('user', JSON.stringify(response.data));
            }

            return response.data;
        });
}

function logout() {
    localStorage.removeItem('user');
}
