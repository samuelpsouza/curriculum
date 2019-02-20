import actions from './actions';

const initState = {
  user: {
    isAuthenticated: false
  }
}

export default function (state = initState, action) {
  switch (action.type) {
    case actions.LOGIN_SUCCESS:
      return { ...state, 
        isAuthenticated: true,
        user: action.payload
      };
    case actions.LOGIN_FAILURE:
      return { ...state, 
        isAuthenticated: false
      };
    case actions.LOGOUT:
      return { ...state, 
        isAuthenticated: false,
        user: action.payload
      };
    default:
      return state
  }
}
