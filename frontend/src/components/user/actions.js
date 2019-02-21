const actions = {
    LOGIN_REQUEST: 'LOGIN_REQUEST',
    LOGIN_SUCCESS: 'LOGIN_SUCCESS',
    LOGIN_FAILURE: 'LOGIN_FAILURE',
    LOGOUT_REQUEST: 'LOGOUT_REQUEST',
    LOGOUT_SUCCESS: 'LOGOUT_SUCCESS',
    LOGOUT_FAILURE: 'LOGOUT_FAILURE'
}

const requestLogin = (credentials) => {
    return {
        type: actions.LOGIN_REQUEST,
        payload: {
            isFetching: true,
            isAuthenticated: false,
            credentials
        }
    };
}

const receiveLogin = (user) => {
    return {
        type: actions.LOGIN_SUCCESS,
        payload: {
            isFetching: false,
            isAuthenticated: true,
            id_token: user.id_token
        }
    };
}

const loginError = (message) => {
    return {
        type: actions.LOGIN_FAILURE,
        payload: {
            isFetching: false,
            isAuthenticated: false,
            message
        }
    };
}

const requestLogout = () => {
    return {
        type: actions.LOGOUT_REQUEST,
        payload: {
            isFetching: true,
            isAuthenticated: false
        }
    };
}

const receiveLogout = () => {
    return {
        type: actions.LOGOUT_SUCCESS,
        payload: {
            isFetching: false,
            isAuthenticated: false
        }
    };
}

const loginUser = (credentials) => {
    let config = {
        method: 'POST',
        headers: {
            'Content-Type':'application/json'
        },
        body: JSON.stringify(credentials)
    }

    return dispatch => {
        dispatch(requestLogin(credentials));

        return fetch('http://localhost:8080/auth/login', config)
            .then(response => {
                response.json().then(user => ( {user, response} ) => {
                    if(!response.success){
                        dispatch(loginError(response.message))
                        return Promise.reject(response.data)
                    }else {
                        localStorage.setItem('id_token', user.id_token);
                        localStorage.setItem('access_token', user.access_token)
                        dispatch(receiveLogin(user))
                    }
                })
            })
            .catch(err => console.log("Error: ", err))
    }
}

const logoutUser = () => {
    return dispatch => {
        dispatch(requestLogout());
        localStorage.removeItem('id_token');
        localStorage.removeItem('access_token');
        dispatch(receiveLogout());
    }
}

export default [loginUser, logoutUser, actions];